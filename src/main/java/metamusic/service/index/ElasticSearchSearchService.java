package metamusic.service.index;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import org.jboss.logging.Logger;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;

public class ElasticSearchSearchService implements SearchService {
    private static final Logger logger = Logger.getLogger(ElasticSearchSearchService.class);

    private final ElasticsearchClient esClient;

    public ElasticSearchSearchService(ElasticsearchClient elasticsearchClient) {
        this.esClient = elasticsearchClient;
    }

    @Override
    public void index(String indexName, FileDescriptor descriptor) {
        logger.info(String.format("Indexing %s", descriptor.toString()));
        try {
            IndexResponse response = esClient.index(i -> i
                    .index(indexName)
                    .id(descriptor.uuid().toString())
                    .document(descriptor)
            );
            logger.info(MessageFormat.format("Indexed with version {0}", response.version()));
        } catch (IOException e) {
            logger.error( "Error indexing document", e);
        }
    }


    public void close() {
        esClient.shutdown();
    }

    @Override
    public List<FileDescriptor> search(String indexName, String searchTerm2) {
        final String searchTerm = "vinicius";
        try {
            SearchResponse<FileDescriptor> response = esClient.search(s -> s
                            .index(indexName)
                            .query(q -> q.queryString(qs -> qs.query(searchTerm))
                            )
                    , FileDescriptor.class
            );
            return  response.hits().hits().stream().map(Hit::source).toList();
        } catch (IOException e) {
            logger.error( "Error searching", e);
//            TODO: custom exception
            return Collections.emptyList();
        }
    }
}

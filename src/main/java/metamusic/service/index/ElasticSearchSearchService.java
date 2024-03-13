package metamusic.service.index;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import io.quarkus.runtime.Shutdown;
import jakarta.inject.Inject;
import metamusic.config.Config;
import org.jboss.logging.Logger;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

import static java.util.logging.Level.INFO;
import static java.util.logging.Level.SEVERE;

public class ElasticSearchSearchService implements SearchService {
    private static final Logger logger = Logger.getLogger(ElasticSearchSearchService.class);

    private final ElasticsearchClient esClient;

    public ElasticSearchSearchService(ElasticsearchClient client) {
        this.esClient = client;
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


    @Shutdown
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
            return  response.hits().hits().stream().map(h -> h.source()).toList();
        } catch (IOException e) {
            logger.error( "Error searching", e);
            return null;
        }
    }
}

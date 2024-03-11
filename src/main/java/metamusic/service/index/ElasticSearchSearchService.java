package metamusic.service.index;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.TransportUtils;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.ws.rs.Produces;
import metamusic.config.Config;
import metamusic.model.SearchResult;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.text.MessageFormat;
import java.util.logging.Logger;

import static java.util.logging.Level.INFO;
import static java.util.logging.Level.SEVERE;
public class ElasticSearchSearchService implements SearchService {

    private ElasticsearchClient esClient;
    private RestClient restClient;

    @Inject
    private Config config;
    private static final Logger logger = Logger.getLogger(ElasticSearchSearchService.class.getName());

//    public ElasticSearchSearchService buildWithApiKey() throws IOException {
//        URL elasticUrl = URI.create(config.elasticServerUrl).toURL();
//        SSLContext sslContext = TransportUtils.sslContextFromCaFingerprint(config.elasticFingerPrint);
//        RestClient restClient = RestClient
//                .builder(new HttpHost(elasticUrl.getHost(), elasticUrl.getPort(), elasticUrl.getProtocol()))
//                .setDefaultHeaders(new Header[]{
//                        new BasicHeader("Authorization", "ApiKey " + config.elasticApiKey)
//                })
//                .setHttpClientConfigCallback(hc -> hc.setSSLContext(sslContext))
//                .build();
//        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
//        ElasticsearchClient client = new ElasticsearchClient(transport);
//        logger.log(INFO, String.format("Connection with client established: %s ", client.info()));
//        ElasticSearchSearchService service = new ElasticSearchSearchService();
//        service.setRestClient(restClient);
//        service.setEsClient(client);
//        return service;
//    }

    @Override
    public void index(String indexName, AudioFileDescriptor descriptor) {
        IndexResponse response = null;
        logger.info(String.format("Indexing %s", descriptor.toString()));
        try {
            response = esClient.index(i -> i
                    .index(indexName)
                    .id(descriptor.uuid().toString())
                    .document(descriptor)
            );
            logger.log(INFO, MessageFormat.format("Indexed with version {0}", response.version()));
        } catch (IOException e) {
            logger.log(SEVERE, "Error indexing document", e);
        }
        logger.info("Indexed with version " + response.version());
    }

    //    @Override
//    public Object search()
    @Override
    public void close() throws IOException {
        this.restClient.close();
    }

    @Override
    public SearchResult search(String indexName, String searchTerm) {
        return null;
    }


    public void setRestClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public void setEsClient(ElasticsearchClient esClient) {
        this.esClient = esClient;
    }
}

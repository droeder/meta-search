package metamusic.service.index;


import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.TransportUtils;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.ws.rs.Produces;
import metamusic.config.Config;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;
import org.jboss.logging.Logger;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

@Singleton
public class ElasticSearchFactory {


    private static final Logger logger = Logger.getLogger(ElasticSearchFactory.class);

    private final Config config;

    @Inject
    public ElasticSearchFactory(Config config) {
        this.config = config;
    }

    @Produces
    @Singleton
    public SearchService buildWithApiKey() throws IOException {
        URL elasticUrl = URI.create(config.searchServerUrl).toURL();
        SSLContext sslContext = TransportUtils.sslContextFromCaFingerprint(config.elasticFingerPrint);
        RestClient restClient = RestClient
                .builder(new HttpHost(elasticUrl.getHost(), elasticUrl.getPort(), elasticUrl.getProtocol()))
                .setDefaultHeaders(new Header[]{
                        new BasicHeader("Authorization", "ApiKey " + config.elasticApiKey)
                })
                .setHttpClientConfigCallback(hc -> hc.setSSLContext(sslContext))
                .build();
        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
        ElasticsearchClient client = new ElasticsearchClient(transport);
        logger.info(String.format("Connection with client established: %s ", client.info()));
        return new ElasticSearchSearchService(client);
    }

}

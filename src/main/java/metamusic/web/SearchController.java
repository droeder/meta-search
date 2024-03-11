package metamusic.web;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import metamusic.config.Config;
import metamusic.model.SearchResult;
import metamusic.service.extract.MetadataService;
import metamusic.service.index.AudioFileDescriptor;
import metamusic.service.index.SearchService;

import java.util.List;

@Path("/index")
public class SearchController {
    @Inject
    private SearchService searchService;
    @Inject
    private MetadataService extractorService;

    @Inject
    private Config config;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public SearchResult search(@QueryParam("search") String searchTerm) {
        return searchService.search(config.elasticIndexName, searchTerm);
    }
}

package metamusic.web;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import metamusic.config.Config;
import metamusic.service.index.FileDescriptor;
import metamusic.service.index.SearchService;

import java.util.List;


@Path("/search")
public class SearchController {
    private final SearchService searchService;

    private final Config config;

    @Inject
    public SearchController(SearchService searchService, Config config) {
        this.searchService = searchService;
        this.config = config;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<FileDescriptor> search(@QueryParam("term") String searchTerm) {
        return searchService.search(config.indexName, searchTerm);
    }
}
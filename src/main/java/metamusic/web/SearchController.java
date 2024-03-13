package metamusic.web;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import metamusic.config.Config;
import metamusic.service.extract.MetadataService;
import metamusic.service.index.FileDescriptor;
import metamusic.service.index.SearchService;

import java.util.List;

@Path("/search")
public class SearchController {
    @Inject
    private SearchService searchService;
    @Inject
    private MetadataService extractorService;

    @Inject
    private Config config;


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String index() {
        List<FileDescriptor> fileDescriptors = extractorService.extractMetadata(config.getSourcePaths());
        fileDescriptors.forEach(descriptor -> searchService.index(config.indexName, descriptor));
        return "success";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<FileDescriptor> search(@QueryParam("term") String searchTerm) {
        return searchService.search(config.indexName, searchTerm);
    }
}
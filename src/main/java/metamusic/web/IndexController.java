package metamusic.web;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import metamusic.config.Config;
import metamusic.service.extract.MetadataService;
import metamusic.service.index.FileDescriptor;
import metamusic.service.index.SearchService;

import java.util.List;


@Path("/index")
@SuppressWarnings("unused")
public class IndexController {
    private final SearchService searchService;
    private final MetadataService extractorService;
    private final Config config;

    @Inject
    @SuppressWarnings("unused")
    public IndexController(SearchService searchService, MetadataService extractorService, Config config) {
        this.searchService = searchService;
        this.extractorService = extractorService;
        this.config = config;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @SuppressWarnings("unused")
    public String index() {
        List<FileDescriptor> fileDescriptors = extractorService.extractMetadata(config.getSourcePaths());
        fileDescriptors.forEach(descriptor -> searchService.index(config.indexName, descriptor));
        return "ok";
    }
}
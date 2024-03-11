package metamusic.web;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import metamusic.config.Config;
import metamusic.service.extract.MetadataService;
import metamusic.service.index.AudioFileDescriptor;
import metamusic.service.index.SearchService;

import java.util.List;

@Path("/index")
public class IndexController {
    @Inject
    private SearchService searchService;
    @Inject
    private MetadataService extractorService;

    @Inject
    private Config config;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String index() {
        List<AudioFileDescriptor> fileDescriptors = extractorService.extractMetadata(config.getSourcePaths());
        fileDescriptors.forEach(descriptor -> searchService.index(config.elasticIndexName, descriptor));
        return "success";
    }
}

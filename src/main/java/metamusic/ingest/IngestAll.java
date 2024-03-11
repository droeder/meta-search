package metamusic.ingest;

import metamusic.service.extract.MetadataService;
import metamusic.service.index.AudioFileDescriptor;
import metamusic.service.index.SearchService;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class IngestAll {

    private final SearchService indexService;
    private final MetadataService extractorService;
    private final List<Path> source = new ArrayList<>();


    public IngestAll(List<Path> sourcePaths, SearchService indexService, MetadataService extractorService) {
        this.indexService = indexService;
        this.extractorService = extractorService;
        this.source.addAll(sourcePaths);
    }

    public void execute() {
//        read all
        List<AudioFileDescriptor> audioFileDescriptors = extractorService.extractMetadata(this.source);
//        ingest
        indexService.index("INDEX_NAME", audioFileDescriptors.getFirst());
    }
}

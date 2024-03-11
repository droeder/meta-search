package metamusic;

import metamusic.service.extract.AudioExtractorService;
import metamusic.service.index.AudioFileDescriptor;
import metamusic.service.index.ElasticSearchSearchService;
import metamusic.service.index.SearchService;

import java.nio.file.Path;
import java.util.List;

public class Main {
    private static final String SERVER_URL = "https://localhost:9200";
    private static final String API_KEY = "akRXdV9vMEI0ak05VzJJSktfdEg6WGh1UGRuUU9SN2lLSUxtMlhjNzNFUQ==";
    private static final String FINGER_PRINT = "1bdca58f0e6a47b44b1a175d0d511ea0cd2dc070cfcba9ea0910b9e9a395a2c0";
    private static final String INDEX_NAME = "search-metamusic-tracks";

    public static void main(String[] args) throws Exception {
        Path source = Path.of("/home/droeder/dev/metamusic/src/test/resources/");
//        List<AudioFileDescriptor> fileDescriptors = new AudioExtractorService().extractMetadata(List.of(source));
//        SearchService indexService = ElasticSearchSearchService.buildWithApiKey(SERVER_URL, API_KEY, FINGER_PRINT);
//        for (AudioFileDescriptor fileDescriptor : fileDescriptors) {
//            indexService.index(INDEX_NAME, fileDescriptor);
//        }
    }

}

package metamusic.config;

import jakarta.inject.Singleton;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
@Singleton
public class Config {

    @ConfigProperty(name="ELASTIC_SERVER_URL")
    public String searchServerUrl;
    @ConfigProperty(name="ELASTIC_API_KEY")
    public String elasticApiKey;
    @ConfigProperty(name="ELASTIC_FINGER_PRINT")
    public String elasticFingerPrint;
    @ConfigProperty(name="ELASTIC_INDEX_NAME")
    public String indexName;

    @ConfigProperty(name="SOURCE_PATHS")
    private String sourcePathsString;

    private List<Path> paths = null;

    public List<Path> getSourcePaths(){
        if (paths == null) {
            paths = new ArrayList<>();
            for (String sourcePath : sourcePathsString.split(",")) {
                var file = new File(sourcePath);
                if (!file.exists()) {
                    throw new IllegalArgumentException("Source path does not exist: " + sourcePath);
                }
                paths.add(file.toPath());
            }
        }
        return paths;
    }
}

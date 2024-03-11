package metamusic.service.extract;

import metamusic.service.index.AudioFileDescriptor;

import java.nio.file.Path;
import java.util.List;

public interface MetadataService {

    List<AudioFileDescriptor> extractMetadata(List<Path> sources);
}

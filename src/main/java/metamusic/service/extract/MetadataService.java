package metamusic.service.extract;

import metamusic.service.index.FileDescriptor;

import java.nio.file.Path;
import java.util.List;

public interface MetadataService {

    List<FileDescriptor> extractMetadata(List<Path> sources);
}

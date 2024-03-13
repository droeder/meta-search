package metamusic.service.index;

import java.io.IOException;
import java.util.List;

public interface SearchService {

    void index(String indexName, FileDescriptor descriptor);
    List<FileDescriptor> search(String indexName, String searchTerm);
}

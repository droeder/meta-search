package metamusic.service.index;

import metamusic.model.SearchResult;

import java.io.IOException;

public interface SearchService {

    void index(String indexName, AudioFileDescriptor descriptor);

    void close() throws IOException;

    SearchResult search(String indexName, String searchTerm);
}

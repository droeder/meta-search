package metamusic.service.index;

import java.util.Map;

public record FileDescriptor(String id, String path, Map<String, String> medadata) {

}


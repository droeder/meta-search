package metamusic.service.index;

import java.util.Map;
import java.util.UUID;

public record AudioFileDescriptor(String path, Map<String, String> medadata, UUID uuid) {
    public AudioFileDescriptor(String path, Map<String, String> medadata) {
        this(path, medadata, UUID.randomUUID());
    }
}


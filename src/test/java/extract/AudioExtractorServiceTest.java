package extract;

import metamusic.service.extract.AudioExtractorService;
import metamusic.service.index.AudioFileDescriptor;
import org.jaudiotagger.tag.FieldKey;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class AudioExtractorServiceTest {
    @Test
    public void testExtractMetadataContainsPath() {
        List<Path> sources = List.of(Path.of("src/test/resources"));
        List<AudioFileDescriptor> actual = new AudioExtractorService().extractMetadata(sources);

        assertThat(actual).isNotNull();
        AudioFileDescriptor first = actual.getFirst();
        assertThat(first).isNotNull();
        assertThat(first.path()).contains("src/test/resources");
        Map<String, String> metadata = first.medadata();
        assertThat(metadata).isNotNull();
        assertThat(metadata.get(FieldKey.ARTIST.toString())).isEqualTo("Vinicius Honorio");
        assertThat(metadata.get(FieldKey.BPM.toString())).isEqualTo("126");
        assertThat(metadata.get(FieldKey.TITLE.toString())).isEqualTo("5A - Walking Shadow (Original Mix)");
    }
}
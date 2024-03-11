package ingest;

import metamusic.service.extract.AudioExtractorService;
import metamusic.service.extract.MetadataService;
import metamusic.service.index.AudioFileDescriptor;
import metamusic.service.index.SearchService;
import metamusic.ingest.IngestAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.ArrayList;

import static org.mockito.Mockito.*;


class IngestAllTest {

    private ArrayList<Path> sourcePaths;

    @BeforeEach
    public void setUp() {
        this.sourcePaths = new ArrayList<>() {{
            add(Path.of(("src/test/resources")));
        }};
    }

    @Test
    void testIngestion() {
        MetadataService extractorMock = mock(AudioExtractorService.class);
        AudioFileDescriptor descriptorMock = mock(AudioFileDescriptor.class);
        when(extractorMock.extractMetadata(this.sourcePaths)).thenReturn(new ArrayList<>() {{
            add(descriptorMock);
        }});
        SearchService indexerMock = mock(SearchService.class);
        var subject = new IngestAll(this.sourcePaths, indexerMock, extractorMock);
        subject.execute();
        verify(extractorMock, times(1)).extractMetadata(this.sourcePaths);
        verify(indexerMock, times(1)).index("INDEX_NAME", descriptorMock);
    }
}
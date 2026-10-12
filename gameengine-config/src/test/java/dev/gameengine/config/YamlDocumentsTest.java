package dev.gameengine.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import dev.gameengine.testkit.TestResources;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import org.junit.jupiter.api.Test;

class YamlDocumentsTest {

    @Test
    void loadsTopLevelMapping() {
        String yaml = TestResources.readResource(getClass(), "/fixtures/valid.yml");

        Map<String, Object> mapping =
                YamlDocuments.loadMapping(new ByteArrayInputStream(yaml.getBytes(StandardCharsets.UTF_8)), "valid.yml");

        assertEquals("skywars", mapping.get("game"));
        assertEquals(12, mapping.get("max-players"));
    }

    @Test
    void rejectsEmptyDocument() {
        ByteArrayInputStream input = new ByteArrayInputStream(new byte[0]);
        assertThrows(GameEngineConfigException.class, () -> YamlDocuments.loadMapping(input, "empty.yml"));
    }

    @Test
    void rejectsNonMappingDocument() {
        ByteArrayInputStream input =
                new ByteArrayInputStream("- just\n- a\n- list\n".getBytes(StandardCharsets.UTF_8));
        assertThrows(GameEngineConfigException.class, () -> YamlDocuments.loadMapping(input, "list.yml"));
    }

    @Test
    void rejectsMalformedYaml() {
        ByteArrayInputStream input =
                new ByteArrayInputStream("game: [unclosed\n".getBytes(StandardCharsets.UTF_8));
        assertThrows(GameEngineConfigException.class, () -> YamlDocuments.loadMapping(input, "broken.yml"));
    }
}

package dev.gameengine.config;

import java.io.InputStream;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import org.yaml.snakeyaml.Yaml;

/**
 * Loads a single YAML mapping document.
 *
 * <p>Only parsing and diagnostics conventions are established here. Game and arena
 * schemas are not defined yet.
 */
public final class YamlDocuments {

    private YamlDocuments() {
    }

    /**
     * Parses {@code input} as a YAML mapping.
     *
     * @return an unmodifiable view of the top-level mapping
     * @throws GameEngineConfigException when the document is missing, empty, or not a mapping
     */
    public static Map<String, Object> loadMapping(InputStream input, String sourceName) {
        Objects.requireNonNull(input, "input");
        Objects.requireNonNull(sourceName, "sourceName");
        Object parsed;
        try {
            parsed = new Yaml().load(input);
        } catch (Exception e) {
            throw new GameEngineConfigException("Invalid YAML in " + sourceName + ": " + e.getMessage(), e);
        }
        if (parsed == null) {
            throw new GameEngineConfigException("Empty YAML document in " + sourceName);
        }
        if (!(parsed instanceof Map)) {
            throw new GameEngineConfigException(
                    "Expected a top-level mapping in " + sourceName + " but found "
                            + parsed.getClass().getSimpleName());
        }
        @SuppressWarnings("unchecked")
        Map<String, Object> mapping = (Map<String, Object>) parsed;
        return Collections.unmodifiableMap(mapping);
    }
}

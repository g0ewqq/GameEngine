package dev.gameengine.testkit;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * Helpers for loading test fixture resources from the classpath.
 */
public final class TestResources {

    private TestResources() {
    }

    public static String readResource(Class<?> anchor, String name) {
        Objects.requireNonNull(anchor, "anchor");
        Objects.requireNonNull(name, "name");
        try (InputStream input = anchor.getResourceAsStream(name)) {
            if (input == null) {
                throw new IllegalArgumentException(
                        "Test resource not found: " + name + " relative to " + anchor.getName());
            }
            return new String(input.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to read test resource " + name, e);
        }
    }
}

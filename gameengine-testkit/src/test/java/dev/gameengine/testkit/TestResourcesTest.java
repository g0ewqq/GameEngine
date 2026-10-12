package dev.gameengine.testkit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class TestResourcesTest {

    @Test
    void readsClasspathResource() {
        assertEquals("hello testkit", TestResources.readResource(getClass(), "/sample.txt").strip());
    }

    @Test
    void rejectsMissingResource() {
        assertThrows(IllegalArgumentException.class, () -> TestResources.readResource(getClass(), "/missing.txt"));
    }
}

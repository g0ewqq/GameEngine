package dev.gameengine.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class MatchIdentityTest {

    @Test
    void createAssignsUniqueIds() {
        MatchIdentity first = MatchIdentity.create("skywars", "arena-1");
        MatchIdentity second = MatchIdentity.create("skywars", "arena-1");

        assertEquals("skywars", first.gameId());
        assertEquals("arena-1", first.arenaId());
        assertNotEquals(first.matchId(), second.matchId());
    }

    @Test
    void rejectsBlankIds() {
        assertThrows(IllegalArgumentException.class, () -> MatchIdentity.create("  ", "arena-1"));
        assertThrows(IllegalArgumentException.class, () -> MatchIdentity.create("skywars", ""));
        assertThrows(NullPointerException.class, () -> MatchIdentity.create(null, "arena-1"));
    }
}

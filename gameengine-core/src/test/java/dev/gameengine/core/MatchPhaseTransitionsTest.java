package dev.gameengine.core;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dev.gameengine.api.MatchPhase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MatchPhaseTransitionsTest {

    @ParameterizedTest
    @CsvSource({
        "WAITING, STARTING",
        "STARTING, IN_GAME",
        "STARTING, WAITING",
        "IN_GAME, ENDING",
        "ENDING, RESETTING",
        "RESETTING, WAITING"
    })
    void allowsDocumentedTransitions(MatchPhase from, MatchPhase to) {
        assertTrue(MatchPhaseTransitions.isAllowed(from, to));
    }

    @Test
    void rejectsSkippedAndBackwardTransitions() {
        assertFalse(MatchPhaseTransitions.isAllowed(MatchPhase.WAITING, MatchPhase.IN_GAME));
        assertFalse(MatchPhaseTransitions.isAllowed(MatchPhase.WAITING, MatchPhase.ENDING));
        assertFalse(MatchPhaseTransitions.isAllowed(MatchPhase.IN_GAME, MatchPhase.WAITING));
        assertFalse(MatchPhaseTransitions.isAllowed(MatchPhase.WAITING, MatchPhase.WAITING));
        assertFalse(MatchPhaseTransitions.isAllowed(MatchPhase.ENDING, MatchPhase.WAITING));
    }
}

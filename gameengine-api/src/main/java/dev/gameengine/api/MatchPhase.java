package dev.gameengine.api;

/**
 * Intended lifecycle states of a match.
 *
 * <p>The full transition rules and failure-handling invariants are documented in
 * {@code docs/lifecycle.md}. This enum only fixes the vocabulary shared across the
 * engine; it does not implement any state machine.
 */
public enum MatchPhase {
    WAITING,
    STARTING,
    IN_GAME,
    ENDING,
    RESETTING
}

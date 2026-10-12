package dev.gameengine.core;

import dev.gameengine.api.MatchPhase;
import java.util.Objects;

/**
 * Documents which direct {@link MatchPhase} transitions the engine will allow.
 *
 * <p>Cancellation and failure recovery are modelled as transitions into {@code ENDING}
 * with a recorded cause, not as separate states; see {@code docs/lifecycle.md}.
 */
public final class MatchPhaseTransitions {

    private MatchPhaseTransitions() {
    }

    public static boolean isAllowed(MatchPhase from, MatchPhase to) {
        Objects.requireNonNull(from, "from");
        Objects.requireNonNull(to, "to");
        return switch (from) {
            case WAITING -> to == MatchPhase.STARTING;
            case STARTING -> to == MatchPhase.IN_GAME || to == MatchPhase.WAITING;
            case IN_GAME -> to == MatchPhase.ENDING;
            case ENDING -> to == MatchPhase.RESETTING;
            case RESETTING -> to == MatchPhase.WAITING;
        };
    }
}

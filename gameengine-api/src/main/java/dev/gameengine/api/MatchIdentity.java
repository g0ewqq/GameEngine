package dev.gameengine.api;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

/**
 * Immutable identity of a single match instance.
 *
 * @param matchId unique id of this match run, never reused
 * @param gameId id of the game definition this match was created from
 * @param arenaId id of the arena hosting this match
 * @param createdAt when the match instance was created
 */
public record MatchIdentity(UUID matchId, String gameId, String arenaId, Instant createdAt) {

    public MatchIdentity {
        Objects.requireNonNull(matchId, "matchId");
        Objects.requireNonNull(gameId, "gameId");
        Objects.requireNonNull(arenaId, "arenaId");
        Objects.requireNonNull(createdAt, "createdAt");
        if (gameId.isBlank()) {
            throw new IllegalArgumentException("gameId must not be blank");
        }
        if (arenaId.isBlank()) {
            throw new IllegalArgumentException("arenaId must not be blank");
        }
    }

    /**
     * Creates an identity with a random match id and the current timestamp.
     */
    public static MatchIdentity create(String gameId, String arenaId) {
        return new MatchIdentity(UUID.randomUUID(), gameId, arenaId, Instant.now());
    }
}

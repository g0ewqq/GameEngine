# Match lifecycle design

This document records the intended design. None of the behaviour below is implemented yet;
each guarantee must be implemented and tested before it is claimed.

## States

```
WAITING -> STARTING -> IN_GAME -> ENDING -> RESETTING -> WAITING
```

`STARTING` may also return to `WAITING` when a start is cancelled before the game
begins. Cancellation and failure after the game has begun are recorded as transitions
into `ENDING` with a cause, not as separate states. The allowed direct transitions are
encoded in `MatchPhaseTransitions` and covered by unit tests.

## Invariants

1. A match cannot start unless its requirements are satisfied (player count, arena
   readiness, game rules).
2. Each match has a unique identity (`MatchIdentity`) and isolated state; arena state is
   never shared between concurrent matches.
3. A player cannot be an active participant in two matches simultaneously.
4. Every started match eventually completes cleanup, even when the game ends
   exceptionally.
5. Ending a match is idempotent: duplicate end requests cannot duplicate rewards,
   teleports, or cleanup.
6. Match-owned tasks, listeners, temporary entities, and temporary resources are
   cancelled or released when the match ends.
7. Players are returned to a valid destination with saved state (inventory, health,
   game mode) restored according to an explicit per-game policy.
8. Arena reset completes before the arena becomes available again.
9. A failed arena reset leaves the arena unavailable and reports the failure; it must
   never silently reopen the arena.
10. Disconnects, server shutdowns, exceptions, and partial startup have defined handling
    (rejoin/replace, forced safe end, or failed-reset quarantine).
11. All Bukkit/Paper operations respect the server threading model: world and entity
    access on the main thread, long I/O off-thread.
12. Cleanup steps are individually fault-tolerant: one failed restoration is recorded
    and the remaining steps still run.

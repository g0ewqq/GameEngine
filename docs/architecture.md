# Architecture

## Module boundaries

```
gameengine-api
  ^        ^
  |        |
gameengine-core   gameengine-config
  ^              ^
   \            /
    gameengine-paper

gameengine-api <-- gameengine-integrations-geyser
gameengine-api, gameengine-core <-- gameengine-testkit (tests only)
```

- `gameengine-api` is pure Java. It must never depend on Paper, Geyser, or any engine
  module. It fixes the shared vocabulary (`MatchPhase`, `MatchIdentity`) used across the engine.
- `gameengine-core` is platform-independent. It depends on the API only and contains no
  Bukkit imports, no Geyser imports, and no server-threading assumptions beyond what is
  documented in `docs/lifecycle.md`.
- `gameengine-config` owns YAML parsing conventions (`YamlDocuments`) and diagnostics
  (`GameEngineConfigException`). Game and arena schemas are not defined yet.
- `gameengine-paper` is the only module that touches Bukkit/Paper. It owns the plugin
  entry point (`GameEnginePlugin`); commands, listeners, and teleport/inventory adapters
  will live here as they are built. Paper is a `compileOnly` dependency so the packaged JAR stays lean.
- `gameengine-integrations-geyser` is reserved for optional Bedrock support. Dependency
  direction is strictly integration → API; core and config never depend on it, and the
  plugin must start without Geyser or Floodgate installed.
- `gameengine-testkit` holds classpath fixture helpers shared by module tests.

## Dependency rules

1. No circular module dependencies (verified by Gradle; `build` fails on cycles).
2. API depends on nothing. Core depends on API only (plus test libraries).
3. Optional integrations are leaves: nothing depends on them.
4. Paper specifics (events, scheduler, entities) never leak into API, core, or config;
   platform adapters translate into engine calls at the Paper boundary.

## Logging and configuration conventions

- Each module uses `java.util.logging` (or the `JavaPlugin` logger inside the Paper
  module) — no logging facade dependency.
- YAML is parsed with SnakeYAML into an immutable top-level mapping; failures throw
  `GameEngineConfigException` with the source name and cause. Full schema validation
  arrives with the game-definition engine.

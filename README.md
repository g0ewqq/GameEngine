# GameEngine

GameEngine is a reusable framework for building and managing custom minigames on
Minecraft Java servers running Paper or Purpur. Server owners define games in YAML;
Java developers will be able to extend behaviour through a public API.

> Status: early development. The repository builds, packages a minimal plugin
> bootstrap, and documents the intended architecture. Gameplay features do not
> exist yet; see [docs/roadmap.md](docs/roadmap.md).

## Goals

- One server hosts multiple independent arenas and concurrent matches.
- Gameplay logic lives in platform-independent modules; Bukkit/Paper specifics stay in
  the Paper adapter.
- Configuration is YAML with clear diagnostics for invalid files.
- Bedrock players (via optional Geyser/Floodgate) share gameplay logic without the
  engine depending on those integrations.

## Non-goals

- No matchmaking, arenas, teams, queues, or countdowns yet.
- No YAML game-definition schema yet.
- No graphical editor.
- No statistics, PlaceholderAPI, or database support yet.

## Platform compatibility

| Component | Baseline |
|---|---|
| Minecraft | Paper/Purpur 1.21.x (via `paper-api:1.21.4-R0.1-SNAPSHOT`) |
| Purpur | Supported through the Paper-compatible API; no separate dependency |
| Java runtime | 21 (matches Paper 1.20–1.21 requirements; Paper 26.x needs Java 25 and is out of scope) |
| Bedrock | Optional Geyser/Floodgate; never required at startup |

## Building

Requires JDK 21 and network access to Maven Central and the PaperMC repository.

```bash
./gradlew build
./gradlew :gameengine-paper:jar
```

The plugin JAR is produced at `gameengine-paper/build/libs/gameengine-paper-<version>.jar`.

On Windows use `gradlew.bat` instead of `./gradlew`.

## Project layout

- `gameengine-api` — public interfaces and contracts (no Paper dependency).
- `gameengine-core` — platform-independent gameplay engine (depends on API only).
- `gameengine-config` — YAML parsing and validation (SnakeYAML).
- `gameengine-paper` — plugin entry point and Paper/Purpur adapters.
- `gameengine-integrations-geyser` — reserved for optional Bedrock integration; the core
  never depends on it.
- `gameengine-testkit` — shared test utilities.
- `examples/` — sample game definitions (to be added as the framework grows).
- `docs/` — [architecture](docs/architecture.md), [lifecycle](docs/lifecycle.md), [roadmap](docs/roadmap.md).

Dependency direction is documented in [docs/architecture.md](docs/architecture.md) and
enforced by the Gradle module graph (no circular dependencies; API and core are free of
Paper and Geyser dependencies).

## Contributing and bug reports

No contribution process is defined yet. Until one is published, please report issues
through the repository's issue tracker with the server version, Java version, and full
startup log.

## License

MIT — see [LICENSE](LICENSE).

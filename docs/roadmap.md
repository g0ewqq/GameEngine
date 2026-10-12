# Roadmap

## Current status

- Gradle multi-module structure, Java 21 baseline, Paper 1.21.x API.
- Minimal plugin bootstrap that packages into a valid JAR.
- YAML parsing and logging conventions.
- Initial unit tests and test infrastructure.
- README, architecture, lifecycle design, roadmap, license.

## Planned work (not started; no code for these exists yet)

- **Public API surface:** game registration, match/arena/player/team
  abstractions, objectives and game events.
- **Match lifecycle:** orchestration of WAITING → … → WAITING, session and
  team management, queue and countdown handling, cleanup and failure recovery.
- **Configuration engine:** YAML game/arena schemas, validation with
  diagnostics, configurable game rules.
- **Paper integration:** commands, permissions, listeners, teleportation and
  inventory handling, arena editing support.
- **Optional integrations:** Geyser/Floodgate Bedrock support,
  PlaceholderAPI, statistics.
- **Examples** will be added alongside the features they demonstrate.

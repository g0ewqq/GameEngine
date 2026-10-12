pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        maven("https://repo.papermc.io/repository/maven-public/")
        maven("https://repo.purpurmc.org/snapshots/")
    }
}

rootProject.name = "gameengine"

include(
    "gameengine-api",
    "gameengine-core",
    "gameengine-config",
    "gameengine-paper",
    "gameengine-integrations-geyser",
    "gameengine-testkit"
)

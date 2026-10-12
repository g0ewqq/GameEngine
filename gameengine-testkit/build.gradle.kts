plugins {
    `java-library`
}

dependencies {
    api(project(":gameengine-api"))
    api(project(":gameengine-core"))
    api(platform("org.junit:junit-bom:5.11.4"))
    api("org.junit.jupiter:junit-jupiter")
}

tasks.jar {
    archiveBaseName.set("gameengine-testkit")
}

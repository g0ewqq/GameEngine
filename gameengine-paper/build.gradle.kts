dependencies {
    implementation(project(":gameengine-api"))
    implementation(project(":gameengine-core"))
    implementation(project(":gameengine-config"))

    compileOnly("io.papermc.paper:paper-api:1.21.4-R0.1-SNAPSHOT")

    testImplementation(project(":gameengine-testkit"))
    testImplementation(platform("org.junit:junit-bom:5.11.4"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.processResources {
    filesMatching(listOf("paper-plugin.yml", "plugin.yml")) {
        expand("version" to project.version)
    }
}

tasks.jar {
    archiveBaseName.set("gameengine-paper")
}

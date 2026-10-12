dependencies {
    implementation(project(":gameengine-api"))
    implementation("org.yaml:snakeyaml:2.3")

    testImplementation(project(":gameengine-testkit"))
    testImplementation(platform("org.junit:junit-bom:5.11.4"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.jar {
    archiveBaseName.set("gameengine-config")
}

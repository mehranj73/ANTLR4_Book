import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("java")
    kotlin("jvm") version "1.7.20"
    application
    id("antlr")

}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    antlr("org.antlr:antlr4:4.11.1")
    implementation("org.antlr:antlr4-runtime:4.11.1")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}

configurations[JavaPlugin.API_CONFIGURATION_NAME].let { apiConfiguration ->
    apiConfiguration.setExtendsFrom(apiConfiguration.extendsFrom.filter { it.name != "antlr" })
}

tasks.generateGrammarSource {
    maxHeapSize = "64m"
    arguments = arguments + listOf("-no-visitor")
    outputDirectory = File("src/main/java")
}
tasks.named("compileKotlin").configure { dependsOn(tasks.generateGrammarSource) }

tasks.test {
    useJUnitPlatform()
}


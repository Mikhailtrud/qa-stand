plugins {
    java
    id("io.qameta.allure") version "4.1.0"
}

group = "io.github.<your-github>"
version = "1.0-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

allure {
    adapter {
        resultsDir.set(layout.buildDirectory.dir("allure-results"))
    }
}

dependencies {

    // JUnit
    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly(libs.junit.launcher)

    // REST Assured
    testImplementation(libs.rest.assured)

    // Jackson
    testImplementation(libs.jackson.databind)

    // AssertJ
    testImplementation(libs.assertj)

    // Datafaker
    testImplementation(libs.datafaker)

    // Lombok
    testCompileOnly(libs.lombok)
    testAnnotationProcessor(libs.lombok)

    // Allure
    testImplementation(libs.allure.junit5)
    testImplementation(libs.allure.rest.assured)

    // DB driver
    testImplementation("org.postgresql:postgresql:42.7.7")
}

tasks.test {
    useJUnitPlatform()

    maxHeapSize = "2g"

    testLogging {
        events("passed", "skipped", "failed")
    }
}

tasks.named<io.qameta.allure.gradle.report.tasks.AllureReport>("allureReport") {
    reportDir.set(layout.projectDirectory.dir("../reports/api/allure-report"))
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
}

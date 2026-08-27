plugins {
    java
    id("io.qameta.allure") version "4.1.0"
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

allure {
    adapter {
        resultsDir.set(layout.projectDirectory.dir("../reports/ui/allure-results"))
    }
    report {
        configFile.set(layout.projectDirectory.file("allurerc.json"))
    }
}

dependencies {

    testImplementation(platform("org.junit:junit-bom:5.13.4"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    testImplementation("com.codeborne:selenide:7.9.3")
    testImplementation("io.rest-assured:rest-assured:5.5.6")

    testImplementation(platform("io.qameta.allure:allure-bom:2.35.1"))

    testImplementation("io.qameta.allure:allure-junit5")
    testImplementation("io.qameta.allure:allure-selenide")
}

tasks.test {
    useJUnitPlatform()
}

tasks.named<io.qameta.allure.gradle.report.tasks.AllureReport>("allureReport") {
    reportDir.set(layout.projectDirectory.dir("../reports/ui/allure-report"))
}

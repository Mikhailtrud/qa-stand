plugins {
    java
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    testImplementation(platform("org.junit:junit-bom:5.13.4"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    testImplementation("com.codeborne:selenide:7.9.3")

    testImplementation(platform("io.qameta.allure:allure-bom:2.35.1"))

    testImplementation("io.qameta.allure:allure-junit5")
    testImplementation("io.qameta.allure:allure-selenide")
}

tasks.test {
    useJUnitPlatform()
}
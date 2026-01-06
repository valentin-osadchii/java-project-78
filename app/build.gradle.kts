plugins {
    id("java")
    id("checkstyle")
    id("org.sonarqube") version "7.2.2.6593"
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

val lombokVersion = "1.18.34"
val junitBomVersion = "5.10.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:$junitBomVersion"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    compileOnly("org.projectlombok:lombok:$lombokVersion")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")

    testCompileOnly("org.projectlombok:lombok:$lombokVersion")
    testAnnotationProcessor("org.projectlombok:lombok:$lombokVersion")

}


tasks.check {
    dependsOn(tasks.checkstyleMain, tasks.checkstyleTest)
}

tasks.build {
    dependsOn(tasks.check)
}

tasks.test {
    useJUnitPlatform()
}


sonar {
    properties {
        property("sonar.projectKey", "valentin-osadchii_java-project-78")
        property("sonar.organization", "valentin-osadchii")
        property("sonar.host.url", "https://sonarcloud.io") //s

    }
}

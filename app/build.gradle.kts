plugins {
    id("java")
    id("checkstyle")
    id("org.sonarqube") version "7.2.2.6593"
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    compileOnly("org.projectlombok:lombok:1.18.34")
    annotationProcessor("org.projectlombok:lombok:1.18.34")

    testCompileOnly("org.projectlombok:lombok:1.18.34")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.34")

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
    }
}

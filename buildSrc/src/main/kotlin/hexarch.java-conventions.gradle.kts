plugins {
    `java-library`
    id("com.adarshr.test-logger")
}

repositories {
    mavenCentral()
}

dependencies {
    annotationProcessor("org.projectlombok:lombok:1.18.24")
    compileOnly("org.projectlombok:lombok:1.18.24")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
    testImplementation("org.mockito:mockito-junit-jupiter:4.5.1")
    testImplementation("org.assertj:assertj-core:3.22.0")
}

group = "hexarch"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17
java.targetCompatibility = JavaVersion.VERSION_17

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}

tasks.jar {
    var parentDir = project.projectDir.parentFile
    var jarFileName = "${project.name}.jar"

    while (parentDir != project.rootDir) {
        jarFileName = "${parentDir.name}.${jarFileName}"
        parentDir = parentDir.parentFile
    }
    archiveFileName.set(jarFileName)
}

tasks.test {
    useJUnitPlatform()
}

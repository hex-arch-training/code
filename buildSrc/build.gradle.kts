plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
}

dependencies {
    implementation("io.freefair.gradle:lombok-plugin:6.4.3")
    implementation("com.adarshr:gradle-test-logger-plugin:3.2.0")
}

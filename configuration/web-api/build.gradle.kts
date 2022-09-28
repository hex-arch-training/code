plugins {
    id("hexarch.db")
    id("hexarch.web")
    id("hexarch.application")
    id("org.springframework.boot") version "2.7.4"
}

dependencies {
    implementation(project(":preparation:adapter-in-web"))
    implementation(project(":preparation:adapter-out-db"))
    implementation(project(":preparation:adapter-out-event"))
    implementation(project(":preparation:domain"))
    implementation(project(":preparation:application"))
    implementation(project(":verification:adapter-in-web"))
    implementation(project(":verification:adapter-in-event"))
    implementation(project(":verification:adapter-out-db"))
    implementation(project(":verification:adapter-out-preparation"))
    implementation(project(":verification:domain"))
    implementation(project(":verification:application"))
    implementation(project(":shared:util"))
    implementation(project(":shared:event"))

    runtimeOnly("com.h2database:h2:2.1.214")
}

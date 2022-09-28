plugins {
    id("hexarch.web")
}

dependencies {
    implementation(project(":verification:domain"))
    implementation(project(":verification:application"))
}

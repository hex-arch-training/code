plugins {
    id("hexarch.application")
}

dependencies {
    implementation(project(":verification:application"))
    implementation(project(":verification:domain"))
    implementation(project(":preparation:application"))
}

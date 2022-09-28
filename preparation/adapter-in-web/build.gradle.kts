plugins {
    id("hexarch.web")
}

dependencies {
    implementation(project(":preparation:application"))
    implementation(project(":preparation:domain"))
}

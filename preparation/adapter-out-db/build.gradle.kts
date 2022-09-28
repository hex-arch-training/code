plugins {
    id("hexarch.db")
}

dependencies {
    implementation(project(":preparation:application"))
    implementation(project(":preparation:domain"))

}

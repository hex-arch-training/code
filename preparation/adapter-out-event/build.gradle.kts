plugins {
    id("hexarch.application")
}

dependencies {
    implementation(project(":preparation:application"))
    implementation(project(":shared:event"))
}

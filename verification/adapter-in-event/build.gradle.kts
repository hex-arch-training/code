plugins {
    id("hexarch.application")
}

dependencies {
    implementation(project(":verification:application"))
    implementation(project(":shared:event"))
}

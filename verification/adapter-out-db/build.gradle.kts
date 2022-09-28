plugins {
    id("hexarch.java-conventions")
}

dependencies {
    implementation(project(":verification:domain"))
    implementation(project(":verification:application"))
}

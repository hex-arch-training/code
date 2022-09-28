plugins {
    id("hexarch.java-conventions")
}

dependencies {
    implementation("org.springframework:spring-context:5.3.23")
    compileOnly ("org.springframework:spring-tx:5.3.23")
    compileOnly ("jakarta.persistence:jakarta.persistence-api:2.2.3")
}

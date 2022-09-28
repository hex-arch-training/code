plugins {
    id("hexarch.java-conventions")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.7.4")
    testImplementation("org.springframework.boot:spring-boot-starter-test:2.7.4")
    testRuntimeOnly("com.h2database:h2:2.1.214")
}

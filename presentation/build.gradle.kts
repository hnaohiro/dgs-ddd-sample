plugins {
    id("org.springframework.boot") version "2.7.4"
    id("io.spring.dependency-management") version "1.0.14.RELEASE"
    id("com.netflix.dgs.codegen") version "5.4.0"
    kotlin("plugin.spring") version "1.6.21"
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":infrastructure"))
    implementation(project(":usecase"))
    implementation("org.springframework.boot:spring-boot-starter")
    implementation(platform("com.netflix.graphql.dgs:graphql-dgs-platform-dependencies:5.3.0"))
    implementation("com.netflix.graphql.dgs:graphql-dgs-spring-boot-starter")
    implementation("org.jetbrains.exposed:spring-transaction:0.40.1")
    implementation("org.jetbrains.exposed:exposed-spring-boot-starter:0.40.1")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<com.netflix.graphql.dgs.codegen.gradle.GenerateJavaTask> {
    packageName = "com.dgsdddsample.presentation.generated"
    language = "kotlin"
    generateClient = false
}

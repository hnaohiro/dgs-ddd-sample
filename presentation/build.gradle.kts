plugins {
    id("org.springframework.boot") version "2.7.4"
    id("io.spring.dependency-management") version "1.0.14.RELEASE"
    id("com.netflix.dgs.codegen") version "5.4.0"
    kotlin("plugin.spring") version "1.6.21"
}

dependencies {
    implementation(project(":configuration"))
    implementation(project(":domain"))
    implementation(project(":usecase"))
    implementation("io.insert-koin:koin-core:3.2.2")
    implementation("org.springframework.boot:spring-boot-starter")
    implementation(platform("com.netflix.graphql.dgs:graphql-dgs-platform-dependencies:5.3.0"))
    implementation("com.netflix.graphql.dgs:graphql-dgs-spring-boot-starter")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<com.netflix.graphql.dgs.codegen.gradle.GenerateJavaTask> {
    packageName = "com.dgsdddsample.presentation.generated"
    language = "kotlin"
    generateClient = false
}

dependencies {
    implementation(project(":configuration"))
    implementation(project(":domain"))
    implementation(project(":infrastructure"))
    implementation(project(":presentation"))
    implementation(project(":usecase"))
    implementation("io.insert-koin:koin-core:3.2.2")
    implementation(platform("com.netflix.graphql.dgs:graphql-dgs-platform-dependencies:5.3.0"))
    implementation("com.netflix.graphql.dgs:graphql-dgs-spring-boot-starter")
    implementation("org.jetbrains.exposed:exposed-spring-boot-starter:0.40.1")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

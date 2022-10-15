import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.4"
    id("io.spring.dependency-management") version "1.0.14.RELEASE"
    id("com.netflix.dgs.codegen") version "5.4.0"
    id("org.flywaydb.flyway") version "8.2.0"
    kotlin("jvm") version "1.7.20"
    kotlin("plugin.spring") version "1.6.21"
}

group = "com.dgsdddsample"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation(platform("com.netflix.graphql.dgs:graphql-dgs-platform-dependencies:latest.release"))
    implementation("com.netflix.graphql.dgs:graphql-dgs-spring-boot-starter")
    implementation("org.jetbrains.exposed:exposed-core:0.39.2")
    implementation("org.jetbrains.exposed:exposed-dao:0.39.2")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.39.2")
    implementation("org.jetbrains.exposed:spring-transaction:0.39.2")
    implementation("org.jetbrains.exposed:exposed-spring-boot-starter:0.39.2")
    implementation("com.github.guepardoapps:kulid:2.0.0.0")
    implementation("mysql:mysql-connector-java:8.0.30")
    implementation("org.flywaydb:flyway-core")
    implementation("org.flywaydb:flyway-mysql")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

dependencyLocking {
    lockAllConfigurations()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<com.netflix.graphql.dgs.codegen.gradle.GenerateJavaTask> {
    packageName = "com.dgsdddsample.presentation.generated"
    language = "kotlin"
    generateClient = false
}

flyway {
    val host = System.getenv("DB_HOST") ?: "127.0.0.1"
    val port = System.getenv("DB_PORT") ?: "3306"
    val database = System.getenv("DB_DATABASE") ?: "dgs_ddd_sample_development"
    url = System.getenv("DB_URL") ?: "jdbc:mysql://$host:$port/${database}"
    user = System.getenv("DB_USER") ?: "root"
    password = System.getenv("DB_PASSWORD") ?: "root"
}

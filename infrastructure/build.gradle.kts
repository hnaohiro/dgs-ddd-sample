plugins {
    id("org.flywaydb.flyway") version "8.2.0"
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":usecase"))
    implementation("org.jetbrains.exposed:exposed-core:0.40.1")
    implementation("org.jetbrains.exposed:exposed-dao:0.40.1")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.40.1")
    implementation("mysql:mysql-connector-java:8.0.30")
    implementation("org.flywaydb:flyway-core:9.5.1")
    implementation("org.flywaydb:flyway-mysql:9.5.1")
    implementation("com.github.guepardoapps:kulid:2.0.0.0")
}

flyway {
    val host = System.getenv("DB_HOST") ?: "127.0.0.1"
    val port = System.getenv("DB_PORT") ?: "3306"
    val database = System.getenv("DB_DATABASE") ?: "dgs_ddd_sample_development"
    url = System.getenv("DB_URL") ?: "jdbc:mysql://$host:$port/${database}"
    user = System.getenv("DB_USER") ?: "root"
    password = System.getenv("DB_PASSWORD") ?: "root"
}

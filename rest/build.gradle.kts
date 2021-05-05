plugins {
    id("org.springframework.boot") version BuildPlugins.Versions.springBoot2
    id("io.spring.dependency-management") version BuildPlugins.Versions.springDependencyManagement
    id("maven-publish")
}

dependencies {
    api(project(":business"))
    implementation(project(":persistence"))
    implementation(project(":aspect"))
    implementation("jakarta.validation:jakarta.validation-api:3.0.0")
    implementation("org.glassfish:jakarta.el:4.0.1")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-hateoas")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("ch.qos.logback:logback-classic")
    implementation("ch.qos.logback:logback-core")
    implementation("com.h2database:h2")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "junit", module = "junit")
    }
}

springBoot {
    buildInfo()
}

tasks {
    bootJar {
        launchScript()
    }
}
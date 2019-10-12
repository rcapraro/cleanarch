import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot") version BuildPlugins.Versions.springBoot2
    id("io.spring.dependency-management") version BuildPlugins.Versions.springDependencyManagement
    kotlin("plugin.jpa") version kotlinVersion
}

dependencies {
    implementation(project(":business"))
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    testCompile("org.springframework.boot:spring-boot-starter-test")
    testCompile("com.h2database:h2")
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}

springBoot {
    buildInfo()
}

tasks.getByName<Jar>("jar") {
    enabled = true
}

tasks.getByName<BootJar>("bootJar") {
    enabled = false
}
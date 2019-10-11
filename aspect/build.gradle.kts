import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot") version BuildPlugins.Versions.springBoot2
    id("io.spring.dependency-management") version BuildPlugins.Versions.springDependencyManagement
}

dependencies {
    implementation(project(":business"))
    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("org.springframework:spring-tx")
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
plugins {
    id("org.springframework.boot") version BuildPlugins.Versions.springBoot2
    id("io.spring.dependency-management") version BuildPlugins.Versions.springDependencyManagement
    id("maven-publish")
}

dependencies {
    implementation(project(":business"))
    implementation(project(":persistence"))
    implementation(project(":aspect"))
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
    testCompile(TestLibraries.archUnitApi)
    testRuntime(TestLibraries.archUnitEngine)
}

springBoot {
    buildInfo()
}

tasks {
    bootJar {
        launchScript()
    }
}
import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    kotlin("plugin.allopen") version kotlinVersion
    id("org.jetbrains.dokka") version BuildPlugins.Versions.dokka
    id("com.adarshr.test-logger") version BuildPlugins.Versions.testLogger
}

allprojects {
    group = "com.capraro"
    version = "1.0.0-SNAPSHOT"
    description = "Clean Arch / Spring boot sample"

    repositories {
        jcenter()
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.gradle.jacoco")
    apply(plugin = "org.jetbrains.dokka")

    tasks.withType<DokkaTask> {
        outputFormat = "html"
        outputDirectory = "$buildDir/doc"
        includes = listOf("packages.md", "module.md")
    }

    dependencies {
        implementation(Libraries.kotlinStdLib)
        implementation(Libraries.kotlinLogging)
        implementation(kotlin("reflect"))
        testImplementation(TestLibraries.assertJ)
        testImplementation(TestLibraries.junit5)
    }

    configurations {
        testImplementation {
            exclude(group = "junit")
        }
    }

    tasks {
        compileKotlin {
            kotlinOptions {
                freeCompilerArgs = listOf("-Xjsr305=strict")
                jvmTarget = "1.8"
            }
        }

        compileTestKotlin {
            kotlinOptions {
                freeCompilerArgs = listOf("-Xjsr305=strict")
                jvmTarget = "1.8"
            }
        }
    }

    tasks.test {
        useJUnitPlatform()
    }
}

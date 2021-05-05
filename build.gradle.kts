plugins {
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    kotlin("plugin.allopen") version kotlinVersion
    id("org.jetbrains.dokka") version kotlinVersion
    id("com.adarshr.test-logger") version BuildPlugins.Versions.testLogger
}

extra["hibernate-validator.version"] = "7.0.1.Final"

allprojects {
    group = "com.capraro"
    version = "1.0.0"
    description = "Clean Arch / Spring boot sample"

    repositories {
        mavenCentral()
    }

    tasks.withType<Wrapper> {
        gradleVersion = "7.0"
    }
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.gradle.jacoco")
    apply(plugin = "org.jetbrains.dokka")

    tasks.dokkaHtml.configure {
        outputDirectory.set(buildDir.resolve("dokka"))
        dokkaSourceSets.configureEach {
            includes.from("packages.md", "module.md")
        }
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

    tasks.withType<Test> {
        useJUnitPlatform()
    }

}

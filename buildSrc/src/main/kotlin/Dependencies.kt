const val kotlinVersion = "1.4.32"

object BuildPlugins {

    object Versions {
        const val springDependencyManagement = "1.0.11.RELEASE"
        const val springBoot2 = "2.4.5"
        const val testLogger = "3.0.0"
    }
}

object Libraries {
    private object Versions {
        const val kalidation = "1.8.0"
        const val arrow = "0.13.2"
        const val kotlinLogging = "2.0.6"
    }

    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion"
    const val kalidation = "io.github.rcapraro:kalidation:${Versions.kalidation}"
    const val arrowCore = "io.arrow-kt:arrow-core:${Versions.arrow}"
    const val kotlinLogging = "io.github.microutils:kotlin-logging:${Versions.kotlinLogging}"

}

object TestLibraries {
    private object Versions {
        const val junit5 = "5.7.1"
        const val assertJ = "3.19.0"
    }

    const val junit5 = "org.junit.jupiter:junit-jupiter:${Versions.junit5}"
    const val assertJ = "org.assertj:assertj-core:${Versions.assertJ}"
}
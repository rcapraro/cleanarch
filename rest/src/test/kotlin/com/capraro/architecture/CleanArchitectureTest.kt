package com.capraro.architecture

import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.junit.ArchUnitRunner
import com.tngtech.archunit.lang.ArchRule
import com.tngtech.archunit.library.Architectures.layeredArchitecture
import org.junit.runner.RunWith


@RunWith(ArchUnitRunner::class)
@AnalyzeClasses(packages = ["com.capraro"])
class CleanArchitectureTest {

    companion object {
        @ArchTest
        @JvmField
        var layer_dependencies_are_respected: ArchRule = layeredArchitecture()

                .layer("Business").definedBy("com.capraro.business..")
                .layer("Persistence").definedBy("com.capraro.persistence..")
                .layer("Controller").definedBy("com.capraro..controller..")

                .whereLayer("Business").mayOnlyBeAccessedByLayers("Persistence", "Controller")
                .whereLayer("Persistence").mayOnlyBeAccessedByLayers("Controller")
                .whereLayer("Controller").mayNotBeAccessedByAnyLayer()
    }
}

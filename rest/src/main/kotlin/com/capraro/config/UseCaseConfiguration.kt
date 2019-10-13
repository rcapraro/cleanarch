package com.capraro.config

import com.capraro.business.UseCase
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.FilterType

/**
 * Spring configuration to scan all classes annotated with @UseCase.
 */
@Configuration
@ComponentScan(basePackages = ["com.capraro.business"],
        includeFilters = [ComponentScan.Filter(type = FilterType.ANNOTATION,
                value = [UseCase::class])])
class UseCaseConfiguration
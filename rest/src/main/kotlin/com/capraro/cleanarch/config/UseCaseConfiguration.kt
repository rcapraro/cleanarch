package com.capraro.cleanarch.config

import com.capraro.cleanarch.UseCase
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.FilterType

@Configuration
@ComponentScan(basePackages = ["com.capraro.cleanarch"],
        includeFilters = [ComponentScan.Filter(type = FilterType.ANNOTATION,
                value = [UseCase::class])])
class UseCaseConfiguration
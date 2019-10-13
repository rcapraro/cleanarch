package com.capraro.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.LocaleResolver
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver
import java.util.*

/**
 * i18n configuration with default locale as ENGLISH.
 */
@Configuration
class I18nConfiguration {

    /**
     * Configures the LocaleResolver.
     *
     * @return The configured LocaleResolver Beam.
     */
    @Bean("localeResolver")
    fun acceptHeaderLocaleResolver(): LocaleResolver {
        val resolver = AcceptHeaderLocaleResolver()

        resolver.defaultLocale = Locale.ENGLISH
        resolver.supportedLocales = listOf(Locale.ENGLISH, Locale.FRENCH)

        return resolver
    }
}
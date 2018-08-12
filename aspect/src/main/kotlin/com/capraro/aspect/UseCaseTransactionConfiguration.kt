package com.capraro.aspect

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableAspectJAutoProxy
@EnableTransactionManagement
class UseCaseTransactionConfiguration {

    @Bean
    fun useCaseTransactionAspect(transactionTemplate: TransactionalUseCaseExecutor) = TransactionalUseCaseAspect(transactionTemplate)

    @Bean
    fun transactionalUseCaseExecutor() = TransactionalUseCaseExecutor()
}
package com.capraro.aspect

import com.capraro.business.UseCase
import mu.KotlinLogging
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.springframework.transaction.annotation.Transactional
import java.util.function.Supplier

private val logger = KotlinLogging.logger {}

@Aspect
class TransactionalUseCaseAspect(private val transactionalUseCaseExecutor: TransactionalUseCaseExecutor) {

    /**
     * Find all the public method of class annotated with [UseCase].
     */
    @Pointcut("@within(useCase)")
    fun inUseCase(useCase: UseCase) {
    }

    /**
     * Advice the pointCut with [TransactionalUseCaseExecutor].
     */
    @Around("inUseCase(useCase)")
    fun useCase(proceedingJoinPoint: ProceedingJoinPoint, useCase: UseCase): Any? {
        return transactionalUseCaseExecutor.executeInTransaction(Supplier { proceedingJoinPoint.proceed() })
    }
}

open class TransactionalUseCaseExecutor {
    @Transactional
    open fun <T> executeInTransaction(execution: Supplier<T>): T {
        logger.debug { "executing Usecase in transaction" }
        return execution.get()
    }
}
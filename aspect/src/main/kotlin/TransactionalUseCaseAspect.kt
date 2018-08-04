package com.capraro.cleanarch.config

import com.capraro.cleanarch.order.usecase.UseCase
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.springframework.transaction.annotation.Transactional
import java.util.function.Supplier

@Aspect
class TransactionalUseCaseAspect(private val transactionalUseCaseExecutor: TransactionalUseCaseExecutor) {

    @Pointcut("@within(useCase)")
    fun inUseCase(useCase: UseCase) {
    }

    @Around("inUseCase(useCase)")
    fun useCase(proceedingJoinPoint: ProceedingJoinPoint, useCase: UseCase): Any? {
        return transactionalUseCaseExecutor.executeInTransaction(Supplier { proceedingJoinPoint.proceed() })
    }
}

open class TransactionalUseCaseExecutor {
    @Transactional
    open fun <T> executeInTransaction(execution: Supplier<T>): T {
        return execution.get()
    }
}
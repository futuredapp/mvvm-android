package com.thefuntasty.mvvm.rxusecases.test

import com.thefuntasty.mvvm.rxusecases.disposables.FlowableDisposablesOwner
import com.thefuntasty.mvvm.rxusecases.usecases.FlowableUseCase
import io.mockk.every
import io.mockk.just
import io.mockk.runs
import io.reactivex.Flowable
import io.reactivex.disposables.Disposable

/**
 * Mock [FlowableDisposablesOwner.execute] method.
 *
 * When the execute method will be called then the argument passed in [resultBlock] will be used as a result of mocked use case
 * and corresponding methods for the given use case will be called.
 * So when `Flowable.just` will be passed then `onNext` will be called etc.
 *
 * Usage:
 * mockFlowableUseCase.everyExecute(args = ...) { Flowable.just(...) }
 */
inline fun <reified ARGS : Any, RETURN_VALUE, USE_CASE : FlowableUseCase<ARGS, RETURN_VALUE>> USE_CASE.everyExecute(args: ARGS, resultBlock: () -> Flowable<RETURN_VALUE>) {
    mockCurrentDisposable()
    every { this@everyExecute.create(args) } returns resultBlock()
}

/**
 * Mock [FlowableDisposablesOwner.execute] method with `any()` matcher argument used as input argument.
 *
 * When the execute method will be called then the argument passed in [resultBlock] will be used as a result of mocked use case
 * and corresponding methods for the given use case will be called.
 * So when `Flowable.just` will be passed then `onNext` will be called etc.
 *
 * Usage:
 * mockFlowableUseCase.everyExecute(args = ...) { Flowable.just(...) }
 */
inline fun <reified ARGS : Any, RETURN_VALUE, USE_CASE : FlowableUseCase<ARGS, RETURN_VALUE>> USE_CASE.everyExecute(resultBlock: () -> Flowable<RETURN_VALUE>) {
    mockCurrentDisposable()
    every { this@everyExecute.create(any()) } returns resultBlock()
}

/**
 * Mock [FlowableDisposablesOwner.execute] method for use cases with nullable input argument
 *
 * When the execute method will be called then the argument passed in [resultBlock] will be used as a result of mocked use case
 * and corresponding methods for the given use case will be called.
 * So when `Flowable.just` will be passed then `onNext` will be called etc.
 *
 * Usage:
 * mockFlowableUseCase.everyExecute(args = ...) { Flowable.just(...) }
 */
inline fun <reified ARGS : Any?, RETURN_VALUE, USE_CASE : FlowableUseCase<ARGS?, RETURN_VALUE>> USE_CASE.everyExecuteNullable(args: ARGS, resultBlock: () -> Flowable<RETURN_VALUE>) {
    mockCurrentDisposable()
    every { this@everyExecuteNullable.create(args) } returns resultBlock()
}

/**
 * Mock [FlowableDisposablesOwner.execute] method for use cases with nullable input argument
 * and `any()` matcher argument used as input argument.
 *
 * When the execute method will be called then the argument passed in [resultBlock] will be used as a result of mocked use case
 * and corresponding methods for the given use case will be called.
 * So when `Flowable.just` will be passed then `onNext` will be called etc.
 *
 * Usage:
 * mockFlowableUseCase.everyExecute(args = ...) { Flowable.just(...) }
 */
inline fun <reified ARGS : Any, RETURN_VALUE, USE_CASE : FlowableUseCase<ARGS?, RETURN_VALUE>> USE_CASE.everyExecuteNullable(resultBlock: () -> Flowable<RETURN_VALUE>) {
    mockCurrentDisposable()
    every { this@everyExecuteNullable.create(any()) } returns resultBlock()
}

@PublishedApi
internal fun <RETURN_VALUE, USE_CASE : FlowableUseCase<*, RETURN_VALUE>> USE_CASE.mockCurrentDisposable() {
    every { this@mockCurrentDisposable getProperty "currentDisposable" } returns null
    every { this@mockCurrentDisposable setProperty "currentDisposable" value any<Disposable>() } just runs
}
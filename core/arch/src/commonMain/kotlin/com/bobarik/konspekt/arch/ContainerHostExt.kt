package com.bobarik.konspekt.arch

import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.annotation.OrbitDsl
import org.orbitmvi.orbit.syntax.IntentContext

@OrbitDsl
fun <STATE : Any> ContainerHost<STATE, *>.reduce(
    transform: IntentContext<STATE>.() -> STATE
) = intent { reduce(transform) }

@OrbitDsl
fun <STATE : Any> ContainerHost<STATE, *>.blockingReduce(
    transform: IntentContext<STATE>.() -> STATE
) = blockingIntent { reduce(transform) }

@OrbitDsl
fun <SIDE_EFFECT : Any> ContainerHost<*, SIDE_EFFECT>.postEffect(
    sideEffect: SIDE_EFFECT
) = intent { postSideEffect(sideEffect) }

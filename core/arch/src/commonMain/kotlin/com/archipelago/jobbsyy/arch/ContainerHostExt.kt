package com.archipelago.jobbsyy.arch

import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.annotation.OrbitDsl
import org.orbitmvi.orbit.syntax.simple.SimpleContext
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce

@OrbitDsl
fun <STATE : BaseState> ContainerHost<STATE, *>.reduce(
    transform: SimpleContext<STATE>.() -> STATE
) = intent { reduce(transform) }

@OrbitDsl
fun <STATE : BaseState> ContainerHost<STATE, *>.blockingReduce(
    transform: SimpleContext<STATE>.() -> STATE
) = blockingIntent { reduce(transform) }

@OrbitDsl
fun <SIDE_EFFECT : BaseSideEffect> ContainerHost<*, SIDE_EFFECT>.postEffect(
    sideEffect: SIDE_EFFECT
) = intent { postSideEffect(sideEffect) }

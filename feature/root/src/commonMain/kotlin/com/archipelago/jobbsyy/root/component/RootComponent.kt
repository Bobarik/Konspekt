package com.archipelago.jobbsyy.root.component

import com.archipelago.jobbsyy.navigation.BaseChild
import com.archipelago.jobbsyy.navigation.StackComponent
import com.archipelago.jobbsyy.home.component.HomeComponent
import com.archipelago.jobbsyy.login.component.LoginComponent

interface RootComponent : StackComponent<RootComponent.Child> {

    sealed interface Child: BaseChild {
        class LoginChild(val component: LoginComponent) : Child
        class HomeChild(val component: HomeComponent) : Child
    }
}

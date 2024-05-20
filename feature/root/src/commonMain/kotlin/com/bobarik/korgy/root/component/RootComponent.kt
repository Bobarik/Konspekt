package com.bobarik.korgy.root.component

import com.bobarik.korgy.navigation.BaseChild
import com.bobarik.korgy.navigation.StackComponent
import com.bobarik.korgy.home.component.HomeComponent
import com.bobarik.korgy.login.component.LoginComponent

interface RootComponent : StackComponent<RootComponent.Child> {

    sealed interface Child: BaseChild {
        class LoginChild(val component: LoginComponent) : Child
        class HomeChild(val component: HomeComponent) : Child
    }
}

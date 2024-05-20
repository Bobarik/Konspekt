package com.bobarik.konspekt.root.component

import com.bobarik.konspekt.navigation.BaseChild
import com.bobarik.konspekt.navigation.StackComponent
import com.bobarik.konspekt.home.component.HomeComponent
import com.bobarik.konspekt.login.component.LoginComponent

interface RootComponent : StackComponent<RootComponent.Child> {

    sealed interface Child: BaseChild {
        class LoginChild(val component: LoginComponent) : Child
        class HomeChild(val component: HomeComponent) : Child
    }
}

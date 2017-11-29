package com.giftcards.generatoramz.amzfreegiftcards.inject

import com.giftcards.generatoramz.amzfreegiftcards.core.MyApplication
import com.giftcards.generatoramz.amzfreegiftcards.screens.BaseActivity
import com.giftcards.generatoramz.amzfreegiftcards.screens.dialogs.LoginDialog
import com.giftcards.generatoramz.amzfreegiftcards.screens.dialogs.SignupDialog
import dagger.Component

@Component(modules = arrayOf(AppModule::class, MainModule::class))
interface AppComponent {

    fun inject(screen: BaseActivity)
    fun inject(app: MyApplication)
    fun inject(dialog: LoginDialog)
    fun inject(dialog: SignupDialog)
}
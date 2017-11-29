package com.giftcards.generatoramz.amzfreegiftcards.screens.dialogs

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import butterknife.ButterKnife
import butterknife.OnClick
import com.giftcards.generatoramz.amzfreegiftcards.AppTools
import com.giftcards.generatoramz.amzfreegiftcards.R
import com.giftcards.generatoramz.amzfreegiftcards.core.MyApplication
import com.giftcards.generatoramz.amzfreegiftcards.core.managers.CoinsManager
import com.giftcards.generatoramz.amzfreegiftcards.core.managers.DialogsManager
import com.giftcards.generatoramz.amzfreegiftcards.core.managers.PreferencesManager
import com.giftcards.generatoramz.amzfreegiftcards.core.managers.RetrofitManager
import com.giftcards.generatoramz.amzfreegiftcards.inject.AppModule
import com.giftcards.generatoramz.amzfreegiftcards.inject.DaggerAppComponent
import kotlinx.android.synthetic.main.dialog_signup.view.*
import javax.inject.Inject
import kotlin.concurrent.thread

class SignupDialog(private var onSignup: () -> Unit) : DialogFragment() {

    @Inject lateinit var retrofitManager: RetrofitManager
    @Inject lateinit var preferencesManager: PreferencesManager
    @Inject lateinit var coinsManager: CoinsManager
    @Inject lateinit var dialogsManager: DialogsManager

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog.window.requestFeature(Window.FEATURE_NO_TITLE)

        DaggerAppComponent.builder()
                .appModule(AppModule(context))
                .mainModule((activity.application as MyApplication).mainModule)
                .build().inject(this)

        var view = inflater?.inflate(R.layout.dialog_signup, container, false)

        ButterKnife.bind(this, view!!)

        return view
    }

    @OnClick(R.id.signup)
    fun login() {
        if (AppTools.isNetworkAvaliable(context)) {
            if ((root().usernameTxt.text.length >= 4) and (root().passwordTxt.text.length >= 4)) {
                if (root().passwordTxt.text.toString() == root().confirmPassTxt.text.toString()) {
                    preferencesManager.put(PreferencesManager.USERNAME, root().usernameTxt.text.toString())
                    preferencesManager.put(PreferencesManager.PASSWORD, root().passwordTxt.text.toString())
                    var progress = dialogsManager.showProgressDialog(activity.supportFragmentManager)
                    thread {
                        Thread.sleep(2000)
                        activity.runOnUiThread {
                            progress.dismiss()
                            onSignup()
                        }
                    }
                } else {
                    dialogsManager.showAlertDialog(activity.supportFragmentManager,
                            "Passwords do not match!", {})
                }
            } else {
                dialogsManager.showAlertDialog(activity.supportFragmentManager,
                        "Signup data is too short!", {})
            }
        } else {
            dialogsManager.showAlertDialog(activity.supportFragmentManager,
                    "Sorry, no internet connection!", {})
        }
    }

    @OnClick(R.id.signupCancel)
    fun cancel() {
        dismiss()
    }

    fun root() : View {
        return view?.rootView!!
    }
}
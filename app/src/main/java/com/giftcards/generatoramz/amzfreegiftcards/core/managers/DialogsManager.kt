package com.giftcards.generatoramz.amzfreegiftcards.core.managers

import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import com.giftcards.generatoramz.amzfreegiftcards.dialogs.AdvAlertDialog
import com.giftcards.generatoramz.amzfreegiftcards.dialogs.ProgressDialog
import com.giftcards.generatoramz.amzfreegiftcards.screens.dialogs.*

class DialogsManager {

    companion object {
        var PROGRESS = "PROGRESS"
        var SIGNUP = "SIGNUP"
        var LOGIN = "LOGIN"
        var ALERT = "ALERT"
        var INVITE = "INVITE"
        var ADV_ALERT = "ADV_ALERT"
    }

    fun showLoginDialog(fm: FragmentManager, onLogin: () -> Unit) {
        var dialog = LoginDialog(onLogin)
        dialog.isCancelable = false
        show(fm, dialog, LOGIN)
    }

    fun showSignupDialog(fm: FragmentManager, onSignup: () -> Unit) {
        var dialog = SignupDialog(onSignup)
        dialog.isCancelable = false
        show(fm, dialog, SIGNUP)
    }

    fun showProgressDialog(fm: FragmentManager) : ProgressDialog {
        var dialog = ProgressDialog()
        dialog.isCancelable = false
        show(fm, dialog, PROGRESS)
        return dialog
    }

    fun showAlertDialog(fm: FragmentManager, alert: String, onOk: () -> Unit) {
        var dialog = AlertDialog(alert, onOk)
        dialog.isCancelable = false
        show(fm, dialog, ALERT)
    }

    fun showInviteDialog(fm: FragmentManager, alert: String, code: String, onOk: () -> Unit) {
        var dialog = InviteDialog(alert, code, onOk)
        dialog.isCancelable = false
        show(fm, dialog, INVITE)
    }

    fun showAdvAlertDialog(fm: FragmentManager, alert: String, onOk: () -> Unit, onCancel: () -> Unit) {
        var dialog = AdvAlertDialog(alert, onOk, onCancel)
        dialog.isCancelable = false
        show(fm, dialog, ADV_ALERT)
    }

    private fun show (fm: FragmentManager, dialog: DialogFragment, tag: String) {
        dialog.show(fm, tag)
    }
}

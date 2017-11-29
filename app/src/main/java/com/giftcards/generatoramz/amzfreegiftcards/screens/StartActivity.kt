package com.giftcards.generatoramz.amzfreegiftcards.screens

import android.content.Intent
import android.os.Bundle
import android.view.View
import butterknife.OnClick
import com.giftcards.generatoramz.amzfreegiftcards.AppTools

import com.giftcards.generatoramz.amzfreegiftcards.R
import com.giftcards.generatoramz.amzfreegiftcards.core.managers.PreferencesManager
import com.giftcards.generatoramz.amzfreegiftcards.data.Invite
import com.vicpin.krealmextensions.save

class StartActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        if (!preferencesManager.get(PreferencesManager.FIRST_LAUNCH, true)) {
            goOffers()
        } else {
            bind()
        }
    }

    @OnClick(R.id.login ,R.id.register)
    fun start(v: View) {
        when (v.id) {
            R.id.login -> {
                dialogsManager.showLoginDialog(supportFragmentManager, {
                    preferencesManager.put(PreferencesManager.FIRST_LAUNCH, false)
                    generateInviteCode()
                    goOffers()
                })
            }
            R.id.register -> {
                dialogsManager.showSignupDialog(supportFragmentManager, {
                    preferencesManager.put(PreferencesManager.FIRST_LAUNCH, false)
                    generateInviteCode()
                    goOffers()
                })
            }
        }
    }

    private fun generateInviteCode() {
        var code = AppTools.createCode()
        preferencesManager.put(PreferencesManager.INVITE, code)
        Invite(code).save()
    }

    private fun goOffers() {
        startActivity(Intent(this, OffersActivity::class.java)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP))
        finish()
    }
}

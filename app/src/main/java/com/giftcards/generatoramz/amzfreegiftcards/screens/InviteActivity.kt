package com.giftcards.generatoramz.amzfreegiftcards.screens

import android.content.Intent
import android.os.Bundle
import android.view.View
import butterknife.OnClick
import com.giftcards.generatoramz.amzfreegiftcards.AppTools
import com.giftcards.generatoramz.amzfreegiftcards.R
import com.giftcards.generatoramz.amzfreegiftcards.core.managers.PreferencesManager
import com.giftcards.generatoramz.amzfreegiftcards.data.Invite
import com.vicpin.krealmextensions.query
import com.vicpin.krealmextensions.save
import kotlinx.android.synthetic.main.activity_invite.*
import kotlinx.android.synthetic.main.toolbar.*

class InviteActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invite)

        bindCoinView()
        bind()

        toolbarText.text = "Invite Friends"

        initBanner()

        refCodeText.text = preferencesManager.get(PreferencesManager.INVITE, "")
    }

    @OnClick(R.id.shareBtn, R.id.validateBtn, R.id.checkBtn)
    fun feferral(view: View) {
        animationsManager.btnClick(view, {}, {
            when (view.id) {
                R.id.shareBtn -> {
                    var mess = "Hi friends, I'am using this app to get free Amazon Gift Cards: \"https://play.google.com/store/apps/details?id=" +
                            packageName + "\". Here is my invite code: ${preferencesManager.get(PreferencesManager.INVITE, "")}. Use it to get 300 bonus coins!"
                    try {
                        startActivity(Intent.createChooser(Intent(Intent.ACTION_SEND).setType("text/plain").putExtra(Intent.EXTRA_TEXT, mess), "Share"))
                    } catch (ex: Exception) {}
                }
                R.id.validateBtn -> {
                    if (validationTxt.text.toString().isNotEmpty()) {
                        if (AppTools.checkCode(validationTxt.text.toString())) {
                            if (Invite().query { q -> q.equalTo("code", validationTxt.text.toString()) }.isEmpty()) {
                                Invite(validationTxt.text.toString()).save()
                                coinsManager.addCoins(100)
                                updateCoins()
                                dialogsManager.showAlertDialog(supportFragmentManager,
                                        "Congratulations! You got 100 coins!", {
                                    admobInterstitial?.show {  }
                                })
                            } else {
                                dialogsManager.showAlertDialog(supportFragmentManager,
                                        "Validation code is not valid!", {
                                    admobInterstitial?.show {  }
                                })
                            }
                        } else {
                            dialogsManager.showAlertDialog(supportFragmentManager,
                                    "Validation code is not valid!", {
                                admobInterstitial?.show {  }
                            })
                        }
                    } else {
                        dialogsManager.showAlertDialog(supportFragmentManager,
                                "Validation code is empty!", {
                            admobInterstitial?.show { }
                        })
                    }
                }
                R.id.checkBtn -> {
                    if (codeTxt.text.toString().isNotEmpty()) {
                        if (AppTools.checkCode(codeTxt.text.toString())) {
                            if (Invite().query { q -> q.equalTo("code", codeTxt.text.toString()) }.isEmpty()) {
                                Invite(codeTxt.text.toString()).save()
                                coinsManager.addCoins(300)
                                updateCoins()
                                var valCode = AppTools.createCode()
                                Invite(valCode).save()
                                dialogsManager.showInviteDialog(supportFragmentManager,
                                        "Congratulations! You got 300 coins! Send this validation code to your friend!",
                                        valCode, {
                                    dialogsManager.showAlertDialog(supportFragmentManager,
                                            "Copied to clipboard!", {
                                        admobInterstitial?.show { }
                                    })
                                })
                            } else {
                                dialogsManager.showAlertDialog(supportFragmentManager,
                                        "Invite code have been already used, try another one!", {
                                    admobInterstitial?.show { }
                                })
                            }
                        } else {
                            dialogsManager.showAlertDialog(supportFragmentManager,
                                    "Invite code is not valid!", {
                                admobInterstitial?.show { }
                            })
                        }
                    } else {
                        dialogsManager.showAlertDialog(supportFragmentManager,
                                "Invite code is empty!", {
                            admobInterstitial?.show { }
                        })
                    }
                }
            }
        })
    }

    @OnClick(R.id.addCoinsText)
    fun back(view: View) {
        startActivity(Intent(this, OffersActivity::class.java)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP))
        finish()
    }

    override fun onBackPressed() {
        startActivity(Intent(this, OffersActivity::class.java)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP))
        finish()
    }
}
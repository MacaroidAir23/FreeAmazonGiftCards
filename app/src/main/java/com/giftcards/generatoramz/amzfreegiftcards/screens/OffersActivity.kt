package com.giftcards.generatoramz.amzfreegiftcards.screens

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import butterknife.OnClick
import com.giftcards.generatoramz.amzfreegiftcards.AppTools
import com.giftcards.generatoramz.amzfreegiftcards.R
import com.giftcards.generatoramz.amzfreegiftcards.core.MyApplication
import com.giftcards.generatoramz.amzfreegiftcards.core.analytics.Analytics
import com.giftcards.generatoramz.amzfreegiftcards.core.managers.PreferencesManager
import kotlinx.android.synthetic.main.toolbar.*

class OffersActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_offers)

        bindCoinView()
        bind()

        preferencesManager.put(PreferencesManager.ADDITIONAL_LIFE, false)

        toolbarText.text = "Earn Coins"

        initBanner()

        if (intent.getBooleanExtra("notification", false)) {
            coinsManager.addCoins(4)
            updateCoins()
            Analytics.report(Analytics.NOTIFICATION)
            dialogsManager.showAlertDialog(supportFragmentManager,
                    "You got 4 coins!", {
                admobInterstitial?.show { }
            })
        }
    }

    @OnClick(R.id.addCoinsText)
    fun back(view: View) {
        dialogsManager.showAlertDialog(supportFragmentManager, "You already here!", {
            admobInterstitial?.show { }
        })
    }

    override fun onBackPressed() {
        dialogsManager.showAdvAlertDialog(supportFragmentManager, "Do you really want to exit?", {
            finish()
        }, {
            admobInterstitial?.show { }
        })
    }

    @OnClick(R.id.bestOffers,
            R.id.awesomeOffers,
            R.id.bestVideo,
            R.id.beautifulVideo,
            R.id.coolVideo,
            R.id.interestingVideo,
            R.id.awesomeVideo,
            R.id.giftCards,
            R.id.ticketcsGame,
            R.id.rateUs,
            R.id.share)
    fun offersClick(view: View) {
        animationsManager.btnClick(view, {}, {
            if (view.id == R.id.bestOffers) {
                if ((application as MyApplication).advertisement?.house != null) {
                    (application as MyApplication).advertisement?.house?.show(coinsView)
                } else {
                    dialogsManager.showAlertDialog(supportFragmentManager, "No offers available!", {
                        admobInterstitial?.show { }
                    })
                }
            } else if (view.id == R.id.awesomeOffers) {
                if ((application as MyApplication).advertisement?.offertoro != null) {
                    (application as MyApplication).advertisement?.offertoro?.show(this, coinsView)
                } else {
                    dialogsManager.showAlertDialog(supportFragmentManager, "No offers available!", {
                        admobInterstitial?.show { }
                    })
                }
            } else if (view.id == R.id.bestVideo) {
                if ((application as MyApplication).advertisement?.admob != null) {
                    if (!(application as MyApplication).advertisement?.admob?.show(coinsView)!!) {
                        dialogsManager.showAlertDialog(supportFragmentManager, "No offers available!", {
                            admobInterstitial?.show { }
                        })
                    }
                } else {
                    dialogsManager.showAlertDialog(supportFragmentManager, "No offers available!", {
                        admobInterstitial?.show { }
                    })
                }
            } else if (view.id == R.id.beautifulVideo) {
                if ((application as MyApplication).advertisement?.vungle != null) {
                    if (!(application as MyApplication).advertisement?.vungle?.showVideo(coinsView)!!) {
                        dialogsManager.showAlertDialog(supportFragmentManager, "No offers available!", {
                            admobInterstitial?.show { }
                        })
                    }
                } else {
                    dialogsManager.showAlertDialog(supportFragmentManager, "No offers available!", {
                        admobInterstitial?.show { }
                    })
                }
            } else if (view.id == R.id.interestingVideo) {
                if ((application as MyApplication).advertisement?.adcolony != null) {
                    if (!(application as MyApplication).advertisement?.adcolony?.showVideo(coinsView)!!) {
                        dialogsManager.showAlertDialog(supportFragmentManager, "No offers available!", {
                            admobInterstitial?.show { }
                        })
                    }
                } else {
                    dialogsManager.showAlertDialog(supportFragmentManager, "No offers available!", {
                        admobInterstitial?.show { }
                    })
                }
            } else if (view.id == R.id.coolVideo) {
                if ((application as MyApplication).advertisement?.unity != null) {
                    if (!(application as MyApplication).advertisement?.unity?.showVideo(this, coinsView)!!) {
                        dialogsManager.showAlertDialog(supportFragmentManager, "No offers available!", {
                            admobInterstitial?.show { }
                        })
                    }
                } else {
                    dialogsManager.showAlertDialog(supportFragmentManager, "No offers available!", {
                        admobInterstitial?.show { }
                    })
                }
            } else if (view.id == R.id.awesomeVideo) {
                dialogsManager.showAlertDialog(supportFragmentManager, "No offers available!", {
                    admobInterstitial?.show { }
                })
            } else if (view.id == R.id.giftCards) {
                startActivity(Intent(this, GiftCardsActivity::class.java)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP))
                finish()
            } else if (view.id == R.id.ticketcsGame) {
                if (AppTools.isNetworkAvaliable(this)) {
                    startActivity(Intent(this, GameActivity::class.java)
                            .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP))
                    finish()
                } else {
                    dialogsManager.showAlertDialog(supportFragmentManager, "No internet connection!", {
                        admobInterstitial?.show {  }
                    })
                }
            } else if (view.id == R.id.rateUs) {
                dialogsManager.showAlertDialog(supportFragmentManager, "Ple".plus("ase, ").plus("rat").plus("e us")
                        .plus(" 5").plus(" sta").plus("rs!"), {
                    try {
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packageName)))
                    } catch (ex: Exception) {
                    }
                })
            } else if (view.id == R.id.share) {
                startActivity(Intent(this, InviteActivity::class.java)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP))
                finish()
            }
        })
    }
}
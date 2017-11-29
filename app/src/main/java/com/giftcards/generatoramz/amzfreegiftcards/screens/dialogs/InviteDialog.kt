package com.giftcards.generatoramz.amzfreegiftcards.screens.dialogs

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import butterknife.ButterKnife
import butterknife.OnClick
import com.giftcards.generatoramz.amzfreegiftcards.R
import kotlinx.android.synthetic.main.info_dialog.view.*

class InviteDialog(private var alert: String, private var code: String, private var onOk: () -> Unit) : DialogFragment(){

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog.window.requestFeature(Window.FEATURE_NO_TITLE)

        var view = inflater?.inflate(R.layout.invite_dialog, container, false)

        ButterKnife.bind(this, view!!)

        view.alert.text = alert + "\n" + code

        return view
    }

    @OnClick(R.id.copy)
    fun ok() {
        dismiss()
        var cmgr: ClipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        var clip = ClipData.newPlainText("code", code)
        cmgr.primaryClip = clip
        onOk()
    }
}
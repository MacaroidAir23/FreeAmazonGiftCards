package com.giftcards.generatoramz.amzfreegiftcards.data

import io.realm.RealmObject

open class Invite() : RealmObject() {

    var code: String = ""

    constructor(code: String) : this() {
        this.code = code
    }
}
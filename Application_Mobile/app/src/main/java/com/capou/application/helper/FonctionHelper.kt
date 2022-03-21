package com.capou.application.helper

class FonctionHelper {

    fun helpterText(text:String):String{
        var firstLetter = text.substring(0,1).uppercase()
        var otherLetter = text.substring(1)
        var newText = "${firstLetter}${otherLetter}"
        return newText
    }
}
package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?):Pair<String?, String?> {
        if(fullName == "" || fullName == " ") return Pair(null, null)

        val parts: List<String>? = fullName?.split(" ")

        val firstName = parts?.getOrNull(0) ?: null
        val lastName = parts?.getOrNull(1) ?: null
        return Pair(firstName, lastName)
    }

    fun transliterations(payload: String, divider:String =" "): String{
        var translitLetters = mapOf<String, String>("а" to "a",
        "б" to "b",
        "в" to "v",
        "г" to "g",
        "д" to "d",
        "е" to "e",
        "ё" to "e",
        "ж" to "zh",
        "з" to "z",
        "и" to "i",
        "й" to "i",
        "к" to "k",
        "л" to "l",
        "м" to "m",
        "н" to "n",
        "о" to "o",
        "п" to "p",
        "р" to "r",
        "с" to "s",
        "т" to "t",
        "у" to "u",
        "ф" to "f",
        "х" to "h",
        "ц" to "c",
        "ч" to "ch",
        "ш" to "sh",
        "щ" to "sh'",
        "ъ" to "",
        "ы" to "i",
        "ь" to "",
        "э" to "e",
        "ю" to "yu",
        "я" to "ya"
        )
        var arrays = payload.split(" ")
        var firstName = ""
        var lastName = ""

        arrays.getOrNull(0)?.toCharArray()?.map {
            if(translitLetters.containsKey(it.toString().toLowerCase())){
                firstName += translitLetters.getValue(it.toString().toLowerCase())
            }
        }
        arrays.getOrNull(1)?.toCharArray()?.map {
            if(translitLetters.containsKey(it.toString().toLowerCase())){
                lastName += translitLetters.getValue(it.toString().toLowerCase())
            }
        }

        return firstName.capitalize() + divider + lastName.capitalize()
    }

    fun toInitials(firstName:String?, lastName: String?): String?{
        if((firstName == null || firstName == "" || firstName == " ") && (lastName == null || lastName == "" || lastName == " ")) return null

        var firstInitial = firstName?.getOrNull(0) ?: ""
        var lastInitial = lastName?.getOrNull(0) ?: ""

        var initials = firstInitial.toString().toUpperCase() + lastInitial.toString().toUpperCase()
        return initials
    }


}
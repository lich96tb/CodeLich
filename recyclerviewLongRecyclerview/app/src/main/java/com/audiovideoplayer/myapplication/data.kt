package com.audiovideoplayer.myapplication

import com.audiovideoplayer.myapplication.model.Parent
import com.example.framgianguyenthanhtungh.expandablerecycler.model.Child


fun createChildItem1(): List<Child> {
    return listOf(
        Child("1", 1),
        Child("2", 2),
        Child("3", 3),
        Child("4", 4),
        Child("5", 5)
    )
}

fun createChildItem2(): List<Child> {
    return listOf(
        Child("21", 1),
        Child("22", 2),
        Child("23", 3),
        Child("24", 4),
        Child("25", 5)
    )
}

fun createChildItem3(): List<Child> {
    return listOf(
        Child("31", 1),
        Child("32", 2),
        Child("33", 3),
        Child("34", 4),
        Child("35", 5)
    )
}

fun createChildItem4(): List<Child> {
    return listOf(
        Child("41", 1),
        Child("42", 2),
        Child("43", 3),
        Child("44", 4),
        Child("45", 5)
    )
}

fun createParent(): List<Parent> {
    return listOf(
        Parent(
            "1",
            createChildItem1()
        ),
        Parent(
            "2",
            createChildItem2()
        ),
        Parent(
            "3",
            createChildItem3()
        ),
        Parent(
            "4",
            createChildItem4()
        )
    )
}

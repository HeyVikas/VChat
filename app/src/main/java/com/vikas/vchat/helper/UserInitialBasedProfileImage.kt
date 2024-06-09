package com.vikas.vchat.helper

fun userInitialBasedProfileImage(name: String): String {
    return "https://placehold.co/400x400/B679C6/FFFFFFpng?text=${name.first()}"
}
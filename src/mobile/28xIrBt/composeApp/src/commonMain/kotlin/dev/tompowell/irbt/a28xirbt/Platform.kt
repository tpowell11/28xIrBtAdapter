package dev.tompowell.irbt.a28xirbt

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
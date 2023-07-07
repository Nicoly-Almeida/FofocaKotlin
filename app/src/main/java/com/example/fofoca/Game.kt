package com.example.fofoca


class Game {
    private val fofocas = mutableListOf<Fofoca>()

    fun addFofoca(fofoca: Fofoca) {
        fofocas.add(fofoca)
    }

    fun getRandomFofoca(): Fofoca {
        return fofocas.random()
    }

    fun size(): Int {
        return fofocas.size
    }
}
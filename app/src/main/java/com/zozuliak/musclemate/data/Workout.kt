package com.zozuliak.musclemate.data

import kotlinx.serialization.Serializable


@Serializable
data class Workout(
    val id: String? = null,
    val userId: String,
    var name: String,
    var exercises: List<Exercise>? = null
)

@Serializable
data class Exercise(
    var id: Int,
    var name: String,
    var sets: List<Set>? = null,
    var group: String,
    var personalRecord: Set? = null,
)

@Serializable
data class Set(
    var weight: Float,
    var repetitions: Int
)



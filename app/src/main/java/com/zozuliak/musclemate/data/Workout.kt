package com.zozuliak.musclemate.data

import com.zozuliak.musclemate.R
import kotlinx.serialization.Serializable


@Serializable
data class Workout(
    val id: String? = null,
    val userId: String,
    var name: String,
)

@Serializable
data class Exercise(
    var id: String? = null,
    val workoutId: String,
    var position: Int,
    var name: String,
    var sets: List<Set>? = null,
    var group: String,
    var personalRecord: Set? = null,
    var restTime: Int? = null
)

@Serializable
data class Set(
    var weight: Float,
    var repetitions: Int
)

enum class Group(val img_id: Int) {
    ABDOMINAL(R.drawable.abdominal),
    BICEPS(R.drawable.biceps),
    BRACHIALIS(R.drawable.brachialis),
    CALVES(R.drawable.calves),
    CHEST(R.drawable.chest),
    FOREARM(R.drawable.forearm),
    GLUTEUS(R.drawable.gluteus),
    LATS(R.drawable.lats),
    FEMORIS(R.drawable.femoris),
    QUADRICEP(R.drawable.quadricep),
    SHOULDER(R.drawable.shoulder),
    TRAPEZIUS(R.drawable.trapezius),
    TRICEPS(R.drawable.triceps)
}

fun String.toGroup(): Group? {
    return Group.values().find { it.name.equals(this, ignoreCase = true) }
}


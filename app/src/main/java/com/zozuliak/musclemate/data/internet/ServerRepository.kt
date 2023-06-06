package com.zozuliak.musclemate.data.internet

import com.zozuliak.musclemate.data.Workout

interface ServerRepository {

    suspend fun addWorkout(workout: Workout) : Resource<String?>

    suspend fun updateWorkout(workout: Workout) : Resource<Boolean?>

    suspend fun deleteWorkoutById(id: String) : Resource<Boolean?>

    suspend fun getWorkoutsForUser(userId: String) : Resource<List<Workout>>
}
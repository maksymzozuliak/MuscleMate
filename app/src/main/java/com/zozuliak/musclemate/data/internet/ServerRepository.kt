package com.zozuliak.musclemate.data.internet

import com.zozuliak.musclemate.data.Exercise
import com.zozuliak.musclemate.data.Workout

interface ServerRepository {

    suspend fun addWorkout(workout: Workout) : Resource<String?>

    suspend fun updateWorkout(workout: Workout) : Resource<Boolean?>

    suspend fun deleteWorkoutById(id: String) : Resource<Boolean?>

    suspend fun getWorkoutsForUser(userId: String) : Resource<List<Workout>>

    suspend fun addExercise(exercise: Exercise) : Resource<String?>

    suspend fun updateExercise(exercise: Exercise) : Resource<Boolean?>

    suspend fun deleteExerciseById(id: String) : Resource<Boolean?>

    suspend fun getExercisesForWorkout(workoutId: String) : Resource<List<Exercise>>

    suspend fun moveExercise(up: Boolean, id: String) : Resource<Boolean?>
}
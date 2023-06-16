package com.zozuliak.musclemate.data.internet

import com.zozuliak.musclemate.data.Exercise
import com.zozuliak.musclemate.data.Workout
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ServerApi {

    @POST("/workouts")
    suspend fun addWorkout(
        @Body workout: Workout,
    ) : Response<String?>

    @POST("/workouts")
    suspend fun updateWorkout(
        @Body workout: Workout,
    ) : Response<Boolean?>

    @DELETE("/workouts/{id}")
    suspend fun deleteWorkoutById(
        @Path("id") id: String,
    ) : Response<Boolean?>

    @GET("/workouts/{userId}")
    suspend fun getWorkoutsForUser(
        @Path("userId") userId: String,
    ) : Response<List<Workout>>

    @POST("/exercises")
    suspend fun addExercise(
        @Body exercise: Exercise,
    ) : Response<String?>

    @POST("/exercises")
    suspend fun updateExercise(
        @Body exercise: Exercise,
    ) : Response<Boolean?>

    @DELETE("/exercises/{id}")
    suspend fun deleteExerciseById(
        @Path("id") id: String,
    ) : Response<Boolean?>

    @GET("/exercises/{workoutId}")
    suspend fun getExercisesForWorkout(
        @Path("workoutId") workoutId: String,
    ) : Response<List<Exercise>>

    @POST("/exercises/move/{id}")
    suspend fun moveExercises(
        @Path("id") id: String,
        @Query("up") up: Boolean,
    ) : Response<Boolean?>



}
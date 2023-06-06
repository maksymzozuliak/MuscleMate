package com.zozuliak.musclemate.data.internet

import com.zozuliak.musclemate.data.Workout
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ServerApi {

    @POST("/workout")
    suspend fun addWorkout(
        @Body workout: Workout,
    ) : Response<String?>

    @POST("/workout")
    suspend fun updateWorkout(
        @Body workout: Workout,
    ) : Response<String?>

    @DELETE("/workout/{id}")
    suspend fun deleteWorkoutById(
        @Path("id") id: String,
    ) : Response<String?>

    @GET("/workouts/{userId}")
    suspend fun getWorkoutsForUser(
        @Path("userId") userId: String,
    ) : Response<List<Workout>>

}
package com.zozuliak.musclemate.data.internet

import com.zozuliak.musclemate.data.Workout
import javax.inject.Inject

class ServerRepositoryImpl @Inject constructor(
    private val api: ServerApi
) : ServerRepository {

    override suspend fun addWorkout(workout: Workout): Resource<String?> {
        return try {
            val response = api.addWorkout(workout = workout)
            val result = response.body()
            if(response.isSuccessful && result != null) {
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error")
        }
    }

    override suspend fun updateWorkout(workout: Workout): Resource<Boolean?> {
        return try {
            val response = api.updateWorkout(workout = workout)
            val result = response.body()
            if(response.isSuccessful && result != null) {
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error")
        }
    }

    override suspend fun deleteWorkoutById(id: String): Resource<Boolean?> {
        return try {
            val response = api.deleteWorkoutById(id = id)
            val result = response.body()
            if(response.isSuccessful && result != null) {
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error")
        }
    }

    override suspend fun getWorkoutsForUser(userId: String): Resource<List<Workout>> {
        return try {
            val response = api.getWorkoutsForUser(userId = userId)
            val result = response.body()
            if(response.isSuccessful && result != null) {
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error")
        }
    }
}
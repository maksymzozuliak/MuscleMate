package com.zozuliak.musclemate.data.internet

import android.util.Log
import com.zozuliak.musclemate.data.Exercise
import com.zozuliak.musclemate.data.Workout
import retrofit2.Response
import javax.inject.Inject

class ServerRepositoryImpl @Inject constructor(
    private val api: ServerApi
) : ServerRepository {

    private fun <T> checkResult(response: Response<T>) : Resource<T> {
        val result = response.body()
        return if(response.isSuccessful && result != null) {
            Resource.Success(result)
        } else {
            Resource.Error(response.message())
        }
    }

    override suspend fun addWorkout(workout: Workout): Resource<String?> {
        return try {
            val response = api.addWorkout(workout = workout)
            checkResult(response)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error")
        }
    }

    override suspend fun updateWorkout(workout: Workout): Resource<Boolean?> {
        return try {
            val response = api.updateWorkout(workout = workout)
            checkResult(response)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error")
        }
    }

    override suspend fun deleteWorkoutById(id: String): Resource<Boolean?> {
        return try {
            val response = api.deleteWorkoutById(id = id)
            checkResult(response)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error")
        }
    }

    override suspend fun getWorkoutsForUser(userId: String): Resource<List<Workout>> {
        return try {
            val response = api.getWorkoutsForUser(userId = userId)
            checkResult(response)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error")
        }
    }

    override suspend fun addExercise(exercise: Exercise): Resource<String?> {
        return try {
            val response = api.addExercise(exercise = exercise)
            checkResult(response)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error")
        }
    }

    override suspend fun updateExercise(exercise: Exercise): Resource<Boolean?> {
        return try {
            val response = api.updateExercise(exercise = exercise)
            checkResult(response)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error")
        }
    }

    override suspend fun deleteExerciseById(id: String): Resource<Boolean?> {
        return try {
            val response = api.deleteExerciseById(id = id)
            checkResult(response)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error")
        }
    }

    override suspend fun getExercisesForWorkout(workoutId: String): Resource<List<Exercise>> {
        return try {
            val response = api.getExercisesForWorkout(workoutId = workoutId)
            checkResult(response)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error")
        }
    }

    override suspend fun moveExercise(up: Boolean, id: String): Resource<Boolean?> {
        return try {
            val response = api.moveExercises(up = up, id = id)
            checkResult(response)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error")
        }
    }
}
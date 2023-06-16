package com.zozuliak.musclemate.presentation.main_screen

import android.nfc.Tag
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zozuliak.musclemate.data.Exercise
import com.zozuliak.musclemate.data.Workout
import com.zozuliak.musclemate.data.internet.Resource
import com.zozuliak.musclemate.data.internet.ServerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val serverRepository: ServerRepository
) : ViewModel() {

    private val currentUser = "987654321"

    private val _workoutList = mutableStateOf(listOf<Workout>())
    val workoutList: State<List<Workout>> = _workoutList

    private val _toastState = MutableLiveData<ToastState>()
    val toastState: LiveData<ToastState> = _toastState

    private val _currentWorkout = mutableStateOf<Workout?>(null)
    val currentWorkout: State<Workout?> = _currentWorkout

    private val _exerciseList = mutableStateOf(listOf<Exercise>())
    val exerciseList: State<List<Exercise>> = _exerciseList


    init {
        viewModelScope.launch {
            try {
                _workoutList.value = getWorkoutsForUser(currentUser)
                _currentWorkout.value = _workoutList.value[0]
                _exerciseList.value = getExercisesForWorkout(_currentWorkout.value?.id ?: "")
            } catch (e: Exception) {
                showToast("Error while loading")
            }
        }
    }

    private suspend fun getWorkoutsForUser(userId: String): List<Workout> {

        val response = serverRepository.getWorkoutsForUser(userId = userId)

        return if (response is Resource.Success) {
            response.data ?: listOf()
        } else {
            showToast(response.message?: "Error while loading")
            listOf()
        }
    }

    private suspend fun getExercisesForWorkout(workoutId: String): List<Exercise> {

        val response = serverRepository.getExercisesForWorkout(workoutId = workoutId)

        return if (response is Resource.Success) {
            response.data ?: listOf()
        } else {
            showToast(response.message?: "Error while loading")
            listOf()
        }
    }

    fun changeCurrentWorkout(workout: Workout) {
        _currentWorkout.value = workout
        viewModelScope.launch {
            _exerciseList.value = getExercisesForWorkout(workout.id ?: "")
        }
    }

    fun moveUp(exercise: Exercise) {
        if (exercise.position != 1) {
            viewModelScope.launch {
                serverRepository.moveExercise(true, exercise.id ?: "")
                _exerciseList.value = getExercisesForWorkout(_currentWorkout.value!!.id!!)
            }
        }
    }

    fun moveDown(exercise: Exercise) {
        if (exercise.position != _exerciseList.value.size) {
            viewModelScope.launch {
                serverRepository.moveExercise(false, exercise.id ?: "")
                _exerciseList.value = getExercisesForWorkout(_currentWorkout.value!!.id!!)
            }
        }
    }

    private fun showToast(message: String) {
        _toastState.value = ToastState(message)
    }

}

data class ToastState(val message: String)
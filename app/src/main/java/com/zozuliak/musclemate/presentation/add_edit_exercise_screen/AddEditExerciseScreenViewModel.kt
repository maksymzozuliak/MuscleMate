package com.zozuliak.musclemate.presentation.add_edit_exercise_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zozuliak.musclemate.data.Exercise
import com.zozuliak.musclemate.data.Set
import com.zozuliak.musclemate.data.internet.Resource
import com.zozuliak.musclemate.data.internet.ServerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditExerciseScreenViewModel @Inject constructor(
    private val serverRepository: ServerRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _name = mutableStateOf("")
    val name: State<String> = _name

    private val _group = mutableStateOf("")
    val group: State<String> = _group

    private val _toastMessage = mutableStateOf("")
    val toastMessage: State<String> = _toastMessage

    private val _restMinutes = mutableStateOf(2)
    val restMinutes: State<Int> = _restMinutes

    private val _restSeconds = mutableStateOf(0)
    val restSeconds: State<Int> = _restSeconds

    private val _isUnsaved = mutableStateOf(false)
    val isUnsaved: State<Boolean> = _isUnsaved

    private val currentExerciseId = mutableStateOf<String?>(null)

    private val workoutId = mutableStateOf<String>("noId")

    private val position = mutableStateOf(1)

    private val personalRecord = mutableStateOf<Set?>(null)

    private val _exerciseSets = mutableStateOf(listOf<Set>())
    val exerciseSets: State<List<Set>> = _exerciseSets

    init {

        savedStateHandle.get<String>("exerciseId")?.let { exerciseId ->
            if(exerciseId != "noId") {
                viewModelScope.launch {
                    val response = serverRepository.getExercisesById(exerciseId)
                    if (response.data != null) {
                        currentExerciseId.value = response.data.id
                        workoutId.value = response.data.workoutId
                        position.value = response.data.position
                        personalRecord.value = response.data.personalRecord
                        _name.value = response.data.name
                        _group.value = response.data.group
                        _exerciseSets.value = response.data.sets?: listOf()
                        _restMinutes.value = ((response.data.restTime ?: 120) / 60).toInt()
                        _restSeconds.value = (response.data.restTime ?: 0) % 60
                    }
                }
            }
        }
        savedStateHandle.get<Int>("position")?.let { _position ->
            if(_position != -1) {
                position.value = _position
            }
        }
        savedStateHandle.get<String>("workoutId")?.let { _workoutId ->
            if(_workoutId != "noId") {
                workoutId.value = _workoutId
            }
        }
    }

    fun onToastEnded() {
        _toastMessage.value = ""
    }

    fun changeUnsavedStatus(isUnsaved: Boolean) {
        _isUnsaved.value = isUnsaved
    }

    fun saveExercise() {
        if (_name.value.isBlank()) {
            showToast("Enter name")
        } else if (_group.value.isBlank()) {
            showToast("Select group")
        } else {
            viewModelScope.launch {
                val exercise = Exercise(
                    id = currentExerciseId.value,
                    workoutId = workoutId.value,
                    position = position.value,
                    name = _name.value,
                    sets = _exerciseSets.value,
                    group = _group.value,
                    personalRecord = personalRecord.value,
                    restTime = toSeconds(_restMinutes.value, _restSeconds.value)
                )
                lateinit var result : Resource<*>
                if (currentExerciseId.value != null) {
                    result = serverRepository.updateExercise(exercise)
                } else {
                    result = serverRepository.addExercise(exercise)
                    currentExerciseId.value = result.data?: ""
                }
                if (result is Resource.Success) {
                    showToast("Saved")
                    if (result.data is String?) {
                        currentExerciseId.value = result.data as String? ?: ""
                    }
                    _isUnsaved.value = false
                } else {
                    showToast(result.message?: "Error")
                }
            }
        }
    }

    fun changeNameText(name: String) {
        _name.value = name
        _isUnsaved.value = true
    }

    fun changeGroup(group: String) {
        _group.value = group
        _isUnsaved.value = true
    }

    fun changeRestMinutes(minutes: Int) {
        _restMinutes.value = minutes
        _isUnsaved.value = true
    }

    fun changeRestSeconds(seconds: Int) {
        _restSeconds.value = seconds
        _isUnsaved.value = true
    }

    fun addSet(position: Int?, reps: Int, weight: Int, weightExtended: Int) {
        val set = Set(repetitions = reps, weight = "$weight.$weightExtended".toFloat())
        if (position != null ) {
            val newList = _exerciseSets.value.toMutableList()
            newList[position] = set
            _exerciseSets.value = newList.toList()
        } else {
            _exerciseSets.value = _exerciseSets.value + set
        }
        _isUnsaved.value = true
    }

    fun deleteLastSet() {
        if (_exerciseSets.value.isNotEmpty()) {
            _exerciseSets.value = _exerciseSets.value.dropLast(1)
            _isUnsaved.value = true
        }
    }

    private fun showToast(message: String) {
        _toastMessage.value = message
    }

    private fun toSeconds(minutes: Int, seconds: Int) : Int {
        return minutes * 60 + seconds
    }
}

fun Float.toWeightString() : String{
    val afterComma = ((this * 100) % 100).toInt()
    return this.toInt().toString() + when (afterComma){
        25 -> " .25"
        50 -> " .5"
        75 -> " .75"
        else -> ""
    } + " kg"
}
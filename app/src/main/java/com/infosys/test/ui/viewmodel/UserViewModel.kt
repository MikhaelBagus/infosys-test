package com.infosys.test.ui.viewmodel

import androidx.lifecycle.*
import com.infosys.test.data.repository.UserRepository
import com.infosys.test.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class UiState {
    object Loading : UiState()
    data class Success(val data: List<User>) : UiState()
    data class Error(val message: String) : UiState()
}

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repo: UserRepository
) : ViewModel() {

    private val _state = MutableLiveData<UiState>()
    val state: LiveData<UiState> get() = _state

    fun fetchUsers() {
        viewModelScope.launch {
            _state.value = UiState.Loading
            try {
                val users = repo.getUsers()
                _state.value = UiState.Success(users)
            } catch (e: Exception) {
                _state.value = UiState.Error(e.message ?: "Terjadi kesalahan")
            }
        }
    }
}

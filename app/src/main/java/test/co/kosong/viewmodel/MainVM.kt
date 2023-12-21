package test.co.kosong.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import test.co.kosong.model.libur
import test.co.kosong.repository.Repository
import javax.inject.Inject

@HiltViewModel
class MainVM @Inject constructor(private val repository: Repository) : ViewModel() {

    private lateinit var disposable: Disposable


    val holidays = mutableStateOf<List<libur>>(emptyList())
    val error = mutableStateOf<String>("")

    init{
        getHolidays()
    }

    private fun getHolidays() {
        viewModelScope.launch {
            try {

                disposable = repository.getNow()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        {
                            holidays.value = it
                        }, {
                            error.value = error.value
                        }
                    )


            } catch (e: Exception) {
                // Handle error
                error.value = e.message.toString()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        if (this::disposable.isInitialized) {
            disposable.dispose()
        }
    }
}
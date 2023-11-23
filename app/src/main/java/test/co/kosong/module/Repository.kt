package test.co.kosong.module

import javax.inject.Inject

class Repository @Inject constructor(private val apiService: ApiService) {
    fun getNow() = apiService.getNow()
}
package test.co.kosong.repository

import test.co.kosong.module.ApiService
import javax.inject.Inject

class Repository @Inject constructor(private val apiService: ApiService) {
    fun getNow() = apiService.getNow()
}
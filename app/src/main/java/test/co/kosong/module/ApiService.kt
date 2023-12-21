package test.co.kosong.module

import io.reactivex.Single
import retrofit2.http.GET
import test.co.kosong.model.libur
import test.co.kosong.utils.C.NOW

interface ApiService {

    @GET(NOW)
    fun getNow(): Single<List<libur>>

    @GET(NOW)
    suspend fun getNow2(): List<libur>

    @GET(NOW)
    fun getNow3(): Single<List<libur>>
}
package test.co.kosong.repository

import android.app.Application
import test.co.kosong.viewmodel.RegisterViewModel

interface DatabaseRepository {

    fun addUserDetails(registerViewModel: RegisterViewModel, application: Application)

}
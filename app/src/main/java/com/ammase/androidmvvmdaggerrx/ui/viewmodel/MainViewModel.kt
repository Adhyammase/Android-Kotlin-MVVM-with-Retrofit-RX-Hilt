package com.ammase.androidmvvmdaggerrx.ui.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ammase.androidmvvmdaggerrx.model.SignResponseModel
import com.ammase.androidmvvmdaggerrx.repository.MainRepository
import com.ammase.androidmvvmdaggerrx.utils.Resource
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (
    val mainRepository: MainRepository,
) : ViewModel() {

    private val login = MutableLiveData<Resource<SignResponseModel>>()
    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun postLogin(body: JsonObject) {
        login.postValue(Resource.loading(null))
        compositeDisposable.add(
            mainRepository.postLogin(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    login.postValue(Resource.success(result))
                }, {
                    login.postValue(Resource.error(it.toString(), null))
                })
        )
    }

    fun getLogin(): LiveData<Resource<SignResponseModel>> {
        return login
    }


}
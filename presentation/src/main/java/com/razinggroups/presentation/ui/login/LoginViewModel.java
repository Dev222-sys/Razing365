package com.razinggroups.presentation.ui.login;

import com.razinggroups.domain.model.Credentials;
import com.razinggroups.domain.model.Login;
import com.razinggroups.domain.usecases.GetCredentialsUseCase;
import com.razinggroups.domain.usecases.LoginUseCase;
import com.razinggroups.domain.usecases.SetCredentialsUseCase;
import com.razinggroups.presentation.base.BaseViewModel;

import io.reactivex.observers.DisposableSingleObserver;

public class LoginViewModel extends BaseViewModel<LoginNavigator> {

    LoginUseCase loginUseCase;
    SetCredentialsUseCase setCredentialsUseCase;
    GetCredentialsUseCase getCredentialsUseCase;

    public LoginViewModel(LoginUseCase loginUseCase, SetCredentialsUseCase setCredentialsUseCase, GetCredentialsUseCase getCredentialsUseCase) {
        this.loginUseCase = loginUseCase;
        this.setCredentialsUseCase = setCredentialsUseCase;
        this.getCredentialsUseCase = getCredentialsUseCase;
    }

    public void login(String username, String password) {
        loginUseCase.execute(new DisposableSingleObserver<Login>() {
            @Override
            public void onSuccess(Login login) {
                if(getNavigator()!=null)
                    getNavigator().onLoginSuccess(login);
            }

            @Override
            public void onError(Throwable e) {
                if(getNavigator()!=null)
                    getNavigator().onError(e.toString());
            }
        }, LoginUseCase.Params.loginUseCase(username, password));
    }

    public void setCred(String username, String password) {
        setCredentialsUseCase.execute(new DisposableSingleObserver<Boolean>() {
            @Override
            public void onSuccess(Boolean aBoolean) {
            }
            @Override
            public void onError(Throwable e) {

            }
        }, SetCredentialsUseCase.Params.SetCredentialsUseCase(username, password));
    }

    public void getCred()
    {
        getCredentialsUseCase.execute(new DisposableSingleObserver<Credentials>() {
            @Override
            public void onSuccess(Credentials credentials) {
                if(getNavigator()!=null)
                    getNavigator().onCredLoaded(credentials);
            }

            @Override
            public void onError(Throwable e) {

            }
        }, GetCredentialsUseCase.Params.GetCredentialsUseCase());
    }
}

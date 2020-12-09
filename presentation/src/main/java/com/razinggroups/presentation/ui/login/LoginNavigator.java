package com.razinggroups.presentation.ui.login;

import com.razinggroups.domain.model.Credentials;
import com.razinggroups.domain.model.Login;

public interface LoginNavigator {
    void onLoginSuccess(Login login);

    void onError(String toString);

    void onCredLoaded(Credentials credentials);
}

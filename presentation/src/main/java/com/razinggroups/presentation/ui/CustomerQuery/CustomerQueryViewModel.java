package com.razinggroups.presentation.ui.CustomerQuery;
import com.razinggroups.domain.model.CustomerQuery.Customer;
import com.razinggroups.domain.usecases.CustomerQueryUserCase;
import com.razinggroups.presentation.base.BaseViewModel;
import io.reactivex.observers.DisposableSingleObserver;

public class CustomerQueryViewModel extends BaseViewModel<CustomerQueryNavigaor> {
    CustomerQueryUserCase customerQueryUserCase;

    public CustomerQueryViewModel(CustomerQueryUserCase customerQueryUserCase) {
        this.customerQueryUserCase = customerQueryUserCase;
    }
    public void customer(String username, String password) {
        customerQueryUserCase.execute(new DisposableSingleObserver<Customer>() {
            @Override
            public void onSuccess(Customer customer) {
                if(getNavigator()!=null)
                    getNavigator().onLoginSuccess(customer);
            }

            @Override
            public void onError(Throwable e) {
                if(getNavigator()!=null)
                    getNavigator().onError(e.toString());
            }
        }, CustomerQueryUserCase.Params.customerQueryUserCase(username, password));
    }

}

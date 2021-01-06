package com.razinggroups.presentation.ui.CustomerQuery;
import com.razinggroups.domain.model.CustomerQuery.Customer;
import com.razinggroups.domain.model.employee.EmployeeDetail;
import com.razinggroups.domain.usecases.CustomerQueryUserCase;
import com.razinggroups.domain.usecases.UpdateEmployeeUseCase;
import com.razinggroups.presentation.base.BaseViewModel;
import io.reactivex.observers.DisposableSingleObserver;
import  com.razinggroups.domain.model.Message;

public class CustomerQueryViewModel extends BaseViewModel<CustomerQueryNavigaor> {
    CustomerQueryUserCase customerQueryUserCase;

    public CustomerQueryViewModel(CustomerQueryUserCase customerQueryUserCase) {
        this.customerQueryUserCase = customerQueryUserCase;
    }




    public void CustomerQuery(Customer customer) {
        customerQueryUserCase.execute(new DisposableSingleObserver<com.razinggroups.domain.model.Message>() {
            @Override
            public void onSuccess(Message message) {
                if(getNavigator()!=null)
                    getNavigator().onQuerySubmittionResponse(message.getMessage());
            }

            @Override
            public void onError(Throwable e) {
                if(getNavigator()!=null)
                    getNavigator().onError(e.getMessage());
            }
        }, CustomerQueryUserCase.Params.CustomerQueryUseCase(customer));
    }

}

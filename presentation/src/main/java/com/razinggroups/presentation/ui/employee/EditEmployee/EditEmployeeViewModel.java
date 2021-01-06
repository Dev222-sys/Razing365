package com.razinggroups.presentation.ui.employee.EditEmployee;

import com.razinggroups.presentation.base.BaseViewModel;
import com.razinggroups.domain.model.Message;
import com.razinggroups.domain.model.brand.FetchAllBrandDetailsResponse;
import com.razinggroups.domain.model.employee.EmployeeDetail;
import com.razinggroups.domain.usecases.DeleteUseCase;
import com.razinggroups.domain.usecases.FetchAllBrandDetailsUseCase;
import com.razinggroups.domain.usecases.FetchSingleEmployee;
import com.razinggroups.domain.usecases.UpdateEmployeeUseCase;

import io.reactivex.observers.DisposableSingleObserver;

public class EditEmployeeViewModel extends BaseViewModel<EditEmployeeNavigator> {

   FetchSingleEmployee fetchSingleEmployee;
   UpdateEmployeeUseCase updateEmployeeUseCase;
   DeleteUseCase deleteUseCase;
   FetchAllBrandDetailsUseCase fetchAllBrandDetailsUseCase;

    public EditEmployeeViewModel(FetchSingleEmployee fetchAllEmployeesUseCase,UpdateEmployeeUseCase updateEmployeeUseCase, DeleteUseCase deleteUseCase, FetchAllBrandDetailsUseCase fetchAllBrandDetailsUseCase) {


        this.fetchSingleEmployee = fetchAllEmployeesUseCase;
        this.updateEmployeeUseCase = updateEmployeeUseCase;
        this.deleteUseCase = deleteUseCase;
        this.fetchAllBrandDetailsUseCase = fetchAllBrandDetailsUseCase;
    }

    public void fetchData(long id) {
        fetchSingleEmployee.execute(new DisposableSingleObserver<com.razinggroups.domain.model.employee.EmployeeDetail>() {
            @Override
            public void onSuccess(com.razinggroups.domain.model.employee.EmployeeDetail employeeList) {
                if(getNavigator()!=null)
                    getNavigator().onDataLoaded(employeeList);
            }

            @Override
            public void onError(Throwable e) {
                if(getNavigator()!=null)
                    getNavigator().onError(e.getMessage());

            }
        }, FetchSingleEmployee.Params.FetchSingleEmployee(id));
    }

    public void updateEmployee(EmployeeDetail employeeDetail) {
        updateEmployeeUseCase.execute(new DisposableSingleObserver<Message>() {
            @Override
            public void onSuccess(Message message) {
                if(getNavigator()!=null)
                    getNavigator().onUpdate(message.getMessage());
            }
            @Override
            public void onError(Throwable e) {
                if(getNavigator()!=null)
                    getNavigator().onError(e.getMessage());
            }
        }, UpdateEmployeeUseCase.Params.UpdateEmployeeUseCase(employeeDetail));
    }

    public void deleteEmployee(String id) {
        deleteUseCase.execute(new DisposableSingleObserver<com.razinggroups.domain.model.Message>() {
            @Override
            public void onSuccess(Message message) {
                if(getNavigator()!=null)
                    getNavigator().onUpdate(message.getMessage());
            }

            @Override
            public void onError(Throwable e) {
                if(getNavigator()!=null)
                    getNavigator().onError(e.getMessage());

            }
        }, DeleteUseCase.Params.DeleteUseCase(id
                , "employee"));
    }
    public void fetchBrand()
    {
        fetchAllBrandDetailsUseCase.execute(new DisposableSingleObserver<com.razinggroups.domain.model.brand.FetchAllBrandDetailsResponse>() {
            @Override
            public void onSuccess(FetchAllBrandDetailsResponse fetchAllBrandDetailsResponse) {
                if(getNavigator()!=null)
                    getNavigator().onBrandLoaded(fetchAllBrandDetailsResponse);
            }

            @Override
            public void onError(Throwable e) {
                if(getNavigator()!=null)
                    getNavigator().onError(e.getMessage());
            }
        }, FetchAllBrandDetailsUseCase.Params.FetchAllBrandDetailsUseCase());
    }

}

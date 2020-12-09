package com.razinggroups.presentation.ui.createTask;

import com.razinggroups.presentation.base.BaseViewModel;
import com.razinggroups.domain.model.Message;
import com.razinggroups.domain.model.brand.FetchAllBrandDetailsResponse;
import com.razinggroups.domain.model.employee.EmployeeList;
import com.razinggroups.domain.model.employeeTask.CreateEmployeeTaskRequest;
import com.razinggroups.domain.usecases.CreateTaskUseCase;
import com.razinggroups.domain.usecases.FetchAllBrandDetailsUseCase;
import com.razinggroups.domain.usecases.FetchAllEmployeesUseCase;

import io.reactivex.observers.DisposableSingleObserver;

public class CreateTaskViewModel extends BaseViewModel<CreateTaskNavigator> {

    com.razinggroups.domain.usecases.FetchAllBrandDetailsUseCase fetchAllBrandDetailsUseCase;
    com.razinggroups.domain.usecases.FetchAllEmployeesUseCase fetchAllEmployeesUseCase;
    com.razinggroups.domain.usecases.CreateTaskUseCase createTaskUseCase;

    public CreateTaskViewModel(com.razinggroups.domain.usecases.FetchAllBrandDetailsUseCase fetchAllBrandDetailsUseCase, com.razinggroups.domain.usecases.FetchAllEmployeesUseCase fetchAllEmployeesUseCase, com.razinggroups.domain.usecases.CreateTaskUseCase createTaskUseCase) {
        this.fetchAllBrandDetailsUseCase = fetchAllBrandDetailsUseCase;
        this.fetchAllEmployeesUseCase = fetchAllEmployeesUseCase;
        this.createTaskUseCase = createTaskUseCase;
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
    public void fetchEmployees()
    {
        fetchAllEmployeesUseCase.execute(new DisposableSingleObserver<com.razinggroups.domain.model.employee.EmployeeList>() {
            @Override
            public void onSuccess(EmployeeList employeeList) {
                if(getNavigator()!=null)
                    getNavigator().onEmployeesLoaded(employeeList);
            }

            @Override
            public void onError(Throwable e) {
                if(getNavigator()!=null)
                    getNavigator().onError(e.getMessage());
            }
        }, FetchAllEmployeesUseCase.Params.fetchAllEmployessUSeCase("all"));
    }
    public void createTask(CreateEmployeeTaskRequest request)
    {
        createTaskUseCase.execute(new DisposableSingleObserver<com.razinggroups.domain.model.Message>() {
            @Override
            public void onSuccess(Message message) {
                if(getNavigator()!=null)
                    getNavigator().onCreateResponse(message.getMessage());
            }

            @Override
            public void onError(Throwable e) {
                if(getNavigator()!=null)
                    getNavigator().onError(e.getMessage());
            }
        }, CreateTaskUseCase.Params.CreateTaskUseCase("employee",null,request));
    }
}

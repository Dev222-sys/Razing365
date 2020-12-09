package com.razinggroups.presentation.ui.brandCompany.CompanyManipulateScreen;

import com.razinggroups.presentation.base.BaseViewModel;
import com.razinggroups.domain.model.Message;
import com.razinggroups.domain.model.company.CreateCompanyRequest;
import com.razinggroups.domain.model.company.FetchAllCompanyDetailsResponse;
import com.razinggroups.domain.usecases.DeleteUseCase;
import com.razinggroups.domain.usecases.FetchAllCompanyDetailsUsecase;
import com.razinggroups.domain.usecases.ManipulateCompany;

import io.reactivex.observers.DisposableSingleObserver;

public class CompanyManipulateViewModel  extends BaseViewModel<CompanyManipulateNavigator> {

    com.razinggroups.domain.usecases.ManipulateCompany manipulateCompany;
    com.razinggroups.domain.usecases.FetchAllCompanyDetailsUsecase fetchAllCompanyDetailsUsecase;
    com.razinggroups.domain.usecases.DeleteUseCase deleteUseCase;

    public CompanyManipulateViewModel(com.razinggroups.domain.usecases.ManipulateCompany manipulateCompany, com.razinggroups.domain.usecases.FetchAllCompanyDetailsUsecase fetchAllCompanyDetailsUsecase, com.razinggroups.domain.usecases.DeleteUseCase deleteUseCase) {
        this.manipulateCompany = manipulateCompany;
        this.fetchAllCompanyDetailsUsecase = fetchAllCompanyDetailsUsecase;
        this.deleteUseCase = deleteUseCase;
    }

    public void fetchData() {
        fetchAllCompanyDetailsUsecase.execute(new DisposableSingleObserver<com.razinggroups.domain.model.company.FetchAllCompanyDetailsResponse>() {
            @Override
            public void onSuccess(FetchAllCompanyDetailsResponse fetchAllCompanyDetailsResponse) {
                if(getNavigator()!=null)
                    getNavigator().onDataloaded(fetchAllCompanyDetailsResponse);
            }

            @Override
            public void onError(Throwable e) {
                if(getNavigator()!=null)
                    getNavigator().onError(e.getMessage());
            }
        }, FetchAllCompanyDetailsUsecase.Params.FetchAllCompanyDetailsUsecase());
    }

    public void manipulate(CreateCompanyRequest request, String type) {
        manipulateCompany.execute(new DisposableSingleObserver<com.razinggroups.domain.model.Message>() {
            @Override
            public void onSuccess(com.razinggroups.domain.model.Message message) {
                if(getNavigator()!=null)
                    getNavigator().onUpdate(message);
            }

            @Override
            public void onError(Throwable e) {
                if(getNavigator()!=null)
                    getNavigator().onError(e.getMessage());
            }
        }, ManipulateCompany.Params.ManipulateCompany(request, type));
    }
    public void delete(String id)
    {
        deleteUseCase.execute(new DisposableSingleObserver<com.razinggroups.domain.model.Message>() {
            @Override
            public void onSuccess(Message message) {
                if(getNavigator()!=null)
                    getNavigator().onUpdate(message);

            }

            @Override
            public void onError(Throwable e) {
                if(getNavigator()!=null)
                    getNavigator().onError(e.getMessage());

            }
        }, DeleteUseCase.Params.DeleteUseCase(id,"company"));
    }
}

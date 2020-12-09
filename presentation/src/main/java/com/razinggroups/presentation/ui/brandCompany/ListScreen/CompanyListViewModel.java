package com.razinggroups.presentation.ui.brandCompany.ListScreen;

import com.razinggroups.presentation.base.BaseViewModel;
import com.razinggroups.domain.model.brand.FetchAllBrandDetailsResponse;
import com.razinggroups.domain.model.company.FetchAllCompanyDetailsResponse;
import com.razinggroups.domain.usecases.FetchAllBrandDetailsUseCase;
import com.razinggroups.domain.usecases.FetchAllCompanyDetailsUsecase;

import io.reactivex.observers.DisposableSingleObserver;

public class CompanyListViewModel extends BaseViewModel<CompanyListNavigator> {

    com.razinggroups.domain.usecases.FetchAllCompanyDetailsUsecase fetchAllCompanyDetailsUsecase;
    com.razinggroups.domain.usecases.FetchAllBrandDetailsUseCase fetchAllBrandDetailsUseCase;


    public CompanyListViewModel(com.razinggroups.domain.usecases.FetchAllCompanyDetailsUsecase fetchAllCompanyDetailsUsecase, com.razinggroups.domain.usecases.FetchAllBrandDetailsUseCase fetchAllBrandDetailsUseCase) {
        this.fetchAllCompanyDetailsUsecase = fetchAllCompanyDetailsUsecase;
        this.fetchAllBrandDetailsUseCase = fetchAllBrandDetailsUseCase;
    }

    public void fetchCompanyData() {
        fetchAllCompanyDetailsUsecase.execute(new DisposableSingleObserver<com.razinggroups.domain.model.company.FetchAllCompanyDetailsResponse>() {
            @Override
            public void onSuccess(FetchAllCompanyDetailsResponse fetchAllCompanyDetailsResponse) {
                if(getNavigator()!=null)
                    getNavigator().onCompanyDataLoaded(fetchAllCompanyDetailsResponse);
            }

            @Override
            public void onError(Throwable e) {
                if(getNavigator()!=null)
                    getNavigator().onError(e.getMessage());

            }
        }, FetchAllCompanyDetailsUsecase.Params.FetchAllCompanyDetailsUsecase());
    }

    public void fetchBrandData() {
        fetchAllBrandDetailsUseCase.execute(new DisposableSingleObserver<com.razinggroups.domain.model.brand.FetchAllBrandDetailsResponse>() {
            @Override
            public void onSuccess(FetchAllBrandDetailsResponse fetchAllBrandDetailsResponse) {
                if(getNavigator()!=null)
                    getNavigator().getBrandDataLoaded(fetchAllBrandDetailsResponse);
            }

            @Override
            public void onError(Throwable e) {
                if(getNavigator()!=null)
                    getNavigator().onError(e.getMessage());
            }
        }, FetchAllBrandDetailsUseCase.Params.FetchAllBrandDetailsUseCase());
    }
}

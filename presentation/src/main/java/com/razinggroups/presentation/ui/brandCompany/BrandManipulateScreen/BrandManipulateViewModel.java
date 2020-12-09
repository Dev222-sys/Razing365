package com.razinggroups.presentation.ui.brandCompany.BrandManipulateScreen;

import com.razinggroups.presentation.base.BaseViewModel;
import com.razinggroups.domain.model.Message;
import com.razinggroups.domain.model.brand.CreateBrandRequest;
import com.razinggroups.domain.model.brand.FetchAllBrandDetailsResponse;
import com.razinggroups.domain.model.company.FetchAllCompanyDetailsResponse;
import com.razinggroups.domain.usecases.DeleteUseCase;
import com.razinggroups.domain.usecases.FetchAllBrandDetailsUseCase;
import com.razinggroups.domain.usecases.FetchAllCompanyDetailsUsecase;
import com.razinggroups.domain.usecases.ManipulateBrand;

import io.reactivex.observers.DisposableSingleObserver;

public class BrandManipulateViewModel extends BaseViewModel<BrandManipulateNavigator> {

    com.razinggroups.domain.usecases.ManipulateBrand manipulateBrand;
    com.razinggroups.domain.usecases.FetchAllBrandDetailsUseCase fetchAllBrandDetailsUseCase;
    com.razinggroups.domain.usecases.DeleteUseCase deleteUseCase;
    com.razinggroups.domain.usecases.FetchAllCompanyDetailsUsecase fetchAllCompanyDetailsUsecase;


    public BrandManipulateViewModel(com.razinggroups.domain.usecases.ManipulateBrand manipulateBrand, com.razinggroups.domain.usecases.FetchAllBrandDetailsUseCase fetchAllBrandDetailsUseCase, com.razinggroups.domain.usecases.DeleteUseCase deleteUseCase, com.razinggroups.domain.usecases.FetchAllCompanyDetailsUsecase fetchAllCompanyDetailsUsecase) {
        this.manipulateBrand = manipulateBrand;
        this.fetchAllBrandDetailsUseCase = fetchAllBrandDetailsUseCase;
        this.deleteUseCase = deleteUseCase;
        this.fetchAllCompanyDetailsUsecase = fetchAllCompanyDetailsUsecase;
    }

    public void fetchData() {
        fetchAllBrandDetailsUseCase.execute(new DisposableSingleObserver<com.razinggroups.domain.model.brand.FetchAllBrandDetailsResponse>() {
            @Override
            public void onSuccess(FetchAllBrandDetailsResponse fetchAllBrandDetailsResponse) {
                if(getNavigator()!=null)
                getNavigator().onDataloaded(fetchAllBrandDetailsResponse);
            }

            @Override
            public void onError(Throwable e) {
                if(getNavigator()!=null)
                    getNavigator().onError(e.getMessage());
            }
        }, FetchAllBrandDetailsUseCase.Params.FetchAllBrandDetailsUseCase());
    }

    public void manipulate(CreateBrandRequest request, String type) {
        manipulateBrand.execute(new DisposableSingleObserver<com.razinggroups.domain.model.Message>() {
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
        }, ManipulateBrand.Params.ManipulateBrand(request, type));
    }

    public void delete(String id) {
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
        }, DeleteUseCase.Params.DeleteUseCase(id, "brand"));
    }
    public void fetchCompany()
    {
        fetchAllCompanyDetailsUsecase.execute(new DisposableSingleObserver<com.razinggroups.domain.model.company.FetchAllCompanyDetailsResponse>() {
            @Override
            public void onSuccess(FetchAllCompanyDetailsResponse fetchAllCompanyDetailsResponse) {
                if(getNavigator()!=null)
                    getNavigator().onCompanyloaded(fetchAllCompanyDetailsResponse);
            }

            @Override
            public void onError(Throwable e) {
                if(getNavigator()!=null)
                    getNavigator().onError(e.getMessage());

            }
        }, FetchAllCompanyDetailsUsecase.Params.FetchAllCompanyDetailsUsecase());
    }
}

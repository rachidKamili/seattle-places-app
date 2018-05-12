package me.kamili.rachid.seattleplace.view.base;

import javax.inject.Inject;

public class BasePresenter<V extends BaseView> {

    @Inject
    protected V mView;

    protected V getView() {
        return mView;
    }

    protected void dettachView() {
        mView = null;
    }

}

package com.development.simplestockmanager.web.controller.search.entity;

import com.development.simplestockmanager.business.object.nullpackage.ProviderNull;
import com.development.simplestockmanager.business.persistence.Provider;
import com.development.simplestockmanager.common.constant.WebConstant;
import com.development.simplestockmanager.web.service.general.NavigationService;
import com.development.simplestockmanager.common.web.controller.common.entity.ProviderCommonController;
import com.development.simplestockmanager.common.web.controller.base.SearchController;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Search view controller class for Provider object
 *
 * @author foxtrot
 */
@ManagedBean(name = "providerSearch")
@ViewScoped
public class ProviderSearchController extends ProviderCommonController implements SearchController {

    private Provider browser;
    private List<Provider> list;

    public ProviderSearchController() {
        super(WebConstant.VALIDATOR.MODE.SEARCH);
        clear();
    }

    @Override
    public void search() {
        list = specificController.findAllForBrowser(browser, status, createdDateFrom, createdDateTo, lastModifiedDateFrom, lastModifiedDateTo,
                createdUser.getSelectedValue().getId(), lastModifiedUser.getSelectedValue().getId());
    }

    @Override
    public final void clear() {
        browser = new ProviderNull();
        list = new ArrayList<>();
        status = WebConstant.STATUS.INDETERMINATE;
        
        super.clear();
    }
    
    public void initView(Provider provider) {
        this.provider = provider;
    }
    
    public void initEdit(Provider provider) {
        sendObjectToSession(WebConstant.SESSION.PROVIDER, provider);
        new NavigationService().redirect(WebConstant.WEB.EDIT.ENTITY.PROVIDER);
    }

    public List<Provider> getList() {
        return list;
    }

    public Provider getBrowser() {
        return browser;
    }

    public void setBrowser(Provider browser) {
        this.browser = browser;
    }

}

package com.development.simplestockmanager.web.controller.search.entity;

import com.development.simplestockmanager.business.object.nullpackage.BrandNull;
import com.development.simplestockmanager.business.persistence.Brand;
import com.development.simplestockmanager.common.constant.WebConstant;
import com.development.simplestockmanager.web.service.general.NavigationService;
import com.development.simplestockmanager.common.web.controller.common.entity.BrandCommonController;
import com.development.simplestockmanager.common.web.controller.base.SearchController;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Search view controller class for Brand object
 *
 * @author foxtrot
 */
@ManagedBean(name = "brandSearch")
@ViewScoped
public class BrandSearchController extends BrandCommonController implements SearchController {

    private Brand browser;
    private List<Brand> list;

    public BrandSearchController() {
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
        browser = new BrandNull();
        list = new ArrayList<>();
        status = WebConstant.STATUS.INDETERMINATE;
        
        super.clear();
    }

    public void initView(Brand brand) {
        this.brand = brand;
    }

    public void initEdit(Brand brand) {
        sendObjectToSession(WebConstant.SESSION.BRAND, brand);
        new NavigationService().redirect(WebConstant.WEB.EDIT.ENTITY.BRAND);
    }
    
    public List<Brand> getList() {
        return list;
    }

    public Brand getBrowser() {
        return browser;
    }

    public void setBrowser(Brand browser) {
        this.browser = browser;
    }

}

package com.development.simplestockmanager.web.controller.edit.entity;

import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.persistence.Product;
import com.development.simplestockmanager.common.constant.CommonConstant;
import com.development.simplestockmanager.common.constant.WebConstant;
import com.development.simplestockmanager.web.service.general.NavigationService;
import com.development.simplestockmanager.common.web.controller.common.entity.ProductCommonController;
import com.development.simplestockmanager.common.web.controller.base.EditController;
import com.development.simplestockmanager.web.object.selector.entity.BrandSelector;
import com.development.simplestockmanager.web.object.selector.entity.ProviderSelector;
import com.development.simplestockmanager.web.object.selector.type.ProductTypeSelector;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Edit view controller class for Product object
 *
 * @author foxtrot
 */
@ManagedBean(name = "productEdit")
@ViewScoped
public class ProductEditController extends ProductCommonController implements EditController {

    private Product baseProduct;

    public ProductEditController() {
        super(WebConstant.VALIDATOR.MODE.EDIT);
        
        try {
            product = (Product) receiveObjectFromSession(WebConstant.SESSION.PRODUCT);
            baseProduct = new Product(product);
        } catch (Exception e) {
            back();
        }
        
        productTypeSelector = new ProductTypeSelector(WebConstant.SELECTOR.MODE.ENABLE, product.getProductType());
        brandSelector = new BrandSelector(WebConstant.SELECTOR.MODE.ENABLE, product.getBrand());
        providerSelector = new ProviderSelector(WebConstant.SELECTOR.MODE.ENABLE, product.getProvider());
    }
    
    @Override
    public void edit() {
        product.setProductType(productTypeSelector.getSelectedValue());
        product.setBrand(brandSelector.getSelectedValue());
        product.setProvider(providerSelector.getSelectedValue());
        
        if (product.equals(baseProduct)) {
            action = true;
            severity = FacesMessage.SEVERITY_INFO;
            summary = messageService.getSummary(CommonConstant.MESSAGE.SUMMARY.INFO);
            detail = messageService.getDetail(CommonConstant.ENTITY.PRODUCT, product.getId(), CommonConstant.MESSAGE.DETAIL.INFO.NONE);
            
            getContext().addMessage(null, new FacesMessage(severity, summary, detail));
        } else {
            validator.setObject(product);

            if (validator.validate()) {
                product.setLastModifiedDate(new Date());
                product.setLastModifiedUser(user);

                Long feedback = generalController.update(product);

                if (feedback == BusinessConstant.UPDATE.FAILURE) {
                    severity = FacesMessage.SEVERITY_FATAL;
                    summary = messageService.getSummary(CommonConstant.MESSAGE.SUMMARY.FATAL);
                    detail = messageService.getDetail(CommonConstant.MESSAGE.DETAIL.FATAL.DATABASE);
                } else {
                    action = true;
                    severity = FacesMessage.SEVERITY_INFO;
                    summary = messageService.getSummary(CommonConstant.MESSAGE.SUMMARY.INFO);
                    detail = messageService.getDetail(CommonConstant.ENTITY.PRODUCT, product.getId(), CommonConstant.MESSAGE.DETAIL.INFO.EDIT);
                }

                getContext().addMessage(null, new FacesMessage(severity, summary, detail));
            } else {
                for (FacesMessage message : validator.getMessageList()) {
                    getContext().addMessage(null, message);
                }
            }
        }
    }
    
    @Override
    public final void back() {
        new NavigationService().redirect(WebConstant.WEB.SEARCH.ENTITY.PRODUCT);
    }
    
}

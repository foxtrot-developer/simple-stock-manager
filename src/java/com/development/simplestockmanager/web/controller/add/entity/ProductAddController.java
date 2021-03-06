package com.development.simplestockmanager.web.controller.add.entity;

import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.persistence.Product;
import com.development.simplestockmanager.common.constant.CommonConstant;
import com.development.simplestockmanager.common.constant.WebConstant;
import com.development.simplestockmanager.common.web.controller.base.AddController;
import com.development.simplestockmanager.common.web.controller.common.entity.ProductCommonController;
import com.development.simplestockmanager.web.object.selector.entity.BrandSelector;
import com.development.simplestockmanager.web.object.selector.entity.ProviderSelector;
import com.development.simplestockmanager.web.object.selector.type.ProductTypeSelector;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Search view controller class for Product object
 *
 * @author foxtrot
 */
@ManagedBean(name = "productAdd")
@ViewScoped
public class ProductAddController extends ProductCommonController implements AddController {

    public ProductAddController() {
        super(WebConstant.VALIDATOR.MODE.CREATE);
        product = new Product();
        productTypeSelector = new ProductTypeSelector(WebConstant.SELECTOR.MODE.ENABLE);
        brandSelector = new BrandSelector(WebConstant.SELECTOR.MODE.ENABLE);
        providerSelector = new ProviderSelector(WebConstant.SELECTOR.MODE.ENABLE);
    }

    @Override
    public void add() {
        product.setProductType(productTypeSelector.getSelectedValue());
        product.setBrand(brandSelector.getSelectedValue());
        product.setProvider(providerSelector.getSelectedValue());
        validator.setObject(product);
        
        if (validator.validate()) {
            product.setCreatedDate(new Date());
            product.setLastModifiedDate(new Date());
            product.setCreatedUser(user);
            product.setLastModifiedUser(user);

            Long id = generalController.create(product);

            if (id == BusinessConstant.IDENTIFIER.INVALID) {
                severity = FacesMessage.SEVERITY_FATAL;
                summary = messageService.getSummary(CommonConstant.MESSAGE.SUMMARY.FATAL);
                detail = messageService.getDetail(CommonConstant.MESSAGE.DETAIL.FATAL.DATABASE);
            } else {
                action = true;
                severity = FacesMessage.SEVERITY_INFO;
                summary = messageService.getSummary(CommonConstant.MESSAGE.SUMMARY.INFO);
                detail = messageService.getDetail(CommonConstant.ENTITY.PRODUCT, id, CommonConstant.MESSAGE.DETAIL.INFO.CREATE);
            }

            getContext().addMessage(null, new FacesMessage(severity, summary, detail));
        } else {
            for (FacesMessage message : validator.getMessageList()) {
                getContext().addMessage(null, message);
            }
        }
    }

}

package com.development.simplestockmanager.web.controller.add.entity;

import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.persistence.Employee;
import com.development.simplestockmanager.common.constant.CommonConstant;
import com.development.simplestockmanager.common.constant.WebConstant;
import com.development.simplestockmanager.common.web.controller.base.AddController;
import com.development.simplestockmanager.common.web.controller.common.entity.EmployeeCommonController;
import com.development.simplestockmanager.web.object.selector.type.EmployeeTypeSelector;
import com.development.simplestockmanager.web.object.selector.type.LanguageSelector;
import com.development.simplestockmanager.web.object.selector.type.SexTypeSelector;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Search view controller class for Employee object
 *
 * @author foxtrot
 */
@ManagedBean(name = "employeeAdd")
@ViewScoped
public class EmployeeAddController extends EmployeeCommonController implements AddController {

    public EmployeeAddController() {
        super(WebConstant.VALIDATOR.MODE.CREATE);
        employee = new Employee();
        sexTypeSelector = new SexTypeSelector(WebConstant.SELECTOR.MODE.ENABLE);
        employeeTypeSelector = new EmployeeTypeSelector(WebConstant.SELECTOR.MODE.ENABLE);
        languageSelector = new LanguageSelector(WebConstant.SELECTOR.MODE.NONE);
    }

    @Override
    public void add() {
        employee.setSexType(sexTypeSelector.getSelectedValue());
        employee.setEmployeeType(employeeTypeSelector.getSelectedValue());
        employee.setLanguage(languageSelector.getSelectedValue());
        validator.setObject(employee);
        
        if (validator.validate()) {
            employee.setCreatedDate(new Date());
            employee.setLastModifiedDate(new Date());
            employee.setCreatedUser(user);
            employee.setLastModifiedUser(user);
            employee.setLastOnlineDate(new Date(0)); 

            Long id = generalController.create(employee);

            if (id == BusinessConstant.IDENTIFIER.INVALID) {
                severity = FacesMessage.SEVERITY_FATAL;
                summary = messageService.getSummary(CommonConstant.MESSAGE.SUMMARY.FATAL);
                detail = messageService.getDetail(CommonConstant.MESSAGE.DETAIL.FATAL.DATABASE);
            } else {
                action = true;
                severity = FacesMessage.SEVERITY_INFO;
                summary = messageService.getSummary(CommonConstant.MESSAGE.SUMMARY.INFO);
                detail = messageService.getDetail(CommonConstant.ENTITY.EMPLOYEE, id, CommonConstant.MESSAGE.DETAIL.INFO.CREATE);
            }

            getContext().addMessage(null, new FacesMessage(severity, summary, detail));
        } else {
            for (FacesMessage message : validator.getMessageList()) {
                getContext().addMessage(null, message);
            }
        }
    }

}

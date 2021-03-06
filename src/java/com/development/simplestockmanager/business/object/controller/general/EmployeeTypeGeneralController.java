package com.development.simplestockmanager.business.object.controller.general;

import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.object.nullpackage.EmployeeTypeNull;
import com.development.simplestockmanager.business.object.helper.EmployeeTypeHelper;
import com.development.simplestockmanager.business.persistence.EmployeeType;
import com.development.simplestockmanager.business.persistence.controller.EmployeeTypeJpaController;
import com.development.simplestockmanager.business.persistence.controller.exceptions.IllegalOrphanException;
import com.development.simplestockmanager.business.persistence.controller.exceptions.NonexistentEntityException;

/**
 * General controller class for EmployeeType object
 *
 * @author foxtrot
 */
public class EmployeeTypeGeneralController {

    private final EmployeeTypeJpaController controller;

    public EmployeeTypeGeneralController() {
        EmployeeTypeHelper helper = new EmployeeTypeHelper();
        controller = helper.getJpaController();
    }

    public long create(EmployeeType employeeType) {
        try {
            controller.create(employeeType);
        } catch (Exception e) {
            employeeType = new EmployeeTypeNull();
        }

        return employeeType.getId();
    }

    public EmployeeType read(EmployeeType employeeType) {
        try {
            employeeType = controller.findEmployeeType(employeeType.getId());

            if (employeeType == null) {
                throw new Exception();
            }
        } catch (Exception e) {
            employeeType = new EmployeeTypeNull();
        }

        return employeeType;
    }

    public long update(EmployeeType employeeType) {
        long status;

        try {
            controller.edit(employeeType);
            status = BusinessConstant.UPDATE.SUCCESS;
        } catch (Exception e) {
            status = BusinessConstant.UPDATE.FAILURE;
        }

        return status;
    }

    public long delete(EmployeeType employeeType) {
        long status;

        try {
            controller.destroy(employeeType.getId());
            status = BusinessConstant.DELETE.SUCCESS;
        } catch (IllegalOrphanException | NonexistentEntityException e) {
            status = BusinessConstant.DELETE.FAILURE;
        }

        return status;
    }

}

package com.development.simplestockmanager.web.object.selector.entity;

import com.development.simplestockmanager.common.web.object.selector.common.CommonSelector;
import com.development.simplestockmanager.common.web.object.selector.base.BaseSelector;
import com.development.simplestockmanager.business.object.controller.specific.EmployeeSpecificController;
import com.development.simplestockmanager.business.object.nullpackage.EmployeeNull;
import com.development.simplestockmanager.business.persistence.Employee;
import com.development.simplestockmanager.common.constant.WebConstant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Selector class for Employee object
 *
 * @author foxtrot
 */
public class EmployeeSelector extends CommonSelector implements BaseSelector {

    private final EmployeeSpecificController specificController;
    private HashMap<String, Employee> hashMap;

    public EmployeeSelector(long mode) {
        super(mode);
        this.specificController = new EmployeeSpecificController();
        this.browser = "";
        search();
    }

    public EmployeeSelector(long mode, Employee employee) {
        this(mode);
        this.selection = getDisplayName(employee);
    }

    @Override
    public void search() {
        clear();
        List<Employee> employeeList;

        if (mode == WebConstant.SELECTOR.MODE.ALL) {
            employeeList = specificController.findAllByBrowser(browser);
        } else {
            employeeList = specificController.findEnableByBrowser(browser);
        }

        for (Employee employee : employeeList) {
            String key = getDisplayName(employee);
            hashMap.put(key, employee);
            list.add(key);
        }
    }

    @Override
    public void clear() {
        list = new ArrayList<>();
        hashMap = new HashMap<>();
    }
    
    public Employee getSelectedValue() {
        Employee employee = new EmployeeNull();

        if (!selection.isEmpty()) {
            employee = hashMap.get(selection);
        }

        return employee;
    }
    
    public String getDisplayName(Employee employee) {
        String name = "";
        
        if (employee != null) {
            name = "(" + employee.getUsername() + ") " + employee.getLastname() + ", " + employee.getFirstname();
        }
        
        return name;
    }
    
}

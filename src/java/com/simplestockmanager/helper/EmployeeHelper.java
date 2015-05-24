/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplestockmanager.helper;

import com.simplestockmanager.persistence.controller.EmployeeJpaController;
import javax.persistence.Query;

/**
 *
 * @author foxtrot
 */
public class EmployeeHelper {

    public static EmployeeJpaController getJpaController() {
        return new EmployeeJpaController(EntityManagerHelper.getEntityManagerFactory());
    }

    public static Query getFindByIdQuery(long id) {
        Query query = EntityManagerHelper.getEntityManager().createNamedQuery("Employee.findById");
        query.setParameter("id", id);

        return query;
    }
}

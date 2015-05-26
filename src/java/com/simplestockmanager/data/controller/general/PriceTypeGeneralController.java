/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplestockmanager.data.controller.general;

import com.simplestockmanager.constant.DeleteConstant;
import com.simplestockmanager.constant.IdentifierConstant;
import com.simplestockmanager.constant.UpdateConstant;
import com.simplestockmanager.data.nullpackage.PriceTypeNull;
import com.simplestockmanager.helper.PriceTypeHelper;
import com.simplestockmanager.persistence.PriceType;
import com.simplestockmanager.persistence.controller.PriceTypeJpaController;
import javax.persistence.Query;

/**
 * TESTED
 * @author foxtrot
 */
public class PriceTypeGeneralController {

    public static long create(String type) {
        PriceType priceType;

        Query query = PriceTypeHelper.getFindByTypeQuery(type);

        try {
            priceType = (PriceType) query.getSingleResult();
        } catch (Exception e) {
            priceType = new PriceType(type);

            try {
                PriceTypeJpaController priceTypeJpaController = PriceTypeHelper.getJpaController();
                priceTypeJpaController.create(priceType);
            } catch (Exception e2) {
                priceType = new PriceTypeNull();
            }
        }

        return priceType.getId();
    }

    public static PriceType read(long id) {
        PriceType priceType;

        try {
            Query query = PriceTypeHelper.getFindByIdQuery(id);
            priceType = (PriceType) query.getSingleResult();
        } catch (Exception e) {
            priceType = new PriceTypeNull();
        }

        return priceType;
    }

    public static long update(long id, String type) {

        long status = UpdateConstant.FAILURE;

        if (read(id).getId() != IdentifierConstant.INVALID) {
            Query query = PriceTypeHelper.getFindByTypeQuery(type);

            if (query.getResultList().isEmpty()) {
                PriceType priceType = new PriceType(id, type);

                try {
                    PriceTypeJpaController priceTypeJpaController = PriceTypeHelper.getJpaController();
                    priceTypeJpaController.edit(priceType);
                    status = UpdateConstant.SUCCESS;
                } catch (Exception e) {

                }
            }
        }

        return status;
    }

    public static long delete(long id) {

        long status = DeleteConstant.FAILURE;

        if (read(id).getId() != IdentifierConstant.INVALID) {
            try {
                PriceTypeJpaController priceTypeJpaController = PriceTypeHelper.getJpaController();
                priceTypeJpaController.destroy(id);
                status = DeleteConstant.SUCCESS;
            } catch (Exception e) {

            }
        }

        return status;
    }
}

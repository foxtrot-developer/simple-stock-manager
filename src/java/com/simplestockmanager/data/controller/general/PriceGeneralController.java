/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simplestockmanager.data.controller.general;

import com.simplestockmanager.constant.DeleteConstant;
import com.simplestockmanager.constant.IdentifierConstant;
import com.simplestockmanager.constant.UpdateConstant;
import com.simplestockmanager.data.nullpackage.PriceNull;
import com.simplestockmanager.helper.PriceHelper;
import com.simplestockmanager.persistence.Price;
import com.simplestockmanager.persistence.controller.PriceJpaController;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Query;

/**
 *
 * @author foxtrot
 */
public class PriceGeneralController {
    public static Long create(long stockID, long priceTypeID, BigDecimal cost, Date createdDate, Date lastModifiedDate, BigDecimal initialAmount, BigDecimal actualAmount,
            Date endDate, boolean isEnable) {
        
        Price price = new Price(stockID, priceTypeID, cost, createdDate, lastModifiedDate, initialAmount, actualAmount, endDate, isEnable);
        
        try {
            PriceJpaController priceJpaController = PriceHelper.getJpaController();
            priceJpaController.create(price);
        } catch (Exception e) {
            price = new PriceNull();
        }
        
        return price.getId();
    }
    
    public static Price read(Long id) {
        Price price;
        
        try {
            Query query = PriceHelper.getFindByIdQuery(id);
            price = (Price) query.getSingleResult();
        } catch (Exception e) {
            price = new PriceNull();
        }
        
        return price;
    }
    
    public static long update(Long id, long stockID, long priceTypeID, BigDecimal cost, Date createdDate, Date lastModifiedDate, BigDecimal initialAmount,
            BigDecimal actualAmount, Date endDate, boolean isEnable) {

        long state = UpdateConstant.FAILURE;

        if (read(id).getId() != IdentifierConstant.INVALID) {
            Price price = new Price(id, stockID, priceTypeID, cost, createdDate, lastModifiedDate, isEnable);

            try {
                PriceJpaController priceJpaController = PriceHelper.getJpaController();
                priceJpaController.edit(price);
                state = UpdateConstant.SUCCESS;
            } catch (Exception e) {

            }
        }

        return state;
    }

    public static long delete(Long id) {

        long state = DeleteConstant.FAILURE;

        if (read(id).getId() != IdentifierConstant.INVALID) {

            try {
                PriceJpaController priceJpaController = PriceHelper.getJpaController();
                priceJpaController.destroy(id);
                state = UpdateConstant.SUCCESS;
            } catch (Exception e) {

            }
        }

        return state;
    }
}

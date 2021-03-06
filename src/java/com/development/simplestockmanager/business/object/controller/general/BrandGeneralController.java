package com.development.simplestockmanager.business.object.controller.general;

import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.object.nullpackage.BrandNull;
import com.development.simplestockmanager.business.object.helper.BrandHelper;
import com.development.simplestockmanager.business.persistence.Brand;
import com.development.simplestockmanager.business.persistence.controller.BrandJpaController;
import com.development.simplestockmanager.business.persistence.controller.exceptions.IllegalOrphanException;
import com.development.simplestockmanager.business.persistence.controller.exceptions.NonexistentEntityException;

/**
 * General controller class for Brand object
 *
 * @author foxtrot
 */
public class BrandGeneralController {

    private final BrandJpaController controller;

    public BrandGeneralController() {
        BrandHelper helper = new BrandHelper();
        controller = helper.getJpaController();
    }

    public long create(Brand brand) {
        try {
            controller.create(brand);
        } catch (Exception e) {
            brand = new BrandNull();
        }

        return brand.getId();
    }

    public Brand read(Brand brand) {
        try {
            brand = controller.findBrand(brand.getId());

            if (brand == null) {
                throw new Exception();
            }
        } catch (Exception e) {
            brand = new BrandNull();
        }

        return brand;
    }

    
    public long update(Brand brand) {
        long status;

        try {
            controller.edit(brand);
            status = BusinessConstant.UPDATE.SUCCESS;
        } catch (Exception e) {
            System.out.println(e);
            status = BusinessConstant.UPDATE.FAILURE;
        }

        return status;
    }

    public long delete(Brand brand) {
        long status;

        try {
            controller.destroy(brand.getId());
            status = BusinessConstant.DELETE.SUCCESS;
        } catch (IllegalOrphanException | NonexistentEntityException e) {
            status = BusinessConstant.DELETE.FAILURE;
        }

        return status;
    }
    
}

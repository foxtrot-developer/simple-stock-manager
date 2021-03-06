package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.common.business.helper.common.CommonHelper;
import com.development.simplestockmanager.common.business.helper.base.BaseTypeHelper;
import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.persistence.controller.PriceTypeJpaController;
import com.development.simplestockmanager.common.constant.WebConstant;
import java.util.Date;
import javax.persistence.Query;

/**
 * Helper class for PriceType object
 *
 * @author foxtrot
 */
public class PriceTypeHelper extends CommonHelper implements BaseTypeHelper {

    public PriceTypeHelper() {
        super(BusinessConstant.QUERY.PRICE_TYPE);
    }

    public PriceTypeJpaController getJpaController() {
        return new PriceTypeJpaController(entityManagerFactory);
    }

    @Override
    public Query getFindByType(String type) {
        Query query = entityManager.createNamedQuery("PriceType.findByType");
        query.setParameter("type", type);

        return query;
    }

    @Override
    public Query getFindAll() {
        Query query = entityManager.createNamedQuery("PriceType.findAll");

        return query;
    }

    @Override
    public Query getFindEnable() {
        Query query = entityManager.createNamedQuery("PriceType.findByEnable");
        query.setParameter("enable", true);

        return query;
    }
    
    public Query getFindForBrowserQuery(String type, String translation, long status, Date createdDateFrom, Date createdDateTo, Date lastModifiedDateFrom,
            Date lastModifiedDateTo, long createdUserID, long lastModifiedUserID) {
        
        String query = "SELECT distinct(p) FROM PriceType p INNER JOIN p.priceTypeTranslationList AS pt where 1 = 1"
                + (type.isEmpty() ? "" : " AND p.type LIKE '%" + type + "%'")
                + (translation.isEmpty() ? "" : " AND pt.translation LIKE '%" + translation + "%'")
                + (status == WebConstant.STATUS.HIDDEN ? " AND p.enable = FALSE" : "")
                + (status == WebConstant.STATUS.VISIBLE ? " AND p.enable = TRUE" : "")
                + getAuditoryQuery(createdDateFrom, createdDateTo, lastModifiedDateFrom, lastModifiedDateTo, createdUserID, lastModifiedUserID);
        System.out.println("# " + query);
        
        return entityManager.createQuery(query);
    }
    
}

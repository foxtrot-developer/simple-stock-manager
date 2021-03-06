package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.common.business.helper.common.CommonHelper;
import com.development.simplestockmanager.common.constant.BusinessConstant;
import com.development.simplestockmanager.business.persistence.controller.AnalyticsTimeJpaController;

/**
 * Helper class for AnalyticsTime object
 *
 * @author foxtrot
 */
public class AnalyticsTimeHelper extends CommonHelper {

    public AnalyticsTimeHelper() {
        super(BusinessConstant.QUERY.ANALYTICS_TIME);
    }

    public AnalyticsTimeJpaController getJpaController() {
        return new AnalyticsTimeJpaController(entityManagerFactory);
    }
}

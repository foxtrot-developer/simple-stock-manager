package com.development.simplestockmanager.business.object.helper;

import com.development.simplestockmanager.business.common.BusinessConstant;
import javax.persistence.Query;

/**
 * Helper class for Language object
 *
 * @author foxtrot
 */
public class LanguageHelper extends CommonHelper {

    public LanguageHelper() {
        super(BusinessConstant.QUERY.LANGUAGE);
    }

    public Query getFindAll() {
        Query query = entityManager.createNamedQuery("Language.findAll");

        return query;
    }

}

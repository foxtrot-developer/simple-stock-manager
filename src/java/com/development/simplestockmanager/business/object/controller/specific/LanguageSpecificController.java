package com.development.simplestockmanager.business.object.controller.specific;

import com.development.simplestockmanager.business.object.helper.LanguageHelper;
import com.development.simplestockmanager.business.persistence.Language;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

/**
 * Specific controller class for Language object
 *
 * @author foxtrot
 */
public class LanguageSpecificController {

    private final String language;
    private final LanguageHelper helper;

    public LanguageSpecificController(String language) {
        this.language = language;
        helper = new LanguageHelper();
    }

    public List<Language> getFindAllForSelector() {
        List<Language> list = new ArrayList<>();

        try {
            Query query = helper.getFindAllForSelector(language);
            for (Object object : query.getResultList()) {
                list.add((Language) object);
            }
        } catch (Exception e) {
            list = new ArrayList<>();
        }

        return list;
    }
}
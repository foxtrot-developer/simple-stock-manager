package com.development.simplestockmanager.common.language;

import com.development.simplestockmanager.common.constant.CommonConstant;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 *
 * @author foxtrot
 */
public class LanguageController {

    private final String language;
    private Map<String, Locale> supportedLanguages;
    private final ResourceBundle translation;

    private void initialization() {
        supportedLanguages = new HashMap();
        supportedLanguages.put("en_US", Locale.ENGLISH);
        supportedLanguages.put("es_ES", new Locale("es", "ES"));
        supportedLanguages.put("ca_ES", new Locale("ca", "ES"));
    }

    public LanguageController(String language) {
        this.language = language;
        initialization();

        translation = ResourceBundle.getBundle(CommonConstant.LANGUAGE_PATH, supportedLanguages.get(language));
    }

    public String getWord(String keyword) {
        try {
            return translation.getString(keyword);
        } catch (Exception e) {
            return "Undefined";
        }
    }

    public String getLanguage() {
        return language;
    }

}

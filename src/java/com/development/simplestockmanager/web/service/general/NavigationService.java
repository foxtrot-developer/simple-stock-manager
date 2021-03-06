package com.development.simplestockmanager.web.service.general;

import com.development.simplestockmanager.common.constant.WebConstant;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;

/**
 * Service class for menu internationalization functionality
 *
 * @author foxtrot
 */
public class NavigationService implements Serializable {

    public void redirect(String url) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(WebConstant.WEB.BASE + url);
        } catch (IOException ex) {
            Logger.getLogger(NavigationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

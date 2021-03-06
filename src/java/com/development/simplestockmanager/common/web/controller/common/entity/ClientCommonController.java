package com.development.simplestockmanager.common.web.controller.common.entity;

import com.development.simplestockmanager.common.web.controller.common.BaseCommonController;
import com.development.simplestockmanager.business.object.controller.general.ClientGeneralController;
import com.development.simplestockmanager.business.object.controller.specific.ClientSpecificController;
import com.development.simplestockmanager.business.persistence.Client;
import com.development.simplestockmanager.web.object.selector.type.SexTypeSelector;
import com.development.simplestockmanager.web.object.validator.entity.ClientValidator;

/**
 * Common controller class for Brand object
 *
 * @author foxtrot
 */
public class ClientCommonController extends BaseCommonController {
    
    protected final ClientValidator validator;
    protected final ClientGeneralController generalController;
    protected final ClientSpecificController specificController;
    
    protected Client client;
    protected SexTypeSelector sexTypeSelector;
    
    public ClientCommonController(long mode) {
        generalController = new ClientGeneralController();
        specificController = new ClientSpecificController();
        validator = new ClientValidator(mode);
    }

    public Client getClient() {
        return client;
    }

    public SexTypeSelector getSexTypeSelector() {
        return sexTypeSelector;
    }
}

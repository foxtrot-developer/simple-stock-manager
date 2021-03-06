package com.development.simplestockmanager.common.web.object.validator.base;

import java.util.List;

/**
 * Interface class for validator object
 *
 * @author foxtrot
 */
public interface BaseValidator {

    public boolean validate();
    
    public void setObject(Object object);

    public List<String> checkFields();

    public List<String> inconsistenceFields();
    
}

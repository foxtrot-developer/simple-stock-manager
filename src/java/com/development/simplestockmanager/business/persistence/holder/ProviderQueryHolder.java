package com.development.simplestockmanager.business.persistence.holder;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Query holder for Provider object
 *
 * @author foxtrot
 */
@Entity
@NamedQueries({
    @NamedQuery(
            name = "Provider.findAllByBrowser",
            query = "SELECT p FROM Provider p WHERE p.name LIKE :browser ORDER BY p.name"
    ),
    @NamedQuery(
            name = "Provider.findEnableByBrowser",
            query = "SELECT p FROM Provider p WHERE p.name LIKE :browser AND p.enable = TRUE ORDER BY p.name"
    )
})
public class ProviderQueryHolder implements Serializable {

    @Id
    private final long id = 1L;
}

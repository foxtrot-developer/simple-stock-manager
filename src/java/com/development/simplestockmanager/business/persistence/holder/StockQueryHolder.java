package com.development.simplestockmanager.business.persistence.holder;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Query holder for Product object
 *
 * @author foxtrot
 */
@Entity
@NamedQueries({
    @NamedQuery(
            name = "Stock.findByRelation",
            query = "SELECT s FROM Stock s WHERE s.store = :store AND s.product = :product")
})
public class StockQueryHolder implements Serializable {

    @Id
    private final long id = 1L;
}

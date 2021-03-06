package com.development.simplestockmanager.business.persistence;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author foxtrot
 */
@Entity
@Table(name = "PRICE_TYPE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PriceType.findAll", query = "SELECT p FROM PriceType p"),
    @NamedQuery(name = "PriceType.findById", query = "SELECT p FROM PriceType p WHERE p.id = :id"),
    @NamedQuery(name = "PriceType.findByType", query = "SELECT p FROM PriceType p WHERE p.type = :type"),
    @NamedQuery(name = "PriceType.findByEnable", query = "SELECT p FROM PriceType p WHERE p.enable = :enable"),
    @NamedQuery(name = "PriceType.findByCreatedDate", query = "SELECT p FROM PriceType p WHERE p.createdDate = :createdDate"),
    @NamedQuery(name = "PriceType.findByLastModifiedDate", query = "SELECT p FROM PriceType p WHERE p.lastModifiedDate = :lastModifiedDate")})
public class PriceType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "TYPE")
    private String type;
    @Basic(optional = false)
    @Column(name = "ENABLE")
    private boolean enable;
    @Basic(optional = false)
    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Basic(optional = false)
    @Column(name = "LAST_MODIFIED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "priceType")
    private List<Price> priceList;
    @JoinColumn(name = "CREATED_USER", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Employee createdUser;
    @JoinColumn(name = "LAST_MODIFIED_USER", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Employee lastModifiedUser;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reference")
    private List<PriceTypeTranslation> priceTypeTranslationList;

    public PriceType() {
    }

    public PriceType(Long id) {
        this.id = id;
    }

    public PriceType(PriceType priceType) {
        this.enable = priceType.enable;
        this.id = priceType.id;
        this.type = priceType.type;
        this.createdDate = priceType.createdDate;
        this.createdUser = priceType.createdUser;
        this.lastModifiedDate = priceType.lastModifiedDate;
        this.lastModifiedUser = priceType.lastModifiedUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean getEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @XmlTransient
    public List<Price> getPriceList() {
        return priceList;
    }

    public void setPriceList(List<Price> priceList) {
        this.priceList = priceList;
    }

    public Employee getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(Employee createdUser) {
        this.createdUser = createdUser;
    }

    public Employee getLastModifiedUser() {
        return lastModifiedUser;
    }

    public void setLastModifiedUser(Employee lastModifiedUser) {
        this.lastModifiedUser = lastModifiedUser;
    }

    @XmlTransient
    public List<PriceTypeTranslation> getPriceTypeTranslationList() {
        return priceTypeTranslationList;
    }

    public void setPriceTypeTranslationList(List<PriceTypeTranslation> priceTypeTranslationList) {
        this.priceTypeTranslationList = priceTypeTranslationList;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.id);
        hash = 17 * hash + Objects.hashCode(this.type);
        hash = 17 * hash + (this.enable ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PriceType other = (PriceType) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        return this.enable == other.enable;
    }

    @Override
    public String toString() {
        return "com.development.simplestockmanager.business.persistence.PriceType[ id=" + id + " ]";
    }
    
}

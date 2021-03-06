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
@Table(name = "SEX_TYPE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SexType.findAll", query = "SELECT s FROM SexType s"),
    @NamedQuery(name = "SexType.findById", query = "SELECT s FROM SexType s WHERE s.id = :id"),
    @NamedQuery(name = "SexType.findByType", query = "SELECT s FROM SexType s WHERE s.type = :type"),
    @NamedQuery(name = "SexType.findByEnable", query = "SELECT s FROM SexType s WHERE s.enable = :enable"),
    @NamedQuery(name = "SexType.findByCreatedDate", query = "SELECT s FROM SexType s WHERE s.createdDate = :createdDate"),
    @NamedQuery(name = "SexType.findByLastModifiedDate", query = "SELECT s FROM SexType s WHERE s.lastModifiedDate = :lastModifiedDate")})
public class SexType implements Serializable {
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sexType")
    private List<Employee> employeeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reference")
    private List<SexTypeTranslation> sexTypeTranslationList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sexType")
    private List<Client> clientList;
    @JoinColumn(name = "CREATED_USER", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Employee createdUser;
    @JoinColumn(name = "LAST_MODIFIED_USER", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Employee lastModifiedUser;

    public SexType() {
    }

    public SexType(Long id) {
        this.id = id;
    }

    public SexType(SexType sexType) {
        this.enable = sexType.enable;
        this.id = sexType.id;
        this.type = sexType.type;
        this.createdDate = sexType.createdDate;
        this.createdUser = sexType.createdUser;
        this.lastModifiedDate = sexType.lastModifiedDate;
        this.lastModifiedUser = sexType.lastModifiedUser;
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
    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @XmlTransient
    public List<SexTypeTranslation> getSexTypeTranslationList() {
        return sexTypeTranslationList;
    }

    public void setSexTypeTranslationList(List<SexTypeTranslation> sexTypeTranslationList) {
        this.sexTypeTranslationList = sexTypeTranslationList;
    }

    @XmlTransient
    public List<Client> getClientList() {
        return clientList;
    }

    public void setClientList(List<Client> clientList) {
        this.clientList = clientList;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.type);
        hash = 79 * hash + (this.enable ? 1 : 0);
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
        final SexType other = (SexType) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (this.enable != other.enable) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.development.simplestockmanager.business.persistence.SexType[ id=" + id + " ]";
    }
    
}

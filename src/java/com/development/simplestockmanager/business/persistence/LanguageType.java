/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.development.simplestockmanager.business.persistence;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author foxtrot
 */
@Entity
@Table(name = "LANGUAGE_TYPE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LanguageType.findAll", query = "SELECT l FROM LanguageType l"),
    @NamedQuery(name = "LanguageType.findById", query = "SELECT l FROM LanguageType l WHERE l.id = :id"),
    @NamedQuery(name = "LanguageType.findByCode", query = "SELECT l FROM LanguageType l WHERE l.code = :code"),
    @NamedQuery(name = "LanguageType.findByLanguage", query = "SELECT l FROM LanguageType l WHERE l.language = :language"),
    @NamedQuery(name = "LanguageType.findByEnable", query = "SELECT l FROM LanguageType l WHERE l.enable = :enable")})
public class LanguageType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "Code")
    private String code;
    @Basic(optional = false)
    @Column(name = "LANGUAGE")
    private String language;
    @Basic(optional = false)
    @Column(name = "ENABLE")
    private boolean enable;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "languageType")
    private List<Employee> employeeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "languageType")
    private List<PriceType> priceTypeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "languageType")
    private List<SexType> sexTypeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "languageType")
    private List<PaymentType> paymentTypeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "languageType")
    private List<EmployeeType> employeeTypeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "languageType")
    private List<ProductType> productTypeList;

    public LanguageType() {
    }

    public LanguageType(Long id) {
        this.id = id;
    }

    public LanguageType(Long id, String code, String language, boolean enable) {
        this.id = id;
        this.code = code;
        this.language = language;
        this.enable = enable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean getEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    @XmlTransient
    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @XmlTransient
    public List<PriceType> getPriceTypeList() {
        return priceTypeList;
    }

    public void setPriceTypeList(List<PriceType> priceTypeList) {
        this.priceTypeList = priceTypeList;
    }

    @XmlTransient
    public List<SexType> getSexTypeList() {
        return sexTypeList;
    }

    public void setSexTypeList(List<SexType> sexTypeList) {
        this.sexTypeList = sexTypeList;
    }

    @XmlTransient
    public List<PaymentType> getPaymentTypeList() {
        return paymentTypeList;
    }

    public void setPaymentTypeList(List<PaymentType> paymentTypeList) {
        this.paymentTypeList = paymentTypeList;
    }

    @XmlTransient
    public List<EmployeeType> getEmployeeTypeList() {
        return employeeTypeList;
    }

    public void setEmployeeTypeList(List<EmployeeType> employeeTypeList) {
        this.employeeTypeList = employeeTypeList;
    }

    @XmlTransient
    public List<ProductType> getProductTypeList() {
        return productTypeList;
    }

    public void setProductTypeList(List<ProductType> productTypeList) {
        this.productTypeList = productTypeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LanguageType)) {
            return false;
        }
        LanguageType other = (LanguageType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.development.simplestockmanager.business.persistence.LanguageType[ id=" + id + " ]";
    }
    
}

package com.development.simplestockmanager.business.persistence;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author foxtrot
 */
@Entity
@Table(name = "SEX_TYPE_TRANSLATION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SexTypeTranslation.findAll", query = "SELECT s FROM SexTypeTranslation s"),
    @NamedQuery(name = "SexTypeTranslation.findById", query = "SELECT s FROM SexTypeTranslation s WHERE s.id = :id"),
    @NamedQuery(name = "SexTypeTranslation.findByTranslation", query = "SELECT s FROM SexTypeTranslation s WHERE s.translation = :translation")})
public class SexTypeTranslation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "TRANSLATION")
    private String translation;
    @JoinColumn(name = "LANGUAGE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Language language;
    @JoinColumn(name = "REFERENCE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private SexType reference;

    public SexTypeTranslation() {
    }

    public SexTypeTranslation(Long id) {
        this.id = id;
    }

    public SexTypeTranslation(SexTypeTranslation sexTypeTranslation) {
        this.id = sexTypeTranslation.id;
        this.language = sexTypeTranslation.language;
        this.reference = sexTypeTranslation.reference;
        this.translation = sexTypeTranslation.translation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public SexType getReference() {
        return reference;
    }

    public void setReference(SexType reference) {
        this.reference = reference;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + Objects.hashCode(this.id);
        hash = 11 * hash + Objects.hashCode(this.translation);
        hash = 11 * hash + Objects.hashCode(this.language);
        hash = 11 * hash + Objects.hashCode(this.reference);
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
        final SexTypeTranslation other = (SexTypeTranslation) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.translation, other.translation)) {
            return false;
        }
        if (!Objects.equals(this.language, other.language)) {
            return false;
        }
        return Objects.equals(this.reference, other.reference);
    }

    @Override
    public String toString() {
        return "com.development.simplestockmanager.business.persistence.SexTypeTranslation[ id=" + id + " ]";
    }
    
}

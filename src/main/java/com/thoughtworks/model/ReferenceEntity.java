package com.thoughtworks.model;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@MappedSuperclass
public class ReferenceEntity implements Serializable {

    private static final long serialVersionUID = -900392466097664947L;

    @Id
    private Long id;

    @NotNull
    private String code;

    @NotNull
    private String label;

    @NotNull
    private Integer ordinal;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private DateTime effectiveTime;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private DateTime expiresTime;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private DateTime createdTime;

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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(Integer ordinal) {
        this.ordinal = ordinal;
    }

    public DateTime getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(DateTime effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public DateTime getExpiresTime() {
        return expiresTime;
    }

    public void setExpiresTime(DateTime expiresTime) {
        this.expiresTime = expiresTime;
    }

    public DateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(DateTime createdTime) {
        this.createdTime = createdTime;
    }
}


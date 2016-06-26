package com.thoughtworks.model;

import com.thoughtworks.util.RequestContext;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@MappedSuperclass
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = -6670167684578745939L;

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String referenceId = UUID.randomUUID().toString();

    @Version
    private Integer version;

    @NotNull
    private String createdBy;

    @NotNull
    /*@Temporal(TemporalType.DATE)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")*/
    private DateTime createdTime;

    private String updatedBy;

    /*@Temporal(TemporalType.DATE)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    */
    private DateTime updatedTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public DateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(DateTime createdTime) {
        this.createdTime = createdTime;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public DateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(DateTime updatedTime) {
        this.updatedTime = updatedTime;
    }

    @PrePersist
    public void beforePersist() {
        String username = RequestContext.getUsername();
        if (username == null) {
            throw new IllegalArgumentException("Cannot update a BaseEntity without a username in the RequestContext for this thread.");
        }
        setCreatedBy(username);
        setUpdatedTime(new DateTime());
    }

    @PreUpdate
    public void beforeUpdate() {
        String username = RequestContext.getUsername();
        if (username == null) {
            throw new IllegalArgumentException("Cannot update a BaseEntity without a username in the RequestContext for this thread.");
        }
        setUpdatedBy(username);
        setUpdatedTime(new DateTime());
    }

    public boolean equals(Object that) {
        if (that == null) {
            return false;
        }

        if (this.getClass().equals(that.getClass())) {
            BaseEntity thatBE = (BaseEntity) that;
            if (this.getId() == null || thatBE.getId() == null) {
                return false;
            }
            if (this.getId().equals(thatBE.getId())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        if (getId() == null) {
            return -1;
        }
        return getId().hashCode();
    }
}

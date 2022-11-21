package com.slobx.cra.infrastructure.common.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.persistence.Version;
import java.io.Serializable;
import java.util.UUID;

@Getter
@MappedSuperclass
public abstract class BaseEntity implements IEntity<Long>, Serializable {

    @Version
    @Column(name = "VERSION")
    private Long version;

    @Transient
    private String uuid = null;

    @Override
    public boolean equals(final Object obj) {
        boolean result = false;
        if (this == obj) {
            result = true;
        } else if (obj != null && (getClass().isInstance(obj) || obj.getClass().isInstance(this))) {
            final BaseEntity that = (BaseEntity) obj;
            result = getId() == null ? getKey().equals(that.getKey()) : getId().equals(that.getId());
        }
        return result;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : super.hashCode();
    }

    @Override
    public String getKey() {
        final StringBuilder sb = new StringBuilder(this.getClass().getSimpleName());
        sb.append("_");
        sb.append(getId() == null ? getUuid() : getId());
        return sb.toString();
    }

    @Transient
    private String getUuid() {
        if (uuid == null) {
            uuid = UUID.randomUUID().toString();
        }
        return uuid;
    }
}

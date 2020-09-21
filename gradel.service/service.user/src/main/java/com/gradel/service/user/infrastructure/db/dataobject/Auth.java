package com.gradel.service.user.infrastructure.db.dataobject;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author sdeven.chen.dongwei
 * @since 2020-09-06
 */
public class Auth implements Serializable {

    private static final long serialVersionUID=1L;

    private Long authId;

    private String authName;

    private Long parentAuthId;

    private String creditCode;

    private String resource;

    private Integer sort;

    private Boolean authType;

    private String createName;

    private LocalDateTime createTime;

    private String updateName;

    private LocalDateTime updateTime;

    private String description;


    public Long getAuthId() {
        return authId;
    }

    public void setAuthId(Long authId) {
        this.authId = authId;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public Long getParentAuthId() {
        return parentAuthId;
    }

    public void setParentAuthId(Long parentAuthId) {
        this.parentAuthId = parentAuthId;
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Boolean getAuthType() {
        return authType;
    }

    public void setAuthType(Boolean authType) {
        this.authType = authType;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Auth{" +
        "authId=" + authId +
        ", authName=" + authName +
        ", parentAuthId=" + parentAuthId +
        ", creditCode=" + creditCode +
        ", resource=" + resource +
        ", sort=" + sort +
        ", authType=" + authType +
        ", createName=" + createName +
        ", createTime=" + createTime +
        ", updateName=" + updateName +
        ", updateTime=" + updateTime +
        ", description=" + description +
        "}";
    }
}

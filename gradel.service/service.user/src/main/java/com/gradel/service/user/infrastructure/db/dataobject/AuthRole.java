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
public class AuthRole implements Serializable {

    private static final long serialVersionUID=1L;

    private Long roleAuthId;

    private Long roleId;

    private Long authId;

    private String creditCode;

    private String createName;

    private LocalDateTime createTime;

    private String updateName;

    private LocalDateTime updateTime;

    private String description;


    public Long getRoleAuthId() {
        return roleAuthId;
    }

    public void setRoleAuthId(Long roleAuthId) {
        this.roleAuthId = roleAuthId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getAuthId() {
        return authId;
    }

    public void setAuthId(Long authId) {
        this.authId = authId;
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
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
        return "AuthRole{" +
        "roleAuthId=" + roleAuthId +
        ", roleId=" + roleId +
        ", authId=" + authId +
        ", creditCode=" + creditCode +
        ", createName=" + createName +
        ", createTime=" + createTime +
        ", updateName=" + updateName +
        ", updateTime=" + updateTime +
        ", description=" + description +
        "}";
    }
}

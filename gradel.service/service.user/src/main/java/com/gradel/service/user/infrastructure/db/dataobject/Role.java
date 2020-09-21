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
public class Role implements Serializable {

    private static final long serialVersionUID=1L;

    private Long roleId;

    private String roleName;

    private Long parentRoleId;

    private String creditCode;

    private Integer sort;

    private String createName;

    private LocalDateTime createTime;

    private String updateName;

    private LocalDateTime updateTime;

    private String description;


    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Long getParentRoleId() {
        return parentRoleId;
    }

    public void setParentRoleId(Long parentRoleId) {
        this.parentRoleId = parentRoleId;
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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
        return "Role{" +
        "roleId=" + roleId +
        ", roleName=" + roleName +
        ", parentRoleId=" + parentRoleId +
        ", creditCode=" + creditCode +
        ", sort=" + sort +
        ", createName=" + createName +
        ", createTime=" + createTime +
        ", updateName=" + updateName +
        ", updateTime=" + updateTime +
        ", description=" + description +
        "}";
    }
}

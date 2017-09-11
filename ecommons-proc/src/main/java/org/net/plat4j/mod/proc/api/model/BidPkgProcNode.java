package org.net.plat4j.mod.proc.api.model;

import java.io.Serializable;
import java.util.Date;

public class BidPkgProcNode implements Serializable {
    private Long id;

    private Long pkgProcId;

    private Long procNodeId;

    private String stageCode;

    private String nodeCode;

    private String pkgNodeName;

    private String isRepeatable;

    private String isGrouped;

    private String groupDictVal;

    private String pkgNodeStatus;

    private String bizTableId;

    private String isDeleted;

    private Date createTime;

    private Long createUserId;

    private Date updateTime;

    private Long updateUserId;

    private String isDisplayStatus;

    private String isKeyNode;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPkgProcId() {
        return pkgProcId;
    }

    public void setPkgProcId(Long pkgProcId) {
        this.pkgProcId = pkgProcId;
    }

    public Long getProcNodeId() {
        return procNodeId;
    }

    public void setProcNodeId(Long procNodeId) {
        this.procNodeId = procNodeId;
    }

    public String getStageCode() {
        return stageCode;
    }

    public void setStageCode(String stageCode) {
        this.stageCode = stageCode;
    }

    public String getNodeCode() {
        return nodeCode;
    }

    public void setNodeCode(String nodeCode) {
        this.nodeCode = nodeCode;
    }

    public String getPkgNodeName() {
        return pkgNodeName;
    }

    public void setPkgNodeName(String pkgNodeName) {
        this.pkgNodeName = pkgNodeName;
    }

    public String getIsRepeatable() {
        return isRepeatable;
    }

    public void setIsRepeatable(String isRepeatable) {
        this.isRepeatable = isRepeatable;
    }

    public String getIsGrouped() {
        return isGrouped;
    }

    public void setIsGrouped(String isGrouped) {
        this.isGrouped = isGrouped;
    }

    public String getGroupDictVal() {
        return groupDictVal;
    }

    public void setGroupDictVal(String groupDictVal) {
        this.groupDictVal = groupDictVal;
    }

    public String getPkgNodeStatus() {
        return pkgNodeStatus;
    }

    public void setPkgNodeStatus(String pkgNodeStatus) {
        this.pkgNodeStatus = pkgNodeStatus;
    }

    public String getBizTableId() {
        return bizTableId;
    }

    public void setBizTableId(String bizTableId) {
        this.bizTableId = bizTableId;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getIsDisplayStatus() {
        return isDisplayStatus;
    }

    public void setIsDisplayStatus(String isDisplayStatus) {
        this.isDisplayStatus = isDisplayStatus;
    }

    public String getIsKeyNode() {
        return isKeyNode;
    }

    public void setIsKeyNode(String isKeyNode) {
        this.isKeyNode = isKeyNode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", pkgProcId=").append(pkgProcId);
        sb.append(", procNodeId=").append(procNodeId);
        sb.append(", stageCode=").append(stageCode);
        sb.append(", nodeCode=").append(nodeCode);
        sb.append(", pkgNodeName=").append(pkgNodeName);
        sb.append(", isRepeatable=").append(isRepeatable);
        sb.append(", isGrouped=").append(isGrouped);
        sb.append(", groupDictVal=").append(groupDictVal);
        sb.append(", pkgNodeStatus=").append(pkgNodeStatus);
        sb.append(", bizTableId=").append(bizTableId);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", createTime=").append(createTime);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateUserId=").append(updateUserId);
        sb.append(", isDisplayStatus=").append(isDisplayStatus);
        sb.append(", isKeyNode=").append(isKeyNode);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
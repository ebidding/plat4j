package org.net.plat4j.web.model;

import java.io.Serializable;
import java.util.Date;

public class SysDocFile implements Serializable {
    private String id;

    private String fileName;

    private Double fileSize;

    private String filePath;

    private String fileType;

    private Long seqId;

    private String tableName;

    private Long tableId;

    private String tableColumn;

    private String isDeleted;

    private Long createUserId;

    private Date createTime;

    private Long updateUserId;

    private Date updateTime;

    private String isFtpFile;

    private String fileNums;

    private String fileMd5;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Double getFileSize() {
        return fileSize;
    }

    public void setFileSize(Double fileSize) {
        this.fileSize = fileSize;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Long getSeqId() {
        return seqId;
    }

    public void setSeqId(Long seqId) {
        this.seqId = seqId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public String getTableColumn() {
        return tableColumn;
    }

    public void setTableColumn(String tableColumn) {
        this.tableColumn = tableColumn;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getIsFtpFile() {
        return isFtpFile;
    }

    public void setIsFtpFile(String isFtpFile) {
        this.isFtpFile = isFtpFile;
    }

    public String getFileNums() {
        return fileNums;
    }

    public void setFileNums(String fileNums) {
        this.fileNums = fileNums;
    }

    public String getFileMd5() {
        return fileMd5;
    }

    public void setFileMd5(String fileMd5) {
        this.fileMd5 = fileMd5;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", fileName=").append(fileName);
        sb.append(", fileSize=").append(fileSize);
        sb.append(", filePath=").append(filePath);
        sb.append(", fileType=").append(fileType);
        sb.append(", seqId=").append(seqId);
        sb.append(", tableName=").append(tableName);
        sb.append(", tableId=").append(tableId);
        sb.append(", tableColumn=").append(tableColumn);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateUserId=").append(updateUserId);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isFtpFile=").append(isFtpFile);
        sb.append(", fileNums=").append(fileNums);
        sb.append(", fileMd5=").append(fileMd5);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
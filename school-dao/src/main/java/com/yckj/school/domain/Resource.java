package com.yckj.school.domain;

import java.util.Date;

public class Resource {
    private Long resourceId;

    private String name;

    private String suffix;

    private String oriUrl;

    private String swfUrl;

    private Long majorId;

    private Integer type;

    private String note;

    private String userId;

    private Long fileSize;

    private Integer saveCnt;

    private Integer loadCnt;

    private Integer scoreCnt;

    private Integer score;

    private Integer viewCnt;

    private Date createTime;

    private String personVisible;

    private String statue;

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix == null ? null : suffix.trim();
    }

    public String getOriUrl() {
        return oriUrl;
    }

    public void setOriUrl(String oriUrl) {
        this.oriUrl = oriUrl == null ? null : oriUrl.trim();
    }

    public String getSwfUrl() {
        return swfUrl;
    }

    public void setSwfUrl(String swfUrl) {
        this.swfUrl = swfUrl == null ? null : swfUrl.trim();
    }

    public Long getMajorId() {
        return majorId;
    }

    public void setMajorId(Long majorId) {
        this.majorId = majorId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public Integer getSaveCnt() {
        return saveCnt;
    }

    public void setSaveCnt(Integer saveCnt) {
        this.saveCnt = saveCnt;
    }

    public Integer getLoadCnt() {
        return loadCnt;
    }

    public void setLoadCnt(Integer loadCnt) {
        this.loadCnt = loadCnt;
    }

    public Integer getScoreCnt() {
        return scoreCnt;
    }

    public void setScoreCnt(Integer scoreCnt) {
        this.scoreCnt = scoreCnt;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getViewCnt() {
        return viewCnt;
    }

    public void setViewCnt(Integer viewCnt) {
        this.viewCnt = viewCnt;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPersonVisible() {
        return personVisible;
    }

    public void setPersonVisible(String personVisible) {
        this.personVisible = personVisible == null ? null : personVisible.trim();
    }

    public String getStatue() {
        return statue;
    }

    public void setStatue(String statue) {
        this.statue = statue == null ? null : statue.trim();
    }
}
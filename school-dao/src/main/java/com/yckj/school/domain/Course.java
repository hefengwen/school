package com.yckj.school.domain;

import java.util.Date;

public class Course {
    private Long courseId;

    private String name;

    private String note;

    private String userId;

    private String picture;

    private Long saveCnt;

    private Long viewCnt;

    private Long loadCnt;

    private Integer scoreCnt;

    private Integer score;

    private Integer type;

    private Long majorId;

    private Date createTime;

    private String statue;

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }

    public Long getSaveCnt() {
        return saveCnt;
    }

    public void setSaveCnt(Long saveCnt) {
        this.saveCnt = saveCnt;
    }

    public Long getViewCnt() {
        return viewCnt;
    }

    public void setViewCnt(Long viewCnt) {
        this.viewCnt = viewCnt;
    }

    public Long getLoadCnt() {
        return loadCnt;
    }

    public void setLoadCnt(Long loadCnt) {
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getMajorId() {
        return majorId;
    }

    public void setMajorId(Long majorId) {
        this.majorId = majorId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStatue() {
        return statue;
    }

    public void setStatue(String statue) {
        this.statue = statue == null ? null : statue.trim();
    }
}
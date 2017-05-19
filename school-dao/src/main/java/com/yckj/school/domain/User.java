package com.yckj.school.domain;

import java.util.Date;

public class User {
    private String userId;

    private String name;

    private String phone;

    private String passwd;

    private Integer type;

    private String head;

    private Date createTime;

    private String teacherLevel;

    private String teacherDetail;

    private Long majorId;

    private String teacherId;

    private String locked;

    private Integer score;

    private String statue;

    private String canUpload;

    private String canDownload;

    private Long uploadLimit;

    private Long downloadLimit;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd == null ? null : passwd.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head == null ? null : head.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTeacherLevel() {
        return teacherLevel;
    }

    public void setTeacherLevel(String teacherLevel) {
        this.teacherLevel = teacherLevel == null ? null : teacherLevel.trim();
    }

    public String getTeacherDetail() {
        return teacherDetail;
    }

    public void setTeacherDetail(String teacherDetail) {
        this.teacherDetail = teacherDetail == null ? null : teacherDetail.trim();
    }

    public Long getMajorId() {
        return majorId;
    }

    public void setMajorId(Long majorId) {
        this.majorId = majorId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId == null ? null : teacherId.trim();
    }

    public String getLocked() {
        return locked;
    }

    public void setLocked(String locked) {
        this.locked = locked == null ? null : locked.trim();
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getStatue() {
        return statue;
    }

    public void setStatue(String statue) {
        this.statue = statue == null ? null : statue.trim();
    }

    public String getCanUpload() {
        return canUpload;
    }

    public void setCanUpload(String canUpload) {
        this.canUpload = canUpload == null ? null : canUpload.trim();
    }

    public String getCanDownload() {
        return canDownload;
    }

    public void setCanDownload(String canDownload) {
        this.canDownload = canDownload == null ? null : canDownload.trim();
    }

    public Long getUploadLimit() {
        return uploadLimit;
    }

    public void setUploadLimit(Long uploadLimit) {
        this.uploadLimit = uploadLimit;
    }

    public Long getDownloadLimit() {
        return downloadLimit;
    }

    public void setDownloadLimit(Long downloadLimit) {
        this.downloadLimit = downloadLimit;
    }
}
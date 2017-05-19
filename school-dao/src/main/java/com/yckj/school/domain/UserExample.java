package com.yckj.school.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("user_id like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("user_id not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPasswdIsNull() {
            addCriterion("passwd is null");
            return (Criteria) this;
        }

        public Criteria andPasswdIsNotNull() {
            addCriterion("passwd is not null");
            return (Criteria) this;
        }

        public Criteria andPasswdEqualTo(String value) {
            addCriterion("passwd =", value, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdNotEqualTo(String value) {
            addCriterion("passwd <>", value, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdGreaterThan(String value) {
            addCriterion("passwd >", value, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdGreaterThanOrEqualTo(String value) {
            addCriterion("passwd >=", value, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdLessThan(String value) {
            addCriterion("passwd <", value, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdLessThanOrEqualTo(String value) {
            addCriterion("passwd <=", value, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdLike(String value) {
            addCriterion("passwd like", value, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdNotLike(String value) {
            addCriterion("passwd not like", value, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdIn(List<String> values) {
            addCriterion("passwd in", values, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdNotIn(List<String> values) {
            addCriterion("passwd not in", values, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdBetween(String value1, String value2) {
            addCriterion("passwd between", value1, value2, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdNotBetween(String value1, String value2) {
            addCriterion("passwd not between", value1, value2, "passwd");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andHeadIsNull() {
            addCriterion("head is null");
            return (Criteria) this;
        }

        public Criteria andHeadIsNotNull() {
            addCriterion("head is not null");
            return (Criteria) this;
        }

        public Criteria andHeadEqualTo(String value) {
            addCriterion("head =", value, "head");
            return (Criteria) this;
        }

        public Criteria andHeadNotEqualTo(String value) {
            addCriterion("head <>", value, "head");
            return (Criteria) this;
        }

        public Criteria andHeadGreaterThan(String value) {
            addCriterion("head >", value, "head");
            return (Criteria) this;
        }

        public Criteria andHeadGreaterThanOrEqualTo(String value) {
            addCriterion("head >=", value, "head");
            return (Criteria) this;
        }

        public Criteria andHeadLessThan(String value) {
            addCriterion("head <", value, "head");
            return (Criteria) this;
        }

        public Criteria andHeadLessThanOrEqualTo(String value) {
            addCriterion("head <=", value, "head");
            return (Criteria) this;
        }

        public Criteria andHeadLike(String value) {
            addCriterion("head like", value, "head");
            return (Criteria) this;
        }

        public Criteria andHeadNotLike(String value) {
            addCriterion("head not like", value, "head");
            return (Criteria) this;
        }

        public Criteria andHeadIn(List<String> values) {
            addCriterion("head in", values, "head");
            return (Criteria) this;
        }

        public Criteria andHeadNotIn(List<String> values) {
            addCriterion("head not in", values, "head");
            return (Criteria) this;
        }

        public Criteria andHeadBetween(String value1, String value2) {
            addCriterion("head between", value1, value2, "head");
            return (Criteria) this;
        }

        public Criteria andHeadNotBetween(String value1, String value2) {
            addCriterion("head not between", value1, value2, "head");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andTeacherLevelIsNull() {
            addCriterion("teacher_level is null");
            return (Criteria) this;
        }

        public Criteria andTeacherLevelIsNotNull() {
            addCriterion("teacher_level is not null");
            return (Criteria) this;
        }

        public Criteria andTeacherLevelEqualTo(String value) {
            addCriterion("teacher_level =", value, "teacherLevel");
            return (Criteria) this;
        }

        public Criteria andTeacherLevelNotEqualTo(String value) {
            addCriterion("teacher_level <>", value, "teacherLevel");
            return (Criteria) this;
        }

        public Criteria andTeacherLevelGreaterThan(String value) {
            addCriterion("teacher_level >", value, "teacherLevel");
            return (Criteria) this;
        }

        public Criteria andTeacherLevelGreaterThanOrEqualTo(String value) {
            addCriterion("teacher_level >=", value, "teacherLevel");
            return (Criteria) this;
        }

        public Criteria andTeacherLevelLessThan(String value) {
            addCriterion("teacher_level <", value, "teacherLevel");
            return (Criteria) this;
        }

        public Criteria andTeacherLevelLessThanOrEqualTo(String value) {
            addCriterion("teacher_level <=", value, "teacherLevel");
            return (Criteria) this;
        }

        public Criteria andTeacherLevelLike(String value) {
            addCriterion("teacher_level like", value, "teacherLevel");
            return (Criteria) this;
        }

        public Criteria andTeacherLevelNotLike(String value) {
            addCriterion("teacher_level not like", value, "teacherLevel");
            return (Criteria) this;
        }

        public Criteria andTeacherLevelIn(List<String> values) {
            addCriterion("teacher_level in", values, "teacherLevel");
            return (Criteria) this;
        }

        public Criteria andTeacherLevelNotIn(List<String> values) {
            addCriterion("teacher_level not in", values, "teacherLevel");
            return (Criteria) this;
        }

        public Criteria andTeacherLevelBetween(String value1, String value2) {
            addCriterion("teacher_level between", value1, value2, "teacherLevel");
            return (Criteria) this;
        }

        public Criteria andTeacherLevelNotBetween(String value1, String value2) {
            addCriterion("teacher_level not between", value1, value2, "teacherLevel");
            return (Criteria) this;
        }

        public Criteria andTeacherDetailIsNull() {
            addCriterion("teacher_detail is null");
            return (Criteria) this;
        }

        public Criteria andTeacherDetailIsNotNull() {
            addCriterion("teacher_detail is not null");
            return (Criteria) this;
        }

        public Criteria andTeacherDetailEqualTo(String value) {
            addCriterion("teacher_detail =", value, "teacherDetail");
            return (Criteria) this;
        }

        public Criteria andTeacherDetailNotEqualTo(String value) {
            addCriterion("teacher_detail <>", value, "teacherDetail");
            return (Criteria) this;
        }

        public Criteria andTeacherDetailGreaterThan(String value) {
            addCriterion("teacher_detail >", value, "teacherDetail");
            return (Criteria) this;
        }

        public Criteria andTeacherDetailGreaterThanOrEqualTo(String value) {
            addCriterion("teacher_detail >=", value, "teacherDetail");
            return (Criteria) this;
        }

        public Criteria andTeacherDetailLessThan(String value) {
            addCriterion("teacher_detail <", value, "teacherDetail");
            return (Criteria) this;
        }

        public Criteria andTeacherDetailLessThanOrEqualTo(String value) {
            addCriterion("teacher_detail <=", value, "teacherDetail");
            return (Criteria) this;
        }

        public Criteria andTeacherDetailLike(String value) {
            addCriterion("teacher_detail like", value, "teacherDetail");
            return (Criteria) this;
        }

        public Criteria andTeacherDetailNotLike(String value) {
            addCriterion("teacher_detail not like", value, "teacherDetail");
            return (Criteria) this;
        }

        public Criteria andTeacherDetailIn(List<String> values) {
            addCriterion("teacher_detail in", values, "teacherDetail");
            return (Criteria) this;
        }

        public Criteria andTeacherDetailNotIn(List<String> values) {
            addCriterion("teacher_detail not in", values, "teacherDetail");
            return (Criteria) this;
        }

        public Criteria andTeacherDetailBetween(String value1, String value2) {
            addCriterion("teacher_detail between", value1, value2, "teacherDetail");
            return (Criteria) this;
        }

        public Criteria andTeacherDetailNotBetween(String value1, String value2) {
            addCriterion("teacher_detail not between", value1, value2, "teacherDetail");
            return (Criteria) this;
        }

        public Criteria andMajorIdIsNull() {
            addCriterion("major_id is null");
            return (Criteria) this;
        }

        public Criteria andMajorIdIsNotNull() {
            addCriterion("major_id is not null");
            return (Criteria) this;
        }

        public Criteria andMajorIdEqualTo(Long value) {
            addCriterion("major_id =", value, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdNotEqualTo(Long value) {
            addCriterion("major_id <>", value, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdGreaterThan(Long value) {
            addCriterion("major_id >", value, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdGreaterThanOrEqualTo(Long value) {
            addCriterion("major_id >=", value, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdLessThan(Long value) {
            addCriterion("major_id <", value, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdLessThanOrEqualTo(Long value) {
            addCriterion("major_id <=", value, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdIn(List<Long> values) {
            addCriterion("major_id in", values, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdNotIn(List<Long> values) {
            addCriterion("major_id not in", values, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdBetween(Long value1, Long value2) {
            addCriterion("major_id between", value1, value2, "majorId");
            return (Criteria) this;
        }

        public Criteria andMajorIdNotBetween(Long value1, Long value2) {
            addCriterion("major_id not between", value1, value2, "majorId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdIsNull() {
            addCriterion("teacher_id is null");
            return (Criteria) this;
        }

        public Criteria andTeacherIdIsNotNull() {
            addCriterion("teacher_id is not null");
            return (Criteria) this;
        }

        public Criteria andTeacherIdEqualTo(String value) {
            addCriterion("teacher_id =", value, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdNotEqualTo(String value) {
            addCriterion("teacher_id <>", value, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdGreaterThan(String value) {
            addCriterion("teacher_id >", value, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdGreaterThanOrEqualTo(String value) {
            addCriterion("teacher_id >=", value, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdLessThan(String value) {
            addCriterion("teacher_id <", value, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdLessThanOrEqualTo(String value) {
            addCriterion("teacher_id <=", value, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdLike(String value) {
            addCriterion("teacher_id like", value, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdNotLike(String value) {
            addCriterion("teacher_id not like", value, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdIn(List<String> values) {
            addCriterion("teacher_id in", values, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdNotIn(List<String> values) {
            addCriterion("teacher_id not in", values, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdBetween(String value1, String value2) {
            addCriterion("teacher_id between", value1, value2, "teacherId");
            return (Criteria) this;
        }

        public Criteria andTeacherIdNotBetween(String value1, String value2) {
            addCriterion("teacher_id not between", value1, value2, "teacherId");
            return (Criteria) this;
        }

        public Criteria andLockedIsNull() {
            addCriterion("locked is null");
            return (Criteria) this;
        }

        public Criteria andLockedIsNotNull() {
            addCriterion("locked is not null");
            return (Criteria) this;
        }

        public Criteria andLockedEqualTo(String value) {
            addCriterion("locked =", value, "locked");
            return (Criteria) this;
        }

        public Criteria andLockedNotEqualTo(String value) {
            addCriterion("locked <>", value, "locked");
            return (Criteria) this;
        }

        public Criteria andLockedGreaterThan(String value) {
            addCriterion("locked >", value, "locked");
            return (Criteria) this;
        }

        public Criteria andLockedGreaterThanOrEqualTo(String value) {
            addCriterion("locked >=", value, "locked");
            return (Criteria) this;
        }

        public Criteria andLockedLessThan(String value) {
            addCriterion("locked <", value, "locked");
            return (Criteria) this;
        }

        public Criteria andLockedLessThanOrEqualTo(String value) {
            addCriterion("locked <=", value, "locked");
            return (Criteria) this;
        }

        public Criteria andLockedLike(String value) {
            addCriterion("locked like", value, "locked");
            return (Criteria) this;
        }

        public Criteria andLockedNotLike(String value) {
            addCriterion("locked not like", value, "locked");
            return (Criteria) this;
        }

        public Criteria andLockedIn(List<String> values) {
            addCriterion("locked in", values, "locked");
            return (Criteria) this;
        }

        public Criteria andLockedNotIn(List<String> values) {
            addCriterion("locked not in", values, "locked");
            return (Criteria) this;
        }

        public Criteria andLockedBetween(String value1, String value2) {
            addCriterion("locked between", value1, value2, "locked");
            return (Criteria) this;
        }

        public Criteria andLockedNotBetween(String value1, String value2) {
            addCriterion("locked not between", value1, value2, "locked");
            return (Criteria) this;
        }

        public Criteria andScoreIsNull() {
            addCriterion("score is null");
            return (Criteria) this;
        }

        public Criteria andScoreIsNotNull() {
            addCriterion("score is not null");
            return (Criteria) this;
        }

        public Criteria andScoreEqualTo(Integer value) {
            addCriterion("score =", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotEqualTo(Integer value) {
            addCriterion("score <>", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThan(Integer value) {
            addCriterion("score >", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("score >=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThan(Integer value) {
            addCriterion("score <", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThanOrEqualTo(Integer value) {
            addCriterion("score <=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreIn(List<Integer> values) {
            addCriterion("score in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotIn(List<Integer> values) {
            addCriterion("score not in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreBetween(Integer value1, Integer value2) {
            addCriterion("score between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("score not between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andStatueIsNull() {
            addCriterion("statue is null");
            return (Criteria) this;
        }

        public Criteria andStatueIsNotNull() {
            addCriterion("statue is not null");
            return (Criteria) this;
        }

        public Criteria andStatueEqualTo(String value) {
            addCriterion("statue =", value, "statue");
            return (Criteria) this;
        }

        public Criteria andStatueNotEqualTo(String value) {
            addCriterion("statue <>", value, "statue");
            return (Criteria) this;
        }

        public Criteria andStatueGreaterThan(String value) {
            addCriterion("statue >", value, "statue");
            return (Criteria) this;
        }

        public Criteria andStatueGreaterThanOrEqualTo(String value) {
            addCriterion("statue >=", value, "statue");
            return (Criteria) this;
        }

        public Criteria andStatueLessThan(String value) {
            addCriterion("statue <", value, "statue");
            return (Criteria) this;
        }

        public Criteria andStatueLessThanOrEqualTo(String value) {
            addCriterion("statue <=", value, "statue");
            return (Criteria) this;
        }

        public Criteria andStatueLike(String value) {
            addCriterion("statue like", value, "statue");
            return (Criteria) this;
        }

        public Criteria andStatueNotLike(String value) {
            addCriterion("statue not like", value, "statue");
            return (Criteria) this;
        }

        public Criteria andStatueIn(List<String> values) {
            addCriterion("statue in", values, "statue");
            return (Criteria) this;
        }

        public Criteria andStatueNotIn(List<String> values) {
            addCriterion("statue not in", values, "statue");
            return (Criteria) this;
        }

        public Criteria andStatueBetween(String value1, String value2) {
            addCriterion("statue between", value1, value2, "statue");
            return (Criteria) this;
        }

        public Criteria andStatueNotBetween(String value1, String value2) {
            addCriterion("statue not between", value1, value2, "statue");
            return (Criteria) this;
        }

        public Criteria andCanUploadIsNull() {
            addCriterion("can_upload is null");
            return (Criteria) this;
        }

        public Criteria andCanUploadIsNotNull() {
            addCriterion("can_upload is not null");
            return (Criteria) this;
        }

        public Criteria andCanUploadEqualTo(String value) {
            addCriterion("can_upload =", value, "canUpload");
            return (Criteria) this;
        }

        public Criteria andCanUploadNotEqualTo(String value) {
            addCriterion("can_upload <>", value, "canUpload");
            return (Criteria) this;
        }

        public Criteria andCanUploadGreaterThan(String value) {
            addCriterion("can_upload >", value, "canUpload");
            return (Criteria) this;
        }

        public Criteria andCanUploadGreaterThanOrEqualTo(String value) {
            addCriterion("can_upload >=", value, "canUpload");
            return (Criteria) this;
        }

        public Criteria andCanUploadLessThan(String value) {
            addCriterion("can_upload <", value, "canUpload");
            return (Criteria) this;
        }

        public Criteria andCanUploadLessThanOrEqualTo(String value) {
            addCriterion("can_upload <=", value, "canUpload");
            return (Criteria) this;
        }

        public Criteria andCanUploadLike(String value) {
            addCriterion("can_upload like", value, "canUpload");
            return (Criteria) this;
        }

        public Criteria andCanUploadNotLike(String value) {
            addCriterion("can_upload not like", value, "canUpload");
            return (Criteria) this;
        }

        public Criteria andCanUploadIn(List<String> values) {
            addCriterion("can_upload in", values, "canUpload");
            return (Criteria) this;
        }

        public Criteria andCanUploadNotIn(List<String> values) {
            addCriterion("can_upload not in", values, "canUpload");
            return (Criteria) this;
        }

        public Criteria andCanUploadBetween(String value1, String value2) {
            addCriterion("can_upload between", value1, value2, "canUpload");
            return (Criteria) this;
        }

        public Criteria andCanUploadNotBetween(String value1, String value2) {
            addCriterion("can_upload not between", value1, value2, "canUpload");
            return (Criteria) this;
        }

        public Criteria andCanDownloadIsNull() {
            addCriterion("can_download is null");
            return (Criteria) this;
        }

        public Criteria andCanDownloadIsNotNull() {
            addCriterion("can_download is not null");
            return (Criteria) this;
        }

        public Criteria andCanDownloadEqualTo(String value) {
            addCriterion("can_download =", value, "canDownload");
            return (Criteria) this;
        }

        public Criteria andCanDownloadNotEqualTo(String value) {
            addCriterion("can_download <>", value, "canDownload");
            return (Criteria) this;
        }

        public Criteria andCanDownloadGreaterThan(String value) {
            addCriterion("can_download >", value, "canDownload");
            return (Criteria) this;
        }

        public Criteria andCanDownloadGreaterThanOrEqualTo(String value) {
            addCriterion("can_download >=", value, "canDownload");
            return (Criteria) this;
        }

        public Criteria andCanDownloadLessThan(String value) {
            addCriterion("can_download <", value, "canDownload");
            return (Criteria) this;
        }

        public Criteria andCanDownloadLessThanOrEqualTo(String value) {
            addCriterion("can_download <=", value, "canDownload");
            return (Criteria) this;
        }

        public Criteria andCanDownloadLike(String value) {
            addCriterion("can_download like", value, "canDownload");
            return (Criteria) this;
        }

        public Criteria andCanDownloadNotLike(String value) {
            addCriterion("can_download not like", value, "canDownload");
            return (Criteria) this;
        }

        public Criteria andCanDownloadIn(List<String> values) {
            addCriterion("can_download in", values, "canDownload");
            return (Criteria) this;
        }

        public Criteria andCanDownloadNotIn(List<String> values) {
            addCriterion("can_download not in", values, "canDownload");
            return (Criteria) this;
        }

        public Criteria andCanDownloadBetween(String value1, String value2) {
            addCriterion("can_download between", value1, value2, "canDownload");
            return (Criteria) this;
        }

        public Criteria andCanDownloadNotBetween(String value1, String value2) {
            addCriterion("can_download not between", value1, value2, "canDownload");
            return (Criteria) this;
        }

        public Criteria andUploadLimitIsNull() {
            addCriterion("upload_limit is null");
            return (Criteria) this;
        }

        public Criteria andUploadLimitIsNotNull() {
            addCriterion("upload_limit is not null");
            return (Criteria) this;
        }

        public Criteria andUploadLimitEqualTo(Long value) {
            addCriterion("upload_limit =", value, "uploadLimit");
            return (Criteria) this;
        }

        public Criteria andUploadLimitNotEqualTo(Long value) {
            addCriterion("upload_limit <>", value, "uploadLimit");
            return (Criteria) this;
        }

        public Criteria andUploadLimitGreaterThan(Long value) {
            addCriterion("upload_limit >", value, "uploadLimit");
            return (Criteria) this;
        }

        public Criteria andUploadLimitGreaterThanOrEqualTo(Long value) {
            addCriterion("upload_limit >=", value, "uploadLimit");
            return (Criteria) this;
        }

        public Criteria andUploadLimitLessThan(Long value) {
            addCriterion("upload_limit <", value, "uploadLimit");
            return (Criteria) this;
        }

        public Criteria andUploadLimitLessThanOrEqualTo(Long value) {
            addCriterion("upload_limit <=", value, "uploadLimit");
            return (Criteria) this;
        }

        public Criteria andUploadLimitIn(List<Long> values) {
            addCriterion("upload_limit in", values, "uploadLimit");
            return (Criteria) this;
        }

        public Criteria andUploadLimitNotIn(List<Long> values) {
            addCriterion("upload_limit not in", values, "uploadLimit");
            return (Criteria) this;
        }

        public Criteria andUploadLimitBetween(Long value1, Long value2) {
            addCriterion("upload_limit between", value1, value2, "uploadLimit");
            return (Criteria) this;
        }

        public Criteria andUploadLimitNotBetween(Long value1, Long value2) {
            addCriterion("upload_limit not between", value1, value2, "uploadLimit");
            return (Criteria) this;
        }

        public Criteria andDownloadLimitIsNull() {
            addCriterion("download_limit is null");
            return (Criteria) this;
        }

        public Criteria andDownloadLimitIsNotNull() {
            addCriterion("download_limit is not null");
            return (Criteria) this;
        }

        public Criteria andDownloadLimitEqualTo(Long value) {
            addCriterion("download_limit =", value, "downloadLimit");
            return (Criteria) this;
        }

        public Criteria andDownloadLimitNotEqualTo(Long value) {
            addCriterion("download_limit <>", value, "downloadLimit");
            return (Criteria) this;
        }

        public Criteria andDownloadLimitGreaterThan(Long value) {
            addCriterion("download_limit >", value, "downloadLimit");
            return (Criteria) this;
        }

        public Criteria andDownloadLimitGreaterThanOrEqualTo(Long value) {
            addCriterion("download_limit >=", value, "downloadLimit");
            return (Criteria) this;
        }

        public Criteria andDownloadLimitLessThan(Long value) {
            addCriterion("download_limit <", value, "downloadLimit");
            return (Criteria) this;
        }

        public Criteria andDownloadLimitLessThanOrEqualTo(Long value) {
            addCriterion("download_limit <=", value, "downloadLimit");
            return (Criteria) this;
        }

        public Criteria andDownloadLimitIn(List<Long> values) {
            addCriterion("download_limit in", values, "downloadLimit");
            return (Criteria) this;
        }

        public Criteria andDownloadLimitNotIn(List<Long> values) {
            addCriterion("download_limit not in", values, "downloadLimit");
            return (Criteria) this;
        }

        public Criteria andDownloadLimitBetween(Long value1, Long value2) {
            addCriterion("download_limit between", value1, value2, "downloadLimit");
            return (Criteria) this;
        }

        public Criteria andDownloadLimitNotBetween(Long value1, Long value2) {
            addCriterion("download_limit not between", value1, value2, "downloadLimit");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}
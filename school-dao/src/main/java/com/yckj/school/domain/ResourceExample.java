package com.yckj.school.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ResourceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ResourceExample() {
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

        public Criteria andResourceIdIsNull() {
            addCriterion("resource_id is null");
            return (Criteria) this;
        }

        public Criteria andResourceIdIsNotNull() {
            addCriterion("resource_id is not null");
            return (Criteria) this;
        }

        public Criteria andResourceIdEqualTo(Long value) {
            addCriterion("resource_id =", value, "resourceId");
            return (Criteria) this;
        }

        public Criteria andResourceIdNotEqualTo(Long value) {
            addCriterion("resource_id <>", value, "resourceId");
            return (Criteria) this;
        }

        public Criteria andResourceIdGreaterThan(Long value) {
            addCriterion("resource_id >", value, "resourceId");
            return (Criteria) this;
        }

        public Criteria andResourceIdGreaterThanOrEqualTo(Long value) {
            addCriterion("resource_id >=", value, "resourceId");
            return (Criteria) this;
        }

        public Criteria andResourceIdLessThan(Long value) {
            addCriterion("resource_id <", value, "resourceId");
            return (Criteria) this;
        }

        public Criteria andResourceIdLessThanOrEqualTo(Long value) {
            addCriterion("resource_id <=", value, "resourceId");
            return (Criteria) this;
        }

        public Criteria andResourceIdIn(List<Long> values) {
            addCriterion("resource_id in", values, "resourceId");
            return (Criteria) this;
        }

        public Criteria andResourceIdNotIn(List<Long> values) {
            addCriterion("resource_id not in", values, "resourceId");
            return (Criteria) this;
        }

        public Criteria andResourceIdBetween(Long value1, Long value2) {
            addCriterion("resource_id between", value1, value2, "resourceId");
            return (Criteria) this;
        }

        public Criteria andResourceIdNotBetween(Long value1, Long value2) {
            addCriterion("resource_id not between", value1, value2, "resourceId");
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

        public Criteria andSuffixIsNull() {
            addCriterion("suffix is null");
            return (Criteria) this;
        }

        public Criteria andSuffixIsNotNull() {
            addCriterion("suffix is not null");
            return (Criteria) this;
        }

        public Criteria andSuffixEqualTo(String value) {
            addCriterion("suffix =", value, "suffix");
            return (Criteria) this;
        }

        public Criteria andSuffixNotEqualTo(String value) {
            addCriterion("suffix <>", value, "suffix");
            return (Criteria) this;
        }

        public Criteria andSuffixGreaterThan(String value) {
            addCriterion("suffix >", value, "suffix");
            return (Criteria) this;
        }

        public Criteria andSuffixGreaterThanOrEqualTo(String value) {
            addCriterion("suffix >=", value, "suffix");
            return (Criteria) this;
        }

        public Criteria andSuffixLessThan(String value) {
            addCriterion("suffix <", value, "suffix");
            return (Criteria) this;
        }

        public Criteria andSuffixLessThanOrEqualTo(String value) {
            addCriterion("suffix <=", value, "suffix");
            return (Criteria) this;
        }

        public Criteria andSuffixLike(String value) {
            addCriterion("suffix like", value, "suffix");
            return (Criteria) this;
        }

        public Criteria andSuffixNotLike(String value) {
            addCriterion("suffix not like", value, "suffix");
            return (Criteria) this;
        }

        public Criteria andSuffixIn(List<String> values) {
            addCriterion("suffix in", values, "suffix");
            return (Criteria) this;
        }

        public Criteria andSuffixNotIn(List<String> values) {
            addCriterion("suffix not in", values, "suffix");
            return (Criteria) this;
        }

        public Criteria andSuffixBetween(String value1, String value2) {
            addCriterion("suffix between", value1, value2, "suffix");
            return (Criteria) this;
        }

        public Criteria andSuffixNotBetween(String value1, String value2) {
            addCriterion("suffix not between", value1, value2, "suffix");
            return (Criteria) this;
        }

        public Criteria andOriUrlIsNull() {
            addCriterion("ori_url is null");
            return (Criteria) this;
        }

        public Criteria andOriUrlIsNotNull() {
            addCriterion("ori_url is not null");
            return (Criteria) this;
        }

        public Criteria andOriUrlEqualTo(String value) {
            addCriterion("ori_url =", value, "oriUrl");
            return (Criteria) this;
        }

        public Criteria andOriUrlNotEqualTo(String value) {
            addCriterion("ori_url <>", value, "oriUrl");
            return (Criteria) this;
        }

        public Criteria andOriUrlGreaterThan(String value) {
            addCriterion("ori_url >", value, "oriUrl");
            return (Criteria) this;
        }

        public Criteria andOriUrlGreaterThanOrEqualTo(String value) {
            addCriterion("ori_url >=", value, "oriUrl");
            return (Criteria) this;
        }

        public Criteria andOriUrlLessThan(String value) {
            addCriterion("ori_url <", value, "oriUrl");
            return (Criteria) this;
        }

        public Criteria andOriUrlLessThanOrEqualTo(String value) {
            addCriterion("ori_url <=", value, "oriUrl");
            return (Criteria) this;
        }

        public Criteria andOriUrlLike(String value) {
            addCriterion("ori_url like", value, "oriUrl");
            return (Criteria) this;
        }

        public Criteria andOriUrlNotLike(String value) {
            addCriterion("ori_url not like", value, "oriUrl");
            return (Criteria) this;
        }

        public Criteria andOriUrlIn(List<String> values) {
            addCriterion("ori_url in", values, "oriUrl");
            return (Criteria) this;
        }

        public Criteria andOriUrlNotIn(List<String> values) {
            addCriterion("ori_url not in", values, "oriUrl");
            return (Criteria) this;
        }

        public Criteria andOriUrlBetween(String value1, String value2) {
            addCriterion("ori_url between", value1, value2, "oriUrl");
            return (Criteria) this;
        }

        public Criteria andOriUrlNotBetween(String value1, String value2) {
            addCriterion("ori_url not between", value1, value2, "oriUrl");
            return (Criteria) this;
        }

        public Criteria andSwfUrlIsNull() {
            addCriterion("swf_url is null");
            return (Criteria) this;
        }

        public Criteria andSwfUrlIsNotNull() {
            addCriterion("swf_url is not null");
            return (Criteria) this;
        }

        public Criteria andSwfUrlEqualTo(String value) {
            addCriterion("swf_url =", value, "swfUrl");
            return (Criteria) this;
        }

        public Criteria andSwfUrlNotEqualTo(String value) {
            addCriterion("swf_url <>", value, "swfUrl");
            return (Criteria) this;
        }

        public Criteria andSwfUrlGreaterThan(String value) {
            addCriterion("swf_url >", value, "swfUrl");
            return (Criteria) this;
        }

        public Criteria andSwfUrlGreaterThanOrEqualTo(String value) {
            addCriterion("swf_url >=", value, "swfUrl");
            return (Criteria) this;
        }

        public Criteria andSwfUrlLessThan(String value) {
            addCriterion("swf_url <", value, "swfUrl");
            return (Criteria) this;
        }

        public Criteria andSwfUrlLessThanOrEqualTo(String value) {
            addCriterion("swf_url <=", value, "swfUrl");
            return (Criteria) this;
        }

        public Criteria andSwfUrlLike(String value) {
            addCriterion("swf_url like", value, "swfUrl");
            return (Criteria) this;
        }

        public Criteria andSwfUrlNotLike(String value) {
            addCriterion("swf_url not like", value, "swfUrl");
            return (Criteria) this;
        }

        public Criteria andSwfUrlIn(List<String> values) {
            addCriterion("swf_url in", values, "swfUrl");
            return (Criteria) this;
        }

        public Criteria andSwfUrlNotIn(List<String> values) {
            addCriterion("swf_url not in", values, "swfUrl");
            return (Criteria) this;
        }

        public Criteria andSwfUrlBetween(String value1, String value2) {
            addCriterion("swf_url between", value1, value2, "swfUrl");
            return (Criteria) this;
        }

        public Criteria andSwfUrlNotBetween(String value1, String value2) {
            addCriterion("swf_url not between", value1, value2, "swfUrl");
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

        public Criteria andNoteIsNull() {
            addCriterion("note is null");
            return (Criteria) this;
        }

        public Criteria andNoteIsNotNull() {
            addCriterion("note is not null");
            return (Criteria) this;
        }

        public Criteria andNoteEqualTo(String value) {
            addCriterion("note =", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotEqualTo(String value) {
            addCriterion("note <>", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThan(String value) {
            addCriterion("note >", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThanOrEqualTo(String value) {
            addCriterion("note >=", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLessThan(String value) {
            addCriterion("note <", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLessThanOrEqualTo(String value) {
            addCriterion("note <=", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLike(String value) {
            addCriterion("note like", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotLike(String value) {
            addCriterion("note not like", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteIn(List<String> values) {
            addCriterion("note in", values, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotIn(List<String> values) {
            addCriterion("note not in", values, "note");
            return (Criteria) this;
        }

        public Criteria andNoteBetween(String value1, String value2) {
            addCriterion("note between", value1, value2, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotBetween(String value1, String value2) {
            addCriterion("note not between", value1, value2, "note");
            return (Criteria) this;
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

        public Criteria andFileSizeIsNull() {
            addCriterion("file_size is null");
            return (Criteria) this;
        }

        public Criteria andFileSizeIsNotNull() {
            addCriterion("file_size is not null");
            return (Criteria) this;
        }

        public Criteria andFileSizeEqualTo(Long value) {
            addCriterion("file_size =", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeNotEqualTo(Long value) {
            addCriterion("file_size <>", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeGreaterThan(Long value) {
            addCriterion("file_size >", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeGreaterThanOrEqualTo(Long value) {
            addCriterion("file_size >=", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeLessThan(Long value) {
            addCriterion("file_size <", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeLessThanOrEqualTo(Long value) {
            addCriterion("file_size <=", value, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeIn(List<Long> values) {
            addCriterion("file_size in", values, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeNotIn(List<Long> values) {
            addCriterion("file_size not in", values, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeBetween(Long value1, Long value2) {
            addCriterion("file_size between", value1, value2, "fileSize");
            return (Criteria) this;
        }

        public Criteria andFileSizeNotBetween(Long value1, Long value2) {
            addCriterion("file_size not between", value1, value2, "fileSize");
            return (Criteria) this;
        }

        public Criteria andSaveCntIsNull() {
            addCriterion("save_cnt is null");
            return (Criteria) this;
        }

        public Criteria andSaveCntIsNotNull() {
            addCriterion("save_cnt is not null");
            return (Criteria) this;
        }

        public Criteria andSaveCntEqualTo(Integer value) {
            addCriterion("save_cnt =", value, "saveCnt");
            return (Criteria) this;
        }

        public Criteria andSaveCntNotEqualTo(Integer value) {
            addCriterion("save_cnt <>", value, "saveCnt");
            return (Criteria) this;
        }

        public Criteria andSaveCntGreaterThan(Integer value) {
            addCriterion("save_cnt >", value, "saveCnt");
            return (Criteria) this;
        }

        public Criteria andSaveCntGreaterThanOrEqualTo(Integer value) {
            addCriterion("save_cnt >=", value, "saveCnt");
            return (Criteria) this;
        }

        public Criteria andSaveCntLessThan(Integer value) {
            addCriterion("save_cnt <", value, "saveCnt");
            return (Criteria) this;
        }

        public Criteria andSaveCntLessThanOrEqualTo(Integer value) {
            addCriterion("save_cnt <=", value, "saveCnt");
            return (Criteria) this;
        }

        public Criteria andSaveCntIn(List<Integer> values) {
            addCriterion("save_cnt in", values, "saveCnt");
            return (Criteria) this;
        }

        public Criteria andSaveCntNotIn(List<Integer> values) {
            addCriterion("save_cnt not in", values, "saveCnt");
            return (Criteria) this;
        }

        public Criteria andSaveCntBetween(Integer value1, Integer value2) {
            addCriterion("save_cnt between", value1, value2, "saveCnt");
            return (Criteria) this;
        }

        public Criteria andSaveCntNotBetween(Integer value1, Integer value2) {
            addCriterion("save_cnt not between", value1, value2, "saveCnt");
            return (Criteria) this;
        }

        public Criteria andLoadCntIsNull() {
            addCriterion("load_cnt is null");
            return (Criteria) this;
        }

        public Criteria andLoadCntIsNotNull() {
            addCriterion("load_cnt is not null");
            return (Criteria) this;
        }

        public Criteria andLoadCntEqualTo(Integer value) {
            addCriterion("load_cnt =", value, "loadCnt");
            return (Criteria) this;
        }

        public Criteria andLoadCntNotEqualTo(Integer value) {
            addCriterion("load_cnt <>", value, "loadCnt");
            return (Criteria) this;
        }

        public Criteria andLoadCntGreaterThan(Integer value) {
            addCriterion("load_cnt >", value, "loadCnt");
            return (Criteria) this;
        }

        public Criteria andLoadCntGreaterThanOrEqualTo(Integer value) {
            addCriterion("load_cnt >=", value, "loadCnt");
            return (Criteria) this;
        }

        public Criteria andLoadCntLessThan(Integer value) {
            addCriterion("load_cnt <", value, "loadCnt");
            return (Criteria) this;
        }

        public Criteria andLoadCntLessThanOrEqualTo(Integer value) {
            addCriterion("load_cnt <=", value, "loadCnt");
            return (Criteria) this;
        }

        public Criteria andLoadCntIn(List<Integer> values) {
            addCriterion("load_cnt in", values, "loadCnt");
            return (Criteria) this;
        }

        public Criteria andLoadCntNotIn(List<Integer> values) {
            addCriterion("load_cnt not in", values, "loadCnt");
            return (Criteria) this;
        }

        public Criteria andLoadCntBetween(Integer value1, Integer value2) {
            addCriterion("load_cnt between", value1, value2, "loadCnt");
            return (Criteria) this;
        }

        public Criteria andLoadCntNotBetween(Integer value1, Integer value2) {
            addCriterion("load_cnt not between", value1, value2, "loadCnt");
            return (Criteria) this;
        }

        public Criteria andScoreCntIsNull() {
            addCriterion("score_cnt is null");
            return (Criteria) this;
        }

        public Criteria andScoreCntIsNotNull() {
            addCriterion("score_cnt is not null");
            return (Criteria) this;
        }

        public Criteria andScoreCntEqualTo(Integer value) {
            addCriterion("score_cnt =", value, "scoreCnt");
            return (Criteria) this;
        }

        public Criteria andScoreCntNotEqualTo(Integer value) {
            addCriterion("score_cnt <>", value, "scoreCnt");
            return (Criteria) this;
        }

        public Criteria andScoreCntGreaterThan(Integer value) {
            addCriterion("score_cnt >", value, "scoreCnt");
            return (Criteria) this;
        }

        public Criteria andScoreCntGreaterThanOrEqualTo(Integer value) {
            addCriterion("score_cnt >=", value, "scoreCnt");
            return (Criteria) this;
        }

        public Criteria andScoreCntLessThan(Integer value) {
            addCriterion("score_cnt <", value, "scoreCnt");
            return (Criteria) this;
        }

        public Criteria andScoreCntLessThanOrEqualTo(Integer value) {
            addCriterion("score_cnt <=", value, "scoreCnt");
            return (Criteria) this;
        }

        public Criteria andScoreCntIn(List<Integer> values) {
            addCriterion("score_cnt in", values, "scoreCnt");
            return (Criteria) this;
        }

        public Criteria andScoreCntNotIn(List<Integer> values) {
            addCriterion("score_cnt not in", values, "scoreCnt");
            return (Criteria) this;
        }

        public Criteria andScoreCntBetween(Integer value1, Integer value2) {
            addCriterion("score_cnt between", value1, value2, "scoreCnt");
            return (Criteria) this;
        }

        public Criteria andScoreCntNotBetween(Integer value1, Integer value2) {
            addCriterion("score_cnt not between", value1, value2, "scoreCnt");
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

        public Criteria andViewCntIsNull() {
            addCriterion("view_cnt is null");
            return (Criteria) this;
        }

        public Criteria andViewCntIsNotNull() {
            addCriterion("view_cnt is not null");
            return (Criteria) this;
        }

        public Criteria andViewCntEqualTo(Integer value) {
            addCriterion("view_cnt =", value, "viewCnt");
            return (Criteria) this;
        }

        public Criteria andViewCntNotEqualTo(Integer value) {
            addCriterion("view_cnt <>", value, "viewCnt");
            return (Criteria) this;
        }

        public Criteria andViewCntGreaterThan(Integer value) {
            addCriterion("view_cnt >", value, "viewCnt");
            return (Criteria) this;
        }

        public Criteria andViewCntGreaterThanOrEqualTo(Integer value) {
            addCriterion("view_cnt >=", value, "viewCnt");
            return (Criteria) this;
        }

        public Criteria andViewCntLessThan(Integer value) {
            addCriterion("view_cnt <", value, "viewCnt");
            return (Criteria) this;
        }

        public Criteria andViewCntLessThanOrEqualTo(Integer value) {
            addCriterion("view_cnt <=", value, "viewCnt");
            return (Criteria) this;
        }

        public Criteria andViewCntIn(List<Integer> values) {
            addCriterion("view_cnt in", values, "viewCnt");
            return (Criteria) this;
        }

        public Criteria andViewCntNotIn(List<Integer> values) {
            addCriterion("view_cnt not in", values, "viewCnt");
            return (Criteria) this;
        }

        public Criteria andViewCntBetween(Integer value1, Integer value2) {
            addCriterion("view_cnt between", value1, value2, "viewCnt");
            return (Criteria) this;
        }

        public Criteria andViewCntNotBetween(Integer value1, Integer value2) {
            addCriterion("view_cnt not between", value1, value2, "viewCnt");
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

        public Criteria andPersonVisibleIsNull() {
            addCriterion("person_visible is null");
            return (Criteria) this;
        }

        public Criteria andPersonVisibleIsNotNull() {
            addCriterion("person_visible is not null");
            return (Criteria) this;
        }

        public Criteria andPersonVisibleEqualTo(String value) {
            addCriterion("person_visible =", value, "personVisible");
            return (Criteria) this;
        }

        public Criteria andPersonVisibleNotEqualTo(String value) {
            addCriterion("person_visible <>", value, "personVisible");
            return (Criteria) this;
        }

        public Criteria andPersonVisibleGreaterThan(String value) {
            addCriterion("person_visible >", value, "personVisible");
            return (Criteria) this;
        }

        public Criteria andPersonVisibleGreaterThanOrEqualTo(String value) {
            addCriterion("person_visible >=", value, "personVisible");
            return (Criteria) this;
        }

        public Criteria andPersonVisibleLessThan(String value) {
            addCriterion("person_visible <", value, "personVisible");
            return (Criteria) this;
        }

        public Criteria andPersonVisibleLessThanOrEqualTo(String value) {
            addCriterion("person_visible <=", value, "personVisible");
            return (Criteria) this;
        }

        public Criteria andPersonVisibleLike(String value) {
            addCriterion("person_visible like", value, "personVisible");
            return (Criteria) this;
        }

        public Criteria andPersonVisibleNotLike(String value) {
            addCriterion("person_visible not like", value, "personVisible");
            return (Criteria) this;
        }

        public Criteria andPersonVisibleIn(List<String> values) {
            addCriterion("person_visible in", values, "personVisible");
            return (Criteria) this;
        }

        public Criteria andPersonVisibleNotIn(List<String> values) {
            addCriterion("person_visible not in", values, "personVisible");
            return (Criteria) this;
        }

        public Criteria andPersonVisibleBetween(String value1, String value2) {
            addCriterion("person_visible between", value1, value2, "personVisible");
            return (Criteria) this;
        }

        public Criteria andPersonVisibleNotBetween(String value1, String value2) {
            addCriterion("person_visible not between", value1, value2, "personVisible");
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
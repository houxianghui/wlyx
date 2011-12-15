package com.huateng.blue.work;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkDailyExample {
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table work_daily
     *
     * @ibatorgenerated Wed Aug 10 17:28:40 CST 2011
     */
    protected String orderByClause;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table work_daily
     *
     * @ibatorgenerated Wed Aug 10 17:28:40 CST 2011
     */
    protected List oredCriteria;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table work_daily
     *
     * @ibatorgenerated Wed Aug 10 17:28:40 CST 2011
     */
    public WorkDailyExample() {
        oredCriteria = new ArrayList();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table work_daily
     *
     * @ibatorgenerated Wed Aug 10 17:28:40 CST 2011
     */
    protected WorkDailyExample(WorkDailyExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table work_daily
     *
     * @ibatorgenerated Wed Aug 10 17:28:40 CST 2011
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table work_daily
     *
     * @ibatorgenerated Wed Aug 10 17:28:40 CST 2011
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table work_daily
     *
     * @ibatorgenerated Wed Aug 10 17:28:40 CST 2011
     */
    public List getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table work_daily
     *
     * @ibatorgenerated Wed Aug 10 17:28:40 CST 2011
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table work_daily
     *
     * @ibatorgenerated Wed Aug 10 17:28:40 CST 2011
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table work_daily
     *
     * @ibatorgenerated Wed Aug 10 17:28:40 CST 2011
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table work_daily
     *
     * @ibatorgenerated Wed Aug 10 17:28:40 CST 2011
     */
    public void clear() {
        oredCriteria.clear();
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table work_daily
     *
     * @ibatorgenerated Wed Aug 10 17:28:40 CST 2011
     */
    public static class Criteria {
        protected List criteriaWithoutValue;

        protected List criteriaWithSingleValue;

        protected List criteriaWithListValue;

        protected List criteriaWithBetweenValue;

        protected Criteria() {
            super();
            criteriaWithoutValue = new ArrayList();
            criteriaWithSingleValue = new ArrayList();
            criteriaWithListValue = new ArrayList();
            criteriaWithBetweenValue = new ArrayList();
        }

        public boolean isValid() {
            return criteriaWithoutValue.size() > 0
                || criteriaWithSingleValue.size() > 0
                || criteriaWithListValue.size() > 0
                || criteriaWithBetweenValue.size() > 0;
        }

        public List getCriteriaWithoutValue() {
            return criteriaWithoutValue;
        }

        public List getCriteriaWithSingleValue() {
            return criteriaWithSingleValue;
        }

        public List getCriteriaWithListValue() {
            return criteriaWithListValue;
        }

        public List getCriteriaWithBetweenValue() {
            return criteriaWithBetweenValue;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteriaWithoutValue.add(condition);
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            Map map = new HashMap();
            map.put("condition", condition);
            map.put("value", value);
            criteriaWithSingleValue.add(map);
        }

        protected void addCriterion(String condition, List values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            Map map = new HashMap();
            map.put("condition", condition);
            map.put("values", values);
            criteriaWithListValue.add(map);
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            List list = new ArrayList();
            list.add(value1);
            list.add(value2);
            Map map = new HashMap();
            map.put("condition", condition);
            map.put("values", list);
            criteriaWithBetweenValue.add(map);
        }

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("ID =", value, "id");
            return this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("ID <>", value, "id");
            return this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("ID >", value, "id");
            return this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ID >=", value, "id");
            return this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("ID <", value, "id");
            return this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("ID <=", value, "id");
            return this;
        }

        public Criteria andIdIn(List values) {
            addCriterion("ID in", values, "id");
            return this;
        }

        public Criteria andIdNotIn(List values) {
            addCriterion("ID not in", values, "id");
            return this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("ID between", value1, value2, "id");
            return this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ID not between", value1, value2, "id");
            return this;
        }

        public Criteria andDistributeIdIsNull() {
            addCriterion("DISTRIBUTE_ID is null");
            return this;
        }

        public Criteria andDistributeIdIsNotNull() {
            addCriterion("DISTRIBUTE_ID is not null");
            return this;
        }

        public Criteria andDistributeIdEqualTo(Integer value) {
            addCriterion("DISTRIBUTE_ID =", value, "distributeId");
            return this;
        }

        public Criteria andDistributeIdNotEqualTo(Integer value) {
            addCriterion("DISTRIBUTE_ID <>", value, "distributeId");
            return this;
        }

        public Criteria andDistributeIdGreaterThan(Integer value) {
            addCriterion("DISTRIBUTE_ID >", value, "distributeId");
            return this;
        }

        public Criteria andDistributeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("DISTRIBUTE_ID >=", value, "distributeId");
            return this;
        }

        public Criteria andDistributeIdLessThan(Integer value) {
            addCriterion("DISTRIBUTE_ID <", value, "distributeId");
            return this;
        }

        public Criteria andDistributeIdLessThanOrEqualTo(Integer value) {
            addCriterion("DISTRIBUTE_ID <=", value, "distributeId");
            return this;
        }

        public Criteria andDistributeIdIn(List values) {
            addCriterion("DISTRIBUTE_ID in", values, "distributeId");
            return this;
        }

        public Criteria andDistributeIdNotIn(List values) {
            addCriterion("DISTRIBUTE_ID not in", values, "distributeId");
            return this;
        }

        public Criteria andDistributeIdBetween(Integer value1, Integer value2) {
            addCriterion("DISTRIBUTE_ID between", value1, value2, "distributeId");
            return this;
        }

        public Criteria andDistributeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("DISTRIBUTE_ID not between", value1, value2, "distributeId");
            return this;
        }

        public Criteria andWorkIdIsNull() {
            addCriterion("WORK_ID is null");
            return this;
        }

        public Criteria andWorkIdIsNotNull() {
            addCriterion("WORK_ID is not null");
            return this;
        }

        public Criteria andWorkIdEqualTo(String value) {
            addCriterion("WORK_ID =", value, "workId");
            return this;
        }

        public Criteria andWorkIdNotEqualTo(String value) {
            addCriterion("WORK_ID <>", value, "workId");
            return this;
        }

        public Criteria andWorkIdGreaterThan(String value) {
            addCriterion("WORK_ID >", value, "workId");
            return this;
        }

        public Criteria andWorkIdGreaterThanOrEqualTo(String value) {
            addCriterion("WORK_ID >=", value, "workId");
            return this;
        }

        public Criteria andWorkIdLessThan(String value) {
            addCriterion("WORK_ID <", value, "workId");
            return this;
        }

        public Criteria andWorkIdLessThanOrEqualTo(String value) {
            addCriterion("WORK_ID <=", value, "workId");
            return this;
        }

        public Criteria andWorkIdLike(String value) {
            addCriterion("WORK_ID like", value, "workId");
            return this;
        }

        public Criteria andWorkIdNotLike(String value) {
            addCriterion("WORK_ID not like", value, "workId");
            return this;
        }

        public Criteria andWorkIdIn(List values) {
            addCriterion("WORK_ID in", values, "workId");
            return this;
        }

        public Criteria andWorkIdNotIn(List values) {
            addCriterion("WORK_ID not in", values, "workId");
            return this;
        }

        public Criteria andWorkIdBetween(String value1, String value2) {
            addCriterion("WORK_ID between", value1, value2, "workId");
            return this;
        }

        public Criteria andWorkIdNotBetween(String value1, String value2) {
            addCriterion("WORK_ID not between", value1, value2, "workId");
            return this;
        }

        public Criteria andWorkDateIsNull() {
            addCriterion("WORK_DATE is null");
            return this;
        }

        public Criteria andWorkDateIsNotNull() {
            addCriterion("WORK_DATE is not null");
            return this;
        }

        public Criteria andWorkDateEqualTo(String value) {
            addCriterion("WORK_DATE =", value, "workDate");
            return this;
        }

        public Criteria andWorkDateNotEqualTo(String value) {
            addCriterion("WORK_DATE <>", value, "workDate");
            return this;
        }

        public Criteria andWorkDateGreaterThan(String value) {
            addCriterion("WORK_DATE >", value, "workDate");
            return this;
        }

        public Criteria andWorkDateGreaterThanOrEqualTo(String value) {
            addCriterion("WORK_DATE >=", value, "workDate");
            return this;
        }

        public Criteria andWorkDateLessThan(String value) {
            addCriterion("WORK_DATE <", value, "workDate");
            return this;
        }

        public Criteria andWorkDateLessThanOrEqualTo(String value) {
            addCriterion("WORK_DATE <=", value, "workDate");
            return this;
        }

        public Criteria andWorkDateLike(String value) {
            addCriterion("WORK_DATE like", value, "workDate");
            return this;
        }

        public Criteria andWorkDateNotLike(String value) {
            addCriterion("WORK_DATE not like", value, "workDate");
            return this;
        }

        public Criteria andWorkDateIn(List values) {
            addCriterion("WORK_DATE in", values, "workDate");
            return this;
        }

        public Criteria andWorkDateNotIn(List values) {
            addCriterion("WORK_DATE not in", values, "workDate");
            return this;
        }

        public Criteria andWorkDateBetween(String value1, String value2) {
            addCriterion("WORK_DATE between", value1, value2, "workDate");
            return this;
        }

        public Criteria andWorkDateNotBetween(String value1, String value2) {
            addCriterion("WORK_DATE not between", value1, value2, "workDate");
            return this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("USER_ID is null");
            return this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("USER_ID is not null");
            return this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("USER_ID =", value, "userId");
            return this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("USER_ID <>", value, "userId");
            return this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("USER_ID >", value, "userId");
            return this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("USER_ID >=", value, "userId");
            return this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("USER_ID <", value, "userId");
            return this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("USER_ID <=", value, "userId");
            return this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("USER_ID like", value, "userId");
            return this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("USER_ID not like", value, "userId");
            return this;
        }

        public Criteria andUserIdIn(List values) {
            addCriterion("USER_ID in", values, "userId");
            return this;
        }

        public Criteria andUserIdNotIn(List values) {
            addCriterion("USER_ID not in", values, "userId");
            return this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("USER_ID between", value1, value2, "userId");
            return this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("USER_ID not between", value1, value2, "userId");
            return this;
        }

        public Criteria andContentIsNull() {
            addCriterion("CONTENT is null");
            return this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("CONTENT is not null");
            return this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("CONTENT =", value, "content");
            return this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("CONTENT <>", value, "content");
            return this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("CONTENT >", value, "content");
            return this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("CONTENT >=", value, "content");
            return this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("CONTENT <", value, "content");
            return this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("CONTENT <=", value, "content");
            return this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("CONTENT like", value, "content");
            return this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("CONTENT not like", value, "content");
            return this;
        }

        public Criteria andContentIn(List values) {
            addCriterion("CONTENT in", values, "content");
            return this;
        }

        public Criteria andContentNotIn(List values) {
            addCriterion("CONTENT not in", values, "content");
            return this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("CONTENT between", value1, value2, "content");
            return this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("CONTENT not between", value1, value2, "content");
            return this;
        }

        public Criteria andInputDateIsNull() {
            addCriterion("INPUT_DATE is null");
            return this;
        }

        public Criteria andInputDateIsNotNull() {
            addCriterion("INPUT_DATE is not null");
            return this;
        }

        public Criteria andInputDateEqualTo(String value) {
            addCriterion("INPUT_DATE =", value, "inputDate");
            return this;
        }

        public Criteria andInputDateNotEqualTo(String value) {
            addCriterion("INPUT_DATE <>", value, "inputDate");
            return this;
        }

        public Criteria andInputDateGreaterThan(String value) {
            addCriterion("INPUT_DATE >", value, "inputDate");
            return this;
        }

        public Criteria andInputDateGreaterThanOrEqualTo(String value) {
            addCriterion("INPUT_DATE >=", value, "inputDate");
            return this;
        }

        public Criteria andInputDateLessThan(String value) {
            addCriterion("INPUT_DATE <", value, "inputDate");
            return this;
        }

        public Criteria andInputDateLessThanOrEqualTo(String value) {
            addCriterion("INPUT_DATE <=", value, "inputDate");
            return this;
        }

        public Criteria andInputDateLike(String value) {
            addCriterion("INPUT_DATE like", value, "inputDate");
            return this;
        }

        public Criteria andInputDateNotLike(String value) {
            addCriterion("INPUT_DATE not like", value, "inputDate");
            return this;
        }

        public Criteria andInputDateIn(List values) {
            addCriterion("INPUT_DATE in", values, "inputDate");
            return this;
        }

        public Criteria andInputDateNotIn(List values) {
            addCriterion("INPUT_DATE not in", values, "inputDate");
            return this;
        }

        public Criteria andInputDateBetween(String value1, String value2) {
            addCriterion("INPUT_DATE between", value1, value2, "inputDate");
            return this;
        }

        public Criteria andInputDateNotBetween(String value1, String value2) {
            addCriterion("INPUT_DATE not between", value1, value2, "inputDate");
            return this;
        }
    }
}
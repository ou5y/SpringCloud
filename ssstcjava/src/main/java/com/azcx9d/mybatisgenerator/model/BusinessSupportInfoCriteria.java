package com.azcx9d.mybatisgenerator.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BusinessSupportInfoCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BusinessSupportInfoCriteria() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andBIdIsNull() {
            addCriterion("b_id is null");
            return (Criteria) this;
        }

        public Criteria andBIdIsNotNull() {
            addCriterion("b_id is not null");
            return (Criteria) this;
        }

        public Criteria andBIdEqualTo(Integer value) {
            addCriterion("b_id =", value, "bId");
            return (Criteria) this;
        }

        public Criteria andBIdNotEqualTo(Integer value) {
            addCriterion("b_id <>", value, "bId");
            return (Criteria) this;
        }

        public Criteria andBIdGreaterThan(Integer value) {
            addCriterion("b_id >", value, "bId");
            return (Criteria) this;
        }

        public Criteria andBIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("b_id >=", value, "bId");
            return (Criteria) this;
        }

        public Criteria andBIdLessThan(Integer value) {
            addCriterion("b_id <", value, "bId");
            return (Criteria) this;
        }

        public Criteria andBIdLessThanOrEqualTo(Integer value) {
            addCriterion("b_id <=", value, "bId");
            return (Criteria) this;
        }

        public Criteria andBIdIn(List<Integer> values) {
            addCriterion("b_id in", values, "bId");
            return (Criteria) this;
        }

        public Criteria andBIdNotIn(List<Integer> values) {
            addCriterion("b_id not in", values, "bId");
            return (Criteria) this;
        }

        public Criteria andBIdBetween(Integer value1, Integer value2) {
            addCriterion("b_id between", value1, value2, "bId");
            return (Criteria) this;
        }

        public Criteria andBIdNotBetween(Integer value1, Integer value2) {
            addCriterion("b_id not between", value1, value2, "bId");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIsNull() {
            addCriterion("company_name is null");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIsNotNull() {
            addCriterion("company_name is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyNameEqualTo(String value) {
            addCriterion("company_name =", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotEqualTo(String value) {
            addCriterion("company_name <>", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameGreaterThan(String value) {
            addCriterion("company_name >", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameGreaterThanOrEqualTo(String value) {
            addCriterion("company_name >=", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLessThan(String value) {
            addCriterion("company_name <", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLessThanOrEqualTo(String value) {
            addCriterion("company_name <=", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLike(String value) {
            addCriterion("company_name like", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotLike(String value) {
            addCriterion("company_name not like", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIn(List<String> values) {
            addCriterion("company_name in", values, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotIn(List<String> values) {
            addCriterion("company_name not in", values, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameBetween(String value1, String value2) {
            addCriterion("company_name between", value1, value2, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotBetween(String value1, String value2) {
            addCriterion("company_name not between", value1, value2, "companyName");
            return (Criteria) this;
        }

        public Criteria andBankCodeIsNull() {
            addCriterion("bank_code is null");
            return (Criteria) this;
        }

        public Criteria andBankCodeIsNotNull() {
            addCriterion("bank_code is not null");
            return (Criteria) this;
        }

        public Criteria andBankCodeEqualTo(String value) {
            addCriterion("bank_code =", value, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeNotEqualTo(String value) {
            addCriterion("bank_code <>", value, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeGreaterThan(String value) {
            addCriterion("bank_code >", value, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeGreaterThanOrEqualTo(String value) {
            addCriterion("bank_code >=", value, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeLessThan(String value) {
            addCriterion("bank_code <", value, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeLessThanOrEqualTo(String value) {
            addCriterion("bank_code <=", value, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeLike(String value) {
            addCriterion("bank_code like", value, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeNotLike(String value) {
            addCriterion("bank_code not like", value, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeIn(List<String> values) {
            addCriterion("bank_code in", values, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeNotIn(List<String> values) {
            addCriterion("bank_code not in", values, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeBetween(String value1, String value2) {
            addCriterion("bank_code between", value1, value2, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeNotBetween(String value1, String value2) {
            addCriterion("bank_code not between", value1, value2, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankNameIsNull() {
            addCriterion("bank_name is null");
            return (Criteria) this;
        }

        public Criteria andBankNameIsNotNull() {
            addCriterion("bank_name is not null");
            return (Criteria) this;
        }

        public Criteria andBankNameEqualTo(String value) {
            addCriterion("bank_name =", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotEqualTo(String value) {
            addCriterion("bank_name <>", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameGreaterThan(String value) {
            addCriterion("bank_name >", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameGreaterThanOrEqualTo(String value) {
            addCriterion("bank_name >=", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLessThan(String value) {
            addCriterion("bank_name <", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLessThanOrEqualTo(String value) {
            addCriterion("bank_name <=", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLike(String value) {
            addCriterion("bank_name like", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotLike(String value) {
            addCriterion("bank_name not like", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameIn(List<String> values) {
            addCriterion("bank_name in", values, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotIn(List<String> values) {
            addCriterion("bank_name not in", values, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameBetween(String value1, String value2) {
            addCriterion("bank_name between", value1, value2, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotBetween(String value1, String value2) {
            addCriterion("bank_name not between", value1, value2, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankBranchIsNull() {
            addCriterion("bank_branch is null");
            return (Criteria) this;
        }

        public Criteria andBankBranchIsNotNull() {
            addCriterion("bank_branch is not null");
            return (Criteria) this;
        }

        public Criteria andBankBranchEqualTo(String value) {
            addCriterion("bank_branch =", value, "bankBranch");
            return (Criteria) this;
        }

        public Criteria andBankBranchNotEqualTo(String value) {
            addCriterion("bank_branch <>", value, "bankBranch");
            return (Criteria) this;
        }

        public Criteria andBankBranchGreaterThan(String value) {
            addCriterion("bank_branch >", value, "bankBranch");
            return (Criteria) this;
        }

        public Criteria andBankBranchGreaterThanOrEqualTo(String value) {
            addCriterion("bank_branch >=", value, "bankBranch");
            return (Criteria) this;
        }

        public Criteria andBankBranchLessThan(String value) {
            addCriterion("bank_branch <", value, "bankBranch");
            return (Criteria) this;
        }

        public Criteria andBankBranchLessThanOrEqualTo(String value) {
            addCriterion("bank_branch <=", value, "bankBranch");
            return (Criteria) this;
        }

        public Criteria andBankBranchLike(String value) {
            addCriterion("bank_branch like", value, "bankBranch");
            return (Criteria) this;
        }

        public Criteria andBankBranchNotLike(String value) {
            addCriterion("bank_branch not like", value, "bankBranch");
            return (Criteria) this;
        }

        public Criteria andBankBranchIn(List<String> values) {
            addCriterion("bank_branch in", values, "bankBranch");
            return (Criteria) this;
        }

        public Criteria andBankBranchNotIn(List<String> values) {
            addCriterion("bank_branch not in", values, "bankBranch");
            return (Criteria) this;
        }

        public Criteria andBankBranchBetween(String value1, String value2) {
            addCriterion("bank_branch between", value1, value2, "bankBranch");
            return (Criteria) this;
        }

        public Criteria andBankBranchNotBetween(String value1, String value2) {
            addCriterion("bank_branch not between", value1, value2, "bankBranch");
            return (Criteria) this;
        }

        public Criteria andBankAccountNoIsNull() {
            addCriterion("bank_account_no is null");
            return (Criteria) this;
        }

        public Criteria andBankAccountNoIsNotNull() {
            addCriterion("bank_account_no is not null");
            return (Criteria) this;
        }

        public Criteria andBankAccountNoEqualTo(String value) {
            addCriterion("bank_account_no =", value, "bankAccountNo");
            return (Criteria) this;
        }

        public Criteria andBankAccountNoNotEqualTo(String value) {
            addCriterion("bank_account_no <>", value, "bankAccountNo");
            return (Criteria) this;
        }

        public Criteria andBankAccountNoGreaterThan(String value) {
            addCriterion("bank_account_no >", value, "bankAccountNo");
            return (Criteria) this;
        }

        public Criteria andBankAccountNoGreaterThanOrEqualTo(String value) {
            addCriterion("bank_account_no >=", value, "bankAccountNo");
            return (Criteria) this;
        }

        public Criteria andBankAccountNoLessThan(String value) {
            addCriterion("bank_account_no <", value, "bankAccountNo");
            return (Criteria) this;
        }

        public Criteria andBankAccountNoLessThanOrEqualTo(String value) {
            addCriterion("bank_account_no <=", value, "bankAccountNo");
            return (Criteria) this;
        }

        public Criteria andBankAccountNoLike(String value) {
            addCriterion("bank_account_no like", value, "bankAccountNo");
            return (Criteria) this;
        }

        public Criteria andBankAccountNoNotLike(String value) {
            addCriterion("bank_account_no not like", value, "bankAccountNo");
            return (Criteria) this;
        }

        public Criteria andBankAccountNoIn(List<String> values) {
            addCriterion("bank_account_no in", values, "bankAccountNo");
            return (Criteria) this;
        }

        public Criteria andBankAccountNoNotIn(List<String> values) {
            addCriterion("bank_account_no not in", values, "bankAccountNo");
            return (Criteria) this;
        }

        public Criteria andBankAccountNoBetween(String value1, String value2) {
            addCriterion("bank_account_no between", value1, value2, "bankAccountNo");
            return (Criteria) this;
        }

        public Criteria andBankAccountNoNotBetween(String value1, String value2) {
            addCriterion("bank_account_no not between", value1, value2, "bankAccountNo");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameIsNull() {
            addCriterion("bank_account_name is null");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameIsNotNull() {
            addCriterion("bank_account_name is not null");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameEqualTo(String value) {
            addCriterion("bank_account_name =", value, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameNotEqualTo(String value) {
            addCriterion("bank_account_name <>", value, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameGreaterThan(String value) {
            addCriterion("bank_account_name >", value, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameGreaterThanOrEqualTo(String value) {
            addCriterion("bank_account_name >=", value, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameLessThan(String value) {
            addCriterion("bank_account_name <", value, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameLessThanOrEqualTo(String value) {
            addCriterion("bank_account_name <=", value, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameLike(String value) {
            addCriterion("bank_account_name like", value, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameNotLike(String value) {
            addCriterion("bank_account_name not like", value, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameIn(List<String> values) {
            addCriterion("bank_account_name in", values, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameNotIn(List<String> values) {
            addCriterion("bank_account_name not in", values, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameBetween(String value1, String value2) {
            addCriterion("bank_account_name between", value1, value2, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andBankAccountNameNotBetween(String value1, String value2) {
            addCriterion("bank_account_name not between", value1, value2, "bankAccountName");
            return (Criteria) this;
        }

        public Criteria andLocationIsNull() {
            addCriterion("location is null");
            return (Criteria) this;
        }

        public Criteria andLocationIsNotNull() {
            addCriterion("location is not null");
            return (Criteria) this;
        }

        public Criteria andLocationEqualTo(Integer value) {
            addCriterion("location =", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotEqualTo(Integer value) {
            addCriterion("location <>", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationGreaterThan(Integer value) {
            addCriterion("location >", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationGreaterThanOrEqualTo(Integer value) {
            addCriterion("location >=", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLessThan(Integer value) {
            addCriterion("location <", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLessThanOrEqualTo(Integer value) {
            addCriterion("location <=", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationIn(List<Integer> values) {
            addCriterion("location in", values, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotIn(List<Integer> values) {
            addCriterion("location not in", values, "location");
            return (Criteria) this;
        }

        public Criteria andLocationBetween(Integer value1, Integer value2) {
            addCriterion("location between", value1, value2, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotBetween(Integer value1, Integer value2) {
            addCriterion("location not between", value1, value2, "location");
            return (Criteria) this;
        }

        public Criteria andCertNameIsNull() {
            addCriterion("cert_name is null");
            return (Criteria) this;
        }

        public Criteria andCertNameIsNotNull() {
            addCriterion("cert_name is not null");
            return (Criteria) this;
        }

        public Criteria andCertNameEqualTo(String value) {
            addCriterion("cert_name =", value, "certName");
            return (Criteria) this;
        }

        public Criteria andCertNameNotEqualTo(String value) {
            addCriterion("cert_name <>", value, "certName");
            return (Criteria) this;
        }

        public Criteria andCertNameGreaterThan(String value) {
            addCriterion("cert_name >", value, "certName");
            return (Criteria) this;
        }

        public Criteria andCertNameGreaterThanOrEqualTo(String value) {
            addCriterion("cert_name >=", value, "certName");
            return (Criteria) this;
        }

        public Criteria andCertNameLessThan(String value) {
            addCriterion("cert_name <", value, "certName");
            return (Criteria) this;
        }

        public Criteria andCertNameLessThanOrEqualTo(String value) {
            addCriterion("cert_name <=", value, "certName");
            return (Criteria) this;
        }

        public Criteria andCertNameLike(String value) {
            addCriterion("cert_name like", value, "certName");
            return (Criteria) this;
        }

        public Criteria andCertNameNotLike(String value) {
            addCriterion("cert_name not like", value, "certName");
            return (Criteria) this;
        }

        public Criteria andCertNameIn(List<String> values) {
            addCriterion("cert_name in", values, "certName");
            return (Criteria) this;
        }

        public Criteria andCertNameNotIn(List<String> values) {
            addCriterion("cert_name not in", values, "certName");
            return (Criteria) this;
        }

        public Criteria andCertNameBetween(String value1, String value2) {
            addCriterion("cert_name between", value1, value2, "certName");
            return (Criteria) this;
        }

        public Criteria andCertNameNotBetween(String value1, String value2) {
            addCriterion("cert_name not between", value1, value2, "certName");
            return (Criteria) this;
        }

        public Criteria andCertTypeIsNull() {
            addCriterion("cert_type is null");
            return (Criteria) this;
        }

        public Criteria andCertTypeIsNotNull() {
            addCriterion("cert_type is not null");
            return (Criteria) this;
        }

        public Criteria andCertTypeEqualTo(String value) {
            addCriterion("cert_type =", value, "certType");
            return (Criteria) this;
        }

        public Criteria andCertTypeNotEqualTo(String value) {
            addCriterion("cert_type <>", value, "certType");
            return (Criteria) this;
        }

        public Criteria andCertTypeGreaterThan(String value) {
            addCriterion("cert_type >", value, "certType");
            return (Criteria) this;
        }

        public Criteria andCertTypeGreaterThanOrEqualTo(String value) {
            addCriterion("cert_type >=", value, "certType");
            return (Criteria) this;
        }

        public Criteria andCertTypeLessThan(String value) {
            addCriterion("cert_type <", value, "certType");
            return (Criteria) this;
        }

        public Criteria andCertTypeLessThanOrEqualTo(String value) {
            addCriterion("cert_type <=", value, "certType");
            return (Criteria) this;
        }

        public Criteria andCertTypeLike(String value) {
            addCriterion("cert_type like", value, "certType");
            return (Criteria) this;
        }

        public Criteria andCertTypeNotLike(String value) {
            addCriterion("cert_type not like", value, "certType");
            return (Criteria) this;
        }

        public Criteria andCertTypeIn(List<String> values) {
            addCriterion("cert_type in", values, "certType");
            return (Criteria) this;
        }

        public Criteria andCertTypeNotIn(List<String> values) {
            addCriterion("cert_type not in", values, "certType");
            return (Criteria) this;
        }

        public Criteria andCertTypeBetween(String value1, String value2) {
            addCriterion("cert_type between", value1, value2, "certType");
            return (Criteria) this;
        }

        public Criteria andCertTypeNotBetween(String value1, String value2) {
            addCriterion("cert_type not between", value1, value2, "certType");
            return (Criteria) this;
        }

        public Criteria andCertPhoneIsNull() {
            addCriterion("cert_phone is null");
            return (Criteria) this;
        }

        public Criteria andCertPhoneIsNotNull() {
            addCriterion("cert_phone is not null");
            return (Criteria) this;
        }

        public Criteria andCertPhoneEqualTo(String value) {
            addCriterion("cert_phone =", value, "certPhone");
            return (Criteria) this;
        }

        public Criteria andCertPhoneNotEqualTo(String value) {
            addCriterion("cert_phone <>", value, "certPhone");
            return (Criteria) this;
        }

        public Criteria andCertPhoneGreaterThan(String value) {
            addCriterion("cert_phone >", value, "certPhone");
            return (Criteria) this;
        }

        public Criteria andCertPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("cert_phone >=", value, "certPhone");
            return (Criteria) this;
        }

        public Criteria andCertPhoneLessThan(String value) {
            addCriterion("cert_phone <", value, "certPhone");
            return (Criteria) this;
        }

        public Criteria andCertPhoneLessThanOrEqualTo(String value) {
            addCriterion("cert_phone <=", value, "certPhone");
            return (Criteria) this;
        }

        public Criteria andCertPhoneLike(String value) {
            addCriterion("cert_phone like", value, "certPhone");
            return (Criteria) this;
        }

        public Criteria andCertPhoneNotLike(String value) {
            addCriterion("cert_phone not like", value, "certPhone");
            return (Criteria) this;
        }

        public Criteria andCertPhoneIn(List<String> values) {
            addCriterion("cert_phone in", values, "certPhone");
            return (Criteria) this;
        }

        public Criteria andCertPhoneNotIn(List<String> values) {
            addCriterion("cert_phone not in", values, "certPhone");
            return (Criteria) this;
        }

        public Criteria andCertPhoneBetween(String value1, String value2) {
            addCriterion("cert_phone between", value1, value2, "certPhone");
            return (Criteria) this;
        }

        public Criteria andCertPhoneNotBetween(String value1, String value2) {
            addCriterion("cert_phone not between", value1, value2, "certPhone");
            return (Criteria) this;
        }

        public Criteria andCertIdIsNull() {
            addCriterion("cert_id is null");
            return (Criteria) this;
        }

        public Criteria andCertIdIsNotNull() {
            addCriterion("cert_id is not null");
            return (Criteria) this;
        }

        public Criteria andCertIdEqualTo(String value) {
            addCriterion("cert_id =", value, "certId");
            return (Criteria) this;
        }

        public Criteria andCertIdNotEqualTo(String value) {
            addCriterion("cert_id <>", value, "certId");
            return (Criteria) this;
        }

        public Criteria andCertIdGreaterThan(String value) {
            addCriterion("cert_id >", value, "certId");
            return (Criteria) this;
        }

        public Criteria andCertIdGreaterThanOrEqualTo(String value) {
            addCriterion("cert_id >=", value, "certId");
            return (Criteria) this;
        }

        public Criteria andCertIdLessThan(String value) {
            addCriterion("cert_id <", value, "certId");
            return (Criteria) this;
        }

        public Criteria andCertIdLessThanOrEqualTo(String value) {
            addCriterion("cert_id <=", value, "certId");
            return (Criteria) this;
        }

        public Criteria andCertIdLike(String value) {
            addCriterion("cert_id like", value, "certId");
            return (Criteria) this;
        }

        public Criteria andCertIdNotLike(String value) {
            addCriterion("cert_id not like", value, "certId");
            return (Criteria) this;
        }

        public Criteria andCertIdIn(List<String> values) {
            addCriterion("cert_id in", values, "certId");
            return (Criteria) this;
        }

        public Criteria andCertIdNotIn(List<String> values) {
            addCriterion("cert_id not in", values, "certId");
            return (Criteria) this;
        }

        public Criteria andCertIdBetween(String value1, String value2) {
            addCriterion("cert_id between", value1, value2, "certId");
            return (Criteria) this;
        }

        public Criteria andCertIdNotBetween(String value1, String value2) {
            addCriterion("cert_id not between", value1, value2, "certId");
            return (Criteria) this;
        }

        public Criteria andBizLinkManIsNull() {
            addCriterion("biz_link_man is null");
            return (Criteria) this;
        }

        public Criteria andBizLinkManIsNotNull() {
            addCriterion("biz_link_man is not null");
            return (Criteria) this;
        }

        public Criteria andBizLinkManEqualTo(String value) {
            addCriterion("biz_link_man =", value, "bizLinkMan");
            return (Criteria) this;
        }

        public Criteria andBizLinkManNotEqualTo(String value) {
            addCriterion("biz_link_man <>", value, "bizLinkMan");
            return (Criteria) this;
        }

        public Criteria andBizLinkManGreaterThan(String value) {
            addCriterion("biz_link_man >", value, "bizLinkMan");
            return (Criteria) this;
        }

        public Criteria andBizLinkManGreaterThanOrEqualTo(String value) {
            addCriterion("biz_link_man >=", value, "bizLinkMan");
            return (Criteria) this;
        }

        public Criteria andBizLinkManLessThan(String value) {
            addCriterion("biz_link_man <", value, "bizLinkMan");
            return (Criteria) this;
        }

        public Criteria andBizLinkManLessThanOrEqualTo(String value) {
            addCriterion("biz_link_man <=", value, "bizLinkMan");
            return (Criteria) this;
        }

        public Criteria andBizLinkManLike(String value) {
            addCriterion("biz_link_man like", value, "bizLinkMan");
            return (Criteria) this;
        }

        public Criteria andBizLinkManNotLike(String value) {
            addCriterion("biz_link_man not like", value, "bizLinkMan");
            return (Criteria) this;
        }

        public Criteria andBizLinkManIn(List<String> values) {
            addCriterion("biz_link_man in", values, "bizLinkMan");
            return (Criteria) this;
        }

        public Criteria andBizLinkManNotIn(List<String> values) {
            addCriterion("biz_link_man not in", values, "bizLinkMan");
            return (Criteria) this;
        }

        public Criteria andBizLinkManBetween(String value1, String value2) {
            addCriterion("biz_link_man between", value1, value2, "bizLinkMan");
            return (Criteria) this;
        }

        public Criteria andBizLinkManNotBetween(String value1, String value2) {
            addCriterion("biz_link_man not between", value1, value2, "bizLinkMan");
            return (Criteria) this;
        }

        public Criteria andBizPhoneIsNull() {
            addCriterion("biz_phone is null");
            return (Criteria) this;
        }

        public Criteria andBizPhoneIsNotNull() {
            addCriterion("biz_phone is not null");
            return (Criteria) this;
        }

        public Criteria andBizPhoneEqualTo(String value) {
            addCriterion("biz_phone =", value, "bizPhone");
            return (Criteria) this;
        }

        public Criteria andBizPhoneNotEqualTo(String value) {
            addCriterion("biz_phone <>", value, "bizPhone");
            return (Criteria) this;
        }

        public Criteria andBizPhoneGreaterThan(String value) {
            addCriterion("biz_phone >", value, "bizPhone");
            return (Criteria) this;
        }

        public Criteria andBizPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("biz_phone >=", value, "bizPhone");
            return (Criteria) this;
        }

        public Criteria andBizPhoneLessThan(String value) {
            addCriterion("biz_phone <", value, "bizPhone");
            return (Criteria) this;
        }

        public Criteria andBizPhoneLessThanOrEqualTo(String value) {
            addCriterion("biz_phone <=", value, "bizPhone");
            return (Criteria) this;
        }

        public Criteria andBizPhoneLike(String value) {
            addCriterion("biz_phone like", value, "bizPhone");
            return (Criteria) this;
        }

        public Criteria andBizPhoneNotLike(String value) {
            addCriterion("biz_phone not like", value, "bizPhone");
            return (Criteria) this;
        }

        public Criteria andBizPhoneIn(List<String> values) {
            addCriterion("biz_phone in", values, "bizPhone");
            return (Criteria) this;
        }

        public Criteria andBizPhoneNotIn(List<String> values) {
            addCriterion("biz_phone not in", values, "bizPhone");
            return (Criteria) this;
        }

        public Criteria andBizPhoneBetween(String value1, String value2) {
            addCriterion("biz_phone between", value1, value2, "bizPhone");
            return (Criteria) this;
        }

        public Criteria andBizPhoneNotBetween(String value1, String value2) {
            addCriterion("biz_phone not between", value1, value2, "bizPhone");
            return (Criteria) this;
        }

        public Criteria andStartValIsNull() {
            addCriterion("start_val is null");
            return (Criteria) this;
        }

        public Criteria andStartValIsNotNull() {
            addCriterion("start_val is not null");
            return (Criteria) this;
        }

        public Criteria andStartValEqualTo(BigDecimal value) {
            addCriterion("start_val =", value, "startVal");
            return (Criteria) this;
        }

        public Criteria andStartValNotEqualTo(BigDecimal value) {
            addCriterion("start_val <>", value, "startVal");
            return (Criteria) this;
        }

        public Criteria andStartValGreaterThan(BigDecimal value) {
            addCriterion("start_val >", value, "startVal");
            return (Criteria) this;
        }

        public Criteria andStartValGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("start_val >=", value, "startVal");
            return (Criteria) this;
        }

        public Criteria andStartValLessThan(BigDecimal value) {
            addCriterion("start_val <", value, "startVal");
            return (Criteria) this;
        }

        public Criteria andStartValLessThanOrEqualTo(BigDecimal value) {
            addCriterion("start_val <=", value, "startVal");
            return (Criteria) this;
        }

        public Criteria andStartValIn(List<BigDecimal> values) {
            addCriterion("start_val in", values, "startVal");
            return (Criteria) this;
        }

        public Criteria andStartValNotIn(List<BigDecimal> values) {
            addCriterion("start_val not in", values, "startVal");
            return (Criteria) this;
        }

        public Criteria andStartValBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("start_val between", value1, value2, "startVal");
            return (Criteria) this;
        }

        public Criteria andStartValNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("start_val not between", value1, value2, "startVal");
            return (Criteria) this;
        }

        public Criteria andEndValIsNull() {
            addCriterion("end_val is null");
            return (Criteria) this;
        }

        public Criteria andEndValIsNotNull() {
            addCriterion("end_val is not null");
            return (Criteria) this;
        }

        public Criteria andEndValEqualTo(BigDecimal value) {
            addCriterion("end_val =", value, "endVal");
            return (Criteria) this;
        }

        public Criteria andEndValNotEqualTo(BigDecimal value) {
            addCriterion("end_val <>", value, "endVal");
            return (Criteria) this;
        }

        public Criteria andEndValGreaterThan(BigDecimal value) {
            addCriterion("end_val >", value, "endVal");
            return (Criteria) this;
        }

        public Criteria andEndValGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("end_val >=", value, "endVal");
            return (Criteria) this;
        }

        public Criteria andEndValLessThan(BigDecimal value) {
            addCriterion("end_val <", value, "endVal");
            return (Criteria) this;
        }

        public Criteria andEndValLessThanOrEqualTo(BigDecimal value) {
            addCriterion("end_val <=", value, "endVal");
            return (Criteria) this;
        }

        public Criteria andEndValIn(List<BigDecimal> values) {
            addCriterion("end_val in", values, "endVal");
            return (Criteria) this;
        }

        public Criteria andEndValNotIn(List<BigDecimal> values) {
            addCriterion("end_val not in", values, "endVal");
            return (Criteria) this;
        }

        public Criteria andEndValBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("end_val between", value1, value2, "endVal");
            return (Criteria) this;
        }

        public Criteria andEndValNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("end_val not between", value1, value2, "endVal");
            return (Criteria) this;
        }

        public Criteria andFeeTypeIsNull() {
            addCriterion("fee_type is null");
            return (Criteria) this;
        }

        public Criteria andFeeTypeIsNotNull() {
            addCriterion("fee_type is not null");
            return (Criteria) this;
        }

        public Criteria andFeeTypeEqualTo(String value) {
            addCriterion("fee_type =", value, "feeType");
            return (Criteria) this;
        }

        public Criteria andFeeTypeNotEqualTo(String value) {
            addCriterion("fee_type <>", value, "feeType");
            return (Criteria) this;
        }

        public Criteria andFeeTypeGreaterThan(String value) {
            addCriterion("fee_type >", value, "feeType");
            return (Criteria) this;
        }

        public Criteria andFeeTypeGreaterThanOrEqualTo(String value) {
            addCriterion("fee_type >=", value, "feeType");
            return (Criteria) this;
        }

        public Criteria andFeeTypeLessThan(String value) {
            addCriterion("fee_type <", value, "feeType");
            return (Criteria) this;
        }

        public Criteria andFeeTypeLessThanOrEqualTo(String value) {
            addCriterion("fee_type <=", value, "feeType");
            return (Criteria) this;
        }

        public Criteria andFeeTypeLike(String value) {
            addCriterion("fee_type like", value, "feeType");
            return (Criteria) this;
        }

        public Criteria andFeeTypeNotLike(String value) {
            addCriterion("fee_type not like", value, "feeType");
            return (Criteria) this;
        }

        public Criteria andFeeTypeIn(List<String> values) {
            addCriterion("fee_type in", values, "feeType");
            return (Criteria) this;
        }

        public Criteria andFeeTypeNotIn(List<String> values) {
            addCriterion("fee_type not in", values, "feeType");
            return (Criteria) this;
        }

        public Criteria andFeeTypeBetween(String value1, String value2) {
            addCriterion("fee_type between", value1, value2, "feeType");
            return (Criteria) this;
        }

        public Criteria andFeeTypeNotBetween(String value1, String value2) {
            addCriterion("fee_type not between", value1, value2, "feeType");
            return (Criteria) this;
        }

        public Criteria andFeeValueIsNull() {
            addCriterion("fee_value is null");
            return (Criteria) this;
        }

        public Criteria andFeeValueIsNotNull() {
            addCriterion("fee_value is not null");
            return (Criteria) this;
        }

        public Criteria andFeeValueEqualTo(String value) {
            addCriterion("fee_value =", value, "feeValue");
            return (Criteria) this;
        }

        public Criteria andFeeValueNotEqualTo(String value) {
            addCriterion("fee_value <>", value, "feeValue");
            return (Criteria) this;
        }

        public Criteria andFeeValueGreaterThan(String value) {
            addCriterion("fee_value >", value, "feeValue");
            return (Criteria) this;
        }

        public Criteria andFeeValueGreaterThanOrEqualTo(String value) {
            addCriterion("fee_value >=", value, "feeValue");
            return (Criteria) this;
        }

        public Criteria andFeeValueLessThan(String value) {
            addCriterion("fee_value <", value, "feeValue");
            return (Criteria) this;
        }

        public Criteria andFeeValueLessThanOrEqualTo(String value) {
            addCriterion("fee_value <=", value, "feeValue");
            return (Criteria) this;
        }

        public Criteria andFeeValueLike(String value) {
            addCriterion("fee_value like", value, "feeValue");
            return (Criteria) this;
        }

        public Criteria andFeeValueNotLike(String value) {
            addCriterion("fee_value not like", value, "feeValue");
            return (Criteria) this;
        }

        public Criteria andFeeValueIn(List<String> values) {
            addCriterion("fee_value in", values, "feeValue");
            return (Criteria) this;
        }

        public Criteria andFeeValueNotIn(List<String> values) {
            addCriterion("fee_value not in", values, "feeValue");
            return (Criteria) this;
        }

        public Criteria andFeeValueBetween(String value1, String value2) {
            addCriterion("fee_value between", value1, value2, "feeValue");
            return (Criteria) this;
        }

        public Criteria andFeeValueNotBetween(String value1, String value2) {
            addCriterion("fee_value not between", value1, value2, "feeValue");
            return (Criteria) this;
        }

        public Criteria andMinValueIsNull() {
            addCriterion("min_value is null");
            return (Criteria) this;
        }

        public Criteria andMinValueIsNotNull() {
            addCriterion("min_value is not null");
            return (Criteria) this;
        }

        public Criteria andMinValueEqualTo(String value) {
            addCriterion("min_value =", value, "minValue");
            return (Criteria) this;
        }

        public Criteria andMinValueNotEqualTo(String value) {
            addCriterion("min_value <>", value, "minValue");
            return (Criteria) this;
        }

        public Criteria andMinValueGreaterThan(String value) {
            addCriterion("min_value >", value, "minValue");
            return (Criteria) this;
        }

        public Criteria andMinValueGreaterThanOrEqualTo(String value) {
            addCriterion("min_value >=", value, "minValue");
            return (Criteria) this;
        }

        public Criteria andMinValueLessThan(String value) {
            addCriterion("min_value <", value, "minValue");
            return (Criteria) this;
        }

        public Criteria andMinValueLessThanOrEqualTo(String value) {
            addCriterion("min_value <=", value, "minValue");
            return (Criteria) this;
        }

        public Criteria andMinValueLike(String value) {
            addCriterion("min_value like", value, "minValue");
            return (Criteria) this;
        }

        public Criteria andMinValueNotLike(String value) {
            addCriterion("min_value not like", value, "minValue");
            return (Criteria) this;
        }

        public Criteria andMinValueIn(List<String> values) {
            addCriterion("min_value in", values, "minValue");
            return (Criteria) this;
        }

        public Criteria andMinValueNotIn(List<String> values) {
            addCriterion("min_value not in", values, "minValue");
            return (Criteria) this;
        }

        public Criteria andMinValueBetween(String value1, String value2) {
            addCriterion("min_value between", value1, value2, "minValue");
            return (Criteria) this;
        }

        public Criteria andMinValueNotBetween(String value1, String value2) {
            addCriterion("min_value not between", value1, value2, "minValue");
            return (Criteria) this;
        }

        public Criteria andMaxValueIsNull() {
            addCriterion("max_value is null");
            return (Criteria) this;
        }

        public Criteria andMaxValueIsNotNull() {
            addCriterion("max_value is not null");
            return (Criteria) this;
        }

        public Criteria andMaxValueEqualTo(String value) {
            addCriterion("max_value =", value, "maxValue");
            return (Criteria) this;
        }

        public Criteria andMaxValueNotEqualTo(String value) {
            addCriterion("max_value <>", value, "maxValue");
            return (Criteria) this;
        }

        public Criteria andMaxValueGreaterThan(String value) {
            addCriterion("max_value >", value, "maxValue");
            return (Criteria) this;
        }

        public Criteria andMaxValueGreaterThanOrEqualTo(String value) {
            addCriterion("max_value >=", value, "maxValue");
            return (Criteria) this;
        }

        public Criteria andMaxValueLessThan(String value) {
            addCriterion("max_value <", value, "maxValue");
            return (Criteria) this;
        }

        public Criteria andMaxValueLessThanOrEqualTo(String value) {
            addCriterion("max_value <=", value, "maxValue");
            return (Criteria) this;
        }

        public Criteria andMaxValueLike(String value) {
            addCriterion("max_value like", value, "maxValue");
            return (Criteria) this;
        }

        public Criteria andMaxValueNotLike(String value) {
            addCriterion("max_value not like", value, "maxValue");
            return (Criteria) this;
        }

        public Criteria andMaxValueIn(List<String> values) {
            addCriterion("max_value in", values, "maxValue");
            return (Criteria) this;
        }

        public Criteria andMaxValueNotIn(List<String> values) {
            addCriterion("max_value not in", values, "maxValue");
            return (Criteria) this;
        }

        public Criteria andMaxValueBetween(String value1, String value2) {
            addCriterion("max_value between", value1, value2, "maxValue");
            return (Criteria) this;
        }

        public Criteria andMaxValueNotBetween(String value1, String value2) {
            addCriterion("max_value not between", value1, value2, "maxValue");
            return (Criteria) this;
        }

        public Criteria andRefundFeeruleFlagIsNull() {
            addCriterion("refund_feeRule_flag is null");
            return (Criteria) this;
        }

        public Criteria andRefundFeeruleFlagIsNotNull() {
            addCriterion("refund_feeRule_flag is not null");
            return (Criteria) this;
        }

        public Criteria andRefundFeeruleFlagEqualTo(Integer value) {
            addCriterion("refund_feeRule_flag =", value, "refundFeeruleFlag");
            return (Criteria) this;
        }

        public Criteria andRefundFeeruleFlagNotEqualTo(Integer value) {
            addCriterion("refund_feeRule_flag <>", value, "refundFeeruleFlag");
            return (Criteria) this;
        }

        public Criteria andRefundFeeruleFlagGreaterThan(Integer value) {
            addCriterion("refund_feeRule_flag >", value, "refundFeeruleFlag");
            return (Criteria) this;
        }

        public Criteria andRefundFeeruleFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("refund_feeRule_flag >=", value, "refundFeeruleFlag");
            return (Criteria) this;
        }

        public Criteria andRefundFeeruleFlagLessThan(Integer value) {
            addCriterion("refund_feeRule_flag <", value, "refundFeeruleFlag");
            return (Criteria) this;
        }

        public Criteria andRefundFeeruleFlagLessThanOrEqualTo(Integer value) {
            addCriterion("refund_feeRule_flag <=", value, "refundFeeruleFlag");
            return (Criteria) this;
        }

        public Criteria andRefundFeeruleFlagIn(List<Integer> values) {
            addCriterion("refund_feeRule_flag in", values, "refundFeeruleFlag");
            return (Criteria) this;
        }

        public Criteria andRefundFeeruleFlagNotIn(List<Integer> values) {
            addCriterion("refund_feeRule_flag not in", values, "refundFeeruleFlag");
            return (Criteria) this;
        }

        public Criteria andRefundFeeruleFlagBetween(Integer value1, Integer value2) {
            addCriterion("refund_feeRule_flag between", value1, value2, "refundFeeruleFlag");
            return (Criteria) this;
        }

        public Criteria andRefundFeeruleFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("refund_feeRule_flag not between", value1, value2, "refundFeeruleFlag");
            return (Criteria) this;
        }

        public Criteria andSettleTdflagIsNull() {
            addCriterion("settle_tdflag is null");
            return (Criteria) this;
        }

        public Criteria andSettleTdflagIsNotNull() {
            addCriterion("settle_tdflag is not null");
            return (Criteria) this;
        }

        public Criteria andSettleTdflagEqualTo(String value) {
            addCriterion("settle_tdflag =", value, "settleTdflag");
            return (Criteria) this;
        }

        public Criteria andSettleTdflagNotEqualTo(String value) {
            addCriterion("settle_tdflag <>", value, "settleTdflag");
            return (Criteria) this;
        }

        public Criteria andSettleTdflagGreaterThan(String value) {
            addCriterion("settle_tdflag >", value, "settleTdflag");
            return (Criteria) this;
        }

        public Criteria andSettleTdflagGreaterThanOrEqualTo(String value) {
            addCriterion("settle_tdflag >=", value, "settleTdflag");
            return (Criteria) this;
        }

        public Criteria andSettleTdflagLessThan(String value) {
            addCriterion("settle_tdflag <", value, "settleTdflag");
            return (Criteria) this;
        }

        public Criteria andSettleTdflagLessThanOrEqualTo(String value) {
            addCriterion("settle_tdflag <=", value, "settleTdflag");
            return (Criteria) this;
        }

        public Criteria andSettleTdflagLike(String value) {
            addCriterion("settle_tdflag like", value, "settleTdflag");
            return (Criteria) this;
        }

        public Criteria andSettleTdflagNotLike(String value) {
            addCriterion("settle_tdflag not like", value, "settleTdflag");
            return (Criteria) this;
        }

        public Criteria andSettleTdflagIn(List<String> values) {
            addCriterion("settle_tdflag in", values, "settleTdflag");
            return (Criteria) this;
        }

        public Criteria andSettleTdflagNotIn(List<String> values) {
            addCriterion("settle_tdflag not in", values, "settleTdflag");
            return (Criteria) this;
        }

        public Criteria andSettleTdflagBetween(String value1, String value2) {
            addCriterion("settle_tdflag between", value1, value2, "settleTdflag");
            return (Criteria) this;
        }

        public Criteria andSettleTdflagNotBetween(String value1, String value2) {
            addCriterion("settle_tdflag not between", value1, value2, "settleTdflag");
            return (Criteria) this;
        }

        public Criteria andSettleCycleIsNull() {
            addCriterion("settle_cycle is null");
            return (Criteria) this;
        }

        public Criteria andSettleCycleIsNotNull() {
            addCriterion("settle_cycle is not null");
            return (Criteria) this;
        }

        public Criteria andSettleCycleEqualTo(Integer value) {
            addCriterion("settle_cycle =", value, "settleCycle");
            return (Criteria) this;
        }

        public Criteria andSettleCycleNotEqualTo(Integer value) {
            addCriterion("settle_cycle <>", value, "settleCycle");
            return (Criteria) this;
        }

        public Criteria andSettleCycleGreaterThan(Integer value) {
            addCriterion("settle_cycle >", value, "settleCycle");
            return (Criteria) this;
        }

        public Criteria andSettleCycleGreaterThanOrEqualTo(Integer value) {
            addCriterion("settle_cycle >=", value, "settleCycle");
            return (Criteria) this;
        }

        public Criteria andSettleCycleLessThan(Integer value) {
            addCriterion("settle_cycle <", value, "settleCycle");
            return (Criteria) this;
        }

        public Criteria andSettleCycleLessThanOrEqualTo(Integer value) {
            addCriterion("settle_cycle <=", value, "settleCycle");
            return (Criteria) this;
        }

        public Criteria andSettleCycleIn(List<Integer> values) {
            addCriterion("settle_cycle in", values, "settleCycle");
            return (Criteria) this;
        }

        public Criteria andSettleCycleNotIn(List<Integer> values) {
            addCriterion("settle_cycle not in", values, "settleCycle");
            return (Criteria) this;
        }

        public Criteria andSettleCycleBetween(Integer value1, Integer value2) {
            addCriterion("settle_cycle between", value1, value2, "settleCycle");
            return (Criteria) this;
        }

        public Criteria andSettleCycleNotBetween(Integer value1, Integer value2) {
            addCriterion("settle_cycle not between", value1, value2, "settleCycle");
            return (Criteria) this;
        }

        public Criteria andMinTransferAmtIsNull() {
            addCriterion("min_transfer_amt is null");
            return (Criteria) this;
        }

        public Criteria andMinTransferAmtIsNotNull() {
            addCriterion("min_transfer_amt is not null");
            return (Criteria) this;
        }

        public Criteria andMinTransferAmtEqualTo(BigDecimal value) {
            addCriterion("min_transfer_amt =", value, "minTransferAmt");
            return (Criteria) this;
        }

        public Criteria andMinTransferAmtNotEqualTo(BigDecimal value) {
            addCriterion("min_transfer_amt <>", value, "minTransferAmt");
            return (Criteria) this;
        }

        public Criteria andMinTransferAmtGreaterThan(BigDecimal value) {
            addCriterion("min_transfer_amt >", value, "minTransferAmt");
            return (Criteria) this;
        }

        public Criteria andMinTransferAmtGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("min_transfer_amt >=", value, "minTransferAmt");
            return (Criteria) this;
        }

        public Criteria andMinTransferAmtLessThan(BigDecimal value) {
            addCriterion("min_transfer_amt <", value, "minTransferAmt");
            return (Criteria) this;
        }

        public Criteria andMinTransferAmtLessThanOrEqualTo(BigDecimal value) {
            addCriterion("min_transfer_amt <=", value, "minTransferAmt");
            return (Criteria) this;
        }

        public Criteria andMinTransferAmtIn(List<BigDecimal> values) {
            addCriterion("min_transfer_amt in", values, "minTransferAmt");
            return (Criteria) this;
        }

        public Criteria andMinTransferAmtNotIn(List<BigDecimal> values) {
            addCriterion("min_transfer_amt not in", values, "minTransferAmt");
            return (Criteria) this;
        }

        public Criteria andMinTransferAmtBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("min_transfer_amt between", value1, value2, "minTransferAmt");
            return (Criteria) this;
        }

        public Criteria andMinTransferAmtNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("min_transfer_amt not between", value1, value2, "minTransferAmt");
            return (Criteria) this;
        }

        public Criteria andPayTypeIsNull() {
            addCriterion("pay_type is null");
            return (Criteria) this;
        }

        public Criteria andPayTypeIsNotNull() {
            addCriterion("pay_type is not null");
            return (Criteria) this;
        }

        public Criteria andPayTypeEqualTo(String value) {
            addCriterion("pay_type =", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotEqualTo(String value) {
            addCriterion("pay_type <>", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeGreaterThan(String value) {
            addCriterion("pay_type >", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeGreaterThanOrEqualTo(String value) {
            addCriterion("pay_type >=", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLessThan(String value) {
            addCriterion("pay_type <", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLessThanOrEqualTo(String value) {
            addCriterion("pay_type <=", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLike(String value) {
            addCriterion("pay_type like", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotLike(String value) {
            addCriterion("pay_type not like", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeIn(List<String> values) {
            addCriterion("pay_type in", values, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotIn(List<String> values) {
            addCriterion("pay_type not in", values, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeBetween(String value1, String value2) {
            addCriterion("pay_type between", value1, value2, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotBetween(String value1, String value2) {
            addCriterion("pay_type not between", value1, value2, "payType");
            return (Criteria) this;
        }

        public Criteria andMerchDivIsNull() {
            addCriterion("merch_div is null");
            return (Criteria) this;
        }

        public Criteria andMerchDivIsNotNull() {
            addCriterion("merch_div is not null");
            return (Criteria) this;
        }

        public Criteria andMerchDivEqualTo(String value) {
            addCriterion("merch_div =", value, "merchDiv");
            return (Criteria) this;
        }

        public Criteria andMerchDivNotEqualTo(String value) {
            addCriterion("merch_div <>", value, "merchDiv");
            return (Criteria) this;
        }

        public Criteria andMerchDivGreaterThan(String value) {
            addCriterion("merch_div >", value, "merchDiv");
            return (Criteria) this;
        }

        public Criteria andMerchDivGreaterThanOrEqualTo(String value) {
            addCriterion("merch_div >=", value, "merchDiv");
            return (Criteria) this;
        }

        public Criteria andMerchDivLessThan(String value) {
            addCriterion("merch_div <", value, "merchDiv");
            return (Criteria) this;
        }

        public Criteria andMerchDivLessThanOrEqualTo(String value) {
            addCriterion("merch_div <=", value, "merchDiv");
            return (Criteria) this;
        }

        public Criteria andMerchDivLike(String value) {
            addCriterion("merch_div like", value, "merchDiv");
            return (Criteria) this;
        }

        public Criteria andMerchDivNotLike(String value) {
            addCriterion("merch_div not like", value, "merchDiv");
            return (Criteria) this;
        }

        public Criteria andMerchDivIn(List<String> values) {
            addCriterion("merch_div in", values, "merchDiv");
            return (Criteria) this;
        }

        public Criteria andMerchDivNotIn(List<String> values) {
            addCriterion("merch_div not in", values, "merchDiv");
            return (Criteria) this;
        }

        public Criteria andMerchDivBetween(String value1, String value2) {
            addCriterion("merch_div between", value1, value2, "merchDiv");
            return (Criteria) this;
        }

        public Criteria andMerchDivNotBetween(String value1, String value2) {
            addCriterion("merch_div not between", value1, value2, "merchDiv");
            return (Criteria) this;
        }

        public Criteria andMerchantNameIsNull() {
            addCriterion("merchant_name is null");
            return (Criteria) this;
        }

        public Criteria andMerchantNameIsNotNull() {
            addCriterion("merchant_name is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantNameEqualTo(String value) {
            addCriterion("merchant_name =", value, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantNameNotEqualTo(String value) {
            addCriterion("merchant_name <>", value, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantNameGreaterThan(String value) {
            addCriterion("merchant_name >", value, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantNameGreaterThanOrEqualTo(String value) {
            addCriterion("merchant_name >=", value, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantNameLessThan(String value) {
            addCriterion("merchant_name <", value, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantNameLessThanOrEqualTo(String value) {
            addCriterion("merchant_name <=", value, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantNameLike(String value) {
            addCriterion("merchant_name like", value, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantNameNotLike(String value) {
            addCriterion("merchant_name not like", value, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantNameIn(List<String> values) {
            addCriterion("merchant_name in", values, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantNameNotIn(List<String> values) {
            addCriterion("merchant_name not in", values, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantNameBetween(String value1, String value2) {
            addCriterion("merchant_name between", value1, value2, "merchantName");
            return (Criteria) this;
        }

        public Criteria andMerchantNameNotBetween(String value1, String value2) {
            addCriterion("merchant_name not between", value1, value2, "merchantName");
            return (Criteria) this;
        }

        public Criteria andLicenceNoIsNull() {
            addCriterion("licence_no is null");
            return (Criteria) this;
        }

        public Criteria andLicenceNoIsNotNull() {
            addCriterion("licence_no is not null");
            return (Criteria) this;
        }

        public Criteria andLicenceNoEqualTo(String value) {
            addCriterion("licence_no =", value, "licenceNo");
            return (Criteria) this;
        }

        public Criteria andLicenceNoNotEqualTo(String value) {
            addCriterion("licence_no <>", value, "licenceNo");
            return (Criteria) this;
        }

        public Criteria andLicenceNoGreaterThan(String value) {
            addCriterion("licence_no >", value, "licenceNo");
            return (Criteria) this;
        }

        public Criteria andLicenceNoGreaterThanOrEqualTo(String value) {
            addCriterion("licence_no >=", value, "licenceNo");
            return (Criteria) this;
        }

        public Criteria andLicenceNoLessThan(String value) {
            addCriterion("licence_no <", value, "licenceNo");
            return (Criteria) this;
        }

        public Criteria andLicenceNoLessThanOrEqualTo(String value) {
            addCriterion("licence_no <=", value, "licenceNo");
            return (Criteria) this;
        }

        public Criteria andLicenceNoLike(String value) {
            addCriterion("licence_no like", value, "licenceNo");
            return (Criteria) this;
        }

        public Criteria andLicenceNoNotLike(String value) {
            addCriterion("licence_no not like", value, "licenceNo");
            return (Criteria) this;
        }

        public Criteria andLicenceNoIn(List<String> values) {
            addCriterion("licence_no in", values, "licenceNo");
            return (Criteria) this;
        }

        public Criteria andLicenceNoNotIn(List<String> values) {
            addCriterion("licence_no not in", values, "licenceNo");
            return (Criteria) this;
        }

        public Criteria andLicenceNoBetween(String value1, String value2) {
            addCriterion("licence_no between", value1, value2, "licenceNo");
            return (Criteria) this;
        }

        public Criteria andLicenceNoNotBetween(String value1, String value2) {
            addCriterion("licence_no not between", value1, value2, "licenceNo");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(Integer value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Integer value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Integer value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Integer value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Integer value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Integer> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Integer> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Integer value1, Integer value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Integer value1, Integer value2) {
            addCriterion("state not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andFailurecauseIsNull() {
            addCriterion("failureCause is null");
            return (Criteria) this;
        }

        public Criteria andFailurecauseIsNotNull() {
            addCriterion("failureCause is not null");
            return (Criteria) this;
        }

        public Criteria andFailurecauseEqualTo(String value) {
            addCriterion("failureCause =", value, "failurecause");
            return (Criteria) this;
        }

        public Criteria andFailurecauseNotEqualTo(String value) {
            addCriterion("failureCause <>", value, "failurecause");
            return (Criteria) this;
        }

        public Criteria andFailurecauseGreaterThan(String value) {
            addCriterion("failureCause >", value, "failurecause");
            return (Criteria) this;
        }

        public Criteria andFailurecauseGreaterThanOrEqualTo(String value) {
            addCriterion("failureCause >=", value, "failurecause");
            return (Criteria) this;
        }

        public Criteria andFailurecauseLessThan(String value) {
            addCriterion("failureCause <", value, "failurecause");
            return (Criteria) this;
        }

        public Criteria andFailurecauseLessThanOrEqualTo(String value) {
            addCriterion("failureCause <=", value, "failurecause");
            return (Criteria) this;
        }

        public Criteria andFailurecauseLike(String value) {
            addCriterion("failureCause like", value, "failurecause");
            return (Criteria) this;
        }

        public Criteria andFailurecauseNotLike(String value) {
            addCriterion("failureCause not like", value, "failurecause");
            return (Criteria) this;
        }

        public Criteria andFailurecauseIn(List<String> values) {
            addCriterion("failureCause in", values, "failurecause");
            return (Criteria) this;
        }

        public Criteria andFailurecauseNotIn(List<String> values) {
            addCriterion("failureCause not in", values, "failurecause");
            return (Criteria) this;
        }

        public Criteria andFailurecauseBetween(String value1, String value2) {
            addCriterion("failureCause between", value1, value2, "failurecause");
            return (Criteria) this;
        }

        public Criteria andFailurecauseNotBetween(String value1, String value2) {
            addCriterion("failureCause not between", value1, value2, "failurecause");
            return (Criteria) this;
        }

        public Criteria andMerchantNumberIsNull() {
            addCriterion("merchant_number is null");
            return (Criteria) this;
        }

        public Criteria andMerchantNumberIsNotNull() {
            addCriterion("merchant_number is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantNumberEqualTo(String value) {
            addCriterion("merchant_number =", value, "merchantNumber");
            return (Criteria) this;
        }

        public Criteria andMerchantNumberNotEqualTo(String value) {
            addCriterion("merchant_number <>", value, "merchantNumber");
            return (Criteria) this;
        }

        public Criteria andMerchantNumberGreaterThan(String value) {
            addCriterion("merchant_number >", value, "merchantNumber");
            return (Criteria) this;
        }

        public Criteria andMerchantNumberGreaterThanOrEqualTo(String value) {
            addCriterion("merchant_number >=", value, "merchantNumber");
            return (Criteria) this;
        }

        public Criteria andMerchantNumberLessThan(String value) {
            addCriterion("merchant_number <", value, "merchantNumber");
            return (Criteria) this;
        }

        public Criteria andMerchantNumberLessThanOrEqualTo(String value) {
            addCriterion("merchant_number <=", value, "merchantNumber");
            return (Criteria) this;
        }

        public Criteria andMerchantNumberLike(String value) {
            addCriterion("merchant_number like", value, "merchantNumber");
            return (Criteria) this;
        }

        public Criteria andMerchantNumberNotLike(String value) {
            addCriterion("merchant_number not like", value, "merchantNumber");
            return (Criteria) this;
        }

        public Criteria andMerchantNumberIn(List<String> values) {
            addCriterion("merchant_number in", values, "merchantNumber");
            return (Criteria) this;
        }

        public Criteria andMerchantNumberNotIn(List<String> values) {
            addCriterion("merchant_number not in", values, "merchantNumber");
            return (Criteria) this;
        }

        public Criteria andMerchantNumberBetween(String value1, String value2) {
            addCriterion("merchant_number between", value1, value2, "merchantNumber");
            return (Criteria) this;
        }

        public Criteria andMerchantNumberNotBetween(String value1, String value2) {
            addCriterion("merchant_number not between", value1, value2, "merchantNumber");
            return (Criteria) this;
        }

        public Criteria andMerchantStatusIsNull() {
            addCriterion("merchant_status is null");
            return (Criteria) this;
        }

        public Criteria andMerchantStatusIsNotNull() {
            addCriterion("merchant_status is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantStatusEqualTo(Integer value) {
            addCriterion("merchant_status =", value, "merchantStatus");
            return (Criteria) this;
        }

        public Criteria andMerchantStatusNotEqualTo(Integer value) {
            addCriterion("merchant_status <>", value, "merchantStatus");
            return (Criteria) this;
        }

        public Criteria andMerchantStatusGreaterThan(Integer value) {
            addCriterion("merchant_status >", value, "merchantStatus");
            return (Criteria) this;
        }

        public Criteria andMerchantStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("merchant_status >=", value, "merchantStatus");
            return (Criteria) this;
        }

        public Criteria andMerchantStatusLessThan(Integer value) {
            addCriterion("merchant_status <", value, "merchantStatus");
            return (Criteria) this;
        }

        public Criteria andMerchantStatusLessThanOrEqualTo(Integer value) {
            addCriterion("merchant_status <=", value, "merchantStatus");
            return (Criteria) this;
        }

        public Criteria andMerchantStatusIn(List<Integer> values) {
            addCriterion("merchant_status in", values, "merchantStatus");
            return (Criteria) this;
        }

        public Criteria andMerchantStatusNotIn(List<Integer> values) {
            addCriterion("merchant_status not in", values, "merchantStatus");
            return (Criteria) this;
        }

        public Criteria andMerchantStatusBetween(Integer value1, Integer value2) {
            addCriterion("merchant_status between", value1, value2, "merchantStatus");
            return (Criteria) this;
        }

        public Criteria andMerchantStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("merchant_status not between", value1, value2, "merchantStatus");
            return (Criteria) this;
        }

        public Criteria andBodyIsNull() {
            addCriterion("body is null");
            return (Criteria) this;
        }

        public Criteria andBodyIsNotNull() {
            addCriterion("body is not null");
            return (Criteria) this;
        }

        public Criteria andBodyEqualTo(String value) {
            addCriterion("body =", value, "body");
            return (Criteria) this;
        }

        public Criteria andBodyNotEqualTo(String value) {
            addCriterion("body <>", value, "body");
            return (Criteria) this;
        }

        public Criteria andBodyGreaterThan(String value) {
            addCriterion("body >", value, "body");
            return (Criteria) this;
        }

        public Criteria andBodyGreaterThanOrEqualTo(String value) {
            addCriterion("body >=", value, "body");
            return (Criteria) this;
        }

        public Criteria andBodyLessThan(String value) {
            addCriterion("body <", value, "body");
            return (Criteria) this;
        }

        public Criteria andBodyLessThanOrEqualTo(String value) {
            addCriterion("body <=", value, "body");
            return (Criteria) this;
        }

        public Criteria andBodyLike(String value) {
            addCriterion("body like", value, "body");
            return (Criteria) this;
        }

        public Criteria andBodyNotLike(String value) {
            addCriterion("body not like", value, "body");
            return (Criteria) this;
        }

        public Criteria andBodyIn(List<String> values) {
            addCriterion("body in", values, "body");
            return (Criteria) this;
        }

        public Criteria andBodyNotIn(List<String> values) {
            addCriterion("body not in", values, "body");
            return (Criteria) this;
        }

        public Criteria andBodyBetween(String value1, String value2) {
            addCriterion("body between", value1, value2, "body");
            return (Criteria) this;
        }

        public Criteria andBodyNotBetween(String value1, String value2) {
            addCriterion("body not between", value1, value2, "body");
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
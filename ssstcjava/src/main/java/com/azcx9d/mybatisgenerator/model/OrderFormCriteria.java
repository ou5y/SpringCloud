package com.azcx9d.mybatisgenerator.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderFormCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrderFormCriteria() {
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

        public Criteria andOrderIdIsNull() {
            addCriterion("order_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("order_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(String value) {
            addCriterion("order_id =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(String value) {
            addCriterion("order_id <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(String value) {
            addCriterion("order_id >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(String value) {
            addCriterion("order_id >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(String value) {
            addCriterion("order_id <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(String value) {
            addCriterion("order_id <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLike(String value) {
            addCriterion("order_id like", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotLike(String value) {
            addCriterion("order_id not like", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<String> values) {
            addCriterion("order_id in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<String> values) {
            addCriterion("order_id not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(String value1, String value2) {
            addCriterion("order_id between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(String value1, String value2) {
            addCriterion("order_id not between", value1, value2, "orderId");
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

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andStoreIdIsNull() {
            addCriterion("store_id is null");
            return (Criteria) this;
        }

        public Criteria andStoreIdIsNotNull() {
            addCriterion("store_id is not null");
            return (Criteria) this;
        }

        public Criteria andStoreIdEqualTo(Integer value) {
            addCriterion("store_id =", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdNotEqualTo(Integer value) {
            addCriterion("store_id <>", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdGreaterThan(Integer value) {
            addCriterion("store_id >", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("store_id >=", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdLessThan(Integer value) {
            addCriterion("store_id <", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdLessThanOrEqualTo(Integer value) {
            addCriterion("store_id <=", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdIn(List<Integer> values) {
            addCriterion("store_id in", values, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdNotIn(List<Integer> values) {
            addCriterion("store_id not in", values, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdBetween(Integer value1, Integer value2) {
            addCriterion("store_id between", value1, value2, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdNotBetween(Integer value1, Integer value2) {
            addCriterion("store_id not between", value1, value2, "storeId");
            return (Criteria) this;
        }

        public Criteria andMoneyIsNull() {
            addCriterion("money is null");
            return (Criteria) this;
        }

        public Criteria andMoneyIsNotNull() {
            addCriterion("money is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyEqualTo(Double value) {
            addCriterion("money =", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotEqualTo(Double value) {
            addCriterion("money <>", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThan(Double value) {
            addCriterion("money >", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyGreaterThanOrEqualTo(Double value) {
            addCriterion("money >=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThan(Double value) {
            addCriterion("money <", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyLessThanOrEqualTo(Double value) {
            addCriterion("money <=", value, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyIn(List<Double> values) {
            addCriterion("money in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotIn(List<Double> values) {
            addCriterion("money not in", values, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyBetween(Double value1, Double value2) {
            addCriterion("money between", value1, value2, "money");
            return (Criteria) this;
        }

        public Criteria andMoneyNotBetween(Double value1, Double value2) {
            addCriterion("money not between", value1, value2, "money");
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

        public Criteria andCaiwuTimeIsNull() {
            addCriterion("caiwu_time is null");
            return (Criteria) this;
        }

        public Criteria andCaiwuTimeIsNotNull() {
            addCriterion("caiwu_time is not null");
            return (Criteria) this;
        }

        public Criteria andCaiwuTimeEqualTo(Date value) {
            addCriterion("caiwu_time =", value, "caiwuTime");
            return (Criteria) this;
        }

        public Criteria andCaiwuTimeNotEqualTo(Date value) {
            addCriterion("caiwu_time <>", value, "caiwuTime");
            return (Criteria) this;
        }

        public Criteria andCaiwuTimeGreaterThan(Date value) {
            addCriterion("caiwu_time >", value, "caiwuTime");
            return (Criteria) this;
        }

        public Criteria andCaiwuTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("caiwu_time >=", value, "caiwuTime");
            return (Criteria) this;
        }

        public Criteria andCaiwuTimeLessThan(Date value) {
            addCriterion("caiwu_time <", value, "caiwuTime");
            return (Criteria) this;
        }

        public Criteria andCaiwuTimeLessThanOrEqualTo(Date value) {
            addCriterion("caiwu_time <=", value, "caiwuTime");
            return (Criteria) this;
        }

        public Criteria andCaiwuTimeIn(List<Date> values) {
            addCriterion("caiwu_time in", values, "caiwuTime");
            return (Criteria) this;
        }

        public Criteria andCaiwuTimeNotIn(List<Date> values) {
            addCriterion("caiwu_time not in", values, "caiwuTime");
            return (Criteria) this;
        }

        public Criteria andCaiwuTimeBetween(Date value1, Date value2) {
            addCriterion("caiwu_time between", value1, value2, "caiwuTime");
            return (Criteria) this;
        }

        public Criteria andCaiwuTimeNotBetween(Date value1, Date value2) {
            addCriterion("caiwu_time not between", value1, value2, "caiwuTime");
            return (Criteria) this;
        }

        public Criteria andSellerTimeIsNull() {
            addCriterion("seller_time is null");
            return (Criteria) this;
        }

        public Criteria andSellerTimeIsNotNull() {
            addCriterion("seller_time is not null");
            return (Criteria) this;
        }

        public Criteria andSellerTimeEqualTo(Date value) {
            addCriterion("seller_time =", value, "sellerTime");
            return (Criteria) this;
        }

        public Criteria andSellerTimeNotEqualTo(Date value) {
            addCriterion("seller_time <>", value, "sellerTime");
            return (Criteria) this;
        }

        public Criteria andSellerTimeGreaterThan(Date value) {
            addCriterion("seller_time >", value, "sellerTime");
            return (Criteria) this;
        }

        public Criteria andSellerTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("seller_time >=", value, "sellerTime");
            return (Criteria) this;
        }

        public Criteria andSellerTimeLessThan(Date value) {
            addCriterion("seller_time <", value, "sellerTime");
            return (Criteria) this;
        }

        public Criteria andSellerTimeLessThanOrEqualTo(Date value) {
            addCriterion("seller_time <=", value, "sellerTime");
            return (Criteria) this;
        }

        public Criteria andSellerTimeIn(List<Date> values) {
            addCriterion("seller_time in", values, "sellerTime");
            return (Criteria) this;
        }

        public Criteria andSellerTimeNotIn(List<Date> values) {
            addCriterion("seller_time not in", values, "sellerTime");
            return (Criteria) this;
        }

        public Criteria andSellerTimeBetween(Date value1, Date value2) {
            addCriterion("seller_time between", value1, value2, "sellerTime");
            return (Criteria) this;
        }

        public Criteria andSellerTimeNotBetween(Date value1, Date value2) {
            addCriterion("seller_time not between", value1, value2, "sellerTime");
            return (Criteria) this;
        }

        public Criteria andGoodsIdIsNull() {
            addCriterion("goods_id is null");
            return (Criteria) this;
        }

        public Criteria andGoodsIdIsNotNull() {
            addCriterion("goods_id is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsIdEqualTo(Long value) {
            addCriterion("goods_id =", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdNotEqualTo(Long value) {
            addCriterion("goods_id <>", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdGreaterThan(Long value) {
            addCriterion("goods_id >", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdGreaterThanOrEqualTo(Long value) {
            addCriterion("goods_id >=", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdLessThan(Long value) {
            addCriterion("goods_id <", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdLessThanOrEqualTo(Long value) {
            addCriterion("goods_id <=", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdIn(List<Long> values) {
            addCriterion("goods_id in", values, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdNotIn(List<Long> values) {
            addCriterion("goods_id not in", values, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdBetween(Long value1, Long value2) {
            addCriterion("goods_id between", value1, value2, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdNotBetween(Long value1, Long value2) {
            addCriterion("goods_id not between", value1, value2, "goodsId");
            return (Criteria) this;
        }

        public Criteria andRangliIsNull() {
            addCriterion("rangli is null");
            return (Criteria) this;
        }

        public Criteria andRangliIsNotNull() {
            addCriterion("rangli is not null");
            return (Criteria) this;
        }

        public Criteria andRangliEqualTo(Double value) {
            addCriterion("rangli =", value, "rangli");
            return (Criteria) this;
        }

        public Criteria andRangliNotEqualTo(Double value) {
            addCriterion("rangli <>", value, "rangli");
            return (Criteria) this;
        }

        public Criteria andRangliGreaterThan(Double value) {
            addCriterion("rangli >", value, "rangli");
            return (Criteria) this;
        }

        public Criteria andRangliGreaterThanOrEqualTo(Double value) {
            addCriterion("rangli >=", value, "rangli");
            return (Criteria) this;
        }

        public Criteria andRangliLessThan(Double value) {
            addCriterion("rangli <", value, "rangli");
            return (Criteria) this;
        }

        public Criteria andRangliLessThanOrEqualTo(Double value) {
            addCriterion("rangli <=", value, "rangli");
            return (Criteria) this;
        }

        public Criteria andRangliIn(List<Double> values) {
            addCriterion("rangli in", values, "rangli");
            return (Criteria) this;
        }

        public Criteria andRangliNotIn(List<Double> values) {
            addCriterion("rangli not in", values, "rangli");
            return (Criteria) this;
        }

        public Criteria andRangliBetween(Double value1, Double value2) {
            addCriterion("rangli between", value1, value2, "rangli");
            return (Criteria) this;
        }

        public Criteria andRangliNotBetween(Double value1, Double value2) {
            addCriterion("rangli not between", value1, value2, "rangli");
            return (Criteria) this;
        }

        public Criteria andHuikuanIdIsNull() {
            addCriterion("huikuan_id is null");
            return (Criteria) this;
        }

        public Criteria andHuikuanIdIsNotNull() {
            addCriterion("huikuan_id is not null");
            return (Criteria) this;
        }

        public Criteria andHuikuanIdEqualTo(Integer value) {
            addCriterion("huikuan_id =", value, "huikuanId");
            return (Criteria) this;
        }

        public Criteria andHuikuanIdNotEqualTo(Integer value) {
            addCriterion("huikuan_id <>", value, "huikuanId");
            return (Criteria) this;
        }

        public Criteria andHuikuanIdGreaterThan(Integer value) {
            addCriterion("huikuan_id >", value, "huikuanId");
            return (Criteria) this;
        }

        public Criteria andHuikuanIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("huikuan_id >=", value, "huikuanId");
            return (Criteria) this;
        }

        public Criteria andHuikuanIdLessThan(Integer value) {
            addCriterion("huikuan_id <", value, "huikuanId");
            return (Criteria) this;
        }

        public Criteria andHuikuanIdLessThanOrEqualTo(Integer value) {
            addCriterion("huikuan_id <=", value, "huikuanId");
            return (Criteria) this;
        }

        public Criteria andHuikuanIdIn(List<Integer> values) {
            addCriterion("huikuan_id in", values, "huikuanId");
            return (Criteria) this;
        }

        public Criteria andHuikuanIdNotIn(List<Integer> values) {
            addCriterion("huikuan_id not in", values, "huikuanId");
            return (Criteria) this;
        }

        public Criteria andHuikuanIdBetween(Integer value1, Integer value2) {
            addCriterion("huikuan_id between", value1, value2, "huikuanId");
            return (Criteria) this;
        }

        public Criteria andHuikuanIdNotBetween(Integer value1, Integer value2) {
            addCriterion("huikuan_id not between", value1, value2, "huikuanId");
            return (Criteria) this;
        }

        public Criteria andSellerIdIsNull() {
            addCriterion("seller_id is null");
            return (Criteria) this;
        }

        public Criteria andSellerIdIsNotNull() {
            addCriterion("seller_id is not null");
            return (Criteria) this;
        }

        public Criteria andSellerIdEqualTo(Integer value) {
            addCriterion("seller_id =", value, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdNotEqualTo(Integer value) {
            addCriterion("seller_id <>", value, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdGreaterThan(Integer value) {
            addCriterion("seller_id >", value, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("seller_id >=", value, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdLessThan(Integer value) {
            addCriterion("seller_id <", value, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdLessThanOrEqualTo(Integer value) {
            addCriterion("seller_id <=", value, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdIn(List<Integer> values) {
            addCriterion("seller_id in", values, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdNotIn(List<Integer> values) {
            addCriterion("seller_id not in", values, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdBetween(Integer value1, Integer value2) {
            addCriterion("seller_id between", value1, value2, "sellerId");
            return (Criteria) this;
        }

        public Criteria andSellerIdNotBetween(Integer value1, Integer value2) {
            addCriterion("seller_id not between", value1, value2, "sellerId");
            return (Criteria) this;
        }

        public Criteria andShanxinUserIsNull() {
            addCriterion("shanxin_user is null");
            return (Criteria) this;
        }

        public Criteria andShanxinUserIsNotNull() {
            addCriterion("shanxin_user is not null");
            return (Criteria) this;
        }

        public Criteria andShanxinUserEqualTo(Double value) {
            addCriterion("shanxin_user =", value, "shanxinUser");
            return (Criteria) this;
        }

        public Criteria andShanxinUserNotEqualTo(Double value) {
            addCriterion("shanxin_user <>", value, "shanxinUser");
            return (Criteria) this;
        }

        public Criteria andShanxinUserGreaterThan(Double value) {
            addCriterion("shanxin_user >", value, "shanxinUser");
            return (Criteria) this;
        }

        public Criteria andShanxinUserGreaterThanOrEqualTo(Double value) {
            addCriterion("shanxin_user >=", value, "shanxinUser");
            return (Criteria) this;
        }

        public Criteria andShanxinUserLessThan(Double value) {
            addCriterion("shanxin_user <", value, "shanxinUser");
            return (Criteria) this;
        }

        public Criteria andShanxinUserLessThanOrEqualTo(Double value) {
            addCriterion("shanxin_user <=", value, "shanxinUser");
            return (Criteria) this;
        }

        public Criteria andShanxinUserIn(List<Double> values) {
            addCriterion("shanxin_user in", values, "shanxinUser");
            return (Criteria) this;
        }

        public Criteria andShanxinUserNotIn(List<Double> values) {
            addCriterion("shanxin_user not in", values, "shanxinUser");
            return (Criteria) this;
        }

        public Criteria andShanxinUserBetween(Double value1, Double value2) {
            addCriterion("shanxin_user between", value1, value2, "shanxinUser");
            return (Criteria) this;
        }

        public Criteria andShanxinUserNotBetween(Double value1, Double value2) {
            addCriterion("shanxin_user not between", value1, value2, "shanxinUser");
            return (Criteria) this;
        }

        public Criteria andShanxinStoreIsNull() {
            addCriterion("shanxin_store is null");
            return (Criteria) this;
        }

        public Criteria andShanxinStoreIsNotNull() {
            addCriterion("shanxin_store is not null");
            return (Criteria) this;
        }

        public Criteria andShanxinStoreEqualTo(Double value) {
            addCriterion("shanxin_store =", value, "shanxinStore");
            return (Criteria) this;
        }

        public Criteria andShanxinStoreNotEqualTo(Double value) {
            addCriterion("shanxin_store <>", value, "shanxinStore");
            return (Criteria) this;
        }

        public Criteria andShanxinStoreGreaterThan(Double value) {
            addCriterion("shanxin_store >", value, "shanxinStore");
            return (Criteria) this;
        }

        public Criteria andShanxinStoreGreaterThanOrEqualTo(Double value) {
            addCriterion("shanxin_store >=", value, "shanxinStore");
            return (Criteria) this;
        }

        public Criteria andShanxinStoreLessThan(Double value) {
            addCriterion("shanxin_store <", value, "shanxinStore");
            return (Criteria) this;
        }

        public Criteria andShanxinStoreLessThanOrEqualTo(Double value) {
            addCriterion("shanxin_store <=", value, "shanxinStore");
            return (Criteria) this;
        }

        public Criteria andShanxinStoreIn(List<Double> values) {
            addCriterion("shanxin_store in", values, "shanxinStore");
            return (Criteria) this;
        }

        public Criteria andShanxinStoreNotIn(List<Double> values) {
            addCriterion("shanxin_store not in", values, "shanxinStore");
            return (Criteria) this;
        }

        public Criteria andShanxinStoreBetween(Double value1, Double value2) {
            addCriterion("shanxin_store between", value1, value2, "shanxinStore");
            return (Criteria) this;
        }

        public Criteria andShanxinStoreNotBetween(Double value1, Double value2) {
            addCriterion("shanxin_store not between", value1, value2, "shanxinStore");
            return (Criteria) this;
        }

        public Criteria andJifenUserIsNull() {
            addCriterion("jifen_user is null");
            return (Criteria) this;
        }

        public Criteria andJifenUserIsNotNull() {
            addCriterion("jifen_user is not null");
            return (Criteria) this;
        }

        public Criteria andJifenUserEqualTo(Double value) {
            addCriterion("jifen_user =", value, "jifenUser");
            return (Criteria) this;
        }

        public Criteria andJifenUserNotEqualTo(Double value) {
            addCriterion("jifen_user <>", value, "jifenUser");
            return (Criteria) this;
        }

        public Criteria andJifenUserGreaterThan(Double value) {
            addCriterion("jifen_user >", value, "jifenUser");
            return (Criteria) this;
        }

        public Criteria andJifenUserGreaterThanOrEqualTo(Double value) {
            addCriterion("jifen_user >=", value, "jifenUser");
            return (Criteria) this;
        }

        public Criteria andJifenUserLessThan(Double value) {
            addCriterion("jifen_user <", value, "jifenUser");
            return (Criteria) this;
        }

        public Criteria andJifenUserLessThanOrEqualTo(Double value) {
            addCriterion("jifen_user <=", value, "jifenUser");
            return (Criteria) this;
        }

        public Criteria andJifenUserIn(List<Double> values) {
            addCriterion("jifen_user in", values, "jifenUser");
            return (Criteria) this;
        }

        public Criteria andJifenUserNotIn(List<Double> values) {
            addCriterion("jifen_user not in", values, "jifenUser");
            return (Criteria) this;
        }

        public Criteria andJifenUserBetween(Double value1, Double value2) {
            addCriterion("jifen_user between", value1, value2, "jifenUser");
            return (Criteria) this;
        }

        public Criteria andJifenUserNotBetween(Double value1, Double value2) {
            addCriterion("jifen_user not between", value1, value2, "jifenUser");
            return (Criteria) this;
        }

        public Criteria andJifenStoreIsNull() {
            addCriterion("jifen_store is null");
            return (Criteria) this;
        }

        public Criteria andJifenStoreIsNotNull() {
            addCriterion("jifen_store is not null");
            return (Criteria) this;
        }

        public Criteria andJifenStoreEqualTo(Double value) {
            addCriterion("jifen_store =", value, "jifenStore");
            return (Criteria) this;
        }

        public Criteria andJifenStoreNotEqualTo(Double value) {
            addCriterion("jifen_store <>", value, "jifenStore");
            return (Criteria) this;
        }

        public Criteria andJifenStoreGreaterThan(Double value) {
            addCriterion("jifen_store >", value, "jifenStore");
            return (Criteria) this;
        }

        public Criteria andJifenStoreGreaterThanOrEqualTo(Double value) {
            addCriterion("jifen_store >=", value, "jifenStore");
            return (Criteria) this;
        }

        public Criteria andJifenStoreLessThan(Double value) {
            addCriterion("jifen_store <", value, "jifenStore");
            return (Criteria) this;
        }

        public Criteria andJifenStoreLessThanOrEqualTo(Double value) {
            addCriterion("jifen_store <=", value, "jifenStore");
            return (Criteria) this;
        }

        public Criteria andJifenStoreIn(List<Double> values) {
            addCriterion("jifen_store in", values, "jifenStore");
            return (Criteria) this;
        }

        public Criteria andJifenStoreNotIn(List<Double> values) {
            addCriterion("jifen_store not in", values, "jifenStore");
            return (Criteria) this;
        }

        public Criteria andJifenStoreBetween(Double value1, Double value2) {
            addCriterion("jifen_store between", value1, value2, "jifenStore");
            return (Criteria) this;
        }

        public Criteria andJifenStoreNotBetween(Double value1, Double value2) {
            addCriterion("jifen_store not between", value1, value2, "jifenStore");
            return (Criteria) this;
        }

        public Criteria andRanliMoneyIsNull() {
            addCriterion("ranli_money is null");
            return (Criteria) this;
        }

        public Criteria andRanliMoneyIsNotNull() {
            addCriterion("ranli_money is not null");
            return (Criteria) this;
        }

        public Criteria andRanliMoneyEqualTo(Long value) {
            addCriterion("ranli_money =", value, "ranliMoney");
            return (Criteria) this;
        }

        public Criteria andRanliMoneyNotEqualTo(Long value) {
            addCriterion("ranli_money <>", value, "ranliMoney");
            return (Criteria) this;
        }

        public Criteria andRanliMoneyGreaterThan(Long value) {
            addCriterion("ranli_money >", value, "ranliMoney");
            return (Criteria) this;
        }

        public Criteria andRanliMoneyGreaterThanOrEqualTo(Long value) {
            addCriterion("ranli_money >=", value, "ranliMoney");
            return (Criteria) this;
        }

        public Criteria andRanliMoneyLessThan(Long value) {
            addCriterion("ranli_money <", value, "ranliMoney");
            return (Criteria) this;
        }

        public Criteria andRanliMoneyLessThanOrEqualTo(Long value) {
            addCriterion("ranli_money <=", value, "ranliMoney");
            return (Criteria) this;
        }

        public Criteria andRanliMoneyIn(List<Long> values) {
            addCriterion("ranli_money in", values, "ranliMoney");
            return (Criteria) this;
        }

        public Criteria andRanliMoneyNotIn(List<Long> values) {
            addCriterion("ranli_money not in", values, "ranliMoney");
            return (Criteria) this;
        }

        public Criteria andRanliMoneyBetween(Long value1, Long value2) {
            addCriterion("ranli_money between", value1, value2, "ranliMoney");
            return (Criteria) this;
        }

        public Criteria andRanliMoneyNotBetween(Long value1, Long value2) {
            addCriterion("ranli_money not between", value1, value2, "ranliMoney");
            return (Criteria) this;
        }

        public Criteria andVoucherpicIsNull() {
            addCriterion("voucherPic is null");
            return (Criteria) this;
        }

        public Criteria andVoucherpicIsNotNull() {
            addCriterion("voucherPic is not null");
            return (Criteria) this;
        }

        public Criteria andVoucherpicEqualTo(String value) {
            addCriterion("voucherPic =", value, "voucherpic");
            return (Criteria) this;
        }

        public Criteria andVoucherpicNotEqualTo(String value) {
            addCriterion("voucherPic <>", value, "voucherpic");
            return (Criteria) this;
        }

        public Criteria andVoucherpicGreaterThan(String value) {
            addCriterion("voucherPic >", value, "voucherpic");
            return (Criteria) this;
        }

        public Criteria andVoucherpicGreaterThanOrEqualTo(String value) {
            addCriterion("voucherPic >=", value, "voucherpic");
            return (Criteria) this;
        }

        public Criteria andVoucherpicLessThan(String value) {
            addCriterion("voucherPic <", value, "voucherpic");
            return (Criteria) this;
        }

        public Criteria andVoucherpicLessThanOrEqualTo(String value) {
            addCriterion("voucherPic <=", value, "voucherpic");
            return (Criteria) this;
        }

        public Criteria andVoucherpicLike(String value) {
            addCriterion("voucherPic like", value, "voucherpic");
            return (Criteria) this;
        }

        public Criteria andVoucherpicNotLike(String value) {
            addCriterion("voucherPic not like", value, "voucherpic");
            return (Criteria) this;
        }

        public Criteria andVoucherpicIn(List<String> values) {
            addCriterion("voucherPic in", values, "voucherpic");
            return (Criteria) this;
        }

        public Criteria andVoucherpicNotIn(List<String> values) {
            addCriterion("voucherPic not in", values, "voucherpic");
            return (Criteria) this;
        }

        public Criteria andVoucherpicBetween(String value1, String value2) {
            addCriterion("voucherPic between", value1, value2, "voucherpic");
            return (Criteria) this;
        }

        public Criteria andVoucherpicNotBetween(String value1, String value2) {
            addCriterion("voucherPic not between", value1, value2, "voucherpic");
            return (Criteria) this;
        }

        public Criteria andOrdersourceIsNull() {
            addCriterion("orderSource is null");
            return (Criteria) this;
        }

        public Criteria andOrdersourceIsNotNull() {
            addCriterion("orderSource is not null");
            return (Criteria) this;
        }

        public Criteria andOrdersourceEqualTo(Integer value) {
            addCriterion("orderSource =", value, "ordersource");
            return (Criteria) this;
        }

        public Criteria andOrdersourceNotEqualTo(Integer value) {
            addCriterion("orderSource <>", value, "ordersource");
            return (Criteria) this;
        }

        public Criteria andOrdersourceGreaterThan(Integer value) {
            addCriterion("orderSource >", value, "ordersource");
            return (Criteria) this;
        }

        public Criteria andOrdersourceGreaterThanOrEqualTo(Integer value) {
            addCriterion("orderSource >=", value, "ordersource");
            return (Criteria) this;
        }

        public Criteria andOrdersourceLessThan(Integer value) {
            addCriterion("orderSource <", value, "ordersource");
            return (Criteria) this;
        }

        public Criteria andOrdersourceLessThanOrEqualTo(Integer value) {
            addCriterion("orderSource <=", value, "ordersource");
            return (Criteria) this;
        }

        public Criteria andOrdersourceIn(List<Integer> values) {
            addCriterion("orderSource in", values, "ordersource");
            return (Criteria) this;
        }

        public Criteria andOrdersourceNotIn(List<Integer> values) {
            addCriterion("orderSource not in", values, "ordersource");
            return (Criteria) this;
        }

        public Criteria andOrdersourceBetween(Integer value1, Integer value2) {
            addCriterion("orderSource between", value1, value2, "ordersource");
            return (Criteria) this;
        }

        public Criteria andOrdersourceNotBetween(Integer value1, Integer value2) {
            addCriterion("orderSource not between", value1, value2, "ordersource");
            return (Criteria) this;
        }

        public Criteria andCaiwuIdIsNull() {
            addCriterion("caiwu_id is null");
            return (Criteria) this;
        }

        public Criteria andCaiwuIdIsNotNull() {
            addCriterion("caiwu_id is not null");
            return (Criteria) this;
        }

        public Criteria andCaiwuIdEqualTo(Long value) {
            addCriterion("caiwu_id =", value, "caiwuId");
            return (Criteria) this;
        }

        public Criteria andCaiwuIdNotEqualTo(Long value) {
            addCriterion("caiwu_id <>", value, "caiwuId");
            return (Criteria) this;
        }

        public Criteria andCaiwuIdGreaterThan(Long value) {
            addCriterion("caiwu_id >", value, "caiwuId");
            return (Criteria) this;
        }

        public Criteria andCaiwuIdGreaterThanOrEqualTo(Long value) {
            addCriterion("caiwu_id >=", value, "caiwuId");
            return (Criteria) this;
        }

        public Criteria andCaiwuIdLessThan(Long value) {
            addCriterion("caiwu_id <", value, "caiwuId");
            return (Criteria) this;
        }

        public Criteria andCaiwuIdLessThanOrEqualTo(Long value) {
            addCriterion("caiwu_id <=", value, "caiwuId");
            return (Criteria) this;
        }

        public Criteria andCaiwuIdIn(List<Long> values) {
            addCriterion("caiwu_id in", values, "caiwuId");
            return (Criteria) this;
        }

        public Criteria andCaiwuIdNotIn(List<Long> values) {
            addCriterion("caiwu_id not in", values, "caiwuId");
            return (Criteria) this;
        }

        public Criteria andCaiwuIdBetween(Long value1, Long value2) {
            addCriterion("caiwu_id between", value1, value2, "caiwuId");
            return (Criteria) this;
        }

        public Criteria andCaiwuIdNotBetween(Long value1, Long value2) {
            addCriterion("caiwu_id not between", value1, value2, "caiwuId");
            return (Criteria) this;
        }

        public Criteria andConsumptionpicIsNull() {
            addCriterion("consumptionPic is null");
            return (Criteria) this;
        }

        public Criteria andConsumptionpicIsNotNull() {
            addCriterion("consumptionPic is not null");
            return (Criteria) this;
        }

        public Criteria andConsumptionpicEqualTo(String value) {
            addCriterion("consumptionPic =", value, "consumptionpic");
            return (Criteria) this;
        }

        public Criteria andConsumptionpicNotEqualTo(String value) {
            addCriterion("consumptionPic <>", value, "consumptionpic");
            return (Criteria) this;
        }

        public Criteria andConsumptionpicGreaterThan(String value) {
            addCriterion("consumptionPic >", value, "consumptionpic");
            return (Criteria) this;
        }

        public Criteria andConsumptionpicGreaterThanOrEqualTo(String value) {
            addCriterion("consumptionPic >=", value, "consumptionpic");
            return (Criteria) this;
        }

        public Criteria andConsumptionpicLessThan(String value) {
            addCriterion("consumptionPic <", value, "consumptionpic");
            return (Criteria) this;
        }

        public Criteria andConsumptionpicLessThanOrEqualTo(String value) {
            addCriterion("consumptionPic <=", value, "consumptionpic");
            return (Criteria) this;
        }

        public Criteria andConsumptionpicLike(String value) {
            addCriterion("consumptionPic like", value, "consumptionpic");
            return (Criteria) this;
        }

        public Criteria andConsumptionpicNotLike(String value) {
            addCriterion("consumptionPic not like", value, "consumptionpic");
            return (Criteria) this;
        }

        public Criteria andConsumptionpicIn(List<String> values) {
            addCriterion("consumptionPic in", values, "consumptionpic");
            return (Criteria) this;
        }

        public Criteria andConsumptionpicNotIn(List<String> values) {
            addCriterion("consumptionPic not in", values, "consumptionpic");
            return (Criteria) this;
        }

        public Criteria andConsumptionpicBetween(String value1, String value2) {
            addCriterion("consumptionPic between", value1, value2, "consumptionpic");
            return (Criteria) this;
        }

        public Criteria andConsumptionpicNotBetween(String value1, String value2) {
            addCriterion("consumptionPic not between", value1, value2, "consumptionpic");
            return (Criteria) this;
        }

        public Criteria andCloseTimeIsNull() {
            addCriterion("close_time is null");
            return (Criteria) this;
        }

        public Criteria andCloseTimeIsNotNull() {
            addCriterion("close_time is not null");
            return (Criteria) this;
        }

        public Criteria andCloseTimeEqualTo(Date value) {
            addCriterion("close_time =", value, "closeTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeNotEqualTo(Date value) {
            addCriterion("close_time <>", value, "closeTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeGreaterThan(Date value) {
            addCriterion("close_time >", value, "closeTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("close_time >=", value, "closeTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeLessThan(Date value) {
            addCriterion("close_time <", value, "closeTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeLessThanOrEqualTo(Date value) {
            addCriterion("close_time <=", value, "closeTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeIn(List<Date> values) {
            addCriterion("close_time in", values, "closeTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeNotIn(List<Date> values) {
            addCriterion("close_time not in", values, "closeTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeBetween(Date value1, Date value2) {
            addCriterion("close_time between", value1, value2, "closeTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeNotBetween(Date value1, Date value2) {
            addCriterion("close_time not between", value1, value2, "closeTime");
            return (Criteria) this;
        }

        public Criteria andQuotaTypeIsNull() {
            addCriterion("quota_type is null");
            return (Criteria) this;
        }

        public Criteria andQuotaTypeIsNotNull() {
            addCriterion("quota_type is not null");
            return (Criteria) this;
        }

        public Criteria andQuotaTypeEqualTo(Integer value) {
            addCriterion("quota_type =", value, "quotaType");
            return (Criteria) this;
        }

        public Criteria andQuotaTypeNotEqualTo(Integer value) {
            addCriterion("quota_type <>", value, "quotaType");
            return (Criteria) this;
        }

        public Criteria andQuotaTypeGreaterThan(Integer value) {
            addCriterion("quota_type >", value, "quotaType");
            return (Criteria) this;
        }

        public Criteria andQuotaTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("quota_type >=", value, "quotaType");
            return (Criteria) this;
        }

        public Criteria andQuotaTypeLessThan(Integer value) {
            addCriterion("quota_type <", value, "quotaType");
            return (Criteria) this;
        }

        public Criteria andQuotaTypeLessThanOrEqualTo(Integer value) {
            addCriterion("quota_type <=", value, "quotaType");
            return (Criteria) this;
        }

        public Criteria andQuotaTypeIn(List<Integer> values) {
            addCriterion("quota_type in", values, "quotaType");
            return (Criteria) this;
        }

        public Criteria andQuotaTypeNotIn(List<Integer> values) {
            addCriterion("quota_type not in", values, "quotaType");
            return (Criteria) this;
        }

        public Criteria andQuotaTypeBetween(Integer value1, Integer value2) {
            addCriterion("quota_type between", value1, value2, "quotaType");
            return (Criteria) this;
        }

        public Criteria andQuotaTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("quota_type not between", value1, value2, "quotaType");
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
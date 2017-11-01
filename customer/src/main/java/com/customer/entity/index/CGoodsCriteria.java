package com.customer.entity.index;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CGoodsCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CGoodsCriteria() {
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

        public Criteria andSpuIdIsNull() {
            addCriterion("spu_id is null");
            return (Criteria) this;
        }

        public Criteria andSpuIdIsNotNull() {
            addCriterion("spu_id is not null");
            return (Criteria) this;
        }

        public Criteria andSpuIdEqualTo(Integer value) {
            addCriterion("spu_id =", value, "spuId");
            return (Criteria) this;
        }

        public Criteria andSpuIdNotEqualTo(Integer value) {
            addCriterion("spu_id <>", value, "spuId");
            return (Criteria) this;
        }

        public Criteria andSpuIdGreaterThan(Integer value) {
            addCriterion("spu_id >", value, "spuId");
            return (Criteria) this;
        }

        public Criteria andSpuIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("spu_id >=", value, "spuId");
            return (Criteria) this;
        }

        public Criteria andSpuIdLessThan(Integer value) {
            addCriterion("spu_id <", value, "spuId");
            return (Criteria) this;
        }

        public Criteria andSpuIdLessThanOrEqualTo(Integer value) {
            addCriterion("spu_id <=", value, "spuId");
            return (Criteria) this;
        }

        public Criteria andSpuIdIn(List<Integer> values) {
            addCriterion("spu_id in", values, "spuId");
            return (Criteria) this;
        }

        public Criteria andSpuIdNotIn(List<Integer> values) {
            addCriterion("spu_id not in", values, "spuId");
            return (Criteria) this;
        }

        public Criteria andSpuIdBetween(Integer value1, Integer value2) {
            addCriterion("spu_id between", value1, value2, "spuId");
            return (Criteria) this;
        }

        public Criteria andSpuIdNotBetween(Integer value1, Integer value2) {
            addCriterion("spu_id not between", value1, value2, "spuId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdIsNull() {
            addCriterion("business_id is null");
            return (Criteria) this;
        }

        public Criteria andBusinessIdIsNotNull() {
            addCriterion("business_id is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessIdEqualTo(Integer value) {
            addCriterion("business_id =", value, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdNotEqualTo(Integer value) {
            addCriterion("business_id <>", value, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdGreaterThan(Integer value) {
            addCriterion("business_id >", value, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("business_id >=", value, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdLessThan(Integer value) {
            addCriterion("business_id <", value, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdLessThanOrEqualTo(Integer value) {
            addCriterion("business_id <=", value, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdIn(List<Integer> values) {
            addCriterion("business_id in", values, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdNotIn(List<Integer> values) {
            addCriterion("business_id not in", values, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdBetween(Integer value1, Integer value2) {
            addCriterion("business_id between", value1, value2, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdNotBetween(Integer value1, Integer value2) {
            addCriterion("business_id not between", value1, value2, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessNameIsNull() {
            addCriterion("business_name is null");
            return (Criteria) this;
        }

        public Criteria andBusinessNameIsNotNull() {
            addCriterion("business_name is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessNameEqualTo(String value) {
            addCriterion("business_name =", value, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessNameNotEqualTo(String value) {
            addCriterion("business_name <>", value, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessNameGreaterThan(String value) {
            addCriterion("business_name >", value, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessNameGreaterThanOrEqualTo(String value) {
            addCriterion("business_name >=", value, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessNameLessThan(String value) {
            addCriterion("business_name <", value, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessNameLessThanOrEqualTo(String value) {
            addCriterion("business_name <=", value, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessNameLike(String value) {
            addCriterion("business_name like", value, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessNameNotLike(String value) {
            addCriterion("business_name not like", value, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessNameIn(List<String> values) {
            addCriterion("business_name in", values, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessNameNotIn(List<String> values) {
            addCriterion("business_name not in", values, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessNameBetween(String value1, String value2) {
            addCriterion("business_name between", value1, value2, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessNameNotBetween(String value1, String value2) {
            addCriterion("business_name not between", value1, value2, "businessName");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIsNull() {
            addCriterion("category_id is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIsNotNull() {
            addCriterion("category_id is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdEqualTo(Integer value) {
            addCriterion("category_id =", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotEqualTo(Integer value) {
            addCriterion("category_id <>", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThan(Integer value) {
            addCriterion("category_id >", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("category_id >=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThan(Integer value) {
            addCriterion("category_id <", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThanOrEqualTo(Integer value) {
            addCriterion("category_id <=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIn(List<Integer> values) {
            addCriterion("category_id in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotIn(List<Integer> values) {
            addCriterion("category_id not in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdBetween(Integer value1, Integer value2) {
            addCriterion("category_id between", value1, value2, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotBetween(Integer value1, Integer value2) {
            addCriterion("category_id not between", value1, value2, "categoryId");
            return (Criteria) this;
        }

        public Criteria andBrandIdIsNull() {
            addCriterion("brand_id is null");
            return (Criteria) this;
        }

        public Criteria andBrandIdIsNotNull() {
            addCriterion("brand_id is not null");
            return (Criteria) this;
        }

        public Criteria andBrandIdEqualTo(Integer value) {
            addCriterion("brand_id =", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdNotEqualTo(Integer value) {
            addCriterion("brand_id <>", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdGreaterThan(Integer value) {
            addCriterion("brand_id >", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("brand_id >=", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdLessThan(Integer value) {
            addCriterion("brand_id <", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdLessThanOrEqualTo(Integer value) {
            addCriterion("brand_id <=", value, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdIn(List<Integer> values) {
            addCriterion("brand_id in", values, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdNotIn(List<Integer> values) {
            addCriterion("brand_id not in", values, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdBetween(Integer value1, Integer value2) {
            addCriterion("brand_id between", value1, value2, "brandId");
            return (Criteria) this;
        }

        public Criteria andBrandIdNotBetween(Integer value1, Integer value2) {
            addCriterion("brand_id not between", value1, value2, "brandId");
            return (Criteria) this;
        }

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(BigDecimal value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(BigDecimal value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(BigDecimal value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(BigDecimal value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<BigDecimal> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<BigDecimal> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andShoppingPriceIsNull() {
            addCriterion("shopping_price is null");
            return (Criteria) this;
        }

        public Criteria andShoppingPriceIsNotNull() {
            addCriterion("shopping_price is not null");
            return (Criteria) this;
        }

        public Criteria andShoppingPriceEqualTo(BigDecimal value) {
            addCriterion("shopping_price =", value, "shoppingPrice");
            return (Criteria) this;
        }

        public Criteria andShoppingPriceNotEqualTo(BigDecimal value) {
            addCriterion("shopping_price <>", value, "shoppingPrice");
            return (Criteria) this;
        }

        public Criteria andShoppingPriceGreaterThan(BigDecimal value) {
            addCriterion("shopping_price >", value, "shoppingPrice");
            return (Criteria) this;
        }

        public Criteria andShoppingPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("shopping_price >=", value, "shoppingPrice");
            return (Criteria) this;
        }

        public Criteria andShoppingPriceLessThan(BigDecimal value) {
            addCriterion("shopping_price <", value, "shoppingPrice");
            return (Criteria) this;
        }

        public Criteria andShoppingPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("shopping_price <=", value, "shoppingPrice");
            return (Criteria) this;
        }

        public Criteria andShoppingPriceIn(List<BigDecimal> values) {
            addCriterion("shopping_price in", values, "shoppingPrice");
            return (Criteria) this;
        }

        public Criteria andShoppingPriceNotIn(List<BigDecimal> values) {
            addCriterion("shopping_price not in", values, "shoppingPrice");
            return (Criteria) this;
        }

        public Criteria andShoppingPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("shopping_price between", value1, value2, "shoppingPrice");
            return (Criteria) this;
        }

        public Criteria andShoppingPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("shopping_price not between", value1, value2, "shoppingPrice");
            return (Criteria) this;
        }

        public Criteria andHitNumIsNull() {
            addCriterion("hit_num is null");
            return (Criteria) this;
        }

        public Criteria andHitNumIsNotNull() {
            addCriterion("hit_num is not null");
            return (Criteria) this;
        }

        public Criteria andHitNumEqualTo(Integer value) {
            addCriterion("hit_num =", value, "hitNum");
            return (Criteria) this;
        }

        public Criteria andHitNumNotEqualTo(Integer value) {
            addCriterion("hit_num <>", value, "hitNum");
            return (Criteria) this;
        }

        public Criteria andHitNumGreaterThan(Integer value) {
            addCriterion("hit_num >", value, "hitNum");
            return (Criteria) this;
        }

        public Criteria andHitNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("hit_num >=", value, "hitNum");
            return (Criteria) this;
        }

        public Criteria andHitNumLessThan(Integer value) {
            addCriterion("hit_num <", value, "hitNum");
            return (Criteria) this;
        }

        public Criteria andHitNumLessThanOrEqualTo(Integer value) {
            addCriterion("hit_num <=", value, "hitNum");
            return (Criteria) this;
        }

        public Criteria andHitNumIn(List<Integer> values) {
            addCriterion("hit_num in", values, "hitNum");
            return (Criteria) this;
        }

        public Criteria andHitNumNotIn(List<Integer> values) {
            addCriterion("hit_num not in", values, "hitNum");
            return (Criteria) this;
        }

        public Criteria andHitNumBetween(Integer value1, Integer value2) {
            addCriterion("hit_num between", value1, value2, "hitNum");
            return (Criteria) this;
        }

        public Criteria andHitNumNotBetween(Integer value1, Integer value2) {
            addCriterion("hit_num not between", value1, value2, "hitNum");
            return (Criteria) this;
        }

        public Criteria andSalesNumIsNull() {
            addCriterion("sales_num is null");
            return (Criteria) this;
        }

        public Criteria andSalesNumIsNotNull() {
            addCriterion("sales_num is not null");
            return (Criteria) this;
        }

        public Criteria andSalesNumEqualTo(Integer value) {
            addCriterion("sales_num =", value, "salesNum");
            return (Criteria) this;
        }

        public Criteria andSalesNumNotEqualTo(Integer value) {
            addCriterion("sales_num <>", value, "salesNum");
            return (Criteria) this;
        }

        public Criteria andSalesNumGreaterThan(Integer value) {
            addCriterion("sales_num >", value, "salesNum");
            return (Criteria) this;
        }

        public Criteria andSalesNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("sales_num >=", value, "salesNum");
            return (Criteria) this;
        }

        public Criteria andSalesNumLessThan(Integer value) {
            addCriterion("sales_num <", value, "salesNum");
            return (Criteria) this;
        }

        public Criteria andSalesNumLessThanOrEqualTo(Integer value) {
            addCriterion("sales_num <=", value, "salesNum");
            return (Criteria) this;
        }

        public Criteria andSalesNumIn(List<Integer> values) {
            addCriterion("sales_num in", values, "salesNum");
            return (Criteria) this;
        }

        public Criteria andSalesNumNotIn(List<Integer> values) {
            addCriterion("sales_num not in", values, "salesNum");
            return (Criteria) this;
        }

        public Criteria andSalesNumBetween(Integer value1, Integer value2) {
            addCriterion("sales_num between", value1, value2, "salesNum");
            return (Criteria) this;
        }

        public Criteria andSalesNumNotBetween(Integer value1, Integer value2) {
            addCriterion("sales_num not between", value1, value2, "salesNum");
            return (Criteria) this;
        }

        public Criteria andCollectionNumIsNull() {
            addCriterion("collection_num is null");
            return (Criteria) this;
        }

        public Criteria andCollectionNumIsNotNull() {
            addCriterion("collection_num is not null");
            return (Criteria) this;
        }

        public Criteria andCollectionNumEqualTo(Integer value) {
            addCriterion("collection_num =", value, "collectionNum");
            return (Criteria) this;
        }

        public Criteria andCollectionNumNotEqualTo(Integer value) {
            addCriterion("collection_num <>", value, "collectionNum");
            return (Criteria) this;
        }

        public Criteria andCollectionNumGreaterThan(Integer value) {
            addCriterion("collection_num >", value, "collectionNum");
            return (Criteria) this;
        }

        public Criteria andCollectionNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("collection_num >=", value, "collectionNum");
            return (Criteria) this;
        }

        public Criteria andCollectionNumLessThan(Integer value) {
            addCriterion("collection_num <", value, "collectionNum");
            return (Criteria) this;
        }

        public Criteria andCollectionNumLessThanOrEqualTo(Integer value) {
            addCriterion("collection_num <=", value, "collectionNum");
            return (Criteria) this;
        }

        public Criteria andCollectionNumIn(List<Integer> values) {
            addCriterion("collection_num in", values, "collectionNum");
            return (Criteria) this;
        }

        public Criteria andCollectionNumNotIn(List<Integer> values) {
            addCriterion("collection_num not in", values, "collectionNum");
            return (Criteria) this;
        }

        public Criteria andCollectionNumBetween(Integer value1, Integer value2) {
            addCriterion("collection_num between", value1, value2, "collectionNum");
            return (Criteria) this;
        }

        public Criteria andCollectionNumNotBetween(Integer value1, Integer value2) {
            addCriterion("collection_num not between", value1, value2, "collectionNum");
            return (Criteria) this;
        }

        public Criteria andInventoryNumIsNull() {
            addCriterion("inventory_num is null");
            return (Criteria) this;
        }

        public Criteria andInventoryNumIsNotNull() {
            addCriterion("inventory_num is not null");
            return (Criteria) this;
        }

        public Criteria andInventoryNumEqualTo(Integer value) {
            addCriterion("inventory_num =", value, "inventoryNum");
            return (Criteria) this;
        }

        public Criteria andInventoryNumNotEqualTo(Integer value) {
            addCriterion("inventory_num <>", value, "inventoryNum");
            return (Criteria) this;
        }

        public Criteria andInventoryNumGreaterThan(Integer value) {
            addCriterion("inventory_num >", value, "inventoryNum");
            return (Criteria) this;
        }

        public Criteria andInventoryNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("inventory_num >=", value, "inventoryNum");
            return (Criteria) this;
        }

        public Criteria andInventoryNumLessThan(Integer value) {
            addCriterion("inventory_num <", value, "inventoryNum");
            return (Criteria) this;
        }

        public Criteria andInventoryNumLessThanOrEqualTo(Integer value) {
            addCriterion("inventory_num <=", value, "inventoryNum");
            return (Criteria) this;
        }

        public Criteria andInventoryNumIn(List<Integer> values) {
            addCriterion("inventory_num in", values, "inventoryNum");
            return (Criteria) this;
        }

        public Criteria andInventoryNumNotIn(List<Integer> values) {
            addCriterion("inventory_num not in", values, "inventoryNum");
            return (Criteria) this;
        }

        public Criteria andInventoryNumBetween(Integer value1, Integer value2) {
            addCriterion("inventory_num between", value1, value2, "inventoryNum");
            return (Criteria) this;
        }

        public Criteria andInventoryNumNotBetween(Integer value1, Integer value2) {
            addCriterion("inventory_num not between", value1, value2, "inventoryNum");
            return (Criteria) this;
        }

        public Criteria andFirstPicIsNull() {
            addCriterion("first_pic is null");
            return (Criteria) this;
        }

        public Criteria andFirstPicIsNotNull() {
            addCriterion("first_pic is not null");
            return (Criteria) this;
        }

        public Criteria andFirstPicEqualTo(String value) {
            addCriterion("first_pic =", value, "firstPic");
            return (Criteria) this;
        }

        public Criteria andFirstPicNotEqualTo(String value) {
            addCriterion("first_pic <>", value, "firstPic");
            return (Criteria) this;
        }

        public Criteria andFirstPicGreaterThan(String value) {
            addCriterion("first_pic >", value, "firstPic");
            return (Criteria) this;
        }

        public Criteria andFirstPicGreaterThanOrEqualTo(String value) {
            addCriterion("first_pic >=", value, "firstPic");
            return (Criteria) this;
        }

        public Criteria andFirstPicLessThan(String value) {
            addCriterion("first_pic <", value, "firstPic");
            return (Criteria) this;
        }

        public Criteria andFirstPicLessThanOrEqualTo(String value) {
            addCriterion("first_pic <=", value, "firstPic");
            return (Criteria) this;
        }

        public Criteria andFirstPicLike(String value) {
            addCriterion("first_pic like", value, "firstPic");
            return (Criteria) this;
        }

        public Criteria andFirstPicNotLike(String value) {
            addCriterion("first_pic not like", value, "firstPic");
            return (Criteria) this;
        }

        public Criteria andFirstPicIn(List<String> values) {
            addCriterion("first_pic in", values, "firstPic");
            return (Criteria) this;
        }

        public Criteria andFirstPicNotIn(List<String> values) {
            addCriterion("first_pic not in", values, "firstPic");
            return (Criteria) this;
        }

        public Criteria andFirstPicBetween(String value1, String value2) {
            addCriterion("first_pic between", value1, value2, "firstPic");
            return (Criteria) this;
        }

        public Criteria andFirstPicNotBetween(String value1, String value2) {
            addCriterion("first_pic not between", value1, value2, "firstPic");
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

        public Criteria andIsRecIsNull() {
            addCriterion("is_rec is null");
            return (Criteria) this;
        }

        public Criteria andIsRecIsNotNull() {
            addCriterion("is_rec is not null");
            return (Criteria) this;
        }

        public Criteria andIsRecEqualTo(Integer value) {
            addCriterion("is_rec =", value, "isRec");
            return (Criteria) this;
        }

        public Criteria andIsRecNotEqualTo(Integer value) {
            addCriterion("is_rec <>", value, "isRec");
            return (Criteria) this;
        }

        public Criteria andIsRecGreaterThan(Integer value) {
            addCriterion("is_rec >", value, "isRec");
            return (Criteria) this;
        }

        public Criteria andIsRecGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_rec >=", value, "isRec");
            return (Criteria) this;
        }

        public Criteria andIsRecLessThan(Integer value) {
            addCriterion("is_rec <", value, "isRec");
            return (Criteria) this;
        }

        public Criteria andIsRecLessThanOrEqualTo(Integer value) {
            addCriterion("is_rec <=", value, "isRec");
            return (Criteria) this;
        }

        public Criteria andIsRecIn(List<Integer> values) {
            addCriterion("is_rec in", values, "isRec");
            return (Criteria) this;
        }

        public Criteria andIsRecNotIn(List<Integer> values) {
            addCriterion("is_rec not in", values, "isRec");
            return (Criteria) this;
        }

        public Criteria andIsRecBetween(Integer value1, Integer value2) {
            addCriterion("is_rec between", value1, value2, "isRec");
            return (Criteria) this;
        }

        public Criteria andIsRecNotBetween(Integer value1, Integer value2) {
            addCriterion("is_rec not between", value1, value2, "isRec");
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

        public Criteria andLastUpdateTimeIsNull() {
            addCriterion("last_update_time is null");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeIsNotNull() {
            addCriterion("last_update_time is not null");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeEqualTo(Date value) {
            addCriterion("last_update_time =", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeNotEqualTo(Date value) {
            addCriterion("last_update_time <>", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeGreaterThan(Date value) {
            addCriterion("last_update_time >", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("last_update_time >=", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeLessThan(Date value) {
            addCriterion("last_update_time <", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("last_update_time <=", value, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeIn(List<Date> values) {
            addCriterion("last_update_time in", values, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeNotIn(List<Date> values) {
            addCriterion("last_update_time not in", values, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("last_update_time between", value1, value2, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andLastUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("last_update_time not between", value1, value2, "lastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andIsHotIsNull() {
            addCriterion("is_hot is null");
            return (Criteria) this;
        }

        public Criteria andIsHotIsNotNull() {
            addCriterion("is_hot is not null");
            return (Criteria) this;
        }

        public Criteria andIsHotEqualTo(Integer value) {
            addCriterion("is_hot =", value, "isHot");
            return (Criteria) this;
        }

        public Criteria andIsHotNotEqualTo(Integer value) {
            addCriterion("is_hot <>", value, "isHot");
            return (Criteria) this;
        }

        public Criteria andIsHotGreaterThan(Integer value) {
            addCriterion("is_hot >", value, "isHot");
            return (Criteria) this;
        }

        public Criteria andIsHotGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_hot >=", value, "isHot");
            return (Criteria) this;
        }

        public Criteria andIsHotLessThan(Integer value) {
            addCriterion("is_hot <", value, "isHot");
            return (Criteria) this;
        }

        public Criteria andIsHotLessThanOrEqualTo(Integer value) {
            addCriterion("is_hot <=", value, "isHot");
            return (Criteria) this;
        }

        public Criteria andIsHotIn(List<Integer> values) {
            addCriterion("is_hot in", values, "isHot");
            return (Criteria) this;
        }

        public Criteria andIsHotNotIn(List<Integer> values) {
            addCriterion("is_hot not in", values, "isHot");
            return (Criteria) this;
        }

        public Criteria andIsHotBetween(Integer value1, Integer value2) {
            addCriterion("is_hot between", value1, value2, "isHot");
            return (Criteria) this;
        }

        public Criteria andIsHotNotBetween(Integer value1, Integer value2) {
            addCriterion("is_hot not between", value1, value2, "isHot");
            return (Criteria) this;
        }

        public Criteria andIsNewIsNull() {
            addCriterion("is_new is null");
            return (Criteria) this;
        }

        public Criteria andIsNewIsNotNull() {
            addCriterion("is_new is not null");
            return (Criteria) this;
        }

        public Criteria andIsNewEqualTo(Integer value) {
            addCriterion("is_new =", value, "isNew");
            return (Criteria) this;
        }

        public Criteria andIsNewNotEqualTo(Integer value) {
            addCriterion("is_new <>", value, "isNew");
            return (Criteria) this;
        }

        public Criteria andIsNewGreaterThan(Integer value) {
            addCriterion("is_new >", value, "isNew");
            return (Criteria) this;
        }

        public Criteria andIsNewGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_new >=", value, "isNew");
            return (Criteria) this;
        }

        public Criteria andIsNewLessThan(Integer value) {
            addCriterion("is_new <", value, "isNew");
            return (Criteria) this;
        }

        public Criteria andIsNewLessThanOrEqualTo(Integer value) {
            addCriterion("is_new <=", value, "isNew");
            return (Criteria) this;
        }

        public Criteria andIsNewIn(List<Integer> values) {
            addCriterion("is_new in", values, "isNew");
            return (Criteria) this;
        }

        public Criteria andIsNewNotIn(List<Integer> values) {
            addCriterion("is_new not in", values, "isNew");
            return (Criteria) this;
        }

        public Criteria andIsNewBetween(Integer value1, Integer value2) {
            addCriterion("is_new between", value1, value2, "isNew");
            return (Criteria) this;
        }

        public Criteria andIsNewNotBetween(Integer value1, Integer value2) {
            addCriterion("is_new not between", value1, value2, "isNew");
            return (Criteria) this;
        }

        public Criteria andCommentIdIsNull() {
            addCriterion("comment_id is null");
            return (Criteria) this;
        }

        public Criteria andCommentIdIsNotNull() {
            addCriterion("comment_id is not null");
            return (Criteria) this;
        }

        public Criteria andCommentIdEqualTo(Integer value) {
            addCriterion("comment_id =", value, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdNotEqualTo(Integer value) {
            addCriterion("comment_id <>", value, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdGreaterThan(Integer value) {
            addCriterion("comment_id >", value, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("comment_id >=", value, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdLessThan(Integer value) {
            addCriterion("comment_id <", value, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdLessThanOrEqualTo(Integer value) {
            addCriterion("comment_id <=", value, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdIn(List<Integer> values) {
            addCriterion("comment_id in", values, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdNotIn(List<Integer> values) {
            addCriterion("comment_id not in", values, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdBetween(Integer value1, Integer value2) {
            addCriterion("comment_id between", value1, value2, "commentId");
            return (Criteria) this;
        }

        public Criteria andCommentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("comment_id not between", value1, value2, "commentId");
            return (Criteria) this;
        }

        public Criteria andLoopPicsIsNull() {
            addCriterion("loop_pics is null");
            return (Criteria) this;
        }

        public Criteria andLoopPicsIsNotNull() {
            addCriterion("loop_pics is not null");
            return (Criteria) this;
        }

        public Criteria andLoopPicsEqualTo(String value) {
            addCriterion("loop_pics =", value, "loopPics");
            return (Criteria) this;
        }

        public Criteria andLoopPicsNotEqualTo(String value) {
            addCriterion("loop_pics <>", value, "loopPics");
            return (Criteria) this;
        }

        public Criteria andLoopPicsGreaterThan(String value) {
            addCriterion("loop_pics >", value, "loopPics");
            return (Criteria) this;
        }

        public Criteria andLoopPicsGreaterThanOrEqualTo(String value) {
            addCriterion("loop_pics >=", value, "loopPics");
            return (Criteria) this;
        }

        public Criteria andLoopPicsLessThan(String value) {
            addCriterion("loop_pics <", value, "loopPics");
            return (Criteria) this;
        }

        public Criteria andLoopPicsLessThanOrEqualTo(String value) {
            addCriterion("loop_pics <=", value, "loopPics");
            return (Criteria) this;
        }

        public Criteria andLoopPicsLike(String value) {
            addCriterion("loop_pics like", value, "loopPics");
            return (Criteria) this;
        }

        public Criteria andLoopPicsNotLike(String value) {
            addCriterion("loop_pics not like", value, "loopPics");
            return (Criteria) this;
        }

        public Criteria andLoopPicsIn(List<String> values) {
            addCriterion("loop_pics in", values, "loopPics");
            return (Criteria) this;
        }

        public Criteria andLoopPicsNotIn(List<String> values) {
            addCriterion("loop_pics not in", values, "loopPics");
            return (Criteria) this;
        }

        public Criteria andLoopPicsBetween(String value1, String value2) {
            addCriterion("loop_pics between", value1, value2, "loopPics");
            return (Criteria) this;
        }

        public Criteria andLoopPicsNotBetween(String value1, String value2) {
            addCriterion("loop_pics not between", value1, value2, "loopPics");
            return (Criteria) this;
        }

        public Criteria andShowPicsIsNull() {
            addCriterion("show_pics is null");
            return (Criteria) this;
        }

        public Criteria andShowPicsIsNotNull() {
            addCriterion("show_pics is not null");
            return (Criteria) this;
        }

        public Criteria andShowPicsEqualTo(String value) {
            addCriterion("show_pics =", value, "showPics");
            return (Criteria) this;
        }

        public Criteria andShowPicsNotEqualTo(String value) {
            addCriterion("show_pics <>", value, "showPics");
            return (Criteria) this;
        }

        public Criteria andShowPicsGreaterThan(String value) {
            addCriterion("show_pics >", value, "showPics");
            return (Criteria) this;
        }

        public Criteria andShowPicsGreaterThanOrEqualTo(String value) {
            addCriterion("show_pics >=", value, "showPics");
            return (Criteria) this;
        }

        public Criteria andShowPicsLessThan(String value) {
            addCriterion("show_pics <", value, "showPics");
            return (Criteria) this;
        }

        public Criteria andShowPicsLessThanOrEqualTo(String value) {
            addCriterion("show_pics <=", value, "showPics");
            return (Criteria) this;
        }

        public Criteria andShowPicsLike(String value) {
            addCriterion("show_pics like", value, "showPics");
            return (Criteria) this;
        }

        public Criteria andShowPicsNotLike(String value) {
            addCriterion("show_pics not like", value, "showPics");
            return (Criteria) this;
        }

        public Criteria andShowPicsIn(List<String> values) {
            addCriterion("show_pics in", values, "showPics");
            return (Criteria) this;
        }

        public Criteria andShowPicsNotIn(List<String> values) {
            addCriterion("show_pics not in", values, "showPics");
            return (Criteria) this;
        }

        public Criteria andShowPicsBetween(String value1, String value2) {
            addCriterion("show_pics between", value1, value2, "showPics");
            return (Criteria) this;
        }

        public Criteria andShowPicsNotBetween(String value1, String value2) {
            addCriterion("show_pics not between", value1, value2, "showPics");
            return (Criteria) this;
        }

        public Criteria andArticleNumberIsNull() {
            addCriterion("article_number is null");
            return (Criteria) this;
        }

        public Criteria andArticleNumberIsNotNull() {
            addCriterion("article_number is not null");
            return (Criteria) this;
        }

        public Criteria andArticleNumberEqualTo(String value) {
            addCriterion("article_number =", value, "articleNumber");
            return (Criteria) this;
        }

        public Criteria andArticleNumberNotEqualTo(String value) {
            addCriterion("article_number <>", value, "articleNumber");
            return (Criteria) this;
        }

        public Criteria andArticleNumberGreaterThan(String value) {
            addCriterion("article_number >", value, "articleNumber");
            return (Criteria) this;
        }

        public Criteria andArticleNumberGreaterThanOrEqualTo(String value) {
            addCriterion("article_number >=", value, "articleNumber");
            return (Criteria) this;
        }

        public Criteria andArticleNumberLessThan(String value) {
            addCriterion("article_number <", value, "articleNumber");
            return (Criteria) this;
        }

        public Criteria andArticleNumberLessThanOrEqualTo(String value) {
            addCriterion("article_number <=", value, "articleNumber");
            return (Criteria) this;
        }

        public Criteria andArticleNumberLike(String value) {
            addCriterion("article_number like", value, "articleNumber");
            return (Criteria) this;
        }

        public Criteria andArticleNumberNotLike(String value) {
            addCriterion("article_number not like", value, "articleNumber");
            return (Criteria) this;
        }

        public Criteria andArticleNumberIn(List<String> values) {
            addCriterion("article_number in", values, "articleNumber");
            return (Criteria) this;
        }

        public Criteria andArticleNumberNotIn(List<String> values) {
            addCriterion("article_number not in", values, "articleNumber");
            return (Criteria) this;
        }

        public Criteria andArticleNumberBetween(String value1, String value2) {
            addCriterion("article_number between", value1, value2, "articleNumber");
            return (Criteria) this;
        }

        public Criteria andArticleNumberNotBetween(String value1, String value2) {
            addCriterion("article_number not between", value1, value2, "articleNumber");
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

        public Criteria andRangliEqualTo(BigDecimal value) {
            addCriterion("rangli =", value, "rangli");
            return (Criteria) this;
        }

        public Criteria andRangliNotEqualTo(BigDecimal value) {
            addCriterion("rangli <>", value, "rangli");
            return (Criteria) this;
        }

        public Criteria andRangliGreaterThan(BigDecimal value) {
            addCriterion("rangli >", value, "rangli");
            return (Criteria) this;
        }

        public Criteria andRangliGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("rangli >=", value, "rangli");
            return (Criteria) this;
        }

        public Criteria andRangliLessThan(BigDecimal value) {
            addCriterion("rangli <", value, "rangli");
            return (Criteria) this;
        }

        public Criteria andRangliLessThanOrEqualTo(BigDecimal value) {
            addCriterion("rangli <=", value, "rangli");
            return (Criteria) this;
        }

        public Criteria andRangliIn(List<BigDecimal> values) {
            addCriterion("rangli in", values, "rangli");
            return (Criteria) this;
        }

        public Criteria andRangliNotIn(List<BigDecimal> values) {
            addCriterion("rangli not in", values, "rangli");
            return (Criteria) this;
        }

        public Criteria andRangliBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rangli between", value1, value2, "rangli");
            return (Criteria) this;
        }

        public Criteria andRangliNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("rangli not between", value1, value2, "rangli");
            return (Criteria) this;
        }

        public Criteria andExpressCostIsNull() {
            addCriterion("express_cost is null");
            return (Criteria) this;
        }

        public Criteria andExpressCostIsNotNull() {
            addCriterion("express_cost is not null");
            return (Criteria) this;
        }

        public Criteria andExpressCostEqualTo(BigDecimal value) {
            addCriterion("express_cost =", value, "expressCost");
            return (Criteria) this;
        }

        public Criteria andExpressCostNotEqualTo(BigDecimal value) {
            addCriterion("express_cost <>", value, "expressCost");
            return (Criteria) this;
        }

        public Criteria andExpressCostGreaterThan(BigDecimal value) {
            addCriterion("express_cost >", value, "expressCost");
            return (Criteria) this;
        }

        public Criteria andExpressCostGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("express_cost >=", value, "expressCost");
            return (Criteria) this;
        }

        public Criteria andExpressCostLessThan(BigDecimal value) {
            addCriterion("express_cost <", value, "expressCost");
            return (Criteria) this;
        }

        public Criteria andExpressCostLessThanOrEqualTo(BigDecimal value) {
            addCriterion("express_cost <=", value, "expressCost");
            return (Criteria) this;
        }

        public Criteria andExpressCostIn(List<BigDecimal> values) {
            addCriterion("express_cost in", values, "expressCost");
            return (Criteria) this;
        }

        public Criteria andExpressCostNotIn(List<BigDecimal> values) {
            addCriterion("express_cost not in", values, "expressCost");
            return (Criteria) this;
        }

        public Criteria andExpressCostBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("express_cost between", value1, value2, "expressCost");
            return (Criteria) this;
        }

        public Criteria andExpressCostNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("express_cost not between", value1, value2, "expressCost");
            return (Criteria) this;
        }

        public Criteria andSortIsNull() {
            addCriterion("sort is null");
            return (Criteria) this;
        }

        public Criteria andSortIsNotNull() {
            addCriterion("sort is not null");
            return (Criteria) this;
        }

        public Criteria andSortEqualTo(Integer value) {
            addCriterion("sort =", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotEqualTo(Integer value) {
            addCriterion("sort <>", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThan(Integer value) {
            addCriterion("sort >", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("sort >=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThan(Integer value) {
            addCriterion("sort <", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThanOrEqualTo(Integer value) {
            addCriterion("sort <=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortIn(List<Integer> values) {
            addCriterion("sort in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotIn(List<Integer> values) {
            addCriterion("sort not in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortBetween(Integer value1, Integer value2) {
            addCriterion("sort between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotBetween(Integer value1, Integer value2) {
            addCriterion("sort not between", value1, value2, "sort");
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
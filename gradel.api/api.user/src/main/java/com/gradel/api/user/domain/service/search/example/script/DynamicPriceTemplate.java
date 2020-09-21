package com.gradel.api.user.domain.service.search.example.script;

import java.util.List;

public class DynamicPriceTemplate {
    private int goodsId;
    private String goodName;
    private List<Rule> rules;

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public List<Rule> getRules() {
        return rules;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }
}

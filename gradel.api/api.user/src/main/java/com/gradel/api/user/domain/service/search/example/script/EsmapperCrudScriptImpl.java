package com.gradel.api.user.domain.service.search.example.script;

import com.gradel.api.user.domain.service.search.example.curd.DocumentCRUD;
import org.frameworkset.elasticsearch.boot.BBossESStarter;
import org.frameworkset.elasticsearch.client.ClientInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EsmapperCrudScriptImpl {
    private String mappath = "esmapper/demo.xml";
    @Autowired
    private DocumentCRUD documentCRUD;
    @Autowired
    private BBossESStarter bbossESStarter;

    public void updateDocumentByScriptPath() {
        //初始化数据，会创建type为demo的indice demo，并添加docid为2的文档

        documentCRUD.testCreateIndice();
        documentCRUD.testBulkAddDocument();
        //创建加载配置文件的客户端工具，用来检索文档，单实例多线程安全
        ClientInterface clientUtil = bbossESStarter.getConfigRestClient(mappath);
        Map<String, Object> params = new HashMap<String, Object>();
        DynamicPriceTemplate dynamicPriceTemplate = new DynamicPriceTemplate();
        dynamicPriceTemplate.setGoodsId(1);
        List<Rule> ruleList = new ArrayList<Rule>();
        Rule rule = new Rule();
        rule.setRuleCount(100);
        rule.setRuleExist(true);
        rule.setRuleId("asdfasdfasdf");
        ruleList.add(rule);

        rule = new Rule();
        rule.setRuleCount(101);
        rule.setRuleExist(false);
        rule.setRuleId("bbb$b");
        ruleList.add(rule);

        rule = new Rule();
        rule.setRuleCount(103);
        rule.setRuleExist(true);
        rule.setRuleId("ccccc");
        ruleList.add(rule);
        dynamicPriceTemplate.setRules(ruleList);

        //为id为2的文档增加last和nick两个属性
        params.put("last", "gaudreau");
        params.put("nick", "hockey");
        params.put("dynamicPriceTemplate", dynamicPriceTemplate);
        //通过script脚本为id为2的文档增加last和nick两个属性，为了演示效果强制refresh，实际环境慎用
        clientUtil.updateByPath("demo/_update/2?refresh", "scriptDsl", params);
        //获取更新后的文档，会看到新加的2个字段属性
        String doc = clientUtil.getDocument("demo", "2");
        System.out.println(doc);

    }


    public void updateDocumentByScriptQueryPath() {
        //初始化数据，会创建type为demo的indice demo，并添加docid为2的文档

        documentCRUD.testCreateIndice();
        documentCRUD.testBulkAddDocument();
        //创建加载配置文件的客户端工具，用来检索文档，单实例多线程安全
        ClientInterface clientUtil = bbossESStarter.getConfigRestClient(mappath);
        Map<String, Object> params = new HashMap<String, Object>();
        DynamicPriceTemplate dynamicPriceTemplate = new DynamicPriceTemplate();
        dynamicPriceTemplate.setGoodsId(1);
        dynamicPriceTemplate.setGoodName("asd\"国家");
        List<Rule> ruleList = new ArrayList<Rule>();
        Rule rule = new Rule();
        rule.setRuleCount(100);
        rule.setRuleExist(true);
        rule.setRuleId("asdfasd$fasdf");
        ruleList.add(rule);

        rule = new Rule();
        rule.setRuleCount(101);
        rule.setRuleExist(false);
        rule.setRuleId("bbbb");
        ruleList.add(rule);

        rule = new Rule();
        rule.setRuleCount(103);
        rule.setRuleExist(true);
        rule.setRuleId(null);
        ruleList.add(rule);
        dynamicPriceTemplate.setRules(ruleList);


        //为id为2的文档增加last和nick两个属性
        params.put("last", "gaudre$au");
        params.put("nick", "hockey");
        params.put("id", 3);
        params.put("dynamicPriceTemplate", dynamicPriceTemplate);
        //通过script脚本为id为2的文档增加last和nick两个属性，为了演示效果强制refresh，实际环境慎用
        clientUtil.updateByPath("demo/_update_by_query?refresh", "scriptDslByQuery", params);
        //获取更新后的文档，会看到新加的2个字段属性
        String doc = clientUtil.getDocument("demo", "3");
        System.out.println(doc);

    }


}

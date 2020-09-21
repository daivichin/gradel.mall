package test.com.gradel.api.user.estest;


import com.gradel.api.user.domain.service.search.example.curd.MultiESDocumentCRUD;
import org.frameworkset.elasticsearch.boot.BBossESStarter;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import test.com.gradel.api.user.BaseSpringTest;

/**
 * 多集群演示功能测试用例，spring boot配置项以spring.elasticsearch.bboss.集群名称开头，例如：
 * spring.elasticsearch.bboss.default 默认es集群
 * spring.elasticsearch.bboss.logs  logs es集群
 * 两个集群通过 org.bboss.elasticsearchtest.springboot.MultiESSTartConfigurer加载
 * 对应的配置文件为application-multi-datasource.properties文件
 *
 */
@ActiveProfiles("multi-datasource")
public class MultiBBossES7StartersTestCase  extends BaseSpringTest {
    @Autowired
    //@Qualifier("bbossESStarterDefault")
    private BBossESStarter bbossESStarterDefault;

    @Autowired
    MultiESDocumentCRUD multiESDocumentCRUD;

    @Test
    public void testMultiBBossESStarters() throws Exception {

        //验证环境,获取es状态
//		String response = bbossESStarterDefault.getRestClient().executeHttp("_cluster/state?pretty",ClientInterface.HTTP_GET);
//		System.out.println(response);


        //判断索引类型是否存在，false表示不存在，正常返回true表示存在
        boolean exist = bbossESStarterDefault.getRestClient().existIndiceType("twitter", "tweet");
        System.out.println("default twitter/tweet:" + exist);
        //获取logs对应的Elasticsearch集群客户端，并进行existIndiceType操作
        exist = bbossESStarterDefault.getRestClient("logs").existIndiceType("twitter", "tweet");
        System.out.println("logs twitter/tweet:" + exist);
        //获取logs对应的Elasticsearch集群客户端，判读索引是否存在，false表示不存在，正常返回true表示存在
        exist = bbossESStarterDefault.getRestClient("logs").existIndice("twitter");
        System.out.println("logs  twitter:" + exist);
        //获取logs对应的Elasticsearch集群客户端，判断索引是否定义
        exist = bbossESStarterDefault.getRestClient("logs").existIndice("agentinfo");
        System.out.println("logs agentinfo:" + exist);
    }

    @Test
    public void testCRUD() throws Exception {

        //删除/创建文档索引表
        multiESDocumentCRUD.testCreateIndice();
        //添加/修改单个文档

        multiESDocumentCRUD.testAddAndUpdateDocument();
        //批量添加文档
        multiESDocumentCRUD.testBulkAddDocument();
        //检索文档
        multiESDocumentCRUD.testSearch();
        //批量修改文档
        multiESDocumentCRUD.testBulkUpdateDocument();

        //检索批量修改后的文档
        multiESDocumentCRUD.testSearch();
        //带list复杂参数的文档检索操作
        multiESDocumentCRUD.testSearchArray();
        //带from/size分页操作的文档检索操作
        multiESDocumentCRUD.testPagineSearch();
        //带sourcefilter的文档检索操作
        multiESDocumentCRUD.testSearchSourceFilter();

        multiESDocumentCRUD.updateDemoIndice();
        multiESDocumentCRUD.testBulkAddDocuments();
    }

    @Test
    public void testPerformaceCRUD() throws Exception {

        //删除/创建文档索引表
        multiESDocumentCRUD.testCreateIndice();

        multiESDocumentCRUD.testBulkAddDocuments();
    }
}

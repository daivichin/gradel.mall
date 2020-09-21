package test.com.gradel.api.user.thirddslcontainer;

import com.frameworkset.common.poolman.SQLExecutor;
import com.frameworkset.common.poolman.util.SQLUtil;
import com.gradel.api.user.domain.service.search.example.thirddslcontainer.DocumentCRUD;
import com.gradel.api.user.domain.service.search.example.thirddslcontainer.DslRuleScriptImpl;
import com.gradel.api.user.domain.service.search.example.thirddslcontainer.HighlightSearch;
import org.frameworkset.elasticsearch.boot.BBossESStarter;
import org.frameworkset.elasticsearch.client.ClientInterface;
import org.frameworkset.elasticsearch.template.*;
import org.frameworkset.spi.DefaultApplicationContext;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import test.com.gradel.api.user.BaseSpringTest;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestThirdDslContainer extends BaseSpringTest {
	@Autowired
	private BBossESStarter bbossESStarter;
	private static Logger logger = LoggerFactory.getLogger(TestThirdDslContainer.class);
	@Before
	public void init() throws SQLException {
		/**
		 * "org.sqlite.JDBC",
		 * 							"jdbc:sqlite://" + dbpath.getCanonicalPath(),
		 * 							"root", "root",
		 * 							null,//"false",
		 * 							null,// "READ_UNCOMMITTED",
		 * 							"select 1",
		 * 							dbJNDIName,
		 * 							10,
		 * 							10,
		 * 							20,
		 * 							true,
		 * 							false,
		 * 							null, false, false
		 */
		//定义保存dsl的数据源testdslconfig
		SQLUtil.startPool("testdslconfig",//数据源名称
				"org.sqlite.JDBC",
				"jdbc:sqlite:testdslconfig" ,
				"root", "root",
				null,//"false",
				null,// "READ_UNCOMMITTED",
				"select 1",
				"test-jndi",
				10,
				10,
				20,
				true,
				false,
				null, true, false
		);

		//在sqlite数据源testdslconfig中创建保存dsl语句的数据库表dslconfig
		String createStatusTableSQL = new StringBuilder()
				.append("create table dslconfig (ID string,name string,namespace string,dslTemplate TEXT,vtpl number(1),multiparser number(1) ")
				.append(",referenceNamespace string,referenceTemplateName string,PRIMARY KEY (ID))").toString();

		try {
			String exist = "select 1 from dslconfig";
			//SQLExecutor.updateWithDBName("gencode","drop table BBOSS_GENCODE");

			SQLExecutor.queryObjectWithDBName(int.class,"testdslconfig", exist);
			logger.info("重建建dslconfig表："+createStatusTableSQL+"。");
			SQLExecutor.updateWithDBName("testdslconfig","drop table dslconfig");
			SQLExecutor.updateWithDBName("testdslconfig",createStatusTableSQL);
			logger.info("重建建dslconfig表成功。");
		} catch (Exception e) {

			logger.info("dslconfig table 不存在，创建dslconfig表："+createStatusTableSQL+"。");
			try {
				SQLExecutor.updateWithDBName("testdslconfig",createStatusTableSQL);
				logger.info("创建dslconfig表成功："+createStatusTableSQL+"。");
			} catch (SQLException e1) {
				logger.info("创建dslconfig表失败："+createStatusTableSQL+"。",e1);
				e1.printStackTrace();
			}
		}

		//将配置文件中的sql转存到数据库中
		String dslpath = "esmapper/demo.xml";
	    final String namespace = "testnamespace7";//一个命名空间的dsl可以对应为一个ClientInterface实例
		AOPTemplateContainerImpl aopTemplateContainer = new AOPTemplateContainerImpl(DefaultApplicationContext.getApplicationContext(dslpath));
		int perKeyDSLStructionCacheSize = aopTemplateContainer.getPerKeyDSLStructionCacheSize();
		boolean alwaysCacheDslStruction = aopTemplateContainer.isAlwaysCacheDslStruction();
		List<TemplateMeta> templateMetaList = aopTemplateContainer.getTemplateMetas(namespace);//将demo.xml文件中配置的dsl转换为属于namespace命名空间的对象列表

		//保存dsl到表dslconfig
		SQLExecutor.insertBeans("testdslconfig",
				"insert into dslconfig(ID,name,namespace,dslTemplate,vtpl,multiparser,referenceNamespace,referenceTemplateName) " +
						"values(#[id]," + //主键
						"#[name]," + //dsl名称
						"#[namespace]," + //dsl所属命名空间
						"#[dslTemplate]," + //dsl语句
						"#[vtpl]," + //一般设置为true， dsl语句中是否包含velocity语法内容，包含为true，否则为false（避免进行velocity语法解析，提升性能），默认为true
						"#[multiparser]," + // 一般设置为true，dsl如果包含velocity动态语法，是否需要对每次动态生成的dsl进行模板变量#[xxxx]语法解析，true 需要，false不需要，默认true
						"#[referenceNamespace]," + // 如果对应的配置是一个引用，则需要通过referenceNamespace指定引用的dsl所属的命名空间
						"#[referenceTemplateName])",// 如果对应的配置是一个引用，则需要通过referenceTemplateName指定引用的dsl对应的名称name
				templateMetaList);
	}
	@Test
	public void testThirdDslContainer(){
		//创建一个从数据库加载命名空间为clientInterface7的所有dsl语句的ClientInterface组件实例
		ClientInterface clientInterface = bbossESStarter.getConfigRestClient(new BaseTemplateContainerImpl("testnamespace7") {
			@Override
			protected Map<String, TemplateMeta> loadTemplateMetas(String namespace) {
				try {
					List<BaseTemplateMeta> templateMetas = SQLExecutor.queryListWithDBName(BaseTemplateMeta.class,"testdslconfig","select * from dslconfig where namespace = ?",namespace);
					if(templateMetas == null){
						return null;
					}
					else{
						Map<String,TemplateMeta> templateMetaMap = new HashMap<String, TemplateMeta>(templateMetas.size());
						for(BaseTemplateMeta baseTemplateMeta: templateMetas){
							templateMetaMap.put(baseTemplateMeta.getName(),baseTemplateMeta);
						}
						return templateMetaMap;
					}
				} catch (Exception e) {
					throw new DSLParserException(e);
				}
			}

			@Override
			protected long getLastModifyTime(String namespace) {
				// 模拟每次都更新，返回当前时间戳
				return System.currentTimeMillis();
			}
		});
		try{
			testHighlightSearch(clientInterface);
		}
		catch (Exception e){
			logger.error("",e);
		}
		try{
			testCRUD(clientInterface);
		}
		catch (Exception e){
			logger.error("",e);
		}
		testScriptImpl( clientInterface);
	}

	private void testScriptImpl(ClientInterface clientInterface)  {
		DslRuleScriptImpl scriptImpl = new DslRuleScriptImpl(clientInterface);
		scriptImpl.updateDocumentByScriptPath();
		scriptImpl.updateDocumentByScriptQueryPath();
	}
	private void testHighlightSearch(ClientInterface clientInterface) throws ParseException {
		HighlightSearch highlightSearch = new HighlightSearch(clientInterface);
		highlightSearch.initIndiceAndData();
		highlightSearch.highlightSearch();
		highlightSearch.highlightSearchOther();
	}
	private void testCRUD(ClientInterface clientInterface) throws ParseException {
		DocumentCRUD documentCRUD = new DocumentCRUD(clientInterface);
		//删除/创建文档索引表
		documentCRUD.testCreateIndice();
		//添加/修改单个文档
		documentCRUD.testAddAndUpdateDocument();
		//批量添加文档
		documentCRUD.testBulkAddDocument();
		//检索文档
		documentCRUD.testSearch();
		//批量修改文档
		documentCRUD.testBulkUpdateDocument();

		//检索批量修改后的文档
		documentCRUD.testSearch();
		//带list复杂参数的文档检索操作
		documentCRUD.testSearchArray();
		//带from/size分页操作的文档检索操作
		documentCRUD.testPagineSearch();
		//带sourcefilter的文档检索操作
		documentCRUD.testSearchSourceFilter();

		documentCRUD.updateDemoIndice();
		documentCRUD.testBulkAddDocuments();
		for(int i = 0; i < 10; i ++){
			documentCRUD.testBulkAddUUIDDocuments(i,10);
		}
	}
}

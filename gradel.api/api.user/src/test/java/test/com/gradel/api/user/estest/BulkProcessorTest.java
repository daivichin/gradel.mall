package test.com.gradel.api.user.estest;

import com.gradel.api.user.domain.service.search.example.bulk.TestBulkProcessor;
import org.frameworkset.elasticsearch.boot.BBossESStarter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import test.com.gradel.api.user.BaseSpringTest;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BulkProcessorTest  extends BaseSpringTest {
    private Logger logger = LoggerFactory.getLogger(BulkProcessorTest.class);
    @Autowired
    private BBossESStarter bbossESStarter;
    @Autowired
    private TestBulkProcessor testBulkProcessor7x;

    @Test
    public void testBulkProcessor7x() {
        testBulkProcessor7x.buildBulkProcessor();
        testBulkProcessor7x.testBulkDatas();

    }
}

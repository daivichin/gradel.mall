package test.com.gradel.api.user;

import com.gradel.api.user.Application;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created with IntelliJ IDEA.
 * User: sdeven.chen.dongwei@gmail.com
 * Date: 2016-3-27
 * Depiction: 测试基类
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public abstract class BaseSpringTest {

}
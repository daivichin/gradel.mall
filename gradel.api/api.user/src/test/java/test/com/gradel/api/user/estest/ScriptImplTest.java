package test.com.gradel.api.user.estest;

import com.frameworkset.util.SimpleStringUtil;
import com.gradel.api.user.domain.service.search.example.script.EsmapperCrudScriptImpl;
import org.frameworkset.elasticsearch.serial.SerialUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import test.com.gradel.api.user.BaseSpringTest;

import java.util.HashMap;
import java.util.Map;

public class ScriptImplTest extends BaseSpringTest {
    @Autowired
    private EsmapperCrudScriptImpl script;

    @Test
    public void test() {
        script.updateDocumentByScriptPath();
    }

    @Test
    public void test1() {
        script.updateDocumentByScriptQueryPath();
    }

    public static void main(String[] args) {
        Map value = new HashMap();
        value.put("aaa", "\r\n\"");
        String _value = SerialUtil.object2json(value);
        value.put("aaa", _value);
        _value = SerialUtil.object2json(value);
        System.out.println(_value);
        value = SimpleStringUtil.json2Object(_value, HashMap.class);
        System.out.println(_value);
    }
}

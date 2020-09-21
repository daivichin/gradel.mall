package com.gradel.api.user.domain.service.search.example.entity;

import com.frameworkset.orm.annotation.ESId;
import com.frameworkset.orm.annotation.ESIndex;
import lombok.Data;
import lombok.ToString;
import org.frameworkset.elasticsearch.entity.ESBaseData;

import java.util.Date;

/**
 * 测试实体，可以从ESBaseData对象继承meta属性，检索时会将文档的一下meta属性设置到对象实例中
 */
@Data
@ToString
//@ESIndex(name="demowithesindex-{agentStarttime,yyyy.MM.dd}",type="demowithesindex")
@ESIndex(name="demowithesindex-{dateformat=yyyy.MM.dd}")
public class DemoWithESIndex extends ESBaseData {
	private Object dynamicPriceTemplate;
	//设定文档标识字段
	@ESId(readSet = true,persistent = false)
	private Long demoId;
	private String contentbody;
	/**  当在mapping定义中指定了日期格式时，则需要指定以下两个注解,例如
	 *
	 "agentStarttime": {
	 "type": "date",###指定多个日期格式
	 "format":"yyyy-MM-dd HH:mm:ss.SSS||yyyy-MM-dd'T'HH:mm:ss.SSS||yyyy-MM-dd HH:mm:ss||epoch_millis"
	 }
	 @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	 @Column(dataformat = "yyyy-MM-dd HH:mm:ss.SSS")
	 */

	protected Date agentStarttime;
	private String applicationName;
	private String orderId;
	private int contrastStatus;
	private String name;
}

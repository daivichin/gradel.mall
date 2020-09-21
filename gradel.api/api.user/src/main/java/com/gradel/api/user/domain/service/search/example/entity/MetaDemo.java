package com.gradel.api.user.domain.service.search.example.entity;

import com.frameworkset.orm.annotation.*;
import lombok.Data;
import lombok.ToString;
import org.frameworkset.elasticsearch.entity.Explanation;
import org.frameworkset.elasticsearch.entity.InnerSearchHits;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 测试实体，可以从ESBaseData对象继承meta属性，检索时会将文档的一下meta属性设置到对象实例中
 */
@Data
@ToString
public class MetaDemo  {
	private Object dynamicPriceTemplate;
	//设定文档标识字段
	@ESMetaId
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
	@ESMetaType
	private String type;
	@ESMetaFields
	private Map<String, List<Object>> fields;
	@ESMetaVersion
	private long version;
	@ESMetaIndex
	private String index;//"_index": "trace-2017.09.01",
	@ESMetaHighlight
	private Map<String, List<Object>> highlight;
	@ESMetaSort
	private Object[] sort;
	@ESMetaScore
	private Double score;
	@ESMetaParentId
	private Object parent;
	@ESRouting
	private Object routing;
	@ESMetaFound
	private boolean found;
	@ESMetaNested
	private Map<String, Object> nested;
	@ESMetaInnerHits
	private Map<String, Map<String, InnerSearchHits>> innerHits;
	@ESMetaShard
	private String shard;//"_index": "trace-2017.09.01",
	@ESMetaNode
	private String node;//"_index": "trace-2017.09.01",
	@ESMetaExplanation
	private Explanation explanation;//"_index": "trace-2017.09.01",
	private String name;
}


package com.gradel.api.user.domain.service.search.example.entity;


import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author yinbp[yin-bp@163.com]
 */
@Data
@ToString
public class DemoSearchResult {
	private List<Demo> demos;
	private long totalSize;
}

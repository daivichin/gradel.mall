package com.gradel.api.user.domain.service.search.example.entity;

import com.frameworkset.orm.annotation.ESId;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class NewDemo {
	@ESId(readSet = true)
	private Long demoId;
}

package com.gradel.common.order.model.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RedisPutEsayEntity {
    private String key;
    private Object value;
    private Long expireTime;

}

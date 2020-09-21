package com.gradel.common.order.model.params;

import com.gradel.common.shared.serial.BaseSerial;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class ArticleLikeParams extends BaseSerial {
    /**
     * 用户Id
     */
    private String userId;


}

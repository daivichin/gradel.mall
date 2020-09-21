package com.gradel.common.order.model.message;

import com.gradel.common.shared.serial.BaseSerial;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author sdeven.chen.dongwei
 * @since 2020-03-25
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class CommonMqMessage extends BaseSerial {

    private static final long serialVersionUID=1L;
    /**
     * userId
     */
    private Integer userId;
    /**
     * 商品名
     */
    private String spuName;

    /**
     * 商品Id
     */
    private Integer spuId;

    /**
     * 爆款商品名称
     */
    private String hotSpuName;

    /**
     *
     */
    private Integer bannerId;

    /**
     * 好物文章名称
     */
    private String topicName;

    /**
     * 渠道
     */
    private String channel;


}

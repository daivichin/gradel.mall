package com.gradel.common.order.model.message;

import com.gradel.common.shared.serial.BaseSerial;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author sdeven.chen.dongwei
 * @since 2020-03-25
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class UserClickSpuMqMessage extends BaseSerial {

    /**
     * 用于标识数据归属权。
     */
    @NotNull
    @NotEmpty(message = "userActionSetId 不能为空")
    private Integer userActionSetId;


    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * ip地址
     */
    private String ip;
}

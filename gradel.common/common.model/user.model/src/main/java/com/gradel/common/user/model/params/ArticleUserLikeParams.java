package com.gradel.common.order.model.params;

import com.gradel.common.shared.serial.BaseSerial;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class ArticleUserLikeParams extends BaseSerial {
    /**
     * 用户Id
     */
    @NotNull
    @NotEmpty(message = "用户不能为空")
    private String userId;
}

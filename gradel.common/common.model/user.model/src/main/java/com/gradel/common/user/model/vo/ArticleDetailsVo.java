package com.gradel.common.order.model.vo;

import com.gradel.common.shared.serial.BaseSerial;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class ArticleDetailsVo  extends BaseSerial {
    private Integer id;

    private String title;

    private String subtitle;

    private LocalDateTime addTime;

    private Integer authorId;

    private String authorName;

    private String authorThumbnailUrl;

    private String content;

    private Integer articleClicleNum;
}

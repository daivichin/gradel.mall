package com.gradel.common.order.model.dto.buyin;

import com.gradel.common.shared.serial.BaseSerial;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class GoodsDto extends BaseSerial {

    private Integer id;

    /**
     * 商品编号
     */
    private String goodsSn;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品所属类目ID
     */
    private Integer categoryId;

    private Integer brandId;

    /**
     * 商品宣传图片列表，采用JSON数组格式
     */
    private String gallery;

    /**
     * 商品关键字，采用逗号间隔
     */
    private String keywords;

    /**
     * 商品简介
     */
    private String brief;

    /**
     * 是否上架
     */
    private Boolean isOnSale;

    private Integer sortOrder;

}

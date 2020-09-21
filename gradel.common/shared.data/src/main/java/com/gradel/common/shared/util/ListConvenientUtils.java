package com.gradel.common.shared.util;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * 批次轮调工具类
 * @param <T>
 */
@Data
public class ListConvenientUtils<T> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ListConvenientUtils.class);
    private static final Integer INDEX = 0;
    private static final Integer SUBINDEX = 1000;
    /**
     * 输入数据
     */
    private  List<T> data;

    /**
     * 截取大小 初始化1000
     */
    private Integer subCapacity  = SUBINDEX;
    /**
     * 总次数/总页数
     */
    private  Integer attemptsCount = INDEX;

    /**
     * 总记录数
     */
    private  Integer dataSize = INDEX;
    /**
     * 当前次数
     */
    private  Integer attemptsNum = INDEX;

    /**
     * 当前次数数据量大小,B 单位 (dubbo 当前设计请求数据量容量大小为4M,防止数据大小超过设计预期造成请求失败)
     */
   /*
    private  Long subDataSize;*/

    public ListConvenientUtils(final @NotNull List<T> data, final @NotNull Integer subCapacity)  {
        this.data= data;
        this.subCapacity = subCapacity;
        this.dataSize = this.data.size();
        if (dataSize % this.subCapacity == 0) {
            this.attemptsCount = dataSize / this.subCapacity;
        } else {
            this.attemptsCount = dataSize / this.subCapacity + 1;
        }
    }
    public ListConvenientUtils(final @NotNull List<T> list)  {
        this.data= list;
        this.dataSize = this.data.size();
        if (dataSize % this.subCapacity == 0) {
            this.attemptsCount = dataSize / this.subCapacity;
        } else {
            this.attemptsCount = dataSize / this.subCapacity + 1;
        }
    }
    public ListConvenientUtils()  {
    }

    /**
     * 截取下一个
     * @return
     */
    public List<T> next() {
        if (attemptsNum < attemptsCount ) {
            final int fromIndex = this.attemptsNum * this.subCapacity;
            final int size = this.dataSize - fromIndex ;
            List<T> response = this.data.subList(fromIndex,  size <= this.subCapacity ? fromIndex+size : ((this.attemptsNum + 1) * this.subCapacity));
            //this.subDataSize = ObjectSizeCalculator.getObjectSize(response); openjdk 无法运行该代码，所以注释
            this.attemptsNum++;
            return response;
        }
        return  new ArrayList<>();
    }
}

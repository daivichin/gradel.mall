package com.gradel.common.shared.request;


import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gradel.parent.common.util.exception.BusinessException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * author: sdeven.chen.dongwei@gmail.com
 * Date:2018/08/29
 */
@ToString
public class PageRequest extends BaseRequest {

    //页码,接口从1开始
    @NotNull
    @Getter
    @Setter
    private Integer pageNow;

    //每页显示条数
    @NotNull
    @Getter
    @Setter
    private Integer pageSize;

    //升序降序
    @Getter
    @Setter
    private String order;

    public Page getPage(){
        Page page = new Page();
        page.setCurrent(this.pageNow);
        page.setSize(this.pageSize);
        if(StringUtils.isNotBlank(order)){
            String orderTrim = this.order.trim();
            this.checkSensitive(orderTrim);
            boolean asc;
            String[] split = orderTrim.split(" ");
            int idx = split.length-1;

            if("desc".equalsIgnoreCase(split[idx])){
                asc = false;
            }else if("asc".equalsIgnoreCase(split[idx])){
                asc = true;
            }else{
                throw new BusinessException("异常 orderByField:"+orderTrim);
            }
            split[idx] = null;

            StringBuilder s = new StringBuilder();
            List<OrderItem> orderItems = new ArrayList<>(split.length+1);
            Arrays.stream(split).forEach((b)->{
                OrderItem item = new OrderItem();
                if(StringUtils.isNotBlank(b)){
                    item.setColumn(b);
                    item.setAsc(asc);
                }
                orderItems.add(item);
            });
            page.setOrders(orderItems);
        }
        return page;
    }

    private void checkSensitive(String order){
        String reg = "(select|insert|update|delete|create|alter|drop|truncate|grant|execute|call)(?!_time)";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(order.toLowerCase());
        if(matcher.find()){
            throw new BusinessException("排序 包含敏感单词:"+matcher.group(1));
        }
    }
}

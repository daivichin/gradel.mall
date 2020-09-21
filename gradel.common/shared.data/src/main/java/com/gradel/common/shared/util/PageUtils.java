package com.gradel.common.shared.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gradel.common.shared.request.PageRequest;
import com.gradel.common.shared.response.PageResult;

import java.util.List;
import java.util.function.Function;

/**
 * @author: sdeven.chen.dongwei@gmail.com
 * @contact 279397942@qq.com
 * @date 2018/4/18
 * @description
 */
public class PageUtils {

    public static Page toPage(PageRequest pageRequest) {
        return pageRequest.getPage();
    }

    public static <T> Page<T> toPage(PageRequest pageRequest, Function<Page<T>, List<T>> function) {
        Page<T> page = toPage(pageRequest);
        List<T> records = function.apply(page);
        page.setRecords(records);
        return page;
    }

    public static <T> PageResult<T> toPageResult(PageRequest pagerRequest, Function<Page<T>, List<T>> function) {
        Page<T> page = toPage(pagerRequest, function);
        PageResult<T> pageResult = new PageResult<>();
        pageResult.setPageOffset(page.getCurrent());
        pageResult.setPageSize(page.getSize());
        pageResult.setDatas(page.getRecords());
        pageResult.setTotalRecord(page.getTotal());
        return pageResult;
    }
}

package com.gradel.api.user.domain.service.search;

import java.util.List;

/**
 * 搜索字典服务
 */
public interface DictionaryService {
    /**
     * 获取词典
     * @return
     */
    List<String> getWords();

    /**
     * 获取停止词典
     * @return
     */
    List<String> getExtWords();

    /**
     * 获取词典最后更新时间
     * @return
     */
    long getLastUpdateTime();


}

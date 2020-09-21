package com.gradel.api.user.domain.service.search.impl.search;


import com.gradel.api.user.domain.service.search.DictionaryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DictionarvServiceImpl implements DictionaryService {
    /**
     * 获取词典
     * @return
     */
    @Override
    public List<String> getWords() {
        List<String> dicts = new ArrayList<>();
        dicts.add("首饰");
        dicts.add("腾讯");
        dicts.add("阿里");
        dicts.add("字节");
        return dicts;
    }

    /**
     * 获取停止词典
     * @return
     */
    @Override
    public List<String> getExtWords() {
        List<String> extDicts = new ArrayList<>();
        extDicts.add("去");
        extDicts.add("玩");
        extDicts.add("来");
        return extDicts;
    }

    /**
     * 获取词典最后更新时间
     * @return
     */
    @Override
    public long getLastUpdateTime() {
        return 99999999999999999l;
    }
}

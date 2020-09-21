package com.gradel.service.user.domain.service.impl.search;

import com.gradel.service.user.domain.service.search.DictionaryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionarvServiceImpl implements DictionaryService {
    /**
     * 获取词典
     * @return
     */
    @Override
    public List<String> getWords() {
        return null;
    }

    /**
     * 获取停止词典
     * @return
     */
    @Override
    public List<String> getExtWords() {
        return null;
    }

    /**
     * 获取词典最后更新时间
     * @return
     */
    @Override
    public long getLastUpdateTime() {
        return 0;
    }
}

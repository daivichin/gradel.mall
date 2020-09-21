package com.gradel.api.user.domain.controller;

import com.gradel.api.user.domain.service.search.DictionaryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * 搜索热更新字典
 */
@Slf4j
@RestController
public class ElasticSearchController {
    // 最新更新间隔5分钟
    private static final long MIN_UPDATE_INTERVAL = 300;
    private static final String CHARSET = "UTF-8";
    // 请求头
    private static final String REQUEST_MODIFIED_KEY = "If-Modified-Since";
    private static final String RESPONSE_MODIFIED_KEY = "Last-Modified";
    // 响应头
    private static final String REQUEST_ETAG_KEY = "If-None-Match";
    private static final String RESPONSE_ETAG_KEY = "ETag";

    @Autowired
    private DictionaryService dictionaryService;

    //@RequestMapping(value = "/dict", method = RequestMethod.HEAD)
    public void needDictUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long current = System.currentTimeMillis() / 1000;
        String lastModifiedStr = request.getHeader(REQUEST_MODIFIED_KEY);
        String eTag = request.getHeader(REQUEST_ETAG_KEY);
        if (StringUtils.isEmpty(lastModifiedStr)) {
            // 首次加载
            response.setStatus(HttpStatus.OK.value());
            response.setHeader(RESPONSE_ETAG_KEY,  String.valueOf(System.currentTimeMillis()));
            response.setHeader(RESPONSE_MODIFIED_KEY, String.valueOf(current));
            return;
        }
        long lastModified;
        try {
            lastModified = Long.parseLong(lastModifiedStr);
        } catch (NumberFormatException e) {
            log.error("invalid header info {}", lastModifiedStr, e);
            response.sendError(HttpStatus.BAD_REQUEST.value(), "invalid header info");
            return;
        }

        // 上次更新时间不会大于当前时间
        if (lastModified >= current) {
            log.error("illegal header info {}", lastModifiedStr);
            response.sendError(HttpStatus.BAD_REQUEST.value(), "illegal header info");
            return;
        }

        // 防止频繁更新
        if (current <= lastModified + MIN_UPDATE_INTERVAL) {
            response.setStatus(HttpStatus.NOT_MODIFIED.value());
            return;
        }

        long lastDictionaryUpdateTime = dictionaryService.getLastUpdateTime();
        // 上次更新后如果数据库没有更新则不进行同步
        if (lastModified >= lastDictionaryUpdateTime) {
            response.setStatus(HttpStatus.NOT_MODIFIED.value());
        } else {
            response.setStatus(HttpStatus.OK.value());
            response.setHeader(RESPONSE_ETAG_KEY, String.valueOf(System.currentTimeMillis()));
            response.setHeader(RESPONSE_MODIFIED_KEY, String.valueOf(current));
        }

    }

    //@RequestMapping(value = "/extDict", method = RequestMethod.HEAD)
    public void needExtDictUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long current = System.currentTimeMillis() / 1000;
        String lastModifiedStr = request.getHeader(REQUEST_MODIFIED_KEY);
        String eTag = request.getHeader(REQUEST_ETAG_KEY);
        if (StringUtils.isEmpty(lastModifiedStr)) {
            // 首次加载
            response.setStatus(HttpStatus.OK.value());
            response.setHeader(RESPONSE_ETAG_KEY, eTag);
            response.setHeader(RESPONSE_MODIFIED_KEY, String.valueOf(current));
            return;
        }
        long lastModified;
        try {
            lastModified = Long.parseLong(lastModifiedStr);
        } catch (NumberFormatException e) {
            log.error("invalid header info {}", lastModifiedStr, e);
            response.sendError(HttpStatus.BAD_REQUEST.value(), "invalid header info");
            return;
        }
        // 上次更新时间不会大于当前时间
        if (lastModified >= current) {
            log.error("illegal header info {}", lastModifiedStr);
            response.sendError(HttpStatus.BAD_REQUEST.value(), "illegal header info");
            return;
        }
        // 防止频繁更新
        if (current <= lastModified + MIN_UPDATE_INTERVAL) {
            response.setStatus(HttpStatus.NOT_MODIFIED.value());
            return;
        }
        long lastDictionaryUpdateTime = dictionaryService.getLastUpdateTime();
        // 上次更新后如果数据库没有更新则不进行同步
        if (lastModified >= lastDictionaryUpdateTime) {
            response.setStatus(HttpStatus.NOT_MODIFIED.value());
        } else {
            response.setStatus(HttpStatus.OK.value());
            response.setHeader(RESPONSE_ETAG_KEY, eTag);
            response.setHeader(RESPONSE_MODIFIED_KEY, String.valueOf(current));
        }
    }

    //@RequestMapping(value = "/dict", method = RequestMethod.GET)
    public void sendDict(HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(HttpStatus.OK.value());
        response.setContentType("text/plain; charset=" + CHARSET);
        List<String> words =  dictionaryService.getWords();
        if (words != null) {
            try (OutputStream out = response.getOutputStream()) {
                for (String word : words) {
                    out.write((word + "\n").getBytes(CHARSET));
                }
            } catch (IOException e) {
                log.error("dict update faild!", e);
            }
        }
    }

    /**
     * 停止词词典接口
     * @param request
     * @param response
     */
    //@RequestMapping(value = "/extDict", method = RequestMethod.GET)
    public void sendExtDict(HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(HttpStatus.OK.value());
        response.setContentType("text/plain; charset=" + CHARSET);
        List<String> words = dictionaryService.getExtWords();
        if (words != null) {
            try (OutputStream out = response.getOutputStream()) {
                for (String word : words) {
                    out.write((word + "\n").getBytes(CHARSET));
                }
            } catch (IOException e) {
                log.error("dict update faild!", e);
            }
        }
    }

}

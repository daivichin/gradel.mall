package com.gradel.common.shared.util;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @作者 chencl
 * @创建时间 2019-7-10
 * @描述: 线程池工具类
 */
public class ExecutorUtils {

    private static final ThreadPoolExecutor addFollowRobotExecutor = new ThreadPoolExecutor(1, 1, 2, TimeUnit.MINUTES,
            new SynchronousQueue<Runnable>());

    public static ThreadPoolExecutor getAddFollowRobotExecutor() {
        return addFollowRobotExecutor;
    }
}

package com.gradel.common.shared.validator;


import com.gradel.parent.common.util.exception.ValidationException;

/**
 * Created with IntelliJ IDEA.
 * User: sdeven.chen.dongwei@gmail.com
 * Date: 2016/2/27
 * Description:参数校验接口
 */
public interface IValidator {
    /**
     * 返回多个错误
     * @param obj
     * @throws ValidationException
     */
    public void validateMutil(Object obj) throws ValidationException;

    /**
     * 返回单个错误
     *
     * @param obj
     * @throws ValidationException
     */
    public void validate(Object obj) throws ValidationException;
}

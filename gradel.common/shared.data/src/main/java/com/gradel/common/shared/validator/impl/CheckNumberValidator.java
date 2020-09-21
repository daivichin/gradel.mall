package com.gradel.common.shared.validator.impl;

import com.gradel.common.shared.validator.IValidator;
import com.gradel.parent.common.util.exception.ValidationException;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: sdeven.chen.dongwei@gmail.com
 * Date: 2016/4/18
 */
@Repository("checkNumberValidate")
public class CheckNumberValidator implements IValidator {

    @Override
    public void validateMutil(Object obj) throws ValidationException {

    }

    @Override
    public void validate(Object target) throws ValidationException {

    }
}

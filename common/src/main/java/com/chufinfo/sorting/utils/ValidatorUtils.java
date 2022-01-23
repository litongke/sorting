package com.chufinfo.sorting.utils;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import com.chufinfo.sorting.exception.RRException;

/**
 * hibernate-validator校验工具类
 *
 * 参考文档：http://docs.jboss.org/hibernate/validator/5.4/reference/en-US/html_single/
 *
 * @author kenyon
 * @date 2017-03-15 10:50
 */
public class ValidatorUtils {
    private static Validator validator;

    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }
    
    
    
    /**
     * 校验对象
     * 
     * @param object
     *            待校验对象
     * @param groups
     *            待校验的组s
     * @throws RRException
     *             校验不通过，则报RRException异常
     */
    public static void validateEntity(Object object, Class<?>... groups) throws RRException {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            StringBuilder msg = new StringBuilder();
            for (ConstraintViolation<Object> constraint : constraintViolations) {
                msg.append(constraint.getMessage()).append(";");
            }
            throw new RRException(msg.toString());
        }
    }

    public static Set<ConstraintViolation<Object>> validateEntityReturn(Object object, Class<?>... groups) {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        return constraintViolations;
    }
    
    public static String validateEntityReturnString(Object object, Class<?>... groups) {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            StringBuilder msg = new StringBuilder();
            for (ConstraintViolation<Object> constraint : constraintViolations) {
                msg.append(constraint.getMessage()).append(";");
            }
            return msg.toString();
        }
        return null;
    }

    /**
     * 判断实体类是不是有问题，如果有问题返回True 如果没问题返回 False
     * 
     * @param object
     * @param groups
     * @return
     */
    public static Boolean validateEntityReturnFlag(Object object, Class<?>... groups) {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            return true;
        } else {
            return false;
        }

    }
}

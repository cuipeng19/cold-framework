package com.cold.framework.common.exception;

import com.cold.framework.common.dictionary.ColdState;

/**
 * @author cuipeng
 * @since 2018/12/4 17:48
 */
public class ParamException extends ColdException {

    public ParamException(String message) {
        this(ColdState.PARAM_VALIDATE_FAIL);
        super.message = message;
    }

    public ParamException(ColdState coldState) {
        super(coldState);
    }
}

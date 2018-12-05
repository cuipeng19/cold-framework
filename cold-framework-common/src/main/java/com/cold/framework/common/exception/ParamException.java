package com.cold.framework.common.exception;

import com.cold.framework.common.dictionary.ColdState;

/**
 * @author cuipeng 2018/12/4 17:48
 */
public class ParamException extends ColdException {

    public ParamException() {
        super(ColdState.PARAM_VALIDATE_FAIL);
    }

    public ParamException(String message) {
        super(message);
        state = ColdState.PARAM_VALIDATE_FAIL.getState();
    }

    public ParamException(ColdState coldState) {
        super(coldState);
    }
}

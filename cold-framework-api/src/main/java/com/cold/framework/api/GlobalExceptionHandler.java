package com.cold.framework.api;

import com.cold.framework.api.bean.out.BaseOutVo;
import com.cold.framework.common.dictionary.ColdState;
import com.cold.framework.common.exception.ColdException;
import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Optional;

/**
 * @author cuipeng
 * @since 2018/12/4 17:58
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler
    @ResponseBody
    ResponseEntity<?> exceptionHandler(Throwable throwable) {
        BaseOutVo baseOutVo;
        if(throwable instanceof ColdException) {
            ColdException e = (ColdException)throwable;
            logger.error(e.getMessage(), Optional.ofNullable(e.getSrcException()).orElseGet(() ->e));
            baseOutVo = new BaseOutVo(e.getState(), e.getMessage(), ImmutableMap.of("desc",e.getMessage()));
        } else {
            logger.error("全局异常：", throwable);
            baseOutVo = new BaseOutVo(ColdState.INTERNAL_ERROR);
        }
        return new ResponseEntity<>(baseOutVo, HttpStatus.OK);
    }
}

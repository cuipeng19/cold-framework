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
 * This class provide centralized exception handling across all {@code @RequestMapping} methods
 * through {@code @ExceptionHandler} methods.
 *
 * @author cuipeng
 * @since 2018/12/4 17:58
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Logger for use in this class
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Customize the response for Throwable.
     *
     * @param throwable the throwable
     * @return a {@code ResponseEntity} instance
     */
    @ExceptionHandler
    @ResponseBody
    ResponseEntity<?> exceptionHandler(Throwable throwable) {
        BaseOutVo baseOutVo;
        if(throwable instanceof ColdException) {
            ColdException e = (ColdException)throwable;
            logger.error(e.getMessage(), Optional.ofNullable(e.getSrcException()).orElseGet(() ->e));
            baseOutVo = new BaseOutVo(e.getState(), e.getMessage(), ImmutableMap.of("desc",e.getMessage()));
        } else {
            logger.error("Global Exception: ", throwable);
            baseOutVo = new BaseOutVo(ColdState.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(baseOutVo, HttpStatus.OK);
    }
}

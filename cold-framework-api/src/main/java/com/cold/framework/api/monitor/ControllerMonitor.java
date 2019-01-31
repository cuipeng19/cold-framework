package com.cold.framework.api.monitor;

import com.cold.framework.api.bean.in.BaseInVo;
import com.cold.framework.common.annotation.Token;
import com.cold.framework.common.dictionary.ColdDictionary;
import com.cold.framework.common.dictionary.ColdState;
import com.cold.framework.common.exception.ColdException;
import com.cold.framework.common.exception.ParamException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

/**
 * @author cuipeng
 * @since 2018/12/4 14:23
 */
@Aspect
@Component
public class ControllerMonitor {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    private ParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * Print logs with input and output parameters.
     *
     * @param joinPoint
     * @throws Throwable
     */
    @Around("execution(* com.cold.framework.api.controller.*Controller.*(..))")
    public Object controllerAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        String methodInfo = joinPoint.getTarget().getClass().getSimpleName() + "." + joinPoint.getSignature().getName();

        try {
            logger.debug("{\"Api_Request\":{},\"params:\"{}}", methodInfo, new ObjectMapper().writeValueAsString(joinPoint.getArgs()));
        } catch (Exception e) {
            logger.debug("{\"Api_Request\":{},\"params:\"{}}", methodInfo, Arrays.toString(joinPoint.getArgs()));
        }

        Object o = joinPoint.proceed();

        long duration = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli() - startTime;
        String returnInfo = new ObjectMapper().writeValueAsString(o);
        logger.debug("{\"Api_Response\":{},\"return:\"{},\"duration:\"{}}", methodInfo, returnInfo, duration);

        return o;
    }

    /**
     * Print logs with external calls.
     *
     * @param joinPoint
     * @throws Throwable
     */
    @Around("execution(* org.springframework.web.client.RestOperations.*(..))")
    public Object restAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        String methodInfo = joinPoint.getTarget().getClass().getSimpleName() + "." + joinPoint.getSignature().getName();

        try {
            logger.debug("{\"Rest_Request\":{},\"params:\"{}}", methodInfo, new ObjectMapper().writeValueAsString(joinPoint.getArgs()));
        } catch (Exception e) {
            logger.debug("{\"Rest_Request\":{},\"params:\"{}}", methodInfo, Arrays.toString(joinPoint.getArgs()));
        }

        Object o = joinPoint.proceed();
        long duration = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli() - startTime;
        String returnInfo = new ObjectMapper().writeValueAsString(o);
        logger.debug("{\"Rest_Response\":{},\"return:\"{},\"duration:\"{}}", methodInfo, returnInfo, duration);

        return o;
    }

    /**
     * Check parameters when calling interfaces.
     *
     * @param joinPoint
     */
    @Before("execution(* com.cold.framework.api.controller.*Controller.*(..))")
    public void parameterBefore(JoinPoint joinPoint) throws Throwable {
        Object target = joinPoint.getThis();
        Object[] params = joinPoint.getArgs();
        Method method = ((MethodSignature)joinPoint.getSignature()).getMethod();

        StringBuffer errorMessage = new StringBuffer();
        if(params!=null) {
            for(Object param : params) {
                if(param instanceof BaseInVo) {
                    Optional.ofNullable(param).ifPresent(e -> {
                        Set<ConstraintViolation<Object>> validateSet = validator.validate(e);
                        validateSet.forEach(s -> errorMessage.append(s.getPropertyPath() + ":" + s.getMessage() + ";"));
                    });
                    if(errorMessage.length()!=0) {
                        throw new ParamException(errorMessage.toString());
                    }
                }
            }

            String[] parameterNames = parameterNameDiscoverer.getParameterNames(method);
            Set<ConstraintViolation<Object>> validateResult = validator.forExecutables().validateParameters(target,method,params);
            validateResult.forEach(e -> {
                PathImpl path = (PathImpl) e.getPropertyPath();
                int paramIndex = path.getLeafNode().getParameterIndex();
                String paramName = parameterNames[paramIndex];
                errorMessage.append(paramName + ":" + e.getMessage() + ";");
            });
            if(errorMessage.length()!=0) {
                throw new ParamException(errorMessage.toString());
            }
        }
    }

    /**
     * Check token when calling interfaces.
     *
     * @param joinPoint
     */
    @Before("execution(* com.cold.framework.api.controller.*Controller.*(..))")
    public void TokenBefore(JoinPoint joinPoint) {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        Object token = request.getHeader("HTTP-USER-TOKEN");
        if ("".equals(token) || token == null) {
            token = request.getParameter("HTTP-USER-TOKEN");
        }
        logger.info("token:" + token);

        Object target = joinPoint.getTarget();
        Method method = ((MethodSignature)joinPoint.getSignature()).getMethod();
        Token tokenClass = target.getClass().getAnnotation(Token.class);
        Token tokenMethod = method.getAnnotation(Token.class);

        String[] excludeMethods = new String[]{};
        if(tokenClass!=null) {
            excludeMethods = tokenClass.exclude();
        }

        if(tokenClass!=null || tokenMethod!=null) {
            if(Arrays.asList(excludeMethods).contains(method.getName())) {
                return;
            }
            if(token==null) {
                throw new ColdException(ColdState.TOKEN_NOT_EXIST);
            }
            Object deviceId = stringRedisTemplate.opsForHash().get(ColdDictionary.USER_TOKEN, token);
            if(StringUtils.isBlank((String) deviceId)) {
                throw new ColdException(ColdState.TOKEN_INVALID);
            }
        }
    }

}

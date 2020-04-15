package com.sentence.dictionary.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import sun.misc.Unsafe;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

@Component
@Aspect
public class LoggingAspect {

    private static final Logger log = LogManager.getLogger(LoggingAspect.class);

    @Pointcut("@annotation(Loggable)")
    public void executeLogging(){}

    @Around(value =  "executeLogging()")
    public Object logMethodCall(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object returnValue = proceedingJoinPoint.proceed();
        long totalTime = System.currentTimeMillis() - startTime;
        StringBuilder message = new StringBuilder("Called method: ");
        message.append(proceedingJoinPoint.getSignature().getName());
        message.append(" TotalTime: ").append(totalTime).append(" ms ");
        Object[] args = proceedingJoinPoint.getArgs();
        if (args!=null && args.length>0){
            message.append("Arguments [ ");
            Arrays.asList(args).forEach(arg -> message.append(" | ").append(arg.toString()));
            message.append(" ] ");
        }

        if (returnValue instanceof Collection) {
            Collection returnColValue = (Collection) returnValue;
            message.append("Return CollectionSize:").append(returnColValue.size());
        }else {
            message.append("Return Object: ").append(returnValue.toString());
        }
        log.info(message.toString());
        return returnValue;
    }
}

package com.znaji.timing;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MissionTimingAop {

    @Around("@annotation(com.znaji.timing.MissionTiming)")
    public Object logExecutionTime(ProceedingJoinPoint pjp) {
        long start = System.currentTimeMillis();
        try {
            return pjp.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } finally {
            long end = System.currentTimeMillis();
            System.out.println("[TIMING] " + pjp.getSignature() + " executed in " + (end - start) + " ms");
        }
    }
}

package com.training.spring.bigcorp.config;

import com.training.spring.bigcorp.service.SiteService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MonitoredAspect {
    private final static Logger logger = LoggerFactory.getLogger(SiteService.class);
    @Before("@annotation(Monitored)")
    public void logServiceBeforeCall(JoinPoint jp) {
        logger.debug("Appel finder " + jp.getSignature());
    }
    @After("@annotation(Monitored)")
    public void logServiceAfterCall(JoinPoint jp) {
        logger.debug("Appel finder after " + jp.getSignature());
    }
}

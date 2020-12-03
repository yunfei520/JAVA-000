package com.example.homework07.dynamicdatasource;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;


@Order(-1)	//设置AOP执行顺序(需要在事务之前，否则事务只发生在默认库中)
@Component
@Log4j2
@Aspect
public class DynamicDataSourceAspect {

    //切点
    @Pointcut("execution(* com.example.homework07.service.*.*(..)))")
    public void daoAspect() { }

    /**
     * 执行方法前更换数据源
     *
     * @param joinPoint        切点
     * @param myDataSource 动态数据源
     */
    @Before("@annotation(myDataSource)")
    public void doBefore(JoinPoint joinPoint, MyDataSource myDataSource) {
        Object target = joinPoint.getTarget();
        String method = joinPoint.getSignature().getName();
        log.info("Object is {}, execute method is {}", target, method);

        DataSourceEnum dataSourceEnum = myDataSource.value();
        if (dataSourceEnum == DataSourceEnum.SLAVE1) {
            log.info("Set dataSource to  {}", DataSourceEnum.SLAVE1.getName());
            DynamicDataSourceContextHolder.setDataSourceKey(DataSourceEnum.SLAVE1.getName());
        } else {
            log.info("Current dataSource is {}",  DataSourceEnum.MASTER.getName());
            DynamicDataSourceContextHolder.setDataSourceKey(DataSourceEnum.MASTER.getName());
        }
    }

}

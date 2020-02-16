package com.example.demo.Aspect;

import com.example.demo.Annotation.Log;
import com.example.demo.Mapper.SysLogMapper;
import com.example.demo.pojo.SysLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Objects;

@Aspect
@Component
public class LogAspect {
    @Autowired
    SysLogMapper sysLogMapper;

//    @Pointcut("within(com.example.demo.Controller..*)")
    @Pointcut("@annotation(com.example.demo.Annotation.Log)")
    public void pointcut() { }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point){
        Object result = null;
        long beginTime = System.currentTimeMillis();
        try{
            //执行方法
            result = point.proceed();
        }catch (Throwable e){
            e.printStackTrace();
        }

        //执行时长
        long time = System.currentTimeMillis() - beginTime;
        //保存日志
        saveLog(point, time);
        return result;
    }

    public void saveLog(ProceedingJoinPoint joinPoint, long time){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SysLog sysLog = new SysLog();

        //注解上的描述
        Log logAnnotation = method.getAnnotation(Log.class);
        if(logAnnotation != null){
            sysLog.setOperation(logAnnotation.value());
        }

        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");

        //请求的方法参数值
        Object[] args = joinPoint.getArgs();
        //请求方法的参数名称
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paramNames = u.getParameterNames(method);
        if (args != null && paramNames != null) {
            String params = "";
            for (int i = 0; i < args.length; i++) {
                params += "  " + paramNames[i] + ": " + args[i];
            }
            sysLog.setParams(params);
        }

        //获取request
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        sysLog.setIp("127.0.0.1");
        sysLog.setUsername("liuqian");
        sysLog.setTime((int)time);
        sysLog.setCreateTime(new Date());
        //(String name, String operation, int time, String method, String params, String ip, Date create_time);
        sysLogMapper.insertLog(sysLog.getUsername(), sysLog.getOperation(), sysLog.getTime(), sysLog.getMethod(),   sysLog.getParams(), sysLog.getIp(), sysLog.getCreateTime());

    }
}

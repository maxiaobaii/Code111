package com.baizhi.aspect;

import com.baizhi.annotation.AddLog;
import com.baizhi.entity.Admin;
import com.baizhi.entity.Log;
import com.baizhi.service.LogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @ClassNmae: LogAspect
 * @Author: yddm
 * @DateTime: 2020/8/31 20:43
 * @Description: TODO
 */

@Aspect
@Configuration
public class LogAspect {

    @Resource
    HttpSession session;
    @Resource
    LogService logService;

    /**
     * 弃用
     * <p>
     * 添加日志
     *
     * @param joinPoint
     * @return
     */
    //@Around("execution(* com.baizhi.service.impl.*.*(..))&& !execution(* com.baizhi.service.impl.*.query*(..))")
    /*public Object addLog(ProceedingJoinPoint joinPoint) {
        //谁   时间    操作    是否成功
        Admin admin = (Admin) session.getAttribute("loginAdmin");
        //时间
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(date);
        //操作   那个方法
        String name = joinPoint.getSignature().getName();
        //放行
        String message = null;
        try {
            Object proceed = joinPoint.proceed();
            message = "success";
            System.out.println("管理员：" + admin.getUsername() + "--时间：" + format + "--操作：" + name + "--状态：" + message);
            return proceed;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            message = "error";
            System.out.println("管理员：" + admin.getUsername() + "--时间：" + format + "--操作：" + name + "--状态：" + message);
            return null;
        }
    }*/
    @Around("@annotation(com.baizhi.annotation.AddLog)")
    public Object addLogs(ProceedingJoinPoint joinPoint) {
        //谁   时间    操作    是否成功
        Admin admin = (Admin) session.getAttribute("loginAdmin");
        //时间
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(date);
        //操作  方法上的注解信息
        String name = joinPoint.getSignature().getName();
        //先得到方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        //获取方法上的注解
        AddLog annotation = method.getAnnotation(AddLog.class);
        //获取注解中的内容
        String value = annotation.value();
        //放行
        String message = null;
        try {
            Object proceed = joinPoint.proceed();
            message = "success";
            Log log = new Log();
            log.setId(UUID.randomUUID().toString());
            log.setName(admin.getUsername());
            log.setDate(date);
            log.setManipulate(value);
            log.setStatus(message);
            logService.addLog(log);
            System.out.println("管理员：" + admin.getUsername() + "--时间：" + format + "--操作：" + value + "--状态：" + message);
            //进行日志数据入库
            return proceed;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            message = "error";
            Log log = new Log();
            log.setId(UUID.randomUUID().toString());
            log.setName(admin.getUsername());
            log.setDate(date);
            log.setManipulate(value);
            log.setStatus(message);
            logService.addLog(log);
            System.out.println("管理员：" + admin.getUsername() + "--时间：" + format + "--操作：" + value + "--状态：" + message);
            return null;
        }
    }
}

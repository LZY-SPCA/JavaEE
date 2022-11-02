package whu.edu.assignment6.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class ApiCount {
    public static Map<String,ApiMemory>  ApiCounts = new HashMap<>();
    @Pointcut("execution(* whu.edu.assignment6.controller.*.*(..))")
    public void controllerPointcut(){}

    @Before("controllerPointcut()")
    public void beforeCotrollers(JoinPoint jp){
        System.out.println("----------------------------");
        addToMap(jp.getSignature().getName());
    }

    @Around("controllerPointcut()")
    public Object aroundControllers(ProceedingJoinPoint jp) throws Throwable {
        long t1 = Calendar.getInstance().getTimeInMillis();
        Object retval = jp.proceed();
        long t2 = Calendar.getInstance().getTimeInMillis();
        long time = t2 - t1;
        addTime(jp.getSignature().getName(),time);
        return retval;
    }

    @AfterThrowing(pointcut = "controllerPointcut()",throwing = "e")
    public void afterControllerException(JoinPoint jp,RuntimeException e){
        addException(jp.getSignature().getName());
    }

    public boolean addToMap(String name){
        for(Map.Entry<String, ApiMemory> entry : ApiCounts.entrySet()) {
            if (entry.getKey().equals(name)) {
                ApiMemory apiMemory = entry.getValue();
                apiMemory.setCount();
                entry.setValue(apiMemory);
                System.out.println(entry.getKey()+" Count: "+entry.getValue().getCount());
                return true;
            }
        }
        ApiMemory newApiMemory = new ApiMemory();
        newApiMemory.setCount();
        ApiCounts.put(name, newApiMemory);
        System.out.println(name + " Count: "+newApiMemory.getCount());
        return false;
    }

    public void addTime(String name,long time){
        for(Map.Entry<String, ApiMemory> entry : ApiCounts.entrySet()) {
            if (entry.getKey().equals(name)) {
                ApiMemory apiMemory = entry.getValue();
                apiMemory.setTimes(time);
                entry.setValue(apiMemory);
                System.out.println("++++++++++++++++++++++++++++");
                System.out.println(name + " Time: "+entry.getValue().getTimes());
            }
        }
    }
    public void addException(String name){
        for(Map.Entry<String, ApiMemory> entry : ApiCounts.entrySet()) {
            if (entry.getKey().equals(name)) {
                ApiMemory apiMemory = entry.getValue();
                apiMemory.setException();
                entry.setValue(apiMemory);
                System.out.println("===============================");
                System.out.println(name + " Excption: "+entry.getValue().getExceptionCount());
            }
        }
    }



}

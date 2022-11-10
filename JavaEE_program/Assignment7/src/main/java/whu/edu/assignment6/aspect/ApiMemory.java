package whu.edu.assignment6.aspect;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class ApiMemory {
    int count;
    int ExceptionCount;
    long longestTime;
    long shortestTime;
    long averageTime;
    List<Long> times = Collections.synchronizedList(new ArrayList<>());
    public ApiMemory(){

        count =0;
        ExceptionCount=0;
    }
    public void setException(){
        ExceptionCount += 1;
    }
    public void setCount(){
        count+=1;
    }
    public void setTimes(long time){
        times.add(time);
        longestTime = calLongestTime();
        shortestTime = calShortestTime();
        averageTime = calAverageTime();
    }
    public long calLongestTime(){
        long time= Collections.max(times);
        return time;
    }
    public long calShortestTime(){
        long time = Collections.min(times);
        return time;
    }
    public long calAverageTime() {
        long sum = 0;
        for (long time : times) {
            sum+=time;
        }
        long result = sum/times.size();
        return result;
    }
}

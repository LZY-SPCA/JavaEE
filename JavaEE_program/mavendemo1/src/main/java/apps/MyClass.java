package apps;

import Annotations.InitMethod;

public class MyClass  {


    @InitMethod
    public void init(int y) throws IllegalArgumentException{
        if(y<0){
            throw new IllegalArgumentException();
        }
        System.out.println(y);
        System.out.println("Init");
    }


}

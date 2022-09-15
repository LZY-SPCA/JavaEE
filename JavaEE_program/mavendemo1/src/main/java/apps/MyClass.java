package apps;

import Annotations.InitMethod;

public class MyClass {

    @InitMethod
    public void init(){
        System.out.println("Init");
    }

    public int i=0;
}

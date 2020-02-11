package bean;


import aop.jdkProxy.Subject;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User implements Subject {

    private String id;
    private String name;
    private String age;
    private String remark;

    public void show() {
        System.out.println("my name is "+this.name);
    }
}

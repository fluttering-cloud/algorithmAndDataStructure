class Father {
    public String a = "father";

    public Father() {
        System.out.println("cd....");
        say();
    }

    public void say() {
        System.out.println("I am father" + a);
    }
}

class Son extends Father{
    private String a ="son";

    @Override
    public void say() {
        super.say();
        System.out.println("I am son" + a);
    }
}


public class Solution02 {

    public static void main(String[] args) {
        //Father father = new Father();
        Son son = new Son();
    }

}
/**
 * 请问这个代码会输出什么，为什么？
 *      知识点：类的加载顺序、当父类和子类有同名的方法、变量时的调用顺序
 */

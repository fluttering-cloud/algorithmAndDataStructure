public class Solution01 {

    public static void main(String[] args) {
        String s = "hello2";
        String s1 = "hello";
        String s2 = "2";
        String s3 = s1 + s2;    // 引用变量在编译期间是不能被确定的，所以它们会在运行时才进行计算，结果是存在堆内的
        String s4 = "hello" + "2";  // 字符串常量的运算在编译期间就确定了，所以 s4 是指向的是常量池内的 hello2

        System.out.println(s == s3);
        System.out.println(s == s4);

        final String s5 = "hello";   // 使用final修饰的变量其实也会放在常量池内
        String s6 = s5 + "2";       //  s6可以看成两个常量运算
        System.out.println(s == s6);

        String s7 = s5 + s2;     // 因为 s2 是变量，在编译期间不能确定，所以 s7是的 hello 是存储在堆内的
        System.out.println(s == s7);

        Integer i = 3;
        Integer i1 = 1;
        Integer i2 = 2;
        Integer i3 = 1 + 2;
        Integer i4 = i1 + i2;

        System.out.println(i == i3);
        System.out.println(i == i4);


    }
}
/**
 * 该案例有如下知识点：
 *      1.常量与常量的运算其实在编译是就可以确定，且计算结果也是常量，如 "a" + "b" 的结果也是常量会存放在常量池内
 *      2.常量与变量的运算在编译阶段不能确定，要在运行时才能确定，所以运算结果会放在堆里
 *      3.变量与变量的运算也是在编译阶段不能确定的，要在运行时才能确定，所以结果也会在堆内
 *      4.被 final 修饰的变量在第一次赋值后就不能再次赋值了，也就是说被  final 修饰的变量的引用地址一旦确定就是不
 *        能改变的。 那为什么会这样呢？因为被 final 修饰的变量在编译期间就能确定了，此时该变量在 jvm 内部其实就是
 *        它的引用的值，jvm会将其放在常量池内，这也就是为什么说 final 修饰的变量为常量
 *
 */
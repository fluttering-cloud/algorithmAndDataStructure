import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Stream 与 集合、数组的关系
 *     集合、数组是由于存储数据的
 *     Stream 则是用于对集合、数组内的数据进行计算的
 *
 * Stream的特点：
 *      1.使用Stream不会改变其操作的集合、数组内的值
 *      2.Stream 的方法的返回值还是 Stream
 *      3.Stream 只有在需要结果的时候才会执行
 */
public class MyStream {

    private static  Collection<Integer> collection;

    static {
        collection = new ArrayList();
        collection.add(1);
        collection.add(2);
        collection.add(3);
        collection.add(4);
        collection.add(5);
    }


    public static void main(String[] args) {
        test10();
    }

    // 获得Stream的几种方法
    public static void test01() {

        // 采用集合对象获取
        Collection collection = new HashSet();
        Stream stream = collection.stream();

        // 采用集合对象获取并行流
        Stream stream1 = collection.parallelStream();

        // 采用数组工具类获取
        int[] ints = new int[10];
        IntStream stream2 = Arrays.stream(ints);

        // 通过 Stream.of 获取
        Stream<Integer> integerStream = Stream.of(1,2,3,4);

        // 获取无限流
        // 注意：无限流就是会生成无数数据的流
        Stream.iterate(1,t -> t + 2);
        Stream.generate(Math::random);

    }

    // 流对象常用方法之过滤
    public static void test02() {
        Stream stream = collection.stream();
        Stream stream1 = stream.filter(i -> (int) i > 4);
        stream1.forEach(System.out::println);
    }

    // 流对象采用方法之排序
    public static void test03() {

        Stream<Integer> stream = collection.stream();
        Stream<Integer> sorted = stream.sorted((a, b) -> b - a);
        sorted.forEach(System.out::println);
    }

    public static void test04() {
        Stream<Integer> stream = collection.stream();
        Stream<Integer> integerStream = stream.map(a -> a + 10);
        integerStream.forEach(System.out::println);

    }

    public static void test05() {
        Stream<Integer> stream = collection.stream();
        Stream<Integer> integerStream = stream.flatMap(a -> Stream.of(a + 20));
        integerStream.forEach(System.out::println);
    }

    public static void test06() {
        Stream<Integer> stream = collection.stream();
        boolean b = stream.allMatch(a -> a == 2);
    }

    public static void test07() {
        Stream<Integer> stream = collection.stream();
        stream.forEachOrdered(a -> {
            System.out.println(a);
        });
    }

    public static void test08() {
        Stream<Integer> stream = collection.stream();
        System.out.println(stream.max(Integer::compare).get());
    }

    public static void test09() {
        Stream<Integer> stream = collection.stream();
        Stream<Integer> peek = stream.peek(a -> {
            a = a + 1;
        });
        peek.forEach(System.out::println);
    }

    public static void test10() {
        Stream<Integer> stream = collection.stream();
        System.out.println(stream.reduce((a, b) ->{
            System.out.println(a);
            System.out.println(b);
            return a - b;
        } ).get());
    }

    public static void test11() {
        Stream<Integer> stream = collection.stream();

        Object collect = stream.collect(Collectors.toList());
        Object collect2 = stream.collect(Collectors.toSet());
    }
}

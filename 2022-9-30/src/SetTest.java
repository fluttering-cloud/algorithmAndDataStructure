import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class Student {
    String name;
    int age;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return age == student.age && Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}

public class SetTest {

    public static void main(String[] args) {

        Set set = new HashSet();

        Student student = new Student();
        student.name = "张三";
        student.age = 20;
        boolean add1 = set.add(student);
        System.out.println(add1);

        student.name = "李四";

        boolean add2 = set.add(student);
        System.out.println(add2);
    }

}

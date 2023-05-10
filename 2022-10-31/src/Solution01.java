import java.util.Arrays;
import java.util.Scanner;

public class Solution01 {


    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int f = Integer.parseInt(sc.nextLine());
        for(int i = 0; i < f; i++){
            String s = sc.nextLine();
            String[] strs = s.split(" ");
            int sum = 0;
            for(int j = 1; j < strs.length; j++) {
                sum += Integer.parseInt(strs[j]);
            }
            System.out.println(sum);
        }
    }
}

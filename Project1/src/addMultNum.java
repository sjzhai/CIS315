/**
 * Created by shengjiezhai on 2017/1/15.
 */

import java.util.Scanner;
public class addMultNum {

    private static void pairs(int X, int Y){
        int sum = X + Y;
        int mul = X * Y;
        System.out.printf("%d %d\n", sum, mul);
    }
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        for(int i = 0; i < num; ++i){
            int X = scan.nextInt();
            int Y = scan.nextInt();
            pairs(X, Y);
        }

        scan.close();
    }
}

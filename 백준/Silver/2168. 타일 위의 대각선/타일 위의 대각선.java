// package Algorithm.Algorithm25.Java.BOJ2168;

import java.util.Scanner;

public class Main {

    private static int gcd(int x, int y){
        if (y == 0) return x;
        return gcd(y, x%y);
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();
        
        // 대각선의 수는 각 행과 열에 영향을 받음
        // 그런데 행과 열의 최대공약수만큼 교점이 발생 (그만큼 대각선의 수가 줄음)
        System.out.println(x + y - gcd(x, y));
    }
}

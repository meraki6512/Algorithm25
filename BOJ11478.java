package Algorithm.Algorithm25;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

//https://www.acmicpc.net/problem/11478

public class BOJ11478 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String given = sc.next();                       // only lower case, 1000

        int ans = 0;
        HashSet<String> hashSet = new HashSet<>();

        for (int i=0; i<given.length(); i++){           // n^2
            for (int j=i+1; j<=given.length(); j++){

                String key = given.substring(i, j);     // i~j까지의 given string

                if (!hashSet.contains(key)){
                    ans ++;
                    hashSet.add(key);
                }

            }
        }

        System.out.println(ans);


    }
}

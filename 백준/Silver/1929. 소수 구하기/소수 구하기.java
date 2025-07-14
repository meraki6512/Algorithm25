// package Algorithm.Algorithm25.Java.BOJ1929;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int M = sc.nextInt();
        int N = sc.nextInt();
        if (N <= 1) return;

        Set<Integer> prime = new HashSet<>();
        for (int i=2; i<=N; i++) prime.add(i);

        for (int i=2; i * i <= N; i++){         // rt N *
            if (!prime.contains(i)) continue;
            for (int j = i * i; j<=N; j+=i){
                prime.remove(j);
            }
        }

        for (int i=M; i<=N; i++){
            if (prime.contains(i))
                System.out.println(i);
        }
    }
}

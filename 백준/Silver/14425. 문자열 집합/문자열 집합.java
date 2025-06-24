import java.util.HashSet;
import java.util.Scanner;

//public class BOJ14425 {
public class Main {
    public static void main(String[] args) {

        int N, M;
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();       // 10^4
        M = sc.nextInt();       // 10^4

        HashSet<String> set = new HashSet<>(N);
        for (int i = 0; i<N; i++){
            set.add(sc.next()); // 길이: 500 이하, 알파벳 소문자로만 구성됨
        }

        int ans = 0;
        for (int i=0; i<M; i++){
            String check = sc.next();
            if (set.contains(check)) ans ++;
        }

        System.out.println(ans);

    }
}

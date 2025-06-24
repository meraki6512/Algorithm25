import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int a = sc.nextInt();       // 200,000
        int b = sc.nextInt();       // 200,000

        HashSet<Integer> A = new HashSet<>();
        HashSet<Integer> B = new HashSet<>();

        for (int i=0; i<a; i++) A.add(sc.nextInt());
        for (int i=0; i<b; i++) B.add(sc.nextInt());

        int ans = 0;
        ArrayList<Integer> Alist = new ArrayList<>(A);
        ArrayList<Integer> Blist = new ArrayList<>(B);
        for (int i=0; i<a; i++) if (!B.contains(Alist.get(i))) ans ++;
        for (int i=0; i<b; i++) if (!A.contains(Blist.get(i))) ans ++;

        System.out.println(ans);
    }
}

//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.Scanner;
//
//class Snowman implements Comparable<Snowman>{
//
//    int top_idx;
//    int bottom_idx;
//    int height;
//
//    Snowman(int top_idx, int bottom_idx, int height){
//        this.top_idx = top_idx;
//        this.bottom_idx = bottom_idx;
//        this.height = height;
//    }
//
//    @Override
//    public int compareTo(Snowman o) {
//        return this.height - o.height;
//    }
//}
//
////public class BOJ20366 {
//public class Main {
//
//    public static void main(String[] args) {
//
//        Scanner sc = new Scanner(System.in);
//        int N = sc.nextInt();
//        int[] snow = new int[N];
//        for (int i = 0; i< N; i++){
//            snow[i] = sc.nextInt();
//        }
//
//        ArrayList<Snowman> snowmen = new ArrayList<>();
//        for (int i = 0; i< N; i++){
//            for (int j = i + 1; j < N; j++){
//                snowmen.add(new Snowman(i, j, snow[i] + snow[j]));
//            }
//        }
//
//        Collections.sort(snowmen);
//
//        int min = Integer.MAX_VALUE;
//        for (int i = 0; i < snowmen.size() - 1; i++){
//            Snowman nb = snowmen.get(i+1);
//            Snowman cur = snowmen.get(i);
//
//            if (nb.top_idx != cur.top_idx && nb.bottom_idx != cur.bottom_idx
//                    && nb.top_idx != cur.bottom_idx && nb.bottom_idx != cur.top_idx){
//                min = Math.min(min, nb.height - cur.height);
//            }
//        }
//
//        System.out.println(min);
//
//    }
//}

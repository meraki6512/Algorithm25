package Algorithm.Algorithm25.Java.GRM06;
import java.io.*;
import java.util.*;

class Drink implements Comparable<Drink>{
    int c, d;
    Drink(int c, int d){
        this.c = c;
        this.d = d;
    }

    @Override
    public int compareTo(Drink o){
        return this.d - o.d;
    }
}

public class Number2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        HashMap<String, Integer> map = new HashMap<>();
        map.put("red", 1);
        map.put("orange", 2);
        map.put("yellow", 3);
        map.put("green", 4);
        map.put("blue", 5);
        map.put("navy", 6);
        map.put("purple", 7);

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++){
            boolean ans = true;

            int N = Integer.parseInt(br.readLine());

            PriorityQueue<Drink> drinks = new PriorityQueue<>();
            for (int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                String color = st.nextToken();
                drinks.add(new Drink(map.get(color), Integer.parseInt(st.nextToken())));
            }

            Drink prev = drinks.poll();
            System.out.println(prev.c +", " + prev.d);
            int cnt = 7;

            while (!drinks.isEmpty() && cnt > 1){
                Drink cur = drinks.poll();
                System.out.println(cur.c +", " + cur.d);
                if (cur.c != prev.c) cnt --;

                if (cur.c < prev.c){
                    ans = false;
                    break;
                }
                prev = cur;
            }

            System.out.println(ans && (cnt==1)? "Yes" : "No");
        }


    }
}


// -> 15 / 100 점
package Algorithm.Algorithm25.Java.BOJ21944;

/*
* 21944. https://www.acmicpc.net/problem/21944
* recommend G x: 알고리즘 분류가 G인 문제 중 x=1이면 가장 어려운, x=-1이면 가장 쉬운
*                조건을 만족하는 게 여러 개면 x=1이면 P가 큰, x=-1이면 P가 작은
* recommend2 x: 알고리즘 분류에 상관 없이 x=1이면 가장 어려운 (여러개면 P큰), x=-1이면 반대 
* recommend3 x L: 알고리즘 분류에 상관 없이 
*                 x=1이면 난이도 L보다 크거나 같은 문제 중 가장 쉬운, 조건을 만족하는 문제가 여러개면 P가 작은
*                 x=-1이면 반대
*                 조건을 만족하는 번호가 없으면 -1
* add P L G:
* solved P
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Problem implements Comparable<Problem>{
        int P, L, G;
        Problem(int P, int L, int G){
            this.P = P; // 문제 번호
            this.L = L; // 난이도
            this.G = G; // 알고리즘 분류
        }

        @Override
        public int compareTo(Problem o) {
            return L == o.L ? P - o.P : L - o.L;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());        // 10^5

        TreeSet<Problem> problems = new TreeSet<>();
        Map<Integer, Problem> mapP = new HashMap<>();
        TreeMap<Integer, TreeSet<Problem>> mapL = new TreeMap<>();
        Map<Integer, TreeSet<Problem>> mapG = new HashMap<>();

        for (int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int P = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            int G = Integer.parseInt(st.nextToken());
            Problem problem = new Problem(P, L, G);
            problems.add(problem);
            mapP.put(P, problem);
            TreeSet nL = mapL.getOrDefault(L, new TreeSet<>());
            nL.add(problem);
            mapL.put(L, nL);
            TreeSet nG = mapG.getOrDefault(G, new TreeSet<>());
            nG.add(problem);
            mapG.put(G, nG);
        }

        int M = Integer.parseInt(br.readLine());
        for (int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            switch (st.nextToken()){

                case "add":
                    int P = Integer.parseInt(st.nextToken());
                    int L = Integer.parseInt(st.nextToken());
                    int G = Integer.parseInt(st.nextToken());
                    Problem problem = new Problem(P, L, G);
                    problems.add(problem);
                    mapP.put(P, problem);
                    TreeSet nL = mapL.getOrDefault(L, new TreeSet<>());
                    nL.add(problem);
                    mapL.put(L, nL);
                    TreeSet nG = mapG.getOrDefault(G, new TreeSet<>());
                    nG.add(problem);
                    mapG.put(G, nG);
                    break;

                case "solved":
                    P = Integer.parseInt(st.nextToken());
                    Problem rm = mapP.get(P);
                    problems.remove(rm);
                    mapP.remove(rm.P);
                    mapL.get(rm.L).remove(rm);
                    mapG.get(rm.G).remove(rm);
                    break;

                case "recommend":
                    // G x : 알고리즘 분류 G인 문제 중 x에 따라 출력
                    G = Integer.parseInt(st.nextToken());
                    int x = Integer.parseInt(st.nextToken());
                    if (x==1) System.out.println(mapG.get(G).last().P);
                    else if (x==-1) System.out.println(mapG.get(G).first().P);
                    break;

                case "recommend2":
                    x = Integer.parseInt(st.nextToken());
                    if (x==1) System.out.println(problems.last().P);
                    else if (x==-1) System.out.println(problems.first().P);
                    break;

                case "recommend3":
                    x = Integer.parseInt(st.nextToken());
                    L = Integer.parseInt(st.nextToken());
                    if (x==1){
                        if (L > mapL.lastKey()) System.out.println(-1);
                        else {
                            // 난이도 L보다 크거나 같은 문제 중 가장 쉬운 문제
                            Map<Integer, TreeSet<Problem>> m = mapL.tailMap(L);
                            problem = null;
                            int mL = 101;
                            for (int l=L; l<101; l++){
                                if (m.get(l) == null || m.get(l).isEmpty()) continue;
                                if (l < mL) {
                                    mL = l;
                                    problem = m.get(l).first();
                                }
                            }
                            if (problem == null) System.out.println(-1);
                            else System.out.println(problem.P);

//                            Map.Entry<Integer, TreeSet<Problem>> e = mapL.ceilingEntry(L);
//                            if (e == null) System.out.println(-1);
//                            else if (e.getValue().isEmpty()) System.out.println(-1);
//                            else System.out.println(e.getValue().first().P);
                        }
                    }
                    else if (x==-1){
                        // 난이도 L보다 작은 문제 중 가장 어려운 문제
                        Map<Integer, TreeSet<Problem>> m = mapL.headMap(L);
                        problem = null;
                        int mL = -1;
                        for (int l=L; l>0; l--){
                            if (m.get(l) == null || m.get(l).isEmpty()) continue;
                            if (l >= mL) {
                                mL = l;
                                problem = m.get(l).last();
                            }
                        }
                        if (problem == null) System.out.println(-1);
                        else System.out.println(problem.P);

//                        Map.Entry<Integer, TreeSet<Problem>> e = mapL.lowerEntry(L);
//                        if (e == null) System.out.println(-1);
//                        else if (e.getValue().isEmpty()) System.out.println(-1);
//                        else System.out.println(e.getValue().last().P);
                    }
                    break;
            }
        }

    }
}

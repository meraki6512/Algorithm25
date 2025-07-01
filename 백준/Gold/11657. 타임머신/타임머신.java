////package Algorithm.Algorithm25;
//
//import java.util.*;
//
///*
//* 1번 도시에서 출발해서 나머지 도시로 가는 가장 빠른 시간을 구하라.
//* */
//class Bus{
//    int a, b, c;
//    Bus(int a, int b, int c){
//        this.a = a;
//        this.b = b;
//        this.c = c;
//    }
//}
//
////public class BOJ11657 {
//public class Main {
//
////    private static final int INF = Integer.MAX_VALUE;
//    // 500 * 6000 * 10000 -> long
//    private static final long INF = 5_000_001; // 500 * 10000
//
//    public static long[] bellmanFord(int start, int N, ArrayList<Bus> buses){
//
//        long[] costs = new long[N+1];
//        Arrays.fill(costs, INF);
//        costs[start] = 0;
//        boolean updated = false;
//
//        // 1. 최대 N-1번 체크
//        for (int i = 0; i < N-1; i++){
//            for (Bus bus: buses){
//                // costs[bus.a] != INF && : 시작이 INF면 오버플로우 발생해서 음수값이 들어갈 가능성 있음
//                // 업데이트 가능하면 모두 업데이트 (a를 거쳐 b로 가는 가중치 vs 현재 b의 최단거리)
//                if (costs[bus.a] != INF && costs[bus.a] + bus.c < costs[bus.b]){
//                    costs[bus.b] = costs[bus.a] + bus.c;
//                    updated = true;
//                }
//            }
//
//            if (!updated) return costs; // update가 더이상 안되면 음수 사이클 체크도 할 필요없음
//        }
//
//        // 2. 음수 사이클 체크
//        // : N번째에도 업데이트되는 게 있으면 음수 간선 사이클 존재
//        Set<Integer> affected = new HashSet<>();
//        for (Bus bus: buses){
//            if (costs[bus.a] != INF && costs[bus.a] + bus.c < costs[bus.b]){
//                affected.add(bus.b);   // bus.a -> b에 음수 사이클이 있으므로, a 또는 b 모두 포함해도 됨
//            }
//        }
//
//        // 3. 음수 사이클 추적  (BFS)
//        // : 사이클 내부에서 영향을 받는 모든 정점은 최단 거리 정의할 수 없음
//        boolean[] visited = new boolean[N+1];
//        Queue<Integer> que = new LinkedList<>(affected);
//
//        while (!que.isEmpty()){
//            int cur = que.poll();
//            visited[cur] = true;
//
//            for (Bus bus: buses){
//                if (bus.a == cur && !visited[bus.b]) {
//                    visited[bus.b] = true;
//                    que.add(bus.b);
//                }
//            }
//        }
//
//        // 4. start 정점에서 무한히 타임워프가 가능한지 확인
//        boolean[] reachable_from_start = new boolean[N+1];
//        Queue<Integer> que2 = new LinkedList<>();
//        que2.add(start);
//        reachable_from_start[start] = true;
//
//        while (!que2.isEmpty()){
//            int cur = que2.poll();
//            for (Bus bus: buses){
//                if (bus.a == cur && !reachable_from_start[bus.b]){
//                    reachable_from_start[bus.b] = true;
//                    que2.add(bus.b);
//                }
//            }
//        }
//
//        // 5. 반환
//        // visited == true면 음수 사이클 내의 정점이므로 INF로 처리
//        for (int i=1; i<=N; i++){
//            if (visited[i]){
//                costs[i] = INF;
//
//                // 음수 사이클 내의 정점인데 start에서 도달하는 곳이라면 무한히 타임워프 가능하므로 즉시 종료, null 반환
//                if (reachable_from_start[i])
//                    return null;
//            }
//        }
//
//        return costs;
//    }
//
//    public static void main(String[] args) {
//
//        // In
//        Scanner sc = new Scanner(System.in);
//
//        int N = sc.nextInt();       // 500
//        int M = sc.nextInt();       // 6000
//        // A->B로 가는데 시간 C: -10^4~10^4
//
//        ArrayList<Bus> buses = new ArrayList<>();
//        for (int i=0; i<M; i++){
//            int a = sc.nextInt();
//            int b = sc.nextInt();
//            int c = sc.nextInt();
//            buses.add(new Bus(a, b, c));
//        }
//
//        // Sol
//        long[] ans = bellmanFord(1, N, buses);
//
//        // Out
//        if (ans==null) System.out.println("-1");
//        else {
//            StringBuilder res = new StringBuilder();
//            for (int i = 2; i <= N; i++) {
//                if (ans[i] == INF) res.append("-1\n");
//                else res.append(ans[i]).append("\n");
//            }
//            System.out.println(res.toString().strip());
//        }
//    }
//}

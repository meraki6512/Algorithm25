import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
* 1. 참석자들 중 연결된 그룹을 찾는다.
* 2. 각 그룹에서 대표 1명을 선출한다.
*    (대표는 그룹 내 최단 거리 기준으로 최대 거리가 가장 작은 사람)
* Sol: UnionFind + Floyd / BFS + Floyd
* */

public class Main {

    private static final int INF = 10000;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();                           // 참석 인원 100
        int M = sc.nextInt();                           // 연결 관계 수
        int[][] d = new int[N+1][N+1];                  // 최단 거리
        boolean[][] connected = new boolean[N+1][N+1];  // 연결 여부

        for (int i=0; i<=N; i++) {
            Arrays.fill(d[i], INF);
            d[i][i] = 0;                                // 업데이트 위해 (자기자신 거리 0)
        }

        for (int i=0; i<M; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            d[a][b] = d[b][a] = 1;
            connected[a][b] = connected[b][a] = true;
        }

        // Floyd
        for (int k=1; k<=N; k++){
            for (int a=1; a<=N; a++){
                for (int b=1; b<=N; b++){
                    // a->b vs a->k->b
                    d[a][b] = Math.min(d[a][b], d[a][k] + d[k][b]);
                }
            }
        }

        // BFS
        // 그룹(연결된 요소) 구하기
        boolean[] visited = new boolean[N+1];
        ArrayList<ArrayList<Integer>> groups = new ArrayList<>();

        for (int i=1; i<=N; i++){
            
            // i가 미방문 상태면, i를 기준으로 새로운 그룹을 만듦
            if (!visited[i]){
                visited[i] = true;
                Queue<Integer> que = new LinkedList<>();
                que.add(i);
                ArrayList<Integer> grp = new ArrayList<>();
                grp.add(i);

                // i로부터 연결된 노드를 모두 확인
                while (!que.isEmpty()){
                    int u = que.poll();
                    for (int v=1; v<=N; v++){
                        if (connected[u][v] && !visited[v]){
                            visited[v] = true;
                            que.add(v);
                            grp.add(v);
                        }
                    }
                }
                
                // 현재 그룹을 그룹 리스트에 추가
                groups.add(grp);

            }
        }

        // 그룹별 리더 뽑기
        // 그룹 내에서 d(최단거리)의 최댓값이 가장 작은 사람
        PriorityQueue<Integer> leaders = new PriorityQueue<>();
        for (ArrayList<Integer> g : groups){
            int min = INF;      // 현재 그룹의 d의 최댓값을 비교할 기준값
            int leader = 0;     // 현재 리더

            // 후보자 순회
            for (int cndi : g){
                int max = 0;
                for (int other : g){
                    // 자기 자신을 제외하고 남과의 d 중 최댓값
                    if (cndi == other) continue;
                    max = Math.max(max, d[cndi][other]);
                }
                
                // 현재 후보자의 최댓값이 기준값보다 더 작으면 리더 업데이트
                if (max < min) {
                    min = max;
                    leader = cndi;
                }
            }

            // 리더를 PQ에 추가
            leaders.add(leader);
        }

        // OUT
        StringBuilder sb = new StringBuilder(leaders.size() + "\n");
        while (!leaders.isEmpty()) sb.append(leaders.poll()).append("\n");
        System.out.println(sb);
    }
}

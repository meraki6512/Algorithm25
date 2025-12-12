package Algorithm.Algorithm25.Java.BOJ7142;

/**
 * 틀림 -> 추후 다시 풀기
 * <a href="https://www.acmicpc.net/problem/7142">7142. 데이터 만들기 3</a>
 * <a href="https://www.acmicpc.net/problem/7612">참고 문제 [7612. SSSP]</a>
 *
 * 입력 데이터 X를 만드는 프로그램
 * OptimizedBellmanFord 코드는 데이터 X를 수행할 때 시간 내에 성공해야 함. V * E * Q <= 10^6 -> 1010 * E <= 10^6 -> E ~~ 990
 * FloydWarshall 코드는 데이터 X를 수행할 때 시간 초과(TLE)이어야 함. -> V^3 > 10^6 -> V: 100초과
 *
 * 방향 가중치 그래프 G와 G의 두 정점 s, t가 주어졌을 때,
 * p(s, t) = s에서 t까지 최단 거리의 가중치의 합
 * 입력은 총 두 블록으로 이루어져 있음.
 * 1. 첫 번째 블록: 그래프 G의 인접 리스트
 * - 정점의 개수 V
 * - 0번 정점부터 정점의 정보
 *  : n[i] (정점 i에서 나가는 간선의 개수), n개의 (j, w)쌍 (j: 간선이 가리키는 정점 번호, w는 간선의 가중치)
 * 2. 두 번째 블록: 쿼리 Q개
 * -
 *
 * 1 ≤ V ≤ 300
 * n[i]: 0~ 정수, 0 ≤ Σn[i](E) ≤ 5000
 * |w| < 10^6
 * 1 ≤ Q ≤ 10
 *
 * G에는 음수 사이클 없음
 * 데이터는 작을 수록 좋기 때문에, 최대 T개의 정수로 이루어짐
 *
 * ---------------
 * V = 300, E = V-1, Q = ..
 * 0 -> 1 -> 2 -> ... -> 300
 */
public class Main {

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        int V = 300;
        int E = V-1;

        // 1. 첫 번째 블록: 그래프 G의 인접 리스트
        sb.append(V).append("\n");

        // 0번 정점부터 V-2번 정점까지 (총 E줄)
        for (int i = 0; i < E; i++) {
            sb.append(1) // 나가는 간선 개수: 1개
            .append(" ").append(i + 1).append(" ").append(1).append("\n");  // 도착 정점: i + 1, 가중치: 1
        }

        // V-1번(마지막) 정점
        sb.append(0).append("\n");

        // 2. 두 번째 블록: 쿼리 Q개
        sb.append(1).append("\n");
        sb.append("0 ").append(V - 1).append("\n");

        System.out.println(sb);
    }
}
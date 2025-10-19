// package Algorithm.Algorithm25.Java.BOJ2176;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * "가까워지며 이동한다"
 * dist(u, v) = 정점 u와 v의 사이의 거리
 * 정점 S에서 (x를 거쳐) T로 이동할 때, dist(S, T) > dist(x, T)
 */

public class Main {

	private static class Node implements Comparable<Node> {
		int b, d;
		Node(int b, int d) {
			this.b = b;
			this.d = d;
		}

		@Override
		public int compareTo(Node o) {
			return d - o.d;
		}
	}

	// 정점 N 1000
	// 간선 M 100_000
	// A - B : C

	private static List<Node>[] graph;
	private static int[] dp, dist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		graph = new List[N + 1];
		for (int i = 0; i <= N; i++) graph[i] = new ArrayList<>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			graph[A].add(new Node(B, C));
			graph[B].add(new Node(A, C));
		}

		// dijkstra
		dist = new int[N + 1];
		final int INF = 10_000_001;
		Arrays.fill(dist, INF);
		dijkstra(2);

		// solve w dp
		dp = new int[N + 1];
		Arrays.fill(dp, -1);
		dp[2] = 1;
		System.out.println(move(1));

	}

	// [2]까지의 최단 경로 모두 구하기
	private static void dijkstra(int st) {
		dist[st] = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(st, 0));

		while (!pq.isEmpty()) {
			Node c = pq.poll();
			int cur = c.b;
			if (dist[cur] < c.d) continue;

			// 주변 이웃 노드에 대해 거쳐가는 값이 dist[] 보다 작으면 갱신하고 pq에 넣음
			for (Node n : graph[cur]) {
				int nxt = n.b;
				if (dist[nxt] > dist[cur] + n.d) {
					dist[nxt] = dist[cur] + n.d;
					pq.add(new Node(nxt, dist[nxt]));
				}
			}
		}
	}

	// 거리 반환
	private static int move(int cur){
		if (dp[cur] != -1) return dp[cur];
		dp[cur] = 0;
		for (Node n : graph[cur]){
			int nxt = n.b;
			if (dist[cur] > dist[nxt]) dp[cur] += move(nxt);
		}
		return dp[cur];
	}
}


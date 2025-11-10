package Algorithm.Algorithm25.Java.BOJ16234;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * <a href="https://www.acmicpc.net/problem/16234">16234. 인구 이동</a>
 *
 * 인구이동:
 * 국경선을 공유하는 두 나라의 인구 차이가 L~R이면, 국경선을 연다
 * - 모든 나라의 열릴 국경선이 모두 열렸다면, 인구 이동을 시작한다
 * - 국경선이 열려있어 이동 가능하면 연합이라고 칭함
 * - 연합 인구수 계산 = (연합의 인구수) / (연합을 이루고 있는 칸의 개수) (int)
 * - 연합을 해체하고 국경선을 닫는다
 * 더 이상 인구 이동이 없을 때까지 지속된다
 */
public class Main {

	private static int[] dr = {-1, 1, 0, 0};
	private static int[] dc = {0, 0, -1, 1};
	private static boolean[][] visited;
	private static int N, L, R;
	private static int[][] A;
	private static ArrayList<int[]> alli_list;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		A = new int[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				A[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(move());
	}

	// 더 이상 인구 이동이 일어나지 않을 때까지 반복
	private static int move() {
		int ans = 0;

		while (true){

			boolean moved = false;
			visited = new boolean[N][N];

			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (!visited[r][c]) {
						int sum = bfs(r, c);

						int avg = sum / alli_list.size();
						if (alli_list.size() > 1) {
							for (int[] alli : alli_list) A[alli[0]][alli[1]] = avg;
							moved = true;
						}
					}
				}
			}

			if (!moved) return ans;
			ans ++;
		}
	}

	private static int bfs(int sr, int sc) {

		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[]{sr, sc});
		alli_list = new ArrayList<>();
		alli_list.add(new int[]{sr, sc});
		visited[sr][sc] = true;

		int sum = A[sr][sc];
		while (!q.isEmpty()) {
			int[] tmp = q.poll();
			int r = tmp[0];
			int c = tmp[1];
			for (int i = 0; i < 4; i++) {
				int nr = tmp[0] + dr[i];
				int nc = tmp[1] + dc[i];
				if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
				if (visited[nr][nc]) continue;
				int sub = Math.abs(A[r][c] - A[nr][nc]);
				if (L <= sub && sub <= R) {
					visited[nr][nc] = true;
					q.offer(new int[]{nr, nc});
					alli_list.add(new int[]{nr, nc});
					sum += A[nr][nc];
				}
			}
		}
		return sum;
	}
}

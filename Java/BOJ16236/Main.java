package Algorithm.Algorithm25.Java.BOJ16236;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * <a href="https://www.acmicpc.net/problem/16236">16236. 아기 상어</a>
 */
public class Main {

	private static int N, X, Y, ate, size = 2, ans;
	private static int[][] map;
	private static int[] dx = {-1, 1, 0, 0};
	private static int[] dy = {0, 0, -1, 1};

	private static class Fish implements Comparable<Fish> {
		int x, y, d;
		public Fish(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}

		@Override
		public int compareTo(Fish o) {
			return d != o.d ? (d - o.d)
				: (x != o.x ? (x - o.x) : (y - o.y));
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] == 9) {
					X = i;
					Y = j;
					map[i][j] = 0;
				}
			}
		}

		while (true) {
			Fish target = hunt();
			if (target == null) break;

			// Hunt
			X = target.x;
			Y = target.y;
			map[X][Y] = 0;

			ans += target.d;

			if (++ate == size) {
				size ++;
				ate = 0;
			}
		}

		System.out.println(ans);
	}

	private static Fish hunt(){

		PriorityQueue<Fish> canHunt = new PriorityQueue<>();
		PriorityQueue<Fish> sharkPq = new PriorityQueue<>();
		sharkPq.add(new Fish(X, Y, 0));

		int min = Integer.MAX_VALUE;
		int[][] dist = new int[N][N];
		for (int i = 0; i < N; i++) Arrays.fill(dist[i], -1);
		dist[X][Y] = 0;

		while (!sharkPq.isEmpty()) {
			Fish cur = sharkPq.poll();

			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];

				if (0 <= nx && nx < N && 0 <= ny && ny < N && dist[nx][ny] < 0) {

					// (1) 방문 체크
					dist[nx][ny] = dist[cur.x][cur.y] + 1;

					// (2) 거리 계산: 이동 가능 여부 (>=)
					if (map[nx][ny] > size)
						continue;

					// (3) 최소 거리를 이미 넘어가는지 체크
					if (dist[nx][ny] > min)
						continue;

					// 1. 이동
					sharkPq.add(new Fish(nx, ny, dist[nx][ny]));

					// 2. 물고기 크기 계산
					if (map[nx][ny] > 0 && map[nx][ny] < size) {
						canHunt.add(new Fish(nx, ny, dist[nx][ny]));
						min = Math.min(min, dist[nx][ny]);
					}
				}
			}
		}

		if (canHunt.isEmpty()) return null;
		return canHunt.poll();
	}
}

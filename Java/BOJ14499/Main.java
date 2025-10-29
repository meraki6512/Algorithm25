package Algorithm.Algorithm25.Java.BOJ14499;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href="https://www.acmicpc.net/problem/14499">14499. 주사위 굴리기</a>
 */
public class Main {

	//  1
	// 234
	//  5
	//  6
	private static int[] dice = new int[7];
	private static int N, M, x, y, K;
	private static int[][] map;
	private static int[] dx = {0, 0, 0, -1, 1}; // 동서북남
	private static int[] dy = {0, 1, -1, 0, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++){
			move(Integer.parseInt(st.nextToken()));
		}
	}

	private static void move(int d) {
		int nx = x + dx[d];
		int ny = y + dy[d];
		if (nx < 0 || nx >= N || ny < 0 || ny >= M) return;
		roll(d, nx, ny);
		x = nx;
		y = ny;
	}

	// 1 2 3 4 동 서 북 남
	private static void roll(int d, int x, int y) {
		// 먼저 굴림
		int tmp = dice[3];
		switch (d){
			case 1:
				dice[3] = dice[2];
				dice[2] = dice[6];
				dice[6] = dice[4];
				dice[4] = tmp;
				break;
			case 2:
				dice[3] = dice[4];
				dice[4] = dice[6];
				dice[6] = dice[2];
				dice[2] = tmp;
				break;
			case 3:
				dice[3] = dice[5];
				dice[5] = dice[6];
				dice[6] = dice[1];
				dice[1] = tmp;
				break;
			case 4:
				dice[3] = dice[1];
				dice[1] = dice[6];
				dice[6] = dice[5];
				dice[5] = tmp;
				break;
		}
		// 0이면 주사위의 바닥면을 맵으로 복사
		if (map[x][y] == 0) map[x][y] = dice[6];
		else {
			dice[6] = map[x][y]; // 아니면 칸에 쓰인 수가 주사위의 바닥면으로 복사
			map[x][y] = 0;		 // 후 칸에 쓰인 수 = 0
		}
		System.out.println(dice[3]);
	}
}


// 4 2 0 0 8 				// N, M, x, y, K
// 0 2
// 3 4
// 5 6
// 7 8
// 4 4 4 1 3 3 3 2			// 이동 명령

// 0 0 0 0
// 0 0 0 0

// 0
// 0
// 3
// 0
// 0
// 8
// 6
// 3
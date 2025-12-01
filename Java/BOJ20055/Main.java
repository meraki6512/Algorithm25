package Algorithm.Algorithm25.Java.BOJ20055;

// 칸 2N개 (1~2N-1)
// 각 칸의 내구도 A[i]
// 1번 칸에 로봇을 올리면 N번 칸에서는 내림
// 로봇이 각 칸에 있으면 내구도 --

// 로봇은 스스로 이동할 수 있음
// 1. 벨트 한 칸 먼저 이동
// 2. N번에 가까운 로봇부터 한 칸 이동할 수 있으면 이동

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 로봇의 첫 위치는..
// -> 없는 상태로 시작하는 것 같음
// 칸의 번호가 fixed냐 벨트와 함께 moving이냐..
// -> 고정되어 있는 게 맞는 듯
// N 100
public class Main {

	private static int k = 0; // 내구도 0인 칸의 개수; 입력 받을 때, 내구도 0인 거 있으면 ++
	private static int N, K;
	private static int[] A;
	private static boolean[] occupied;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		A = new int[2*N+1];
		occupied = new boolean[2*N+1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= 2*N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
			if (A[i] == 0) k++;
		}

		System.out.println(run());
	}

	// 컨베이어 벨트 작동
	private static int run() {
		int res = 0;
		while (k < K) {
			res++;

			// 1. 벨트가 각 칸위에 있는 로봇과 함께 한 칸 회전한다.
			int a = A[2*N];
			for (int i = 2*N - 1; i > 0; i--) {
				A[i + 1] = A[i];
				occupied[i + 1] = occupied[i];
			}
			A[1] = a;
			occupied[1] = occupied[N] = false;

			// 2. 가장 먼저 올라간 로봇부터 한 칸 이동할 수 있다면 이동한다.
			for (int i = N; i > 0; i--) {
				if (occupied[i] && canMove(i)){
					move(i);
				}
			}

			// 3. 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
			if (canMove(0)) load(1);
		}

		return res;
	}

	// 내구도 --: 줄인 내구도가 0이면 k++;
	private static void load(int idx){
		if (--A[idx] == 0) k++;
		occupied[idx] = true;
	}

	// 이동하려는 칸: 내구도가 1 이상이고, 로봇이 없으면 이동 가능
	private static boolean canMove(int i) {
		return (A[i+1] >= 1) && (!occupied[i+1]);
	}

	// 로봇 하나의 이동
	// 내구도 --: 줄인 내구도가 0이면 k++;
	// 위치가 N이면 내림
	private static void move(int i) {
		if (--A[i+1] == 0) k++;

		occupied[i] = false;

		if (i+1 != N) {
			occupied[i+1] = true;
		}
	}
}

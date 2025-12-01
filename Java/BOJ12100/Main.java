package Algorithm.Algorithm25.Java.BOJ12100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 12100. 2048
 * 나중에 다시 풀기
 */
public class Main {

	private static int N, ans = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		int[][] board = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(board, 1);
		System.out.println(ans);
	}

	private static void dfs(int[][] board, int depth) {
		if (depth == 5) {
			ans = Math.max(ans, getMaxValueOnBoard(board));
			return;
		}

		// 상하좌우 이동
		dfs(moveBoardToTop(board), depth + 1);
		dfs(moveBoardToBottom(board), depth + 1);
		dfs(moveBoardToLeft(board), depth + 1);
		dfs(moveBoardToRight(board), depth + 1);
	}

	/**
	 * 보드에서 최댓값을 리턴함
	 */
	private static int getMaxValueOnBoard(int[][] board) {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				max = Math.max(board[i][j], max);
			}
		}
		return max;
	}

	/**
	 * 보드를 시계방향(오른쪽으로) 90도 회전시킴
	 * 1 2		3 1
	 * 3 4		4 2
	 * [j][N-1-i] <- [i][j]
	 */
	private static int[][] rotate(int[][] board){
		int[][] newBoard = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				newBoard[j][N-i-1] = board[i][j];
			}
		}
		return newBoard;
	}

	/**
	 * 보드를 반시계방향(왼쪽) 90도 회전시킴
	 * 1 2		2 4
	 * 3 4		1 3
	 * [N-1-j][i] <- [i][j]
	 */
	private static int[][] rotateCCW(int[][] board) {
		int[][] newBoard = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				newBoard[N - 1 - j][i] = board[i][j];
			}
		}
		return newBoard;
	}

	/**
	 * 보드를 좌측으로 움직임
	 */
	private static int[][] moveBoardToLeft(int[][] board) {
		// 한 줄씩 읽으면서, 왼쪽부터 두 개씩 합치기
		int[][] newBoard = new int[N][N];

		for (int i = 0; i < N; i++) {
			int idx = 0;
			Queue<Integer> q = new LinkedList<>();
			for (int j = 0; j < N; j++) {
				if (board[i][j] != 0) {		// * 0이 아닌 값만 넣어야 함
					q.add(board[i][j]);
				}
			}

			while (!q.isEmpty()) {
				int a = q.poll();
				if (!q.isEmpty() && a == q.peek()) {
					q.poll();
					newBoard[i][idx] = a * 2;
				}
				else {
					newBoard[i][idx] = a;
				}
				idx ++;
			}
		}

		return newBoard;
	}

	private static int[][] moveBoardToRight(int[][] board) {
		// 반시계 방향으로 180도 돌리고
		int[][] rotatedBoard = rotateCCW(rotateCCW(board));

		// 왼쪽으로 민 다음
		int[][] movedBoard = moveBoardToLeft(rotatedBoard);

		// 다시 시계 방향으로 180도 돌리기
		return rotate(rotate(movedBoard));
	}

	private static int[][] moveBoardToTop(int[][] board) {
		// 반시계 방향으로 90도 돌리고
		int[][] rotatedBoard = rotateCCW(board);

		// 왼쪽으로 민 다음
		int[][] movedBoard = moveBoardToLeft(rotatedBoard);

		// 다시 시계 방향으로 90도 돌림
		return rotate(movedBoard);
	}

	private static int[][] moveBoardToBottom(int[][] board) {
		// 시계 방향으로 90도 돌리고
		int[][] rotatedBoard = rotate(board);

		// 왼쪽으로 민 다음
		int[][] movedBoard = moveBoardToLeft(rotatedBoard);

		// 다시 반시계 방향으로 90도 돌림
		return rotateCCW(movedBoard);
	}
}

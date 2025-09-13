// package Algorithm.Algorithm25.Java.BOJ2234;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 2234. 성곽의 개수
 * https://www.acmicpc.net/problem/2234
 *
 * IN
 * 1번 줄: N, M (50)
 * M개의 줄: N개의 정수(벽 정보 = 이진수 0~15(남동북서 : 8421))
 *
 * OUT
 * 1. 방의 개수
 * 2. 가장 넓은 방의 넓이
 * 3. 하나의 벽을 제거하여 얻을 수 있는 가장 넓은 방의 크기
 */
public class Main {
    private static int N, M, num, size, max;
    private static int[][] roomNum;
    private static int[][] info;
    private static int[] roomSize;
    private static final int[] bit = {8, 4, 2, 1};  // 남동북서
    private static final int[] dx = {1, 0, -1, 0};
    private static final int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {

        /** In **/
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        roomNum = new int[M][N]; // visited 체크 및 방번호
        info = new int[M][N];
        roomSize = new int[N*M + 1]; // 방번호만큼 필요. 방번호는 1부터 시작해 최대 2500까지
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                info[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        /** 방의 개수와 크기를 탐색함 **/
        num = 1; // visited (false: roomNum == 0) 구분을 위해 1부터 시작
        size = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (roomNum[i][j] == 0) {   // 방문하지 않았으면 탐색
                    roomSize[num] = bfs(i, j);
                    size = Math.max(size, roomSize[num]);
                    num++;
                }
            }
        }

        /**
         // print:
            // roomNum
            for (int i = 0; i < M; i++) {
                System.out.println(Arrays.toString(roomNum[i]));
            }
            // roomSize
            System.out.println(Arrays.toString(roomSize));
         */

        /** 벽 하나를 제거하면서 가장 넓은 방의 크기를 탐색함 **/
        max = 0;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                int curRoom = roomNum[i][j];

                for (int k = 0; k < 4; k++) {
                    int ni = i + dx[k];
                    int nj = j + dy[k];
                    if (!inRange(ni, nj)) continue;
                    int nxtRoom = roomNum[ni][nj];

                    // 벽 뒤가 다른 roomNum 일 경우, 두 방의 roomSize를 더해 맥스 업데이트
                    if (curRoom != nxtRoom) max = Math.max(max, roomSize[curRoom] + roomSize[nxtRoom]);
                }
            }
        }

        /** Out **/
        System.out.println(num - 1); // 1부터 시작했으므로 총 개수 하나 뺌
        System.out.println(size);
        System.out.println(max);
    }

    /**
     * 이동 가능한 경우 (벽이 없는 경우)를 쫓아가며 트래킹하면서
     * 방문 체크하고 크기를 잼.
     */
    private static int bfs(int i, int j) {
        int size = 1;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{i, j});
        roomNum[i][j] = num;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1];

            for (int k = 0; k < 4; k++) {
                if ((bit[k] & info[x][y]) == 0){    // 벽이 없어 이동 가능할 때
                    int nx = x + dx[k], ny = y + dy[k];
                    if (!inRange(nx, ny) || roomNum[nx][ny]!=0) continue; // 범위안에 없거나 이미 방문했다면 패스

                    roomNum[nx][ny] = num;
                    q.add(new int[]{nx, ny});
                    size++;
                }
            }
        }

        return size;
    }

    private static boolean inRange(int x, int y) {
        return x >= 0 && x < M && y >= 0 && y < N;
    }
}

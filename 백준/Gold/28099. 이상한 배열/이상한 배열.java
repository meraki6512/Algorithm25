import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/*
* 이상한 배열
* https://www.acmicpc.net/problem/28099
* 이상한 배열: 같은 값이 있을 때 그 사이의 값이 그 값보다 작거나 같은 배열
* 이상한 배열이면 Yes, 아니면 No를 출력하라.
*
* Segment Tree 사용해야 함 : 구간 쿼리 max, min, sum 등을 항상 log 타임에 구할 수 있음
* 같은 값이 여러번 등장할 때 그 사이 구간의 최댓값이 그 값보다 크면 No
 */
public class Main {

    static class SegmentTree{
        int[] tree;
        int n;
        SegmentTree(int[] arr){
            this.n = arr.length;
            int size = 1;
            while (size < n) size <<= 1;
            tree = new int[size<<1];
            build(arr, 1, 0, n-1);
        }

        // 트리 빌드 (재귀)
        void build(int[] arr, int cur, int s, int e){
            if (s == e) tree[cur] = arr[s];
            else{
                int m = (s+e)/2;
                build(arr, 2*cur, s, m);
                build(arr, 2*cur+1, m+1, e);
                tree[cur] = Math.max(tree[2*cur], tree[2*cur+1]); // 자식 노드 중 최댓값을 현재 노드로 함 -> 최댓값 트리 구성
            }
        }

        // [l, r] 구간 최댓값
        int maxInBetween(int cur, int s, int e, int l, int r){

            if (r<s || e<l) return -1;                  // 구간 벗어난 경우 min값 리턴
            else if (l<=s && e<=r) return tree[cur];    // 구간 포함된 경우 현재 노드 리턴
            
            // 구간의 일부만 포함된 경우, 자식으로 분할
            int m = (s+e)/2;
            return Math.max(maxInBetween(2*cur, s, m, l, r),
                    maxInBetween(2*cur+1, m+1, e, l, r));
        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());    // 2*10^5

        for (int test_case = 0; test_case < T; test_case++) {

            int N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            int[] arr = new int[N];
            for (int i=0; i<N; i++) arr[i] = Integer.parseInt(st.nextToken());
            boolean ans = true;

            // check
            SegmentTree seg = new SegmentTree(arr);
            Map<Integer, Integer> map = new HashMap<>();
            
            for (int i=0; i<N; i++){
                int cur = arr[i];
                
                // 같은 값이 있었다면
                if (map.containsKey(cur)){
                    // 이전에 등장한 위치
                    int prev = map.get(cur);

                    // (이전 위치와 현재 위치 사이에) 값이 있다면
                    if (i - prev > 1){ 
                        if (seg.maxInBetween(1, 0, N-1, prev + 1, i - 1) > cur) { // 구간 내 최댓값이 현재보다 큰지 체크
                            ans = false;                // 크면 false
                            break;
                        }
                    }
                }

                // 현재 위치(인덱스)로 갱신
                map.put(cur, i);    
            }
            sb.append(ans? "Yes\n" : "No\n");
        }
        System.out.println(sb);
    }
}

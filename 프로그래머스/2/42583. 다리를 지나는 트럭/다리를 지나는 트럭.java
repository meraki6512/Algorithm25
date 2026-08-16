import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {        
        // 단순하게 한 번 계산할 때: 10^8
        
        int answer = 0;
        
        // 큐
        // 무게: 따로 계산
        
        int cur_weight = 0;
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i < bridge_length; i++) dq.add(0);
        
        int i = 0;
        while (i < truck_weights.length) {
            
            // 1. 빼주기
            cur_weight -= dq.pop();
            
            // 2. 가능하면 값 넣고 아니면 0 넣기
            int nw = truck_weights[i];            
            if (cur_weight + nw <= weight) {
                cur_weight += nw;
                i++;
                dq.add(nw);
            }
            else {
                dq.add(0);
            }
            answer ++;
        }
        
        while (!dq.isEmpty()) {
            dq.pop();
            answer++;
        }
        
        return answer;
    }
}
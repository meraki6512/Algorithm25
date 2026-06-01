class Solution {
    
    private int[] numbers;
    private int target, answer = 0;
    
    public int solution(int[] Numbers, int Target) {
        numbers = Numbers;
        target = Target;
        
        dfs(0, 0);
        
        return answer;
    }
    
    private void dfs(int idx, int sum) {
        
        if (idx == numbers.length) {
            if (sum == target) {
                answer ++;
            }
            return;
        }
        
        dfs(idx+1, sum + numbers[idx]);
        dfs(idx+1, sum - numbers[idx]);
    }
}
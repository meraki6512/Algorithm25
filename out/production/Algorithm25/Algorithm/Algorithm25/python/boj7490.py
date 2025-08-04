# BOJ 7490 0 만들기

# 1 2 3 4 ... N 으로 쓴 수열
# 숫자 사이에 +, -를 넣거나 숫자를 이어 붙이고
# 그 값을 계산했을 때 0이 될 수 있을까?
# 가능한 모든 수식을 찾아라.

# 테스트 케이스 T (1~9)
# 자연수 N (3~9)

# 생각해보자. 
# 1. dfs - 3^8회
# 2. 수열이 정해져 있으니, ... ... 음.. 다른 방법도 가능할 것 같은데...
# ... eval
# 일단 dfs도 시간적으로 가능할 듯하니 풀어봅시다.

# input
T = int(input())
test_case = []
for _ in range(T):
    test_case.append(int(input()))

# solution
class Solution:

    def exp_to_make_0(self, N):

        ans = []

        self.dfs(self, "1", '+', 2, N, ans)
        self.dfs(self, "1", '-', 2, N, ans)
        self.dfs(self, "1", '', 2, N, ans)

        return ans

    
    def dfs(self, exp, c, cur, N, ans):

        cur_exp_to_cal = exp + c + str(cur)
        cur_exp = exp + ' ' + str(cur) if c == '' else cur_exp_to_cal

        if cur == N:
            if eval("".join(cur_exp_to_cal.split())) == 0:
                ans.append(cur_exp)
            return
        
        else:
            self.dfs(self, cur_exp, '+', cur + 1, N, ans)
            self.dfs(self, cur_exp, '-', cur + 1, N, ans)
            self.dfs(self, cur_exp, '', cur + 1, N, ans)

# output
for i, N in enumerate(test_case):
    ans = list(set(Solution.exp_to_make_0(Solution, N)))
    ans.sort()

    for exp in ans:
        print(exp)
    
    if i < T-1:
        print()
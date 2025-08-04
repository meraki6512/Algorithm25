# BOJ 1806 부분합

# 10,000 이하의 자연수로 이뤄진 수열
# 수열의 연속된 수들의 부분합 중에서 
# 그 합이 S 이상이 되는 것 중 가장 짧은 것의 길이를 구하라.
# (단, 합이 S 이상이 되는 것이 없다면 0 출력)

# 수열의 길이 N (10~10^5)
# 수열의 합의 하한 S (0~10^8)
# 0.5초, 128MB

# 스터디에서 다시 언급된 문제
# 오랜만에 다시 풀어보자!

# input
N, S = map(int, input().split())
nums = list(map(int, input().split()))

# 부분합 
p = [0] * (N+1)
for i in range(N):
    p[i+1] = p[i] + nums[i]

ans = N + 1
s, e = 0, 1

while e < N + 1:                    # 부분합 리스트의 길이 내에서
    
    key = p[e] - p[s]               # 부분합 중에

    if key >= S:                    # S 이상이 되면
        ans = min(ans, e-s)         # 길이가 더 짧은 것 채택
        s += 1                      # 더 짧은 길이에 한해서 계산해야하니까 s++
    
    else:                           # S보다 작으면
        e += 1                      # 범위 늘려줘야 하니까 e++

# output
print(ans if ans != N+1 else 0)
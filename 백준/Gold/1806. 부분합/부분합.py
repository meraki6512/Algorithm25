# BOJ 1806 부분합

# 10,000 이하의 자연수로 이뤄진 수열
# 수열의 연속된 수들의 부분합 중에서 
# 그 합이 S 이상이 되는 것 중 가장 짧은 것의 길이를 구하라.
# (단, 합이 S 이상이 되는 것이 없다면 0 출력)

# 수열의 길이 N (10~10^5)
# 수열의 합의 하한 S (0~10^8)
# 0.5초, 128MB

# 연속된 수? 수열에서 연속되었다는 의미.. 로 생각하자.

N, S = map(int, input().split())
nums = list(map(int, input().split()))

# 2-pointers

f = r = -1
prf_sum = 0
min_len = N+1

while f <= r and r < N: # 10^5
    
    # 현재까지의 합이 S에 못 미칠 경우, 계속 탐색
    if prf_sum < S:
        r += 1
        if r == N:
            break
        prf_sum += nums[r]
    
    else: # prf_sum >= S: # 합을 넘어선 경우
        f += 1
        prf_sum -= nums[f] # 다음 연산을 위해 앞의 값 제외
        min_len = min(r-f+1, min_len) # 최소 길이 계산

result = 0 if min_len == N+1 else min_len
print(result)
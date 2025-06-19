# 13144. List of Unique Numbers
# 1s, 32MB

# 길이가 N인 수열 (10^5)
# 수열에서 연속한 수를 뽑았을 때, 같은 수가 여러번 등장하지 않는 경우의 수를 구하라.
# *조합 시, 같은 수의 조합도 수의 수열에서의 번호가 다르면 다른 조합으로 취급
import sys
input = sys.stdin.readline

N = int(input())
nums = list(map(int, input().split()))

# right를 기준으로 생각하면서 계산을 해줘야한다!
#
# right를 한 칸씩 확장하면서, 중복이 생기면 left를 앞으로 당겨 중복 제거
# 각 구간별로 right - left + 1개!

# 1, 2, 3, 1, 2
# left = 0, right = 3
# left = 1, right = 3
# 231
# 31
# 1
# -> 3-1+1 = 3

used = set()
ans = 0
left = 0

for right in range(N):                # right를 포함하는 가능한 경우의 수
    
    while nums[right] in used:        # right를 포함할 수 있도록 
        used.remove(nums[left])       # 중복되는 left 전부 제거
        left += 1                     # (left를 하나씩 당기면서)

    used.add(nums[right])             # 현재 right를 set에 추가
    ans += (right - left + 1)         # 현재 right를 포함해서 가능한 경우의 수

print(ans)
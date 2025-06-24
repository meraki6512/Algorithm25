# 13144. List of Unique Numbers
# 1s, 32MB

# 길이가 N인 수열 (10^5)
# 수열에서 연속한 수를 뽑았을 때, 같은 수가 여러번 등장하지 않는 경우의 수를 구하라

import sys
input = sys.stdin.readline

N = int(input())
nums = list(map(int, input().split()))

# *조합 시, 같은 수의 조합도 수의 수열에서의 번호가 다르면 다른 조합으로 취급

# 슬라이딩 윈도우
# 그냥 전체 구하면 1+2+...+10^5일테니까 시간 초과날 것
# -> 같은 수의 수열에서의 번호를 저장해두고, 첫번째 슬라이드만 계산한다.
# (회전초밥 문제와 비슷한 느낌)

ans = N

# 윈도우 사이즈는 2부터 N까지 가능
for w_size in range(2, N + 1):

  # 첫번째 윈도우 찾기
  # 어디부터 시작할지, 해당 윈도우에서 각 요소가 unique인 넘버 계산
  s = i = 0
  while s + w_size <= N:
    
    unique = [False] * (N + 1)          # 1번부터, 첫번째 윈도우가 확정되지 않으면 새롭게 정의
  
    while i < w_size:                 # s부터 윈도우를 만들 수 있는 곳까지

      # 해당 번호가 없으면 T, 중복되면 불가
      key = nums[s + i]

      if unique[key]: break
      unique[key] = True

      i += 1

    # 윈도우 시작 지점이 정해지 않았으면 윈도우 이동, 정해졌으면 종료
    if i != w_size:
      i = 0
      s += 1
    else:
      break
  
  # 가능한 윈도우가 있으면 체크해주고, 해당 윈도우부터 시작
  e = s + w_size - 1
  if e < N:
    ans += 1
  
  while e + 1 < N:

    unique[nums[s]] = False      # 슬라이드 첫값 (제외)
    s += 1
    e += 1

    if unique[nums[e]]:          # 슬라이드 끝값 다음값 추가가 불가하면 (이미 존재하면)
      continue                    # 패스
    
    unique[nums[e]] = True       # 슬라이드 끝값 (다음값 추가)

    ans += 1       

print(ans)



# >> 슬라이딩 윈도우처럼 보이지만
# 실제로는 N**2의 연산을 하는 것은 똑같음
# 다시 생각해보자
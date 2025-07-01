# 1005. ACM Craft
# 임계 경로의 소요 시간을 구하라.

# T: 테스트 케이스
# N: 건물 개수: 10^3
# K: 규칙 개수: 10^6
# delays: 정수: 10^6
# rules: X->Y: N 

import sys
input = sys.stdin.readline
T = int(input())

for _ in range(T):

    # input
    N, K = map(int, input().split())
    delays = [0] + list(map(int, input().split())) # 0 마진

    dp = [[0] * (K) for _ in range(N+1)]
    cnt = 0

    for i in range(K):
        X, Y = map(int, input().split())
        dY = delays[dp[Y][0]]
        dX = delays[X]
        
        if dY > dX: 
            dp[Y][0] = dp[Y][0] # 그대로 유지

        elif dY == dX: 
            dp[Y][cnt] = X      # 여러 개 있을 수 있음 
            cnt += 1

        else: # dY < dX
            dp[Y][0] = X        # 업데이트
            cnt = 0

    W = int(input())

    # solution
    ans = delays[W]

    while dp[W][0]:                
        ans += delays[dp[W][0]] # 어차피 여러개라면 값이 모두 같을 것

        max_W = dp[W][0]
        for i in range(1, len(dp[W])):
            if delays[dp[W][i]] > delays[max_W]:
                max_W = dp[W][i]

        W = max_W        # 다음 작업으로 이동

    print(ans)



# self-feedback
# 병렬적으로 있음을 고려해야함 (이 부분을 떠올려놓고 구현 시에 반영 안함.. 주의하기!)
# 스터디원의 설명: 위상정렬 + dp
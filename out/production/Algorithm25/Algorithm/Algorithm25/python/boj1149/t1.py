# 1149. RGB 거리
# 집 N개가 순서대로 있을 때, 이웃 집과 다른 색으로 칠하는 비용의 최솟값

N = int(input()) # 1000
rgb = [list(map(int, input().split())) for _ in range(N)]
dp = [[0, 0, 0] for _ in range(N+1)]

# DP!
# r, g, b 각각의 비용을 모두 구한 뒤 최솟값을 찾는다.
for i in range(1, N+1): # N * N

    dp[i][0] = min(dp[i-1][1], dp[i-1][2]) + rgb[i-1][0]
    dp[i][1] = min(dp[i-1][0], dp[i-1][2]) + rgb[i-1][1]
    dp[i][2] = min(dp[i-1][0], dp[i-1][1]) + rgb[i-1][2]

print(min(dp[N])) # N 


# e.g.
# 3         # 0  0 0 0
# 26 40 83  # 0  0 0 0
# 49 60 57  # 0*89 0 0   *: min(40, 83) + 49 = 89
# 13 89 99  # 0  0 0 0

# 0 0 0 0   # 0 0  0  0
# 0 0 0 0   # 0 26 40 83
# 0 0 0 0   # 0 89 86 83
# 0 0 0 0   # 0 96 172 185


# 참고자료
# https://m.blog.naver.com/occidere/220785383050
# self-feedback
# 접근이나 방향성은 맞았는데, dp로 해결함을 못 떠올림.
# 아직 느낌을 잘 못 찾은 듯. 더 많이 풀기.
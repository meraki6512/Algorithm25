# 13164. 행복 유치원

# n(1~3*10^5)명의 원생들을 키(~10^9자연수)순으로 줄 세우고, K(1~n)개의 조로 나눔
# 단, 각 조에는 적어도 1명, 같은 조의 원생들은 서로 인접
# 티셔츠 비용 = 조별 키큰원생과 키작은 원생의 키 차이
# 전체 조의 티셔츠 비용 합의 최소값은?

# 인원, 조 개수
n, k = map(int, input().split())
heights = list(map(int, input().split()))

costs = [heights[i]-heights[i-1] for i in range(1, n)]
costs.sort() 

print(sum(costs[:n-k])) # 0 ~ n-1-k까지의 합

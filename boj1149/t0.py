# 1149. RGB 거리
# 집 N개가 순서대로 있을 때, 이웃 집과 다른 색으로 칠하는 비용의 최솟값

N = int(input()) # 1000
rgb = [list(map(int, input().split())) for _ in range(N)]

# rgb를 따로 구하고
# 원래의 인덱스를 저장해서 인덱스 기준으로 가장 작은 인덱스에 해당하는 rgb중하나를 고르고
# 만약 이게 바로 전 인덱스의 색깔과 같다면 그다음 작은 인덱스에 해당하는 rgb중하나를 고르고
# 해당 cost를 더함 : 0번부터 N-1번 인덱스까지 다 더하면 됨 

r = dict()
g = dict()
b = dict()

for i in range(N):
    r[i] = rgb[i][0]
    g[i] = rgb[i][1]
    b[i] = rgb[i][2]

print(r)
print(g)
print(b)

rgb_check = [False, False, False]
ans = 0

# 색칠
for i in range(N):

    # 이웃하는 집의 색과 다른 색만 가능
    clr = []
    if not rgb_check[0]:
        clr.append(r[i])

    if not rgb_check[1]:
        clr.append(g[i])

    if not rgb_check[2]:
        clr.append(b[i])

    # 현재 색깔 고르기
    min_clr = min(clr)
    ans += min_clr

    # rgb_check update
    # 이웃집과 중복하지 않게 골랐고, 고른게 r/g/b면 True/False 설정
    if not rgb_check[0] and min_clr == r[i]:
        rgb_check[0] = True
        rgb_check[1] = rgb_check[2] = False

    elif not rgb_check[1] and min_clr == g[i]:
        rgb_check[1] = True
        rgb_check[0] = rgb_check[2] = False

    elif not rgb_check[2] and min_clr == b[i]:
        rgb_check[2] = True
        rgb_check[0] = rgb_check[1] = False

print(ans)


# 예제가 풀리긴 하는 것 같은데..
# 아닌 경우도 있지 않을까..
# 예를 들어, r이 처음에 제일 작아서 골랐지만, 이후 g나 b가 압도적으로 큰 경우. 그리디가 성립하지 못할 듯.
# 그러면 그냥 작아서 고르는 방식이 아니라 완전 탐색으로 r, g, b의 경우를 고려해야할 텐데
# 시간복잡도는 괜찮을까?
# -> dp를 사용하자! (t1)
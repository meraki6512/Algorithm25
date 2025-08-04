# 1764. 듣보잡
# 해시셋

N, M = map(int, input().split()) # 500,000

nev_heard = [input() for _ in range(N)]
nev_seen = [input() for _ in range(M)]

nev_heard_set = set(nev_heard)
ans = []

for name in nev_seen:
    if name in nev_heard_set:
        ans.append(name)

ans.sort()

print(len(ans))
for name in ans:
    print(name)
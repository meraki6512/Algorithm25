# 30466. 우정은 BFS처럼, 사랑은 DFS처럼
# 1s, 1024MB

# 1~N까지 번호로 매겨진 정점으로 이뤄진 트리 (2*10^5)
# d[i] = dfs에서 i번 정점 방문한 순서
# b[i] = bfs에서 i번 정점 방문한 순서
# sum(abs(d[i]-b[i]))이 최대가 되는 트리를 만들어보자.

# * 순회 후보가 여럿이면, 번호가 더 작은 정점을 먼저 순회
# * 가능한 트리가 여러 가지라면, 아무거나 출력

# 아이디어: 한쪽에 깊은, 한쪽에 얕은 자식이 공존해야 두 순회의 순서 차이 값이 최대가 된다.

# input
import sys
write = sys.stdout.write

N = int(input())


# sol
# 최댓값 구하기
# = 3~N까지 차이의 최댓값 = 3~(N//2)과 (N//2)~3의 차이의 합의 2배
# 또는 체인 자식 수 a = N//2, 직접 자식 수를 b = N-a-1이라고 하면
# 체인 자식: a개 * abs((i+1)−(b+i+1)) = ab이고    (di: i+1, bi: b+i+1)
# 직접 자식: b개 * abs((a+i+1)−(i+1)) = ba이라서  (di: a+i+1, bi: i+1)
# = 2ab 로도 계산할 수도 있음
max_sig = 0
s = 3
e = N
while s <= e:
  max_sig += e - s
  s += 1
  e -= 1

max_sig *= 2

print(max_sig)

# 트리 만들기
# 최대한 깊으면서 최대한 넓게 퍼진 형태가 되어야 함
# -> 절반은 1번에 체인 형태로 붙어야 하고, 나머지 절반은 1번에 곧바로 붙어있으면 됨
edges = []
key = N//2 + 1

for i in range(1, key):         # 1~key값까지는 체인 형태 (i, i+1)
  edges.append((i, i + 1))

for i in range(key + 1, N + 1): # key 다음값부터 끝까지는 1번에 곧바로 붙어있는 형태 (1, i)
  edges.append((1, i))


# output
write(''.join(f'{u} {v}\n' for u, v in edges))
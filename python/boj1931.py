# 1931 회의실 배정
# 2초, 128MB

# 1개의 회의실, N개의 회의(10^5), 각 회의 I(시작, 끝: 음이 아닌 int)
# 겹치지 않는 최대 회의 개수 = ?

# input
N = int(input())
info = [list(map(int, input().split())) for _ in range(N)]

# solution
# aprch1. 끝 순서대로 정렬하고 같으면 시작 순서대로 정렬한 후 찾는다 (adopted!) - Greedy. The only.
# aprch2. (끝-시작) 순서대로 정렬하고 찾는다 - X (wrong)
info.sort(key = lambda x: (x[1], x[0])) # nlogn

cur = info[0]
take = [info[0]]

for i, time in enumerate(info):

    if i == 0:
        continue

    if cur[1] > time[0]:    # 현재 회의 끝 시각 > 새 회의 시작 시각이면
        continue            # 회의 불가

    else:
        cur = time          # 회의 시작
        take.append(time)   # 정답 배열에 넣음

# output
print(len(take))
# 9663. N-Queen
# 10s, 128MB

# N: 15
# N * N 체스판에서 N개의 퀸이 서로 공격할 수 없게 놓는 문제
import sys
sys.setrecursionlimit(10**9)

N = int(input())
ans = 0
# row: 현재 놓을 행
# col_mask: 지금까지 퀸을 놓은 열(col)의 상태를 비트마스크로 표현
# ldiag: '/' 방향 대각선 (왼쪽 아래 → 오른쪽 위) 공격 여부 마스크
# rdiag: '\' 방향 대각선 (왼쪽 위 → 오른쪽 아래) 공격 여부 마스크
def dfs(row, col_mask, ldiag, rdiag):
    global ans

    # N개의 퀸을 모두 놓았으면 경우의 수 1 증가
    if row == N:
        ans += 1
        return

    # 현재 행에서 퀸을 놓을 수 있는 위치 계산
    # 전체 가능한 칸 (1 << N) - 1 에서
    # 이미 점령된 col/ldiag/rdiag를 제외한 칸만 선택
    available = ((1 << N) - 1) & ~(col_mask | ldiag | rdiag)

    # 가능한 위치가 남아있는 동안 반복
    while available:
        # 가장 오른쪽 1비트 추출 (가장 우선순위가 높은 자리)
        pick = available & -available

        # 현재 위치를 사용했으니 available에서 해당 위치 제거
        available -= pick

        # col_mask | pick: 해당 열 사용 표시
        # (ldiag | pick) << 1: '/' 방향 대각선은 아래로 갈수록 왼쪽으로 밀리므로 왼쪽 시프트
        # (rdiag | pick) >> 1: '\' 방향 대각선은 아래로 갈수록 오른쪽으로 밀리므로 오른쪽 시프트
        dfs(row + 1,
            col_mask | pick,
            (ldiag | pick) << 1,
            (rdiag | pick) >> 1)


dfs(0, 0, 0, 0)
print(ans)
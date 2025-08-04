T = int(input())

for test_case in range(1, T+1):

    N, M = map(int, input().split())

    arr = [ list(map(int, input().split())) for _ in range(N) ]

    sums = []

    for i in range(N-M+1):
        for j in range(N-M+1): # (N-M+1)**2

            s = 0
            for m in range(M): # M
                s += sum(arr[i+m][j:j+M])
            
            sums.append(s)
    
    ans = max(sums)
    print(f'#{test_case} {ans}')



# ---
# 누적합 코드

T = int(input())

for test_case in range(1, T + 1):
    N, M = map(int, input().split())
    arr = [list(map(int, input().split())) for _ in range(N)]

    # 누적합 배열 초기화 (0-based → 1-based로 크기 N+1 x N+1 사용)
    psum = [[0] * (N + 1) for _ in range(N + 1)]

    # 누적합 계산
    for i in range(1, N + 1):
        for j in range(1, N + 1):
            psum[i][j] = arr[i - 1][j - 1] + psum[i - 1][j] + psum[i][j - 1] - psum[i - 1][j - 1]
            
    # 최대 M x M 부분합 찾기
    ans = 0
    for i in range(M, N + 1):  # i, j는 우측 하단 좌표
        for j in range(M, N + 1):
            total = (
                psum[i][j]
                - psum[i - M][j]
                - psum[i][j - M]
                + psum[i - M][j - M]
            )
            ans = max(ans, total)

    print(f'#{test_case} {ans}')

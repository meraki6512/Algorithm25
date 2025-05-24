# 서로 다른 N개의 자연수의 합 = S (1~2^32-1)
# 개수 N의 최댓값은? 

# 개수가 최대가 되려면 자연수는 작아야 함
# 1부터 센다

# 6(N) - 딱 맞음
# 1 2 3 -> 3(cnt)
# 7(N) - 합이 7보다 크기 때문에 3개가 됨
# 1 2 3 4 -> 3(cnt)
# 8 9
# 1 2 3 4 -> 3
# 10
# 1 2 3 4 -> 4

N = int(input())

total = 0
cnt = 0
while True:

    cnt += 1
    total += cnt

    if total >= N:
        break

ans = cnt if total == N else cnt-1
print(ans)
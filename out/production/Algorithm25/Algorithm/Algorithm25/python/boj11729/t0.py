# 11729. 하노이 탑 이동 순서
# 1s, 256MB

# N개의 원판과 세 칸으로 된 하노이 탑
# 첫 칸에 큰 순서대로 쌓여있고, 마지막 칸으로 이동시키려고 할 때,
# 최소 이동 횟수가 되게 하는 이동 순서 = ?
import time 
import sys
sys.setrecursionlimit(10**9)
N = int(input())
hanoi = [list(range(1, N+1)), [], []]
result = []
ans = []

def dfs(K):
    time.sleep(0.5)
    print(hanoi, result, ans)

    if hanoi[2] and hanoi[2][-1] == 1 and len(hanoi[2]) == N:   # 세번째 하노이의 탑이 1이고 개수가 N이면
        print("ANS!", K, result)
        ans.append([K, list(result)])
        return              # 종료

    if hanoi[1] and hanoi[1][-1] == 1 and len(hanoi[1]) == N:   # 두번째 하노이에 완성
        return
    
    # 완성을 못하더라도 계속 교착되는 상태면 종료
    if (hanoi[0] and hanoi[2] and hanoi[0][-1] > hanoi[2][-1]) or (hanoi[1] and hanoi[2] and hanoi[1][-1] > hanoi[2][-1]):
        return

    for i in range(3):

        if hanoi[i]:
            t = hanoi[i].pop()

            # 다음 거, 다다음거 2개
            for j in range(1, 3):
                nxt = (i+j) % 3

                # 다음 하노이가 있는데 지금 탑이 더 크면 못 옮김
                if hanoi[nxt] and t > hanoi[nxt][-1]:
                    continue

                hanoi[nxt].append(t)
                result.append([i+1, nxt+1])
                dfs(K + 1)
                result.pop()
                hanoi[nxt].pop()
            
            hanoi[i].append(t)
dfs(0)
print(ans)

ans.sort(key=lambda x: x[0])
for K, result in ans:
    print(K)
    for s, e in result:
        print(s, e)


# 뭔가 잘못되고 있다.. 다시 하자. -> t1
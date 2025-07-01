# 7453. 합이 0인 네 정수
# 12s, 1024MB

# 크기가 같은 배열 4개 (A, B, C, D)
# 배열의 크기 n (4000) 

# 합이 0이 되는 쌍의 개수를 출력하라.

# input
import sys
input = sys.stdin.readline
n = int(input())
ABCD = [[] for _ in range(4)]
for _ in range(n):
    e = list(map(int, input().split()))
    for i in range(4):
        ABCD[i].append(e[i])

# sol
# 네 용액의 느낌
# 한 개를 고른 상태로 나머지 세 용액을 고르는 느낌으로 풀면 될듯
for i in range(4):
    ABCD[i].sort()      
    print(ABCD[i])

ans = 0
for i in range(n):                                      # n*
    a = ABCD[0][i]
    
    for j in range(n):                                  # n*
        b = ABCD[1][j]

        s = 0
        e = n-1
        while s < n and e >= 0:                         # n
            key = a + b + ABCD[2][s] + ABCD[3][e]

            if key < 0:
                s += 1

            elif key > 0:
                e -= 1

            else: # key == 0
                print("ans:", a, b, ABCD[2][s], ABCD[3][e], "s:", s, "e:")
                ans += 1

                # 하나의 배열에 같은 정수가 들어있는 경우 고려 (중복 쌍도 다른 쌍)
                sameC = s + 1 < n and ABCD[2][s] == ABCD[2][s + 1]
                sameD = e - 1 >= 0 and ABCD[3][e] == ABCD[3][e - 1]

                if sameC and sameD:
                    s += 1
                    ans += 1

                elif sameC:
                    s += 1
                
                elif sameD:
                    e -= 1
                
                else:
                    s += 1  
                    e -= 1

print(ans)
# 1484. 다이어트
# 2s, 128MB

# G 킬로그램 = 현재 몸무게 제곱 - 예전 몸무게 제곱

# G가 주어졌을 때 현재 몸무게로 가능한 것을 모두 출력하라. (10^5)

# 오름차순으로 출력
# 불가능 시 -1 출력 (자연수가 아닐 경우 불가능)


# 현재 몸무게 y, 이전 몸무게 x
# y^2 - x^2 = G 
# (y-x)*(y+x)=G
# 따라서 y > x가 성립하고, 자연수라는 조건에 따라 x 1개당 y는 최대 1개만 가능함
# x = 1, y = 2부터 시작해서 y를 하나씩 늘려가면서 확인하고
# G보다 커지면 x ++, y = x + 1로 계속 확인하면 됨
# 시작할 때 y + x가 G보다 커지면 더이상 불가능하므로 종료

G = int(input())

x = 1
y = x + 1
ans = []

while x + y <= G:

    while True:
        key = (y + x) * (y - x)

        if key >= G:
            if key == G:
                ans.append(y)
            break
        
        y += 1

    x += 1
    y = x + 1


# output
import sys
write = sys.stdout.write

if ans:
    write('\n'.join(map(str, ans)) + '\n')
else:
    write("-1\n")
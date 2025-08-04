# 2437. 저울
# https://www.acmicpc.net/problem/2437
# apprch4. 참고자료; https://aerocode.net/392

# input
N = int(input()) #1000
weights = list(map(int, input().split()))
weights.sort()

# solution
# 작은 추부터 추가하면서, 추가 하나 추가될 때마다 측정할 수 있는 범위를 늘린다!
# 이때 끊어지는 부분이 생기면 ans
len = 0
for i in range(N):

    if weights[i] - len <= 1:
        len += weights[i]
    
    else:
        break

# ouput
print(len + 1)


# self-feedback
# 간단하게 생각해보기
# 별개로, dfs/bfs 연습 더 하기
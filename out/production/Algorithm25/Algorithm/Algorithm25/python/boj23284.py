# 23284. 모든 스택 수열
# 2s, 1024MB

# 1부터 n까지 오름차순으로 스택에 넣었다가 뽑으면서 하나의 수열을 만들 수 있음
# n이 주어졌을 때, 모든 스택 수열을 구하라. (1~10)

# 사실 n개의 순열을 구하라는 것과 같음 (n!)
# 10! = 대략 3.6*10^6 정도니 그냥 루프 돌리면 되긴 할 듯
# 오늘 공부한 bit mask 써보자. (n * 2^n)
# 그러면 10 * 2^10 = 대략 10^4 정도

# n = int(input())
# stack = list(range(1, n+1))                             # 주의: [range(1, n+1)]로 하면 안됨

# def dfs(path, visited):

#     if len(path) == n:
#         print(*path)
#         return
    
#     for i in range(n):
#         if not visited & (1<<i):                        # i번째를 아직 안 사용함
#             dfs(path + [stack[i]], visited | (1<<i))    # i번째를 사용한다면?

# dfs([], 0)

# -> 라고 생각했지만, 오름차순인 숫자들의 스택으로 만들 수 있는 수열은 그냥 순열보다 가짓수가 적을 수 밖에 없음
# 예를 들어, n=3일 때 312와 같은 수열은 만들어질 수가 없다.

# 그냥 문제대로 스택으로 풉시다!

n = int(input())
ans = []

def generate(push_num, stack, output):

    if len(output) == n:
        ans.append(output)  # 문자열로 만든 뒤에 정렬하면 오류가 있을 수 있다. (e.g. '10' < '2')
        return

    # 1. push
    if push_num <= n:
        generate(push_num + 1, stack + [push_num], output)

    # 2. pop
    if stack:
        generate(push_num, stack[:-1], output + [stack[-1]])

generate(1, [], [])

ans.sort()
for s in ans:
    print(' '.join(map(str, s)))

# 1 [] []
# 2 [1] [] *
# 3 [1, 2] [] *
# 4 [1, 2, 3] [] 
# 4 [1, 2] [3]
# 4 [1] [3, 2]
# 3 2 1         -> 3 [1, 2] []로 돌아감
# 3 [1] [2] *
# 4 [1, 3] [2]
# 4 [1] [2, 3]
# 2 3 1         -> 3 [1] [2]로 돌아감
# 3 [] [2, 1]
# 4 [3] [2, 1]
# 2 1 3         -> 2 [1] []로 돌아감
# 2 [] [1]
# 3 [2] [1] *
# 4 [2, 3] [1]
# 4 [2] [1, 3]
# 1 3 2         -> 3 [2] [1]로 돌아감
# 3 [] [1, 2]
# 4 [3] [1, 2]
# 1 2 3

# 그냥 간단한 문제가 아니었따~
# 백트래킹 + 상태 시뮬레이션 : DFS로 두 가지 선택을 분기
# 다시 풀기, 비슷한 유형 연습하기
# 연습: 스택 수열 (BOJ 23284, 1874), 1~N 순서대로 트리/그래프 타기 (순열, 조합)
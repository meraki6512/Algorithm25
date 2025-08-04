# 2437. 저울
# https://www.acmicpc.net/problem/2437
# apprch1. dfs

# input
N = int(input()) #1000
pds = list(map(int, input().split()))
pds.sort()

# solution
''' 
@param cur_weight:    current weight
@param i:             current index (of pds)
@param trgt_weight:   target weight
@return:              T/F to meet the trgt
'''
def dfs(cur_weight, i, trgt_weight):

    global result

    if cur_weight > trgt_weight:
        return
            
    if cur_weight == trgt_weight: 
        result = True
        return
    
    if cur_weight > max_sum:
        return
    
    # when cur_weight < trgt_weight: keep searching
    if not visited[i]:
        visited[i] = 1
        dfs(cur_weight + pds[i], i + 1, trgt_weight)
        dfs(cur_weight - pds[i-1], i - 1, trgt_weight)


visited = [0] * N
visited[0] = 1
result = False
max_sum = sum(pds)

# output
i = 1
while True:
    dfs(0, 0, i)

    if not result:
        print(i)
        break

    i += 1
    
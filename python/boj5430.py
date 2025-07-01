# boj5430 - AC 
# : 정수 배열에 연산을 하기 위해 만든 언어
# 기능: R(뒤집기)과 D(버리기)

from collections import deque 


class Solution:

    def AC(funs, nums):

        rv = False
        dq = deque(nums)

        for f in funs:
            
            if f == 'R':
                rv = not rv
            
            else: # D

                if not dq:
                    return "error"
                
                elif rv:
                    dq.pop()
                else:
                    dq.popleft()
                
        
        result = list(dq)
        
        if rv:
            result.reverse()
            
        return "["+','.join(result)+"]"
    



funs_list = []
nums_list = []

# 1 <= T <= 100
T = int(input())

for _ in range(T):

    funs_list.append(input())
    n = int(input()) #100,000
    input_str = input().strip('[]')

    # empty check
    if n > 0: 
        nums_list.append(list(input_str.split(',')))
    else: 
        nums_list.append([])


for i in range(T):
    print(Solution.AC(funs_list[i], nums_list[i]))


# 1sec -> 100 * 100000
# AC: O(n) 정도 required

# Time complexity
# pop() O(1)
# pop(0) O(n)
# strip O(n)
# reverse O(n)

# 처음 생각: pop()만 써서 reverse를 최대한 줄이자
# 고친 것: deque 사용해서 앞뒤로 pop
# 주의: 출력할 때 배열을 출력하면 틀림, 배열 형태의 문자열 출력
# 전화번호 목록
# string

# 일관성?
# 전화번호는 누르는 순간 바로 전화가 걸린다
# 따라서 한 번호가 다른 번호의 접두어가 되면 안된다

# 테스트 케이스 t (1~50)
# 전화번호 수 n (1~10^4), 길이 (1~10), 같은 경우 x

# input

t = int(input())
nums_list = []

for _ in range(t): 

    n = int(input())
    nums = []

    for i in range(n): 
        nums.append(input())
    
    nums_list.append(nums)

# solution

# i1: dfs/bfs
# i2: compare len first, slice

# from functools import cmp_to_key
# from collections import deque

# def compare(str1, str2):
#     if len(str1) < len(str2):
#         return -1
#     elif len(str1) == len(str2):
#         return 0
#     else:
#         return 1


# for k in range(t): #50

#     nums = nums_list[k]
#     L = len(nums)
    
#     # sorted : TimSort : O(n log n)
#     # sorted_nums = sorted(nums, key=cmp_to_key(compare), reverse=False)

#     cons = True

#     # O(n^2)
#     for i in range(L): #10^4

#         # key = sorted_nums[i]
#         key = nums[i]
#         Lk = len(key)

#         for j in range(i+1, L):

#             # if Lk == len(nums[j]):
#             if Lk >= len(nums[j]):
#                 # break
#                 continue

#             # elif key == sorted_nums[j][:Lk]:
#             elif key == nums[j][:Lk]:
#                 cons = False
#                 break
        
#         if not cons:
#             break

    ## ---------  ??
    ## Time out  
    # 1 sec
    # 500-n3, 2000-n2, 10^5-nlogn, 10^7-n
    # O(N^2) ... (x?)
    # O(NlogN) ... 
    # i3: 숫자로 비교 -> (x) 001 vs 01
    # Hm...


# 문자열 길이로 정렬하지 말고 문자 자체로 정렬하자

for nums in nums_list: #50

    nums.sort()

    Ln = len(nums)    
    cons = True

    # 그러면 항상 바로 다음 것과만 비교하면 됨.
    for i in range(Ln-1): # 10^5
        if nums[i] in nums[i+1][:len(nums[i])]: # 10
            cons = False
            break

    ans = "YES" if cons else "NO"
    print(ans)

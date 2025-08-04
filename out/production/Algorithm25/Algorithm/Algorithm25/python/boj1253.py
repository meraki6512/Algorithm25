# 1253 좋다

# 2초 # 256MB
# N개의 수 중에서 어떤 수가 다른 수 두 개의 합으로 나타낼 수 있다면 좋은 수
# 좋은 수의 개수를 구하라

# N개의 수가 있는 수열이고, 수의 위치가 다르면 값이 같아도 다른 수다.

# input
N = int(input())
nums = list(map(int, input().split()))
ans = 0

# solution
# TimSort O(nlogn)
nums.sort()

# 수열 순회 
for i, key in enumerate(nums):
    
    # two-pointer 사용
    f = 0
    r = len(nums)-1

    while f < r:

        # 위치가 같으면 pass 
        # (조건상 f==r일 수는 없음)
        if i == f:
            f += 1
            continue
        elif i == r:
            r -= 1
            continue

        # key가 좋은 수인지 체크
        _sum = nums[f] + nums[r]
        if _sum == key:
            ans += 1
            break

        # 더한 값(_sum)을 기준으로 포인터 이동
        elif _sum > key:
            r -= 1
        elif _sum < key:
            f += 1

# output    
print(ans)
            



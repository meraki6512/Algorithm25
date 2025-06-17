# 1644. 소수의 연속합
# 2s, 128MB

# 자연수 N (4*10^6)을
# 연속된 소수의 합으로 표현하는 경우의 수

# 연속하지 않은 경우는 제외
# 하나의 소수는 반드시 한 번만 사용

N = int(input())

# 소수의 부분합 느낌..

# 그럼 소수는 어케 구하냐?
# 일일이 나눠떨어지는 게 없는 지 구하면 - 시간 비용이 너무 큼 - O(N)
# 루트를 적용해 구하면 (16=2*8일 때 2만 고려, 8은 고려X) - O(루트N)까지 줄일 수 있지만 여전히 비용이 커보임 

# -> "에라토스테네스의 체"         
# 2부터 시작해서 배수 모두 제거하는 원리 - O(nloglogn)
# 2*2, 2*3, 2*4, ... 
# 3*3, (3*4,) 3*5, ...
# ...

def eratos(n):

    if n <= 1:
        return []
    
    is_prime = [False] * 2 + [True] * (n - 1)       # 0은 마진, 1은 소수 아님 (둘 다 False 설정)

    for i in range(2, int(n**0.5) + 1):
        if is_prime[i]:                             # 아직 소수로 판별되고 있으면
            for m in range(i*i, n+1, i):            # i*i부터 시작 (그 이전은 이미 그 전에서 처리했음)
                is_prime[m] = False                 # 배수 제거
        
    return [i for i in range(n + 1) if is_prime[i]] # 최종적으로 소수로 판별되는 숫자만 리스트에 담아 반환


# N까지 가능한 소수 모두 구해서
primes = eratos(N)

# 합으로 나타낼 수 있냐
# 근데 그냥 탐색하면 시간 초과가 날 것 같은데, NlogN이면 괜찮을테니까.. 음.. sliding window

# 부분합 배열을 만듦
p = [0] * (len(primes) + 1)
for i in range(len(primes)):
    p[i + 1] = p[i] + primes[i]

# 2pointer
ans = 0
s, e = 0, 1

while e < len(p):
    
    key = p[e] - p[s]

    if key == N:
        ans += 1
        e += 1

    elif key < N:
        e += 1
    
    elif key > N:
        s += 1

print(ans)
# 1094. 막대기
# 2s, 128MB

# 64cm -> Xcm
# 가장 짧은 막대를 절반으로 자름
# 절반 중 하나를 버리고 남은 막대의 합이 X보다 크면 반복
# X가 되는 막대의 개수 = ?

X = int(input())            # 64보다 작거나 같은 자연수
stack = [64]
cnt = 1

while sum(stack) != X:      # 합이 X일 때 종료

    n = stack.pop() // 2    # 가장 짧은 것의 절반 구함
    
    s = sum(stack) if stack else 0  
    
    stack.append(n)         # 절반1 넣음

    if s + n < X:           # 남은 합 + 절반1이 X보다 작을 때는
        stack.append(n)     # 절반2도 넣음
        cnt += 1
    
print(cnt)

# 64
# 32/32     -> 32
# 32/16/16  -> 32/16
# 32/16/8/8 -> 32/16/8


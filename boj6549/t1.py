# 6549. 히스토그램에서 가장 큰 직사각형
# 너비가 1인 직사각형으로 이뤄진 히스토그램에서 
# 높이들이 주어졌을 때, 가장 넓이가 큰 직사각형의 크기를 구하라.

# 1 sec, 256 MB
# n: 직사각형 수 (10^5) : n이나 nlogn 정도

import sys
input = sys.stdin.readline

while True:

    # input
    temp = list(map(int, input().split()))
    n = temp[0]

    if n == 0:
        break

    # solution
    h = temp[1:] + [0]      # stack 마지막을 처리해주기 위해서 마진 0 설정
    stack = []              # 인덱스 
    max_area = 0

    for i in range(n + 1):  # 마진 고려; 앞에서부터 순회

        while stack and h[stack[-1]] > h[i]:   # 현재 h가 작을 때만 (직사각형 계산)

            tg = stack.pop()    # 계산할 직사각형의 인덱스

            height = h[tg]      # 계산 인덱스의 높이
            width = i if not stack else i - stack[-1] - 1   # stack이 비어 있으면: 현재 인덱스 i 
                                                            # stack에 뭔가 있으면: 계산 인덱스 직전까지의 거리; 계산 직전 인덱스는 빼줘야하므로 -1
            max_area = max(max_area, height * width)

        stack.append(i)

    print(max_area)




# self-feedback
# 생각보다 구현은 쉬운데 떠올리기가 쉽지 않다
# stack을 사용할 때 값을 여러번 반복 계산하지 않도록 생각을 해봐야한다
# stack이란 걸 미리 알고 있었지만 width 계산하는 부분에서 계속 헷갈렸다
# velog에 정리하기
# 나중에 다시 풀어보기
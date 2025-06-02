# 6549. 히스토그램에서 가장 큰 직사각형
# 너비가 1인 직사각형으로 이뤄진 히스토그램에서 
# 높이들이 주어졌을 때, 가장 넓이가 큰 직사각형의 크기를 구하라.

# 1 sec, 256 MB
# n: 직사각형 수 (10^5)

import sys
input = sys.stdin.readline

def multiple(item):
    return item[0] * item[1]

while True:

    # input
    h = list(map(int, input().split()))
    n = h[0]

    if n == 0:
        break

    # solution 
    dw = {h[1]: 1}  # 현재 h에 해당하는 너비의 최대 w를 저장할 dict 필요
    stack = [1]     # 인덱스 # 현재 h보다 크거나 같은 것들 중 가장 먼 곳을 구하기 위함
    dup_list = []

    for i in range(2, len(h)): # 10^5

        if not h[i]:       # 히스토그램이 끊기면 (h==0) 초기화
            stack = [i]
            dw[0] = 1
            continue

        # set dw 
        if h[i] > h[i-1]:   # 이전 h보다 크면

            t = len(stack) - 1
            ph = h[stack[t]]
            while t>=0 and h[stack[t]] <= h[i] and h[stack[t]] <= ph: # 이전 h의 w는 모두 ++
                dw[h[stack[t]]] += 1
                ph = h[stack[t]]
                t -= 1
            
            if h[i] in dw.keys(): # 이미 존재하면
                dup_list.append(h[i]*dw[h[i]]) # 이전까지의 값 저장해두고
            
            dw[h[i]] = 1    # 현재 h의 w는 1


        else:               # 이전 h보다 작거나 같으면

            t = len(stack) - 1
            fi = stack[0]
            while t>=0 and h[stack[t]] >= h[i]:   # 크거나 같은 만큼 
                
                if h[stack[t]] in dw.keys():
                    fi = stack[t]   # 현재 h보다 크거나 같은 것들 중에 가장 먼 idx 선택
                   
                else:               # 처음 업데이트가 안됐을 때
                    dw[h[i]] = 2    # 작거나 같은게 들어오면 너비=2
                
                t -= 1
                stack.pop()
            
            if h[i] in dw.keys():   # 이미 존재하면
                dup_list.append(h[i]*dw[h[i]]) # 이전까지의 값 저장해두고
            
            dw[h[i]] = dw[h[fi]] + 1      # 현재 h의 w는 가장 먼 곳의 ++

            
            t = len(stack) - 1
            while t>=0 and h[stack[t]] < h[i]:  # 남아있는 작은 것들에 대해서
                dw[h[stack[t]]] += 1            # 최대 크기 up
                t -= 1

        stack.append(i)


    # output
    print(max(dup_list + list(map(multiple, dw.items()))))



# -> time-out
# 높이마다 너비를 따로 저장하고 여러 번 반복함: O(N^2) 수준
# stack을 전혀 pop해주고 있지 않음.. 
# 당연히 시간초과가 날 수밖에..
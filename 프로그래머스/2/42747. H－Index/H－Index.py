def solution(citations):
    answer = 0
    
    n = len(citations)
    citations.sort() # nlogn
    h = n
    i = 0
    
    while i < n:
        if h <= citations[i]:
            if h <= n-i:
                answer = h
                break
            else:
                h -= 1 
                i = 0
        else:
            i += 1
    
    
    return answer

# n 중 h 이상 인용된 논문이 h 이상이고 나머지 논문이 h 이하 인용되면 h의 최댓값 = H-index
# n 1000, h 10000 # n^2
# 인용횟수 리스트 -> H-index

# h-index가 꼭 citations의 member일 필요는 없다..
# 01356
# 54321

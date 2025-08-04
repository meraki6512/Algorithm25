def solution(citations):
    answer = 0
    
    n = len(citations)         # 전체 논문 개수
    citations.sort()           # 인용 수를 오름차순 정렬 → O(n log n)
    
    h = n                      # H-index 후보를 가장 큰 값(n)부터 줄여가며 찾기
    i = 0                      # 배열 인덱스 초기화
    
    while i < n:               # 배열 끝까지 탐색 → O(n²) 
        if h <= citations[i]:  # 인용 수가 h 이상인 논문 발견 시
            if h <= n - i:     # 그런 논문이 h편 이상이면 h-index 조건 만족
                answer = h     # 정답 저장
                break          # 종료
            else:
                h -= 1         # h 후보 줄이기
                i = 0          # 배열 탐색을 처음부터 다시
        else:
            i += 1             # 인용 수가 아직 h보다 작으면 다음 논문으로

    return answer

# 요약
# n 중 h 이상 인용된 논문이 h 이상이고 나머지 논문이 h 이하 인용되면 h의 최댓값 = H-index
# n 1000, h 10000 # n^2까지도 괜찮음
# 인용횟수 리스트 -> H-index

# 주의
# h-index가 꼭 citations의 member일 필요는 없다..


# 다른 풀이 1
def solution(citations):
    citations.sort(reverse=True)                            # O(n log n)
    answer = max(map(min, enumerate(citations, start=1)))   # O(n log n)
    return answer

# sorted:    [6, 5, 3, 1, 0]
# enumerate: [(1,6), (2,5), (3,3), (4,1), (5,0)] # (i=논문 수, cᵢ=해당 논문 인용 수)
# min:       [1, 2, 3, 1, 0]                     # min(i, cᵢ) = 가능한 H-index 후보
# max:       3


# 다른 풀이 2
def solution(citations):
    
    citations.sort()                # 오름차순 정렬 → O(n log n)
    n = len(citations)
    
    left, right = 0, n              # H-index는 0 ~ n 사이의 값 중 하나
    answer = 0
    while left <= right:            # 이진 탐색 → O(n log n)
        
        mid = (left + right) // 2   # 현재 H-index 후보

        count = sum(1 for c in citations if c >= mid) # mid 이상 인용된 논문 수 세기

        if count >= mid:            # mid(h) 이상 인용된 논문 수 있음 = H-index 후보
            answer = mid            # 조건 만족 → 더 큰 h도 가능할 수 있으니 오른쪽 탐색
            left = mid + 1
        else:                       # mid(h) 이상 인용된 논문 수 없음
            right = mid - 1         # 조건 미달 → 왼쪽으로 줄이기

    return answer

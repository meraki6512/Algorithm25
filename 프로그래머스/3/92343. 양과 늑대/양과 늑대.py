def solution(info, edges):
    
    visited = [0] * len(info)                   # visited[]로 방문 노드 체크
    answer = []                                 
    
    def dfs(sheep, wolf):
        
        # 종료 조건
        if sheep <= wolf:                       # 늑대가 많아져서 잡아먹으면
            return                              # 종료
        else:                                   # 양이 많으면
            answer.append(sheep)                # 양의 수 answer 리스트에 저장
            
        # 탐색
        for p, c in edges:                      # 부모p -> 자식c
            
            if visited[p] and not visited[c]:   # p를 방문하고 c를 방문 안했으면
                visited[c] = 1                  # c 탐색 시작
                
                if info[c] == 0:                # c == 양이면
                    dfs(sheep+1, wolf)          # sheep 추가해 계속 탐색
                else:                           # c == 늑대면
                    dfs(sheep, wolf+1)          # wolf 추가해 계속 탐색
                
                visited[c] = 0                  # back-tracking
        
        
    # root부터 dfs
    visited[0] = 1
    dfs(1, 0)
    
    # print(answer)

    return max(answer)

# 핵심
# 이진 트리
# 0=양, 1=늑대
# 체크할 것 # 양 > 늑대? # 부모 방문? # 자식 노드 방문X?

# self-feedback
# - 자식을 왼, 오로 나눌 필요없이 edges 그 자체로 풀면 됨
# - 조건은 종료 시점에서 확인하면 됨
# - 마지막 sheep 수만 셀 필요도 없음
# 다시 풀어보기
# 백트래킹 연습 문제 더 풀어보기
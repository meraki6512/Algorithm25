# OX퀴즈 

class Solution:

    def get_score(ox):

        scores = []
        result = 0
        cnt = 0

        for q in ox:

            if q == 'O':
                cnt += 1
            else:
                cnt = 0
            
            scores.append(cnt)

        result = sum(scores)

        return result
    
N = int(input())
ox_list = []

for _ in range(N):
    ox_list.append(input().strip())

for i in range(N):
    print(Solution.get_score(ox_list[i]))
    
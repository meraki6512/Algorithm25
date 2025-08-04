# 문자열 폭발

# class Solution:

#     def str_bomb(s, bomb):

#         # init
#         result = ""
#         Lb = len(bomb)  # 1<=N<=36
#         check = bomb[-1]
        
#         for i, c in enumerate(s):
            
#             cnt = 0
#             result += c

#             if check == c:
#                 for t in range(Lb):

#                     if bomb[Lb-1-t] != s[i-t]:
#                         break

#                     cnt += 1

#             # init
#             if cnt == len(bomb):
#                 result = ""

#         return "FRULA" if not result else result 
    

# s = input().strip()
# bomb = input().strip()

# print(Solution.str_bomb(s, bomb))

##############################################################################################
# 문제를 잘못 이해함.
# 폭발 문자열까지의 모든 문자열이 사라지는 게 아니라, 해당 폭발 문자열만 사라지는 것임!

class Solution:

    def str_bomb(s, bomb):

        # init
        result = ""
        Lb = len(bomb)  # 1<=N<=36

        # stack 라이브러리 사용? deque? or class 구현?
        stack = []

        for i, c in enumerate(s):

            stack.append(c)

            # 뒤에서부터 체크크
            if c == bomb[-1]:

                cnt = 0

                # Lb 만큼 확인
                for j in range(Lb):
                    if bomb[Lb-1-j] == stack[len(stack)-1-j]:
                        cnt += 1
                    else:
                        break
                
                # 일치하면 pop
                if cnt == Lb:
                    for t in range(cnt):
                        stack.pop()

        return "".join(stack) if stack else "FRULA"


s = input().strip()
bomb = input().strip()

print(Solution.str_bomb(s, bomb))

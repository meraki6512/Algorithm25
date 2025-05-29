# 1918. 후위 표기식식
# 중위표기식 -> 후위표기식 변환하라.

# 식은 알파벳 대문자와 +, -, *, /, (, )로만 이루어져 있음.
# 길이는 100을 넘지 X
# 음수는 고려하지 X
# 곱셈 기호의 생략은 고려하지 X

infix = list(input())

postfix = ""
stack = [] # operator stack
priority = {'(':2, ')':2,  # 우선순위 낮음
            '+':1, '-':1,
            '*':0, '/':0}  # 우선순위 높음

for c in infix:

    if c == "(":
        stack.append(c)

    elif c == ")":
        # ( 만날 때까지 과정에 있던 모든 연산자는 스택에서 빼버림 (먼저 결과에 넣음)
        while stack and stack[-1] != "(":
            postfix += stack.pop()

        stack.pop() # ( 도 pop
    
    elif c in "+-*/":
        # 우선순위가 높은 게 먼저 나와야 함
        while stack and priority[stack[-1]] <= priority[c]: # 우선순위가 높은 건(작은건)
            postfix += stack.pop()  # 스택에서 빼고 (결과에 먼저 넣고)
    
        stack.append(c)             # 우선순위가 낮은 걸 마지막에 스택에 넣음

    else: # operand
        postfix += c


# 스택에 남아있는 연산자 처리
while stack:
    postfix += stack.pop()

print(postfix)

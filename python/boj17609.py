# 17609. Palindrome
# 문자열의 개수 T (1~30), 문자열 (길이: 3~10^5, 알파벳 소문자 only)
# 회문 -> 0, 유사회문 -> 1 (유사회문: 문자 하나 제거 시 회문), 아니면 -> 2
# ---

# two pointer
# 주의할 점: 순서에 따라 유사회문이 될 수도 있고 안될 수도 있음.
#           ex) abxbbxa -> ans = 1
#               우측의 x를 먼저 처리하면 bxbb가 남아 결과값이 2가 됨
#               좌측의 b를 먼저 처리하면 xbbx가 남아 결과값이 1이 됨
#           ex) abxxbxa -> ans = 1
#               우측의 x를 먼저 처리하면 bxxb가 남아 결과값이 1이 됨
#               좌측의 b를 먼저 처리하면 xxbx가 남아 결과값이 2가 됨

def check_palindrome(s):
    
    f = 0
    r = len(s)-1
    pseudo = False
    result = -1

    while f <= r:
        
        if s[f] == s[r]:
            f+=1
            r-=1        
            continue

        elif pseudo:
            result = 2
            break

        elif s[f+1] == s[r] and s[f] == s[r-1]:
            result = check_palindrome(s[f+1:r])
            pseudo = True
            break

        elif s[f+1] == s[r]:
            pseudo = True
            f+=2
            r-=1

        elif s[f] == s[r-1]:
            pseudo = True
            f+=1
            r-=2

        else:
            result = 2
            break
    

    if result == 2:
        pass
    elif pseudo:
        result = 1
    elif r <= f:
        result = 0


    return result


# input
n = int(input())
strings = []
for i in range(n):
    strings.append(input())

# solution
ans = []
for s in strings: #1~30
    ans.append(check_palindrome(s))

# output
for a in ans:
    print(a)


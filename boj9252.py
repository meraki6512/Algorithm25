# LCS 2
# LCS 길이와 LCS 배열까지 같이 구하기

str1 = input()
str2 = input()

L1 = len(str1) + 1
L2 = len(str2) + 1

dp = [ [ 0 for _ in range(L2) ] for _ in range(L1) ]

for i in range(1, L1):
    for j in range(1, L2):

        if str1[i-1] == str2[j-1]:
            dp[i][j] = dp[i-1][j-1] + 1
        
        else: # !=
            # sequentially count
            dp[i][j] = max(dp[i-1][j], dp[i][j-1])


LCS = ""
i = L1 - 1
j = L2 - 1
count = dp[i][j]

while dp[i][j] > 0:
    
    if dp[i][j] == dp[i-1][j]:
        i -= 1

    elif dp[i][j] == dp[i][j-1]:
        j -= 1

    else:
        LCS += str1[i-1]
        i -= 1
        j -= 1
    
print(count)
if count > 0:
    print("".join(reversed(list(LCS)))) 

# Self-feedback
# 너무 외워져 있음
# 시간이 좀 흐른 후에 다시 풀어보기
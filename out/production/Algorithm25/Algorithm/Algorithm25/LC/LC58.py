# Top Interview 150
# 58. Length of Last Word

class Solution:
    def lengthOfLastWord(self, s: str) -> int:
        
        # # for i in range(len(s)-1, -1, -1):
        #     # if i == ' ':
        #         # break
        #     # ans++

        ans = 0
        for i in s[::-1]:

            if not ans and i == ' ':
                continue

            elif i == ' ':
                break

            ans += 1
        
        return ans


        # use functions?

        words = s.strip().split(" ")

        return len(words[-1])

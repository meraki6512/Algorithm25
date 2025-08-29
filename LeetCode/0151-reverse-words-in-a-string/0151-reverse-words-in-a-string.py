class Solution:
    def reverseWords(self, s: str) -> str:
        
        ans = ""
        words = reversed(s.split())

        for word in words:
            ans += word + " "

        return ans.strip()
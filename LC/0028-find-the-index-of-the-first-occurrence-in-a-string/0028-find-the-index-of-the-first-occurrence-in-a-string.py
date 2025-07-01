class Solution:
    def strStr(self, haystack: str, needle: str) -> int:
        
        for i, c in enumerate(haystack):
            
            if c == needle[0]:

                Ln = len(needle)

                if haystack[i:i+Ln] == needle:
                    return i
        
        return -1
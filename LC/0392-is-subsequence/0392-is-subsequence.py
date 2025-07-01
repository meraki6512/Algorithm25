class Solution:
    def isSubsequence(self, s: str, t: str) -> bool:
        
        ti = 0
        string = list(s)

        while string and ti < len(t):

            if string[0] == t[ti]:
                string.pop(0)
            
            ti += 1
        
        return True if not string else False

class Solution:
    def isIsomorphic(self, s: str, t: str) -> bool:
        
        d = dict()

        for i in range(len(s)):

            if s[i] not in d.keys():

                if t[i] in d.values():
                    return False
                
                d[s[i]] = t[i]

            elif d[s[i]] != t[i]:
                return False
        
        return True

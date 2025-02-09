# Top Interview 150
# 14. Longest Common Prefix

class Solution:
    def longestCommonPrefix(self, strs: List[str]) -> str:

        ans = ""
        strs = sorted(strs)

        first = strs[0]
        last = strs[-1]

        for i in range(min(len(first), len(last))):
            
            if first[i] != last[i]:
                return ans

            ans += first[i]

        return ans

        # got hint from "Solutions"
        # wrong approach at the first tm ...
        # needed to find the longest common "prefix" string!!

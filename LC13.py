# Top Interview 150
# 13. Roman to Integer

class Solution:
    def romanToInt(self, s: str) -> int:
        
        # use dict for roman numerals
        # read from the rear
        # while reading, add the matching numbers
        # but for these cases, subtract instead
        # I comes after V or X
        # X comes after L or C
        # C comes after D or M

        roman = {'I':1, 'V':5, 'X':10, 'L':50, 'C':100, 'D':500, 'M':1000}

        ans = 0
        prev = "ANY"

        for c in s[::-1]:

            cur = roman[c]

            if c == "I" and prev in "VX":
                ans -= cur
            elif c == "X" and prev in "LC":
                ans -= cur
            elif c == "C" and prev in "DM":
                ans -= cur
            else:
                ans += cur
            
            prev = c
        
        return ans

class Solution:
    def isPalindrome(self, s: str) -> bool:
        
        # remove first
        # regular exp ? -> isalnum!, filter.
        # lower 

        alphnum = ("".join(filter(str.isalnum, s))).lower()

        # check palindrome

        f = 0
        r = len(alphnum) - 1

        while f <= r:

            if alphnum[f] == alphnum[r]:
                f += 1
                r -= 1
            
            else:
                return False

        return True
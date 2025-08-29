class Solution:
    def canConstruct(self, ransomNote: str, magazine: str) -> bool:

        ms = set(magazine)
        d = dict(zip(ms, [0 for _ in range(len(ms))]))

        for c in magazine:
            d[c] += 1


        for i, c in enumerate(ransomNote):

            if c not in d.keys():
                return False

            if d[c] > 0:
                d[c] -= 1

            else:
                return False

        return True
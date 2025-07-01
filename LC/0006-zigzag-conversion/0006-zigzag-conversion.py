class Solution:
    def convert(self, s: str, numRows: int) -> str:
        
        # numRows -> rowN
        # 1 -> 0
        # 2 -> 1 
        # 3 -> 3 
        # 4 -> 5
        # n -> 2n-3

        # ex)
        # 1 pop
        # (3) 4 pop
        # (3) 7 pop
        # (3) 10 pop
        # (3) 13 -> Out of range
        # ...

        # But except for the first cycle, 
        # twice a time, should be changed with (0)
        
        # -------------------------------------------------------

        # 14//3 = 4
        # 4회pop 0 (3) 4 (3) 8 (3) 12 ..

        # (14-4)//2 = 7
        # 7회pop 0 (1) ...
        
        # -------------------------------------------------------

        ans = ""
        patterns = list(s)
        n = numRows

        if n == 1:
            ans += s
            return ans
        else:
            curRowN = n
            
        cnt = 0

        while len(patterns) > 0:

            if curRowN == 1:
                ans += "".join(patterns)
                break

            pos = 0
            n = 2 * curRowN - 3

            while pos < len(patterns):

                if cnt > 0 and pos > 0:
                    n = (2 * curRowN - 3) if n == 0 else 0   

                ans += patterns.pop(pos)
                pos += n
            
            cnt += 1
            curRowN -= 1

        return ans





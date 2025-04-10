# 274. H-Index

class Solution:
    def hIndex(self, citations: List[int]) -> int: 

        # sort desc
        
        # 2 0 0
        # 1 2 3 
        # 2>=1: h=1

        # 0
        # 1
        # 0>=1? pass

        # 100
        # 1
        # 100>=1 h=1

        # 6 5 3 1 0        
        # 1 2 3 4 5
        # h=1, h=2 , h=3, pass

        h = 0
        cit = sorted(citations, reverse=True) # key=lambda x: -x

        for i, p in enumerate(cit):
            if p >= i+1:
                h = i+1 
            else:
                break

        return h

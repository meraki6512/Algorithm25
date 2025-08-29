class Solution:
    def canCompleteCircuit(self, gas: List[int], cost: List[int]) -> int:

        # Try 1
        
        # L = len(gas)
        # cal = [0 for i in range(L)]

        # for i in range(L):
        #     cal[i] = gas[(i+1)%L] - cost[(i)%L]
        
        # for i in range(L):

        #     cur = cal[i]

        #     if cur > 0:

        #         cnt = 0

        #         for k in range(i+1, i+L):
        #             cnt += 1
        #             cur += cal[k%L]
                    
        #             if cur < 0:
        #                 break
                    
        #         if cur >= 0 and cnt == L-1:
        #             return i

        # return -1

        # ------------------------------------------------------------------
        # Try 2 - Retry, Same Idea

        # L = len(gas)

        # for i in range(L):

        #     print("i = #" + str(i))
        #     cur = gas[i]
        #     cnt = 1
        #     print(cnt, cur)

        #     for j in range(i, L+i-1):

        #         if cur > 0:
        #             print(f'{cur + gas[(j+1)%L] - cost[(j)%L]} [+gas({gas[(j+1)%L]}) -cost({cost[(j)%L]})]')
        #             cur += gas[(j+1)%L] - cost[(j)%L]
        #             cnt += 1
        #         else:
        #             break

        #     print("cnt = " + str(cnt))
        #     print("cost = " + str(cost[i-1]) + ", cur = " + str(cur))
        #     if cnt == L and cur - cost[i-1] >= 0:
        #         return i

        # return -1

        # ------------------------------------------------------------------
        # Try 3: Fix: consider gas first, and then cost 
        # cur - cost : enough? -> move and get gas!

        # L = len(gas)

        # for i in range(L):

        #     cur = gas[i]
        #     cnt = 0

        #     for k in range(i, i+L):
                
        #         if cur - cost[k%L] < 0:
        #             break
        #         else:
        #             cur += gas[(k+1)%L] - cost[k%L]
        #             cnt += 1
            
        #     if cur >= 0 and cnt == L:
        #         return i
        
        # return -1

        # -> Time out in some cases
        # O(n^2)
        # --------------------------c----------------------------------------
        # Try 4: ... Greedy! (Hint)
        # How can I make it with less time?
        # ... dbl for loops are badd
        # Can I use just one? ...Yes
        # How? using another variable to point the start
        # bcs if cur < 0 that means not enough gas to arrive there.
        # which means not need to cal again..!
    
        # return idx if sum(gas) >= sum(cost) else return -1
        if sum(gas) < sum(cost):
            return -1

        cur = 0
        start = 0
        L = len(gas)

        for i in range(L):
            cur += gas[i] - cost[i]

            if cur < 0:
                start = i + 1
                cur = 0
        
        return start
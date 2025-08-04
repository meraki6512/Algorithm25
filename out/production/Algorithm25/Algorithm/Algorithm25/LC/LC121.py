# Top Interview 150
# 121. Best Time to Buy and Sell Stock

class Solution:
    def maxProfit(self, prices: List[int]) -> int:

        # p, result
        # loop prices
        # if prices[p] is max(prices[p:]): pass
        # else: result.append(max(prices[p:])-prices[p])
        # return max(result)

        ans = 0

        for p in range(len(prices)):

            profit = max(prices[p:]) - prices[p]

            if not profit:
                continue
            elif profit > ans:
                ans = profit

        return ans

      
        # -> time out TT,, New solution? reversely?!
        # m = max(4,0) = 4; p = max(p, m-4) = 0 
        # m = max(6,m); p = max(p, m-6) = 0
        # ... 

        m = p = 0

        for price in prices[::-1]:
            m = max(price, m)
            p = max(p, m-price)

        return p

# Top Interview 150
# 122. Best Time to Buy and Sell Stock II

class Solution:
    def maxProfit(self, prices: List[int]) -> int:

        # from the end, loop 
        # use profits array to save each max profits
        # compare max profit with current profit
               
        profits = [0 for i in range(len(prices))]

        p = m = 0
        n = len(prices)
        
        for i in range(n-1, -1, -1):
            m = max(m, prices[i])
            p = max(p, m-prices[i])
            
            profits[i] = p


        p = 0
        for i in range(n-1, -1, -1):

            # a3: not need to find k
            # k = 1
            # while i + k < n and prices[i] >= prices[i+k]:
                # k += 1
            
            if i + 1 < n:
                p = max(p, prices[i+1] - prices[i] + profits[i+1])
                # profits[i] = p #a1: update profits array
            
            profits[i] = p #a2: for every price
        
        return p

        # Assume that sell every time you can have profits ... 
        # it would be always the max ?! (cuz prices is an integer array)

        p = 0
        for i, price in enumerate(prices):

            if i and price > prices[i-1]:
                p += price - prices[i-1]
            
        return p




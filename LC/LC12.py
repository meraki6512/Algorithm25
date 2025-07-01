# Top Interview 150
# 12. Integer to Roman

class Solution:
    def intToRoman(self, num: int) -> str:
        
        ans = ""

        while num > 0:
            
            i = int(str(num)[0])

            if num >= 1000:
                ans += i*'M'
                num -= i*1000

            elif num >= 100:
                if i > 4 and i < 9:
                    ans += 'D'
                    num -= 500
                elif i == 4:
                    ans += 'CD'
                    num -= 400
                elif i == 9:
                    ans += 'CM'
                    num -= 900
                else: 
                    ans += i*'C'
                    num -= i*100
            
            elif num >= 10:
                if i > 4 and i < 9:
                    ans += 'L'
                    num -= 50
                elif i == 4:
                    ans += 'XL'
                    num -= 40
                elif i == 9:
                    ans += 'XC'
                    num -= 90
                else:
                    ans += i*'X'
                    num -= i*10
            
            else:
                if i > 4 and i < 9:
                    ans += 'V'
                    num -= 5
                elif i == 4:
                    ans += 'IV'
                    num -= 4
                elif i == 9:
                    ans += 'IX'
                    num -= 9
                else:
                    ans += i*'I'
                    num -= i
        
        return ans
            

    # 열라게 하드 코딩.. 이거 맞나
    # 다른 방법 생각해보기

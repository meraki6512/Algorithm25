class Solution:
    def wordPattern(self, pattern: str, s: str) -> bool:
        
        d = dict()
        words = s.split(" ")

        if len(pattern) != len(words):
            return False

        for i, c in enumerate(pattern): # 300

            if c in d:
                # check match
                if d[c] != words[i]:
                    return False
            
            elif words[i] in d.values():
            # c not in d, but words[i] in d:
            # e.g. a b b a / dog dog dog dog
                return False

            else: 
                # put 
                d[c] = words[i]

        return True


# Self-feedback
# 쉬운 문제라고 대충 풀지 말고
# 테스트 케이스 꼭 생각해보자.
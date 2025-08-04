def solution(files):

    keys = []
    
    # 1. HEAD, NUMBER, TAIL 분리 (10^5 = 1000 * 100)
    for f in files:     # 1000
                        # f.split(r'\d+', 1)  # 한 개 이상 연속된 숫자를 기준으로, 1번만 split 
        head = ""
        num = ""
        
        for c in f:     # 100
            
            if c in "0123456789":
                num += c
            elif num:   # num이 비어있지 않으면 (이미 숫자를 선택했으면)
                break
            else:
                head += c

        keys.append((head.lower(), int(num)))   # head의 대소문자는 비교하지 않음
        
    
    # 2. HEAD -> NUMBER 비교 정렬
                                            # lambda x: ... 안에서는 x가 files의 요소 하나를 의미
    pairs = list(zip(files, keys))          # pair = (file, key tuple) # zip은 iterator라서 사용하려면 list로 변환
    pairs.sort(key = lambda x: (x[1][0], x[1][1])) # heads, nums 순으로 비교
    sorted_files = [a for a, t in pairs]
                
    return sorted_files
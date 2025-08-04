# 380. Insert Delete GetRandom O(1)

import random

class RandomizedSet:

    def __init__(self):
        self.randomizedSet = {}

    def insert(self, val: int) -> bool:
        if val in self.randomizedSet:
            return False
        else:
            self.randomizedSet[val] = 1
            # randomizedSet.append(val) #O(1) ??
            return True

    def remove(self, val: int) -> bool:
        if val in self.randomizedSet:
            del(self.randomizedSet[val])
            return True
        else:
            return False

    def getRandom(self) -> int:
        return random.choice(list(self.randomizedSet.keys()))

# Your RandomizedSet object will be instantiated and called as such:
# obj = RandomizedSet()
# param_1 = obj.insert(val)
# param_2 = obj.remove(val)
# param_3 = obj.getRandom()

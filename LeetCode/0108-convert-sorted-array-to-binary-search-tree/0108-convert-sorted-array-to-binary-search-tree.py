# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

# 나중에 다시 풀어볼 것.
# 트리 생성 + 직렬화 과정 직접 만들어서 결과 제공

class Solution:

    def sortedArrayToBST(self, nums: List[int]) -> Optional[TreeNode]:

        # height-balanced.. sorted array.. -> mid!
        # hint - recursive!

        if not nums:
            return None

        mid = len(nums) // 2
        root = TreeNode(nums[mid])

        root.left = self.sortedArrayToBST(nums[:mid])
        root.right = self.sortedArrayToBST(nums[mid+1:])

        # this has serialization process
        # so that it is possible to return just TreeNode
        return root
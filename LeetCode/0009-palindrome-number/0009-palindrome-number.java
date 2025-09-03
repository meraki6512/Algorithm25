class Solution {
    public boolean isPalindrome(int x) {
        String num = x + "";
        int f = 0, r = num.length()-1;
        while (f <= r){
            if (num.charAt(f) != num.charAt(r)) return false;
            f ++;
            r --;
        }
        return true;
    }
}
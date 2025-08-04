package Algorithm.Algorithm25.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Trie5052 {

    static class Trie {
        boolean end;
        boolean pass;
        Trie[] child;

        Trie() {
            end = false;
            pass = false;
            child = new Trie[10];
        }

        public boolean insert(String str, int idx) {

            //끝나는 단어 있으면 false 종료
            if(end) return false;

            //idx가 str만큼 왔을때
            if(idx == str.length()) {
                end = true;
                if(pass) return false; // 더 지나가는 단어 있으면 false 종료
                else return true;
            }
            //아직 안왔을 때
            else {
                int next = str.charAt(idx) - '0';
                if(child[next] == null) {
                    child[next] = new Trie();
                    pass = true;
                }
                return child[next].insert(str, idx+1);
            }

        }
    }

}

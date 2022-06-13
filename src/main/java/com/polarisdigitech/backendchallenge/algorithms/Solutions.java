package com.polarisdigitech.backendchallenge.algorithms;

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Solutions {

    public int lengthOfLongestSubstring(String s) {

        List<String> subStrings=  generateSubStringsAnotherSolution(s);
        //System.out.println("The generated substrings =="+subStrings);
        AtomicInteger currMax = new AtomicInteger();
        subStrings.stream().forEach((sub)->{
            if(sub.chars().filter(e-> Collections.frequency(sub.chars().boxed().collect(Collectors.toList()),e) >1).count()>1){

            }
            else{
                int lent = sub.length();
                if(lent > currMax.get()){
                    currMax.set(lent);
                }
            }
        });


        return currMax.get();
    }

    public List<String> generateSubStringsAnotherSolution(String word){
        List<String> subStrings = new ArrayList<>();
        for(int i = 0; i < word.length(); i++){
            for(int j = i+1; j<= word.length(); j++){
                subStrings.add(word.substring(i,j));
            }
        }
        return subStrings;
    }

    public int lengthOfLongestSubstringAnotherSolution(String s) {

        return generateSubStrings(s);
    }

    public int lengthOfLongestSubstringAnotherSol(String s) {

        int currMax = 0;
        for(int i = 0; i < s.length(); i++){
            for(int j = i+1; j<= s.length(); j++){
                String sub =s.substring(i,j);
                HashSet<Character> char_set = new HashSet<>();

                // Inserting character of String into set
                for(int c  = 0; c< sub.length();c++)
                {
                    if(char_set.contains(sub.charAt(c)))
                        break;
                    char_set.add(sub.charAt(c));
                }

                if(char_set.size() == sub.length()){
                    int lent = sub.length();
                    if(lent > currMax){
                        currMax= lent;
                    }
                }
            }
        }
        return currMax;

    }

    public int generateSubStrings(String word){
        int currMax = 0;
        String sub = "";
        List<String> subStrings = new ArrayList<>();
        for(int i = 0; i < word.length(); i++){
            for(int j = i+1; j<= word.length(); j++){
                sub =word.substring(i,j);
                String finalSub = sub;
                if(sub.chars().filter(e->Collections.frequency(finalSub.chars().boxed().collect(Collectors.toList()),e) >1).count()>1){
                    continue;
                }
                else{
                    int lent = sub.length();
                    if(lent > currMax){
                        currMax= lent;
                    }
                }
            }
        }
        return currMax;
    }

    public boolean isValid(String s) {
        LinkedList<Character> stack = new LinkedList<>();

        for(int i=0; i<s.length(); i++){
            char incomingCh = s.charAt(i);

            if(incomingCh=='(' || incomingCh=='[' || incomingCh=='{'){
                stack.addFirst(incomingCh);
                System.out.println("At i == "+i+" The IncominCh== "+incomingCh);
                continue;

            }
            //{[]}
            if(stack.size() > 0){
                char topCh = stack.getFirst();

                System.out.println("At i == "+i+" The topCh == "+topCh+" The IncominCh== "+incomingCh);
                if(topCh=='(' && incomingCh!=')'){
                    return false;
                }

                if(topCh=='[' && incomingCh!=']'){
                    return false;
                }

                if(topCh=='{' && incomingCh!='}'){
                    return false;
                }


                stack.removeFirst();

            }else{
                return false;
            }
        }

        return stack.size() > 0 ? false : true;
    }
}

class MinStack {

    private List<Integer> stackList;
    private int min;
    int size = 0;
    private List<Integer> arrayOfMins;
    public MinStack() {
        stackList = new ArrayList<>();
        arrayOfMins = new ArrayList();
        min = Integer.MAX_VALUE;
    }

    public void push(int val) {
        if (val < min)
            min = val;
        arrayOfMins.add(min);
        stackList.add(val);
        size++;
    }

    public void pop() {
        size--;
        stackList.remove(size);
        arrayOfMins.remove(size);
        if(stackList.size() == 0)
            min=Integer.MAX_VALUE;
        else
            min = arrayOfMins.get(size-1); // get the 2nd to the last element as the new minimum
    }

    public int top() {
        return stackList.get(size-1);
    }

    public int getMin() {
        return arrayOfMins.get(size-1).intValue();
    }
}
class BaseBall {

    /**
     * Calculate Baseball points based on the given options-ops
     * @param ops
     * @return
     */
    public int calPoints(String[] ops) {

        List<Integer> record = new ArrayList<>();
        int totalSum = 0;
        for(int i =0; i < ops.length; i++){
            String op = ops[i];
            int newScore = 0;
            if(op.matches("-?\\d+"))
            {
                int num = Integer.parseInt(ops[i]);
                record.add(num);
                totalSum+=num;
            }
            else if(op.equals("+")){
                newScore = record.get(record.size()-1) + record.get(record.size()-2);
                record.add(newScore);
                totalSum+=newScore;
            }
            else if(op.equals("D")){
                newScore = 2 * (record.get(record.size()-1));
                record.add(newScore);
                totalSum+=newScore;

            }
            else if(op.equals("C")){
                totalSum-= record.remove(record.size()-1);
            }
        }
        return totalSum;
    }

}

class FolderCrawler{
    class Solution {
        public int minOperations(String[] logs) {
            int folderSum =0;
            String folderRegex = "^([a-zA-Z_0-9]+\\/?)*";

            for(int i = 0; i < logs.length; i++){
                String op = logs[i];
                if(op.matches(folderRegex)){
                    System.out.println(" Matches "+op);
                    folderSum+=1;
                }
                else if(op.startsWith("..")){
                    if(folderSum ==0);
                    else
                        folderSum-=1;
                }

                else if(op.equals("./"));
            }
            return folderSum;
        }
    }

    public static int maxDifference(List<Integer> px) {
        int currentMax = 0;
        for (int i = 0; i< px.size(); i++){
                if((i+1) < px.size() && px.get(i) < px.get(i+1))
                    currentMax = miniMaxDifference(px,i+1);
        }
        return (currentMax > 0 ? currentMax : -1);
    }

    private static int miniMaxDifference(List<Integer> px, int to){
        int currentMax = Integer.MIN_VALUE;
            for (int i = 0; i < to; i++){
                int diff = px.get(to) - px.get(i);
                 if((diff) > currentMax)
                     currentMax = diff;
            }
            return currentMax;
    }

    public static List<CharSequence> listAllPalindrome(String input){
        if (input.length() <= 2) {
            return Collections.emptyList();
        }
        List<CharSequence> out = new ArrayList<>();
        int length = input.length();
        for (int i = 1; i <= length; i++) {
            for (int j = i - 1, k = i; j >= 0 && k < length; j--, k++) {
                if (input.charAt(j) == input.charAt(k)) {
                    out.add(input.subSequence(j, k + 1));
                } else {
                    break;
                }
            }
        }
        return out;
    }

}


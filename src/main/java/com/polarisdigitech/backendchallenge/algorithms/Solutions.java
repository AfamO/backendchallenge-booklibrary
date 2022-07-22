package com.polarisdigitech.backendchallenge.algorithms;

import com.polarisdigitech.backendchallenge.regex.RegularExpressions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Slf4j
public class Solutions {

    public TreeNode<String> buildTree(){
        TreeNode<String> root = new TreeNode<>("A",null);
        TreeNode<String> left = root.addLeftChild("B");
        TreeNode<String> right = root.addRightChild("C");
        left.addLeftChild("D");
        left.addRightChild("E");
        right.addLeftChild("F");
        right.addRightChild("G");

        return root;

    }
    public void testingSwitch(){
        String op = "D";
        switch (op){
            case "C":{
                log.info("Printing "+op);
                break;
            }
            case "D" :{
                log.info("Printing "+op);
            }
            default:
                log.info("Printing Nothing...");
        }
    }

    public void reverseMatrixColumn(){
        List<Integer> col = new ArrayList<>();
        long maxCol = -1;
        int maxColIndex = -1;
        List<List<Integer>> matrix = new ArrayList<>();
        //**
        col = Arrays.asList(1,3);
        matrix.add(col);
        col = Arrays.asList(2,4);
        matrix.add(col);

         /**
        // New Matrix

          col = Arrays.asList(1,2,3);
          matrix.add(col);
          col = Arrays.asList(4,5,6);
          matrix.add(col);
          col = Arrays.asList(7,8,9);
          matrix.add(col);

        // New Matrix

          col = Arrays.asList(112,42,83,119);
          matrix.add(col);
          col = Arrays.asList(56,125, 56,49);
          matrix.add(col);
          col = Arrays.asList(15,78,101,43);
          matrix.add(col);
          col = Arrays.asList(62,98,114,108);
          matrix.add(col);
          */

        log.info("The  Matrix before Reversal =="+Arrays.asList(matrix));
        //this.reverseColumnAndRow(matrix);
        log.info("The  Final Matrix =="+Arrays.asList(matrix));
        log.info("The maximal Sum of Matrix Quarter Quadrant=="+this.sumMaximalQuadrant(matrix));
        int arr [] = new int[]{2,3,5,1,4};
        log.info("The ZigZag Arr =="+this.findArrayZigZagSequence(arr));
        arr = new int[]{1,2,3,4,5,6,7};
        log.info("The ZigZag Arr =="+this.findArrayZigZagSequence(arr));
        long start = System.nanoTime();

        log.info("The palindromeIndex =="+this.getPalindromeIndex("kjowoemiduaaxasnqghxbxkiccikxbxhgqnsaxaaudimeowojk"));
        long end  = System.nanoTime();
        log.info("The TimeTakeInMillis =="+(end-start)/1000000000);
        new ParameterizedTypeReference<List<String>>(){};

    }

    public List<Integer> findArrayZigZagSequence(int arr[]){
        Arrays.sort(arr); // sorts the array first.

        int max = arr[arr.length-1]; // get the max value.

        int mid = arr.length/2;  // get the middle index/position;

        Integer [] zigZagArr = new Integer[arr.length];

        int k = (arr.length+1)/2; // get the k position

        //loop through k times

        //Insert increasing values in front
        for (int i = 0; i <=mid; i++){
            if (i == mid)
                zigZagArr[mid] = max;
            else
                zigZagArr[i] = arr[i];
        }
        int l = mid+1; // increment mid, so the descending values will start from there.
        //Insert decreasing at the back of kth element-I mean the max
        for (int  j = arr.length-2;  j >=mid ; j--){
             zigZagArr[l++] = arr[j];
        }
        return Arrays.asList(zigZagArr);
    }

    //Anther way to do Zigzag
    public static void findZigZagSequence(int [] a, int n){
        Arrays.sort(a);
        int mid = (n)/2;
        int temp = a[mid];
        a[mid] = a[n - 1];
        a[n - 1] = temp;

        int st = mid + 1;
        int ed = n - 2;
        while(st <= ed){
            temp = a[st];
            a[st] = a[ed];
            a[ed] = temp;
            st = st + 1;
            ed = ed - 1;
        }
        for(int i = 0; i < n; i++){
            if(i > 0) System.out.print(" ");
            System.out.print(a[i]);
        }
        System.out.println();
    }

    /**
    Get the index that makes this string a palindrome
     */
    public int getPalindromeIndex(String string){
        StringBuilder sb = new StringBuilder(string);
        if(string.equals(sb.reverse().toString())){
            return -1;
        }
        for (int i = 0; i < string.length(); i++){
            //sb.deleteCharAt(i);;
            sb = new StringBuilder(string.substring(0,i)+string.substring(i+1));
            //System.out.println("After deleting sb =="+sb);
            if (sb.toString().equals(sb.reverse().toString()))
                return i;
        }
        return -1;
    }

    public  long sumMaximalQuadrant(List<List<Integer>> matrix){
        int size = matrix.size()/2;
        long totalMax = 0;
        int currMax = 0;
        for (int row = 0; row < size; row++){

            for (int col = 0; col < size; col++){
                currMax = Integer.MIN_VALUE; // set the max to the current smallest integer value
                currMax = Math.max(matrix.get(row).get(col),currMax);
                int horizontalFocusedSwapIndex = (2*size)-1-col;
                currMax = Math.max(matrix.get(row).get(horizontalFocusedSwapIndex),currMax);
                int verticalFocusedSwapIndex = (2*size)-1-row;
                currMax = Math.max(matrix.get(verticalFocusedSwapIndex).get(col),currMax);
                currMax = Math.max(matrix.get(verticalFocusedSwapIndex).get(horizontalFocusedSwapIndex),currMax);
                totalMax+= currMax;  // adds the currMax to the previously accumulated one.
            }
        }
        return totalMax;
    }
    public long sumOfUpperLeftSubMatrix(List<List<Integer>> matrix) {
        int row = matrix.get(0).size()/2;
        int col = matrix.size()/2;
        long sum  = 0;
        for (int i = 0; i < row; i++){
            for (int j = 0; j< col; j++){
                int val = matrix.get(i).get(j);
                sum+= val;
            }
        }
        return sum;
    }
    public int getMatrixMaxColumnIndex(List<List<Integer>> matrix) {
       List<Integer> col = null;
       long maxColSum= -1;
       int maxColIndex = -1;
        for (int j = 0; j < matrix.get(0).size();j++ ){
            col = new ArrayList<>();
            for (int i = 0; i< matrix.size(); i++){
                col.add(matrix.get(i).get(j));
            }
            long sum = col.stream().mapToLong(l->l).sum();

            if (maxColSum < sum)
            {
                maxColSum = sum;
                maxColIndex = j;
            }
        }
        log.info("The maxColIndex  Col =="+maxColIndex);
        return maxColIndex;
    }
    public int getMatrixMaxRowIndex(List<List<Integer>> matrix){
        long maxRowSum = -1;
        int maxRowIndex = -1;
        for (int i =0; i < matrix.size(); i++){
            long sum = matrix.get(i).stream().mapToLong((l)->l).sum();
            if (maxRowSum < sum)
            {
                maxRowSum =sum;
                maxRowIndex = i;
            }
        }
        log.info("The maxRowIndex  Col =="+maxRowIndex);
        return maxRowIndex;
    }

    public void reverseColumnAndRow(List<List<Integer>> matrix){
        int maxCol = this.getMatrixMaxColumnIndex(matrix);
        for (int row = 0; row < matrix.size()/2; row++){
            int temp= matrix.get(row).get(maxCol);
            int valTobeSwappedWith = matrix.get((matrix.size()-1-row)).get(maxCol);
            matrix.get(row).set(maxCol,valTobeSwappedWith);
            matrix.get((matrix.size()-1-row)).set(maxCol,temp);
        }
        log.info("The  Final Matrix After columnReversal =="+Arrays.asList(matrix));

        // Now reverse Column as well
        int maxRow = this.getMatrixMaxRowIndex(matrix);
        for (int col = 0; col < matrix.get(maxRow).size()/2; col++){
            int temp = matrix.get(maxRow).get(col);
            int valTobeSwappedWith = matrix.get(maxRow).get((matrix.get(maxRow).size()-1-col));
            matrix.get(maxRow).set(col,valTobeSwappedWith);
            matrix.get(maxRow).set((matrix.get(maxRow).size()-1-col),temp);

        }
    }

    public void testOtherAlgortihms(){
        new CuttingRodProblem().testCuttingRodDpProblem();
        new DataStructures().mapManips();
        new RegularExpressions().matchOneOrMoreNumbers();
        SortingAlgorithms sortingAlgorithms = new SortingAlgorithms();
        sortingAlgorithms.callSortingAlgorithms();
        TreeNode<String> rootNode = buildTree();
        BinarySearchTree<String> binarySearchTree = new BinarySearchTree<>(rootNode);
        log.info("Level Order Traversal Results::");
        binarySearchTree.levelOrderTraversal();
        log.info("Binary Search  Results for P::"+binarySearchTree.binarySearch("P",rootNode));
        log.info("Binary Search  Results for C::"+binarySearchTree.binarySearch("C",rootNode));
        log.info("Binary Search  Results for Q::"+binarySearchTree.binarySearch("Q",rootNode));
        TreeNode<String> emptyRoot = null;
        log.info("Binary Search  Results for  empty Node::"+binarySearchTree.binarySearch("Q",emptyRoot));
        emptyRoot = new TreeNode<>("Q",null);
        log.info("Binary Search  Results for Node with NonEmptyValue::"+binarySearchTree.binarySearch("Q",emptyRoot));
        binarySearchTree.insertIntoBinarySearchTree(rootNode,"H");
        log.info("Level Order Traversal Results After Inserting H::");
        binarySearchTree.levelOrderTraversal();
        binarySearchTree.insertIntoBinarySearchTree(rootNode,"A");
        log.info("Level Order Traversal Results After Inserting Already Existing A::");
        binarySearchTree.levelOrderTraversal();
        emptyRoot = null;
        //binarySearchTree.insertIntoBinarySearchTree(emptyRoot,"H");
        //log.info("Level Order Traversal Results After Inserting emptyRoot::");
        //binarySearchTree.levelOrderTraversal();
        reverseMatrixColumn();


    }

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

    /*
     * Complete the 'minimalHeaviestSetA' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    class AmazonInterview {

        public  List<Integer> minimalHeaviestSetA(List<Integer> arr) {
            // Write your code here

            List<Integer> aSubList = new ArrayList<>();
            List<Integer> result = new ArrayList<>();
            //arr.sort((a,b)->a-b);
            for(int i = 0; i < arr.size(); i++){
                for(int j = i+1; j < arr.size(); j++){
                    long suma = arr.get(i) + arr.get(j);
                    final  int ii =i;
                    final int  jj = j;
                    List<Integer> tempSubList = arr.stream().
                            filter((a)->a!=arr.get(ii) && a!=arr.get(jj)).collect(Collectors.toList());

                    long sumList = sumList(tempSubList);
                    //System.out.println("TempSubList =="+tempSubList+" CurrSum =="+suma+" firstA =="+arr.get(i)+" secondA =="+arr.get(j)+" subListSum =="+sumList);
                    if(suma > sumList) {
                        if(tempSubList.indexOf(arr.get(i)) ==-1 && tempSubList.indexOf(arr.get(j)) ==-1){
                            aSubList.add(arr.get(i));
                            aSubList.add(arr.get(j));
                            aSubList.sort((a,b)->a-b);
                            result.addAll(aSubList);
                        }
                    }
                }
            }

            //System.out.println("Sum result =="+sumList(Arrays.asList(1,2,3)));
            //System.out.println("SubList of arr =="+arr.subList(1, 4));

            return result;
        }

        private  long sumList(List<Integer> arr){
            //arr.sort((a,b)->a-b);
            return arr.stream().reduce(Integer.valueOf(0), Integer::sum);

        }
    }

}


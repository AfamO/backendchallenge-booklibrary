package com.polarisdigitech.backendchallenge.helpers;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {

    // Complete the substrCount function below.
    static long substrCount(int n, String s) {
        
        Stream stream = Stream.of("How");

        //Are two strings equal?
        System.out.println("Is String Unique=="+isUnique("GeeksforGeeks"));
        //Are two strings equal?
        System.out.println("Is String Unique=="+isItUnique("aaa"));
        System.out.println("Is String Unique=="+isItUnique("abc"));
        //Are two strings equal?
        System.out.println("Is String Unique=="+uniqueCharacters("GeeksforGeeks"));
        //Are two strings equal?
        System.out.println("Is String Unique=="+isUnique("aadaa"));
        //Are two strings equal?
        System.out.println("Is String Unique=="+isUnique("qwvxc"));

        return 0l;

    }
    static boolean uniqueCharacters(String str)
    {
        // Assuming string can have characters a-z
        // this has 32 bits set to 0
        int checker = 0;
        System.out.println("String::"+str);
        for (int i = 0; i < str.length(); i++) {
            int bitAtIndex = str.charAt(i) - 'a';
            System.out.println("bitAtIndex at "+i+"="+bitAtIndex);
            System.out.println("leftShift at "+i+" of "+bitAtIndex+"="+(1 << bitAtIndex));
            // if that bit is already set in checker,
            // return false
            if ((checker & (1 << bitAtIndex)) > 0)
                return false;

            // otherwise update and continue by
            // setting that bit in the checker
            checker = checker | (1 << bitAtIndex);
        }

        // no duplicates encountered, return true
        return true;
    }

    static boolean isUnique(String string){
        int maxChar = 256;
        System.out.println("String::"+string);
        if(string.length() > maxChar){
            return false;
        }
        boolean [] charBools = new boolean[maxChar];

        for(int i = 0; i <string.length(); i++){
            int index = (int)string.charAt(i);
            int freq =Collections.frequency(string.chars().boxed().collect(Collectors.toList()),string.charAt(i) );
            System.out.println("Freq for char "+string.charAt(i)+" =="+freq);

            if(charBools[index]){
                return false;
            }
            else{
                charBools[index] = true;
            }
        }
        return true;
    }

    static boolean isItUnique(String string) {
        System.out.println("String::"+string);
        return string.chars().filter(e ->frequency(string, (char)e) >1).count() >1? false: true;
    }
    static int frequency(String string, char e){
        return Collections.frequency(string.chars().boxed().collect(Collectors.toList()),e);
    }


    static boolean IsStringUnique(String string){
        char[] charArray = string.toCharArray();

        Arrays.sort(charArray);
        System.out.println("Sorted Array::");
        Arrays.asList(charArray).forEach(System.out::print);
        System.out.println();
        for(int i = 0; i < charArray.length-1;i++){
            if(charArray[i]!=charArray[i+1]){
                continue;
            }
            else{
                return false;
            }
        }
        return true;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String s = scanner.nextLine();

        long result = substrCount(n, s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

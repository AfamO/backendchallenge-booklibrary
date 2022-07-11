package com.polarisdigitech.backendchallenge.algorithms;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeMap;

@Slf4j
public class DataStructures {

    public void mapManips(){
        HashMap<Integer,String> hashMap = new HashMap();
        hashMap.put(2,"B");
        hashMap.put(1,"A");
        hashMap.put(0,"A");
        hashMap.put(6,"E");
        hashMap.put(4,"D");
        hashMap.put(3,"C");
        new StringBuffer();
        System.out.println("MIN VALUE INT =="+Integer.MIN_VALUE+" subs=="+"ac".substring(0,1));

        LinkedHashMap<Integer,String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(2,"B");
        linkedHashMap.put(1,"A");
        linkedHashMap.put(4,"D");
        linkedHashMap.put(3,"C");
        linkedHashMap.put(0,"A");
        linkedHashMap.put(6,"E");

        log.info("My normal hashmap =={}",hashMap);
        log.info("My linkedHashMap =={}",linkedHashMap);

        TreeMap<Integer,String> treeMap = new TreeMap<>();
        treeMap.put(2,"B");
        treeMap.put(1,"A");
        treeMap.put(4,"D");
        treeMap.put(0,"A");
        log.info("My treeMap =={}",treeMap);
    }
}

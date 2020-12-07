/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bariestest;

import java.util.*;

/**
 *
 * @author Sergio Alejandro Gutierrez Sanchez sergioa.gutierrezs@gmail.com
 */
public class Main {
    //String[] array = {"a", "b", "c", "d", "e"};
    //final List<String> BRACKETS = Arrays.asList({"[", "]", "{", "}", "(", ")"});
    
    public static void main(String[] args) {
        System.out.println("(a[0]+b[2c[6]]) {24 + 53} passed:" + parensMatch("(a[0]+b[2c[6]]) {24 + 53}"));
        System.out.println("f(e(d)) passed:" + parensMatch("f(e(d))"));
        System.out.println("[()]{}([]) passed:" + parensMatch("[()]{}([])"));
        System.out.println("((b) passed:" + !parensMatch("((b)"));
        System.out.println("(c] passed:" + !parensMatch("(c]"));
        System.out.println("{(a[]) passed:" + !parensMatch("{(a[])"));
        System.out.println("([)] passed:" + !parensMatch("([)]"));
        System.out.println(")( passed:" + !parensMatch(")("));
        System.out.println(" (empty) passed:" + parensMatch(""));
    }
    
    public static boolean parensMatch(String sentence){
        boolean match = true;
        String finalSentence = clearSentence(sentence);
        
        // Use a stack to make easier to take last value
        Deque<Character> currentPosition = new ArrayDeque<Character>(); 
        
        cicle:
        for (int i = 0; i < finalSentence.length(); i++) {
            char current = finalSentence.charAt(i);
            
            // add the bracket to the list
            if (current == '[' || current == '{' || current == '(') {
                currentPosition.add(current);
                continue;
            }
            
            // if the sentence bewgins with close bracket, menas that is not balanced
            if (currentPosition.isEmpty()) {
                match = false;
                break cicle;
            }
            
            // if the ezxecute is here, means that the current bracket is a closed one
            // so, it means that has to close, the last bracket in the list
            
            // get the last value in the stack to checlk if the close bracket match
            char lastValue = currentPosition.pollLast();
            
            switch (current) {
                case ']' -> {
                    if (lastValue != '[') {
                        match = false;
                        break cicle;
                    }
                }
                case '}' -> {
                    if (lastValue != '{') {
                        match = false;
                        break cicle;
                    }
                }
                case ')' -> {
                    if (lastValue != '(') {
                        match = false;
                        break cicle;
                    }
                }
            }
        }
        
        // if the stack dont is empty, a bracket is not closed, so its not balanced
        if (!currentPosition.isEmpty()) {
            match = false;
        }
        return match;
    }
    
    public static String clearSentence(String sentence) {
        String finalBrackets = "";
        for (int i = 0; i < sentence.length(); i++) {
            char current = sentence.charAt(i);
            if (current == '[' || current == ']' || current == '{' || current == '}' || current == '(' || current == ')') {
                finalBrackets += current;
            }
        }
        return finalBrackets;
    }
}
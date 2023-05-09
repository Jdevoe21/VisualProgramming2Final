/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fproject.arduinosketch.CodeEditer.Blocks;

/**
 *
 * @author Jack
 */
public class IncorrectOperandException extends Exception { 
    /**
     * Throws an exception for a the expression block
     * @param errorMessage Message that is thrown when an error occurs.
     */
     public IncorrectOperandException(String errorMessage) {
         super(errorMessage);
     }
 }

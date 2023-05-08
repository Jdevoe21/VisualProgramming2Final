/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fproject.arduinosketch.CodeEditer;

import fproject.arduinosketch.CodeEditer.Blocks.Block;
import fproject.arduinosketch.CodeEditer.Blocks.Expression;
import fproject.arduinosketch.CodeEditer.Blocks.Variable;
import java.util.ArrayList;

/**
 *
 * @author Jack
 */
public class BlockContainer {
    public ArrayList<Block> blocks;
    public ArrayList<Variable> vars;
    public ArrayList<Expression> expressions;
    
    public BlockContainer(){
        blocks = new ArrayList();
        vars = new ArrayList();
        expressions = new ArrayList();
    }
}

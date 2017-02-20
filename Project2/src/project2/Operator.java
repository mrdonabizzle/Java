/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project2;

/**
 *
 * @author willd
 */

// this Operator class is extended for classes that deal with addition,
// subtraction, multiplication, and division operators
public abstract class Operator {
    abstract public double evaluate(double x, double y);
}

class AddOperator extends Operator {
    @Override
    public double evaluate(double d1, double d2) {
        return d1+d2;
    }
    @Override
    public String toString() {
        return "+";
    }
}

class MulOperator extends Operator {
    @Override
    public double evaluate(double d1, double d2) {
        return d1*d2;
    }
    @Override
    public String toString() {
        return "*";
    }
}

class SubOperator extends Operator {
    @Override
    public double evaluate(double d1, double d2) {
        return d1-d2;
    }
    @Override
    public String toString() {
        return "-";
    }
}

class DivOperator extends Operator {
    @Override
    public double evaluate(double d1, double d2) {
        return d1/d2;
    }   
    @Override
    public String toString() {
        return "/";
    }
}


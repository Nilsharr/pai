
package com.ax.Enums;

public enum MathOperator {
    ADD("+"){
        @Override
        public double apply(double a, double b) { return a + b; }
    },
    SUBTRACT("-"){
        @Override
        public double apply(double a, double b) { return a - b; }
    },
    MULTIPLY("*"){
        @Override
        public double apply(double a, double b) { return a * b; }
    },
    DIVIDE("/"){
        @Override
        public double apply(double a, double b) { return a / b; }
    };
    private final String symbol;
    
    public String getSymbol(){
        return symbol;
    }
    
    private MathOperator(String symbol){
        this.symbol = symbol;
    }
    public abstract double apply(double a, double b);
}

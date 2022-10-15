
package com.mycompany.pai2;

import java.io.Serializable;

public class LoanBean implements Serializable {
    public Double loanAmount = 0.0;
    public Double yearlyPercentage = 0.0;
    public Double installmentNumber = 0.0;

    public Double getMonthlyInstallment(){
        Double monthlyPercentage = yearlyPercentage / 12;        
        Double monthlyInstallment = (loanAmount * monthlyPercentage) / (1 - 1 / Math.pow(1 + monthlyPercentage, installmentNumber));  
        return monthlyInstallment.isNaN() ? 0 : monthlyInstallment;
    }
    
    public Double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Double getYearlyPercentage() {
        return yearlyPercentage;
    }

    public void setYearlyPercentage(Double yearlyPercentage) {
        this.yearlyPercentage = yearlyPercentage / 100;
    }

    public Double getInstallmentNumber() {
        return installmentNumber;
    }

    public void setInstallmentNumber(Double installmentNumber) {
        this.installmentNumber = installmentNumber;
    }
}

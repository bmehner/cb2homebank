/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.mehnertechnologies.cb2homebank;

import java.util.List;

/**
 *
 * @author bodo
 */
public class HomeBankFileWriter {

    private final Cb2HbConfiguration configuration;
    
    public HomeBankFileWriter(Cb2HbConfiguration configuration) {
        this.configuration = configuration;
    }

    public void writeOutputFile(List<FinancialTransaction> financialTransactions) {
        for (FinancialTransaction financialTransaction : financialTransactions) {
            System.out.println("" + financialTransaction.getDescription());
        }
    }
    
}

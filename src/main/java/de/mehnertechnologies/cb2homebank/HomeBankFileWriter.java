package de.mehnertechnologies.cb2homebank;

import java.util.List;

/**
 * Writes a csv file in HomeBank format.
 * @author bjoern.mehner
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

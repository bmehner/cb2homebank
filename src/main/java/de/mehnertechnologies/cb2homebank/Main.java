package de.mehnertechnologies.cb2homebank;

import java.util.List;

/**
 * Main class to start the converter.
 * @author bjoern.mehner
 */
public class Main {
    public static void main(String[] args) {
        
        try {
            ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
            Cb2HbConfiguration configuration = configurationBuilder.buildFromCliArguments(args);
     
            CbCsvFileReader cbCsvFileReader = new CbCsvFileReader(configuration);
            HomeBankFileWriter homeBankFileWriter = new HomeBankFileWriter(configuration);
            
            List<FinancialTransaction> financialTransactions = cbCsvFileReader.readInputFile();
            homeBankFileWriter.writeOutputFile(financialTransactions);
            
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}

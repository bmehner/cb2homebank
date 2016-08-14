package de.mehnertechnologies.cb2homebank;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

/**
 * Writes a csv file in HomeBank format.
 * @author bjoern.mehner
 */
public class HomeBankFileWriter {

    private final Cb2HbConfiguration configuration;
    
    public HomeBankFileWriter(Cb2HbConfiguration configuration) {
        this.configuration = configuration;
    }

    public void writeOutputFile(List<FinancialTransaction> financialTransactions) throws IOException {
        File outputDirectory = configuration.getOutputDirectory();
        String outputFileName = configuration.getInputFile().getName();
        File outputFile = new File(outputDirectory.getPath() + File.separator + outputFileName);
        
        System.out.println(outputFile.getPath());
        
        if(outputFile.exists()) {
            outputFile.delete();
        }
        
        CSVFormat csvFormat = CSVFormat.newFormat(';')
                .withQuote('"')
                .withRecordSeparator("\n");
        
        Appendable out = new FileWriter(outputFile);
        
        try(CSVPrinter csvPrinter = new CSVPrinter(out, csvFormat)) {
            for (FinancialTransaction financialTransaction : financialTransactions) {

                String dateString = financialTransaction.getDateOfBooking().format(DateTimeFormatter.ofPattern("dd-MM-yy"));
                Paymode paymode = mapTransactionType(financialTransaction.getTransactionType());
                String paymodeString = String.valueOf(paymode.getCode());
                String infoString = financialTransaction.getDescription();
                String payeeString = "";
                String memoString = "";
                String amountString = financialTransaction.getAmount().toString();
                String categoryString = "";
                String tagsString = "Commerzbank";
                csvPrinter.printRecord(dateString, paymodeString, infoString, payeeString, memoString, amountString, categoryString, tagsString);
            }
        }
        
    }

    private Paymode mapTransactionType(FinancialTransaction.TransactionType transactionType) {
        switch (transactionType) {
            case Auszahlung:
                return Paymode.Cash;
            case Dauerauftrag:
                return Paymode.StandingOrder;
            case EinzahlungAuszahlung:
                return Paymode.Deposit;
            case Gutschrift:
            case Lastschrift:
            case Überweisung:
                return Paymode.Transfer;
            default:
                return Paymode.None;
        }
    }
    
    enum Paymode {
        None(0),
        CreditCard(1),
        Check(2),
        Cash(3),
        Transfer(4),
        InternalTransfer(5),
        DebitCard(6),
        StandingOrder(7),
        ElectronicPayment(8),
        Deposit(9),
        FIFee(19);
        
        private int code;
        
        private Paymode(int code) {
            this.code = code;
        }
        
        public int getCode() {
            return code;
        }
    }
}

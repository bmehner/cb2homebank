/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.mehnertechnologies.cb2homebank;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 * Reader for the Commerzbank files.
 * @author bodo
 */
public class CbCsvFileReader {
    
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy", Locale.GERMANY);
    NumberFormat numberFormat = NumberFormat.getInstance(Locale.GERMANY);
    private final Cb2HbConfiguration configuration;

    public CbCsvFileReader(Cb2HbConfiguration configuration) {
        this.configuration = configuration;
    }

    public List<FinancialTransaction> readInputFile() throws FileNotFoundException, IOException, ParseException {
        List<FinancialTransaction> financialTransactions = new ArrayList<>();
        FileReader fileReader = new FileReader(configuration.getInputFile());
        CSVFormat csvFormat = CSVFormat.newFormat(';')
                .withQuote('"')
                .withFirstRecordAsHeader()
                .withIgnoreEmptyLines();
        try (CSVParser parser = new CSVParser(fileReader, csvFormat)) {
            for (CSVRecord csvRecord : parser) {
                FinancialTransaction financialTransaction = new FinancialTransaction();
                financialTransaction.withDateOfBooking(LocalDate.parse(csvRecord.get(0), dateTimeFormatter))
                        .withValueDate(LocalDate.parse(csvRecord.get(1), dateTimeFormatter))
                        .withTransactionType(FinancialTransaction.TransactionType.valueOf(csvRecord.get(2).replace("/", "")))
                        .withDescription(csvRecord.get(3).substring(0, 30))
                        .withAmount(numberFormat.parse(csvRecord.get(4)).doubleValue())
                        .withCurrency(FinancialTransaction.Currency.valueOf(csvRecord.get(5)))
                        .withAccountNumber(csvRecord.get(6))
                        .withBankIdentificationCode(csvRecord.get(7))
                        .withIban(csvRecord.get(8));
                financialTransactions.add(financialTransaction);
            }
        }
                
        return financialTransactions;
    }
    
    
}

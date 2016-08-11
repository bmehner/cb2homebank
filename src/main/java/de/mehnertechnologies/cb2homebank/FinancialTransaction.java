package de.mehnertechnologies.cb2homebank;

import java.time.LocalDate;

/**
 * Holds the data for one financial transaction.
 * @author bjoern.mehner
 */
public class FinancialTransaction {
    
    // Buchungstag 09.08.2016
    private LocalDate dateOfBooking;
    // Wertstellung 09.08.2016
    private LocalDate valueDate;
    // Umsatzart (Lastschrift, ...)
    private TransactionType transactionType;
    // Buchungstext
    private String description;
    //Betrag
    private Double amount;
    // Währung
    private Currency currency;
    //Auftraggeberkonto
    private String accountNumber;
    // Bankleitzahl
    private String bankIdentificationCode;
    // IBAN
    private String iban;

    public LocalDate getDateOfBooking() {
        return dateOfBooking;
    }

    public LocalDate getValueDate() {
        return valueDate;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public String getDescription() {
        return description;
    }

    public Double getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getBankIdentificationCode() {
        return bankIdentificationCode;
    }

    public String getIban() {
        return iban;
    }

    public FinancialTransaction withDateOfBooking(LocalDate dateOfBooking) {
        this.dateOfBooking = dateOfBooking;
        return this;
    }
    
    public FinancialTransaction withValueDate(LocalDate valueDate) {
        this.valueDate = valueDate;
        return this;
    }

    public FinancialTransaction withTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
        return this;
    }

    public FinancialTransaction withDescription(String description) {
        this.description = description;
        return this;
    }

    public FinancialTransaction withAmount(Double amount) {
        this.amount = amount;
        return this;
    }

    public FinancialTransaction withCurrency(Currency currency) {
        this.currency = currency;
        return this;
    }

    public FinancialTransaction withAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public FinancialTransaction withBankIdentificationCode(String bankIdentificationCode) {
        this.bankIdentificationCode = bankIdentificationCode;
        return this;
    }

    public FinancialTransaction withIban(String iban) {
        this.iban = iban;
        return this;
    }
    
    enum TransactionType {
        Lastschrift,
        Auszahlung,
        Dauerauftrag,
        Überweisung,
        Gutschrift,
        EinzahlungAuszahlung; 
    }
    
    enum Currency {
        EUR
    }
}

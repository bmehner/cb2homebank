package de.mehnertechnologies.cb2homebank;

import java.util.List;
import org.apache.commons.cli.*;

/**
 * Main class to start the converter.
 * @author bjoern.mehner
 */
public class Main {
    public static void main(String[] args) {
        
        try {
            Cb2HbConfiguration configuration = parseCommandlineArgs(args);
            List<FinancialTransaction> financialTransactions = readInputFile(configuration);
            writeOutputFile(financialTransactions, configuration);
            System.exit(0);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    private static Cb2HbConfiguration parseCommandlineArgs(String[] args) {
        Options options = createOptions();
        
        CommandLineParser parser = new DefaultParser();
        CommandLine line = null;
        
        try {
            line = parser.parse(options, args);
        } catch (ParseException ex) {
            help(options);
            System.exit(1);
        }
        
        String inputFile = line.getOptionValue("if");
        String outputDirectory = line.getOptionValue("d");
        
        return new Cb2HbConfiguration(inputFile, outputDirectory);
    }

    private static void help(Options options) {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("java -jar cb2homebank.jar", options);
    }

    private static Options createOptions() {
        Options options = new Options();
        
        options.addOption("help", "Prints this help");
        Option option1 = new Option("if", true, "Path to Commerzbank CSV input file (required");
        option1.setRequired(true);
        options.addOption(option1);
        Option option2 = new Option("d", true, "Path to output directory (required)");
        option2.setRequired(true);
        options.addOption(option2);
        
        return options;
    }

    private static List<FinancialTransaction> readInputFile(Cb2HbConfiguration configuration) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void writeOutputFile(List<FinancialTransaction> financialTransactions, Cb2HbConfiguration configuration) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

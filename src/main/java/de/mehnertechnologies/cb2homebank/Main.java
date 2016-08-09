package de.mehnertechnologies.cb2homebank;

import org.apache.commons.cli.*;

/**
 * Main class to start the converter.
 * @author bjoern.mehner
 */
public class Main {
    public static void main(String[] args) {
        
        Options options = createOptions();
        
        CommandLineParser parser = new DefaultParser();
        
        try {
            CommandLine line = parser.parse(options, args);
            
            if (line.getOptions().length == 0) {
                help(options);
            }
            
            
            
            //System.out.println("Hello world");
        } catch (ParseException ex) {
            help(options);
        }
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
}

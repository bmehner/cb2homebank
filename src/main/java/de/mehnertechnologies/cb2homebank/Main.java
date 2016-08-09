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
        options.addOption("if", true, "Path to Commerzbank CSV input file");
        options.addOption("d", true, "Path to output directory");
        
        return options;
    }
}

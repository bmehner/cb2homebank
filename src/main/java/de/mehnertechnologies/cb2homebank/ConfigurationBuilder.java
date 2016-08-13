package de.mehnertechnologies.cb2homebank;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * Parses the commandline arguments and builds configuration.
 * @author bjoern.mehner
 */
public class ConfigurationBuilder {
    
    public Cb2HbConfiguration buildFromCliArguments(String[] args) {
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
    
    private void help(Options options) {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("java -jar cb2homebank.jar", options);
    }

    private Options createOptions() {
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

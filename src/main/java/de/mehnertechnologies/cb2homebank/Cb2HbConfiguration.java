package de.mehnertechnologies.cb2homebank;

import java.io.File;

/**
 * Configuration Class for cb2homebank.
 *
 * @author bjoern.mehner
 */
public class Cb2HbConfiguration {

    private final File inputFile;
    private final File outputDirectory;

    public Cb2HbConfiguration(String inputFile, String outputDirectory) {
        this.inputFile = new File(inputFile);
        this.outputDirectory = new File(outputDirectory);
        
        if (isInputFileReadable()) {
            throw new IllegalArgumentException("File does not exist or is not readable: " + inputFile);
        }
        
        if (isOutputDirectoryWriteable() ) {
            throw new IllegalArgumentException("Output directory does not exist or is not writeable: " + outputDirectory);
        }
    }

    private boolean isOutputDirectoryWriteable() {
        return !this.outputDirectory.exists() || !this.outputDirectory.canWrite();
    }

    private boolean isInputFileReadable() {
        return !this.inputFile.exists() || !this.inputFile.canRead();
    }

    public File getInputFile() {
        return inputFile;
    }

    public File getOutputDirectory() {
        return outputDirectory;
    }
}

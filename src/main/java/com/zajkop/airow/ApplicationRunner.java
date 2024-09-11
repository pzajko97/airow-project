package com.zajkop.airow;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ApplicationRunner {
    private String summary;
    private String laps;
    private String samples;
    private String output;

    public static void main(String[] args) throws IOException {
        ApplicationRunner runner = new ApplicationRunner();

        if (runner.isHelpRequested(args)) {
            runner.printHelp();
            return;
        }

        runner.parseArguments(args);
        runner.validateArguments();

        if (runner.output != null && !runner.output.isEmpty()) {
            new DataProcessor(new File(runner.summary), new File(runner.laps), new File(runner.samples), runner.output).process();
        } else {
            new DataProcessor(new File(runner.summary), new File(runner.laps), new File(runner.samples)).process();
        }
    }

    private void parseArguments(String[] args) {
        Map<String, String> arguments = new HashMap<>();

        for (String arg : args) {
            if (arg.startsWith("--")) {
                String[] splitArg = arg.split("=", 2);
                if (splitArg.length == 2) {
                    arguments.put(splitArg[0], splitArg[1]);
                } else {
                    System.err.println("Invalid argument format: " + arg);
                }
            }
        }

        this.summary = arguments.get("--summary");
        this.laps = arguments.get("--laps");
        this.samples = arguments.get("--samples");
        this.output = arguments.get("--output");
    }

    private void validateArguments() {
        if (this.summary == null) {
            throw new IllegalArgumentException("Error: --summary is a required path parameter and must be provided.");
        }
        if (this.laps == null) {
            throw new IllegalArgumentException("Error: --laps is a required path parameter and must be provided.");
        }
        if (this.samples == null) {
            throw new IllegalArgumentException("Error: --samples is a required path parameter and must be provided.");
        }
    }

    private boolean isHelpRequested(String[] args) {
        for (String arg : args) {
            if ("--h".equals(arg)) {
                return true;
            }
        }
        return false;
    }

    private void printHelp() {
        System.out.println("Usage: gradle run --args=\"--summary=/Path/To/summaryFile.json --laps=/Path/To/lapsFile.json --samples=/Path/To/samplesFile.json\"");
        System.out.println("Options:");
        System.out.println("  --summary=<path>  Path to summary file (required)");
        System.out.println("  --laps=<path>     Path to laps file (required)");
        System.out.println("  --samples=<path>  Path to samples file (required)");
        System.out.println("  --output=<path>   Path to output file (optional, default is /build/generated/output.json)");
        System.out.println("  --h               Print this help message");
    }
}

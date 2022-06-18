package com.propine.solution;

import picocli.CommandLine;

public class Main {

    public static void main(String args[]) {
        CommandLine.run(new CommandLineParsed(), args);
    }
}

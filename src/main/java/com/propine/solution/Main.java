package com.propine.solution;

import picocli.CommandLine;

import java.io.IOException;

public class Main {

    public static void main(String args[]) throws IOException {
        CommandLine.run(new CommandLineParsed(), args);
    }
}

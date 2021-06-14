package com.aman.javacamel.processors;

import org.apache.camel.component.file.GenericFile;

import java.util.Comparator;

public class MyFileSorter implements Comparator<String> {
    public int compare(String o1, String o2) {
        return o2.compareToIgnoreCase(o1);
    }
}
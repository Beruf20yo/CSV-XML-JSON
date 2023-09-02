package ru.netology;

import ru.netology.parsers.CsvJsonParser;
import ru.netology.parsers.Parser;
import ru.netology.parsers.XmlJsonParser;

public class Main {
    public static void main(String[] args) {
        Parser csvParser = new CsvJsonParser("src/main/resources/data.csv");
        csvParser.parsing();
        Parser xmlParser = new XmlJsonParser("src/main/resources/data.xml");
        xmlParser.parsing();

    }
}
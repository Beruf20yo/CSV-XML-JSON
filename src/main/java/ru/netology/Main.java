package ru.netology;

public class Main {
    public static void main(String[] args) {

//        CSV_JSON_Parser parser = new CSV_JSON_Parser("src/main/resources/data.csv");
//        parser.work();
        XML_JSON_Parser parser = new XML_JSON_Parser("src/main/resources/data.xml");
        parser.work();

    }
}
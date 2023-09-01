package ru.netology;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.json.JSONObject;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSV_JSON_Parser {


    private final List<Employee> employees = new ArrayList<>();
    private final String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
    private String fileName;

    public CSV_JSON_Parser(String fileName) {
        this.fileName = fileName;
    }

    public CSV_JSON_Parser() {

    }

    public void work() {
        fromCSV();
        toJSON();
    }

    private void fromCSV() {
        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                employees.add(new Employee(Long.parseLong(nextLine[0]), nextLine[1],
                        nextLine[2], nextLine[3], Integer.parseInt(nextLine[4])));
            }

        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }

    }

    private void toJSON() {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        try (FileWriter file = new
                FileWriter("src/main/resources/data.json")) {
            for (Employee employee : employees) {
                file.write(gson.toJson(employee));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


}

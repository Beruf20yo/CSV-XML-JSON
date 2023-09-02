package ru.netology.parsers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.netology.Employee;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public interface Parser {
    String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};

    void parsing();
    default void toJSON(List<Employee> employees, String pathToJson){
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        try (FileWriter file = new
                FileWriter(pathToJson)) {
            for (Employee employee : employees) {
                file.write(gson.toJson(employee));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

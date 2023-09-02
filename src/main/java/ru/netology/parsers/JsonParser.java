package ru.netology.parsers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ru.netology.Employee;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonParser implements Parser{
    private final String fileName;

    public JsonParser(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void parsing() {
       fromJsonToEmployees().stream().map(Employee::toString).forEach(System.out::println);
    }
    private List<Employee> fromJsonToEmployees(){
        List<Employee> employees = new ArrayList<>();
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(fileName));
            JSONArray jsonObject = (JSONArray) obj;
            for (Object o : jsonObject) {
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                Employee employee = gson.fromJson(o.toString(), Employee.class);
                employees.add(employee);
            }

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return employees;
    }
}

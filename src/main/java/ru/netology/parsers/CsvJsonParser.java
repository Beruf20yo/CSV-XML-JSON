package ru.netology.parsers;

import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import ru.netology.Employee;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvJsonParser implements Parser{

    private final String fileName;

    public CsvJsonParser(String fileName) {
        this.fileName = fileName;
    }

    public void parsing() {
        toJSON(fromCSV(),"src/main/resources/data1.json");
    }

    private List<Employee> fromCSV(){
        List<Employee> employees = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new FileReader(fileName))) {
            ColumnPositionMappingStrategy<Employee> strategy =
                    new ColumnPositionMappingStrategy<>();
            strategy.setType(Employee.class);
            strategy.setColumnMapping(columnMapping);
            CsvToBean<Employee> csv = new CsvToBeanBuilder<Employee>(csvReader)
                    .withMappingStrategy(strategy)
                    .build();
            employees = csv.parse();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employees;
    }

}

package com.example.medcardservice.util;
import com.example.medcardservice.model.Mkb10Entry;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;

import java.util.Set;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashSet;

public class CsvUtils {

    private static final String CSV_URL = "https://raw.githubusercontent.com/ak4nv/mkb10/master/mkb10.csv";

    public static Set<Mkb10Entry> parseMkb10Entry () throws IOException, CsvValidationException {
        Set<Mkb10Entry> entries = new HashSet<>();
        URL url = new URL(CSV_URL);
        try (CSVReader reader = new CSVReader(new InputStreamReader(url.openStream()))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                String code = line[2]; // Код болезни
                String name = line[3]; // Название болезни
                System.out.println(code + " " + name);
                if (code.matches("^[A-Z][0-9]{2}(\\.[0-9]{1,2})?$")) { // Фильтрация по формату кода
                    entries.add(new Mkb10Entry(code, name));
                }
            }
        }
        return entries;
    }
}




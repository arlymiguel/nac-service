package com.nace.util;

import com.nace.dto.NaceDto;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

public class CSVHelper {

    public static String TYPE = "text/csv";
    static String[] HEADERs = { "Order", "Level", "Code", "Parent", "Description", "This item includes", "This item also includes", "Rulings", "This item excludes", "Reference to ISIC Rev. 4"};

    public static boolean hasCSVFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public static List<NaceDto> csvToNaceDto(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<NaceDto> naceDtos = new ArrayList<NaceDto>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                NaceDto tutorial = NaceDto.builder()
                        .order(csvRecord.get("Order"))
                        .level(csvRecord.get("Level"))
                        .code(csvRecord.get("Code"))
                        .parent(csvRecord.get("Parent"))
                        .description(csvRecord.get("Description"))
                        .include(csvRecord.get("This item includes"))
                        .rulings(csvRecord.get("Rulings"))
                        .exclude(csvRecord.get("This item excludes"))
                        .reference(csvRecord.get("Reference to ISIC Rev. 4"))
                        .build();

                naceDtos.add(tutorial);
            }

            return naceDtos;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

}

package com.example.task.recommendations;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class CryptoCsvReader {
    @Cacheable("cryptos")
    public List<String> getAvailableCryptos() throws IOException {
        try {
            return Arrays.stream(Objects.requireNonNull(new File(ClassLoader.getSystemResource("Prices")
                            .toURI()).listFiles()))
                    .filter( file -> file.getName().endsWith("_values.csv"))
                    .map(file -> file.getName().replace("_values.csv", ""))
                    .collect(Collectors.toList());
        } catch (URISyntaxException e) {
            throw new IOException(e);
        }
    }
    @Cacheable("cryptoPriceEntries")
    public List<PriceEntry> getDataForCrypto(String file) throws FileNotFoundException {
        String path = ClassLoader.getSystemResource("Prices/" + file + "_values.csv").getPath();
        FileReader filereader = new FileReader(path);
        CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
        CSVReader csvReader = new CSVReaderBuilder(filereader)
                .withCSVParser(parser)
                .withSkipLines(1)
                .build();
        CsvToBean<PriceEntry> csvToBean = new CsvToBean<>();
        ColumnPositionMappingStrategy<PriceEntry> mappingStrategy = new ColumnPositionMappingStrategy<>();
        mappingStrategy.setType(PriceEntry.class);
        mappingStrategy.setColumnMapping("timestamp", "symbol", "price");
        return csvToBean.parse(mappingStrategy, csvReader);
    }
}

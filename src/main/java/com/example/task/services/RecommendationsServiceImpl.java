package com.example.task.services;

import com.example.task.dtos.CryptoFilterCondition;
import com.example.task.dtos.Order;
import com.example.task.exceptions.CryptoNotFoundException;
import com.example.task.exceptions.InvalidDateException;
import com.opencsv.CSVReader;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

@Service
public class RecommendationsServiceImpl implements RecommendationsService {
    @Override
    public List<String> getCryptosByNormalizedRange(Order order) {

        try {
            String path = ClassLoader.getSystemResource("Prices/BTC_values.csv").getPath();
            CSVReader csvReader = new CSVReader(new FileReader(path));



        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public double getPrice(String crypto, CryptoFilterCondition cryptoFilter) throws CryptoNotFoundException {

        return 0;
    }

    @Override
    public String getCryptoWithHighestNormalizedRangeForDate(Date date) throws InvalidDateException {

        return null;
    }
}

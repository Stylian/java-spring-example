package com.example.task.recommendations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationsController {

    @Autowired
    private RecommendationsService recommendationsService;

    // cryptos?sort_by=normalized_range.desc
    @GetMapping("/cryptos")
    public List<String> getCryptosSorted(@RequestParam(name = "sort_by") String sortBy) {

        if (!sortBy.contains(".")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "sorting parameters must be like ?sort_by=normalized_range.desc");
        }
        String[] sortByParts = sortBy.split("\\.");
        CryptoProperty cryptosProperty = CryptoProperty.getEnum(sortByParts[0]);
        Order order = Order.getEnum(sortByParts[1]);
        if (cryptosProperty == null || order == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "the field does not exist or the sorting direction is incorrect");
        }

        if (cryptosProperty.equals(CryptoProperty.NORMALIZED_RANGE)) {
            try {
                return recommendationsService.getCryptosSymbolsByNormalizedRange(order);
            } catch (IOException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "unimplemented sorting");
        }
    }

    // cryptos/{crypto}/values?filter=min
    @GetMapping("/cryptos/{crypto}/values")
    @ResponseBody
    public double getCryptoValues(
            @PathVariable String crypto,
            @RequestParam String filter
    ) {

        CryptoFilterCondition cryptoFilterCondition = CryptoFilterCondition.getEnum(filter);

        if (cryptoFilterCondition == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "");
        }
        try {
            return recommendationsService.getPrice(crypto, cryptoFilterCondition);
        } catch (CryptoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "the crypto requested was not found");
        }
    }

    // crypto?field=normalized_range:max&date=2023-10-21
    @GetMapping("/crypto")
    @ResponseBody
    public String getCryptoByDateAndFieldCondition(
            @RequestParam String field,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date
    ) {

        if (!field.contains(":")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "");
        }
        String[] fieldParts = field.split(":");
        CryptoProperty cryptosProperty = CryptoProperty.getEnum(fieldParts[0]);
        CryptoFilterCondition cryptoFilterCondition = CryptoFilterCondition.getEnum(fieldParts[1]);
        if (cryptosProperty == null || cryptoFilterCondition == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "");
        }

        try {
            if (cryptosProperty.equals(CryptoProperty.NORMALIZED_RANGE) && cryptoFilterCondition.equals(CryptoFilterCondition.MAX)) {
                return recommendationsService.getCryptoWithHighestNormalizedRangeForDate(date);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "unimplemented sorting");
            }
        } catch (InvalidDateException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no date for the specified date have been found");
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "");
        }
    }
}

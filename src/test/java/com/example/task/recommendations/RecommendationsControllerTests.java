package com.example.task.recommendations;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RecommendationsController.class)
public class RecommendationsControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RecommendationsService recommendationsService;
    @Test
    public void testGetCryptosSortedBadRequests() throws Exception {
        Mockito.when(recommendationsService.getCryptosSymbolsByNormalizedRange(any()))
                .thenReturn(Arrays.asList(new String[]{"BTC", "DOGE"}));

        mockMvc.perform((MockMvcRequestBuilders.get("/api/recommendations/cryptos?sort_by=normalized_range:desc")))
                .andExpect(status().isBadRequest());
        mockMvc.perform((MockMvcRequestBuilders.get("/api/recommendations/cryptos?sort_by=wrong_field.desc")))
                .andExpect(status().isBadRequest());
        mockMvc.perform((MockMvcRequestBuilders.get("/api/recommendations/cryptos?sort_by=normalized_range.blue")))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void testGetCryptosSorted() throws Exception {
        Mockito.when(recommendationsService.getCryptosSymbolsByNormalizedRange(any()))
                .thenReturn(Arrays.asList(new String[]{"BTC", "DOGE"}));

        String response = mockMvc.perform((MockMvcRequestBuilders.get("/api/recommendations/cryptos?sort_by=normalized_range.desc")))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Assertions.assertEquals("[\"BTC\",\"DOGE\"]", response);
    }
    @Test
    public void testGetCryptoValues() throws Exception {
        Mockito.when(recommendationsService.getPrice(anyString(), any()))
                .thenReturn(5000d);

        String response = mockMvc.perform((MockMvcRequestBuilders.get("/api/recommendations/cryptos/BTC/values?filter=min")))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Assertions.assertEquals(5000d, Double.parseDouble(response));
    }
    @Test
    public void testGetCryptoValuesCryptoNotFound() throws Exception {
        Mockito.when(recommendationsService.getPrice(anyString(), any()))
                .thenThrow(CryptoNotFoundException.class);

        mockMvc.perform((MockMvcRequestBuilders.get("/api/recommendations/cryptos/NOCOIN/values?filter=min")))
                .andExpect(status().isNotFound());
    }
    @Test
    public void testGetCryptoByDateAndFieldCondition() throws Exception {
        Mockito.when(recommendationsService.getCryptoWithHighestNormalizedRangeForDate(any(Date.class)))
                .thenReturn("BTC");

        String response = mockMvc.perform((MockMvcRequestBuilders.get("/api/recommendations/crypto?field=normalized_range:max&date=2023-10-21")))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Assertions.assertEquals("BTC", response);
    }
    @Test
    public void testGetCryptoByDateAndFieldConditionMissingParamsError() throws Exception {
        Mockito.when(recommendationsService.getCryptoWithHighestNormalizedRangeForDate(any(Date.class)))
                .thenReturn("BTC");

        mockMvc.perform((MockMvcRequestBuilders.get("/api/recommendations/crypto?field=normalized_range:max")))
                 .andExpect(status().isBadRequest());
        mockMvc.perform((MockMvcRequestBuilders.get("/api/recommendations/crypto?field=normalized_range:not_max&date=2023-10-21")))
                 .andExpect(status().isBadRequest());
        mockMvc.perform((MockMvcRequestBuilders.get("/api/recommendations/crypto?field=field_that_does_not_exist:max&date=2023-10-21")))
                 .andExpect(status().isBadRequest());
    }

}

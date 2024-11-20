package com.alura.conversor.services;

import com.alura.conversor.exceptions.ConversionServiceException;
import com.alura.conversor.models.ConversionResult;
import com.alura.conversor.dto.ConversionResponseDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class CurrencyConversionService {

    private static final String ENDPOINT_EXCHANGE_RATE = "https://v6.exchangerate-api.com/v6/fd5e04d899b34eb3822dd24f/pair";
    private final HttpClient httpClient;
    private final Gson gson;

    public CurrencyConversionService(){
        this.httpClient = HttpClient.newHttpClient();
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    public ConversionResult getExchangeRate(String fromCode, String toCode, double amount){

        String url = buildExchangeURL(fromCode,toCode,amount);

      try{
          String jsonResponse = sendGetRequest(url);
          ConversionResponseDTO exchangeRateResponse = gson.fromJson(jsonResponse, ConversionResponseDTO.class);
          // Incluye el monto original al crear el resultado final
          return new ConversionResult(amount, exchangeRateResponse.conversion_rate(), exchangeRateResponse.conversion_result());

      } catch ( IOException | InterruptedException e ) {
          throw new ConversionServiceException("Error al obtener la tasa de cambio entre " + fromCode + " y " + toCode, e);
      }
    }

    private String buildExchangeURL(String fromCode, String toCode, double amount){

        String encodedFromCode = URLEncoder.encode(fromCode, StandardCharsets.UTF_8);
        String encodedToCode = URLEncoder.encode(toCode, StandardCharsets.UTF_8);

        // Construcción de la URL
        return String.format("%s/%s/%s/%.2f", ENDPOINT_EXCHANGE_RATE, encodedFromCode, encodedToCode, amount);
    }

    private String sendGetRequest(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return response.body();
        } else {
            throw new IOException("Error en la solicitud: " + response.statusCode());
        }
    }
}


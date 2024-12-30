package com.br.marlon.screensound.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaHuggingFace {

    private static final String API_URL = "https://api-inference.huggingface.co/models/gpt2";  // URL do modelo GPT-2
    private static final String API_KEY = "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";  // Substitua pela sua chave de API

    public static String obterInformacao(String prompt) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            String requestBody = "{ \"inputs\": \"" + prompt + "\" }";  // Formato esperado pela API do Hugging Face

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .header("Authorization", "Bearer " + API_KEY)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                // Usando Jackson para parsear a resposta
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode responseJson = objectMapper.readTree(response.body());
                return responseJson.get(0).get("generated_text").asText();  // Obtendo a resposta gerada
            } else {
                return "Erro na consulta ao Hugging Face: Código " + response.statusCode();
            }
        } catch (Exception e) {
            return "Erro na consulta ao Hugging Face: " + e.getMessage();
        }
    }
}
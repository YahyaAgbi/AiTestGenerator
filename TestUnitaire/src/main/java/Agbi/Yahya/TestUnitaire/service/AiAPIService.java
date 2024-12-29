package Agbi.Yahya.TestUnitaire.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Service
public class AiAPIService {
    private final RestClient restClient;

    @Value("${gemini.api.key}")
    private String key;

    public AiAPIService(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder
                .baseUrl("https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash-latest:generateContent")
                .build();
    }

    private String callGemini(String prompt) {
        String safePrompt = (prompt == null || prompt.trim().isEmpty())
                ? "Provide a generic helpful response."
                : prompt;

        try {
            Map<String, Object> requestBody = new HashMap<>();
            List<Map<String, Object>> contents = new ArrayList<>();
            Map<String, Object> contentItem = new HashMap<>();
            Map<String, Object> part = new HashMap<>();

            part.put("text", safePrompt);
            contentItem.put("parts", Collections.singletonList(part));
            contents.add(contentItem);
            requestBody.put("contents", contents);

            Map<String, Object> response = restClient.post()
                    .uri(uriBuilder -> uriBuilder.queryParam("key", key).build())
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(requestBody)
                    .retrieve()
                    .body(new ParameterizedTypeReference<Map<String, Object>>() {});

            // Vérification et traitement de la réponse
            List<Map<String, Object>> candidates = (List<Map<String, Object>>) response.get("candidates");
            if (candidates == null || candidates.isEmpty()) {
                return "No candidates in Gemini API response";
            }

            Map<String, Object> firstCandidate = candidates.get(0);
            if (firstCandidate == null) {
                return "First candidate is null";
            }

            List<Map<String, Object>> parts = (List<Map<String, Object>>) firstCandidate.get("content");
            if (parts == null || parts.isEmpty()) {
                return "No parts in response";
            }

            Map<String, Object> firstPart = parts.get(0);
            return (String) firstPart.get("text");
        } catch (ClassCastException e) {
            return "Error parsing API response: " + e.getMessage();
        } catch (Exception e) {
            return "Error calling Gemini API: " + e.getMessage();
        }
    }

    public String generateTest(File file, TestType testType, String add) throws FileNotFoundException {
        try {
            String fileContent = Files.readString(file.toPath());
            String prompt = String.format(
                    "I will give you Java code. Could you please generate %s, return only the Java code. Java code:\"\"\" %s \"\"\" %s",
                    testType.getDescription(),
                    fileContent,
                    add
            );
            return callGemini(prompt);

        } catch (IOException e) {
            throw new FileNotFoundException("Could not read file: " + file.getPath());
        }
    }

    public String genereratePOMtest(String path) throws FileNotFoundException {
        try {
            String fileContent = Files.readString(Paths.get(path, "pom.xml"));
            String prompt = String.format(
                    "I will give you pom.xml. Could you please check if these dependencies and plugins exist, and add them if missing? Return only the pom.xml. pom.xml content:\"\"\" %s \"\"\"",
                    fileContent
            );
            return callGemini(prompt);

        } catch (IOException e) {
            throw new FileNotFoundException("Could not read path: " + path);
        }
    }

    public String getClassAssociatedContent(File[] files) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (File file : files) {
            if (file.exists() && file.isFile()) {
                sb.append(Files.readString(file.toPath())).append("\n");
            }
        }
        String associatedContent = sb.toString().trim();
        return ", this code associated \"\"\" " + associatedContent + " \"\"\" ";
    }
}

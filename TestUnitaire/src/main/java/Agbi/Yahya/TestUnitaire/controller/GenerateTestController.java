package Agbi.Yahya.TestUnitaire.controller;

import Agbi.Yahya.TestUnitaire.service.GenerateTestService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class GenerateTestController {
    @Autowired
    GenerateTestService generateTestService;

    @PostMapping("/Generate/{projectName}")
    public ResponseEntity<Map<String, String>> generateTestU(@PathVariable String projectName) {
        Map<String, String> response = new HashMap<>();
        try {
            System.out.println("Starting test generation for project: " + projectName);
            generateTestService.generateTestGene(projectName);
            response.put("status", "success");
            response.put("message", "Test created successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.err.println("Error generating test for project: " + projectName);
            e.printStackTrace();
            response.put("status", "error");
            response.put("message", "Error while generating the test: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }
}

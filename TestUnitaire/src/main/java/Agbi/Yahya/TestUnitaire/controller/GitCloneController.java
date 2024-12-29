package Agbi.Yahya.TestUnitaire.controller;


import Agbi.Yahya.TestUnitaire.dto.CodeDTO;
import Agbi.Yahya.TestUnitaire.service.CloningService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/git")
@RequiredArgsConstructor
public class GitCloneController {

    private final CloningService cloneService;
    @PostMapping
    public ResponseEntity<Boolean> getProject(@RequestBody CodeDTO.GitRequest github) {
        int result = cloneService.cloneRepo(github.getUrl());
        return switch (result) {
            case 1 -> ResponseEntity.ok(true);
            case 0 -> ResponseEntity.status(409).body(false);
            default -> ResponseEntity.status(500).body(false);
        };
    }
}

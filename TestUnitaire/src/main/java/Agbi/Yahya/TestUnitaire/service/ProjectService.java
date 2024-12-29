package Agbi.Yahya.TestUnitaire.service;

import Agbi.Yahya.TestUnitaire.entities.Projet;
import Agbi.Yahya.TestUnitaire.entities.User;
import Agbi.Yahya.TestUnitaire.repositories.ProjetRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProjectService {
    private final ProjetRepository projetRepo;
    private final FileSystemService fileSystemService;
    private final ObjectMapper objectMapper;
    private final GenerateTestService generateTestService;
    public Projet saveProjetWithDetail(String projetName, String path, User user) throws JsonProcessingException {
        List<String> files=fileSystemService.getFileMap(path);
        String entity=objectMapper.writeValueAsString(generateTestService.getEntity(files));
        Projet projet = Projet.builder()
                .javafile(objectMapper.writeValueAsString(files))
                .nom(projetName)
                .path(path)
                .user(user)
                .entity(entity)
                .build();
        return projetRepo.save(projet);
    }
}
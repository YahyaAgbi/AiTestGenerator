package Agbi.Yahya.TestUnitaire.controller;

import Agbi.Yahya.TestUnitaire.service.FileSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/projet")
public class SystemFileController {
    @Autowired
    private FileSystemService fileservice;

    @GetMapping("/names")
    public List<String> listAllProjects() {
        System.out.println("Fetching all project names");
        return fileservice.getAllProjectNames();
    }

    @GetMapping("/{prj}")
    public List<Map<String, Object>> listFiles(@PathVariable String prj, @RequestParam(defaultValue = "") String localPath) {
        System.out.println("Listing files for project: " + prj + ", Path: " + localPath);
        return fileservice.listFiles(prj, localPath);
    }

    @GetMapping("/{prj}/file/read")
    public ResponseEntity<String> readFile(@PathVariable String prj, @RequestParam(defaultValue = "") String localPath) {
        try {
            System.out.println("Reading file content for project: " + prj + ", Path: " + localPath);
            String content = fileservice.readFileContent(prj, localPath);
            return ResponseEntity.ok(content);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("An error occurred while reading the file: " + e.getMessage());
        }
    }

    @PostMapping("/{prj}/download")
    public ResponseEntity<InputStreamResource> Zipproject(@PathVariable String prj) throws IOException {
        System.out.println("Zipping project: " + prj);
        File zipFile = new File(fileservice.PathZipfile(prj));
        InputStreamResource resource = new InputStreamResource(new FileInputStream(zipFile));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + zipFile.getName() + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(zipFile.length())
                .body(resource);
    }
}

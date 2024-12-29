package Agbi.Yahya.TestUnitaire.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class CodeDTO {
    @Builder
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GitRequest{
        public String url;
    }
    @Builder
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Path{
        public String path;
    }
}

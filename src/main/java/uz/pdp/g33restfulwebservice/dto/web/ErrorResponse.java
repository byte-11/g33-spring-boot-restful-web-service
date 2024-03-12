package uz.pdp.g33restfulwebservice.dto.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private int status;
    private String errorMessage;
    private Object body;
    private String path;
    @Builder.Default
    private LocalDateTime time = LocalDateTime.now();
}

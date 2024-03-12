package uz.pdp.g33restfulwebservice.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TodoCreationDto {
    @NotBlank
    @Length(min = 8, max = 100)
    private String action;

    @NotEmpty
    private String assignee;

    private LocalDateTime time = LocalDateTime.now();
}

package uz.pdp.g33restfulwebservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Todo {
    private Long id;
    private String action;
    private String assignee;
    private LocalDateTime time;
}

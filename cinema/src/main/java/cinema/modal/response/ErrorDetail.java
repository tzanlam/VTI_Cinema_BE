package cinema.modal.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ErrorDetail {
    private LocalDate timestamp;
    private String message;
    private String detail;
}

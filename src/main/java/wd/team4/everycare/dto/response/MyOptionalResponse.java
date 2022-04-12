package wd.team4.everycare.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Getter
@NoArgsConstructor
public class MyOptionalResponse<T> {
    private StatusEnum header;
    private String message;
    private Optional<T> body;

    @Builder
    public MyOptionalResponse(StatusEnum header, String message, Optional<T> body) {
        this.header = header;
        this.message = message;
        this.body = body;
    }
}

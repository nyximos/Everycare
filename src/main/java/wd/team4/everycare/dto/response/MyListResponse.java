package wd.team4.everycare.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class MyListResponse<T> {
    private StatusEnum header;
    private String message;
    private List<T> body;

    @Builder
    public MyListResponse(StatusEnum header, String message, List<T> body) {
        this.header = header;
        this.message = message;
        this.body = body;
    }
}

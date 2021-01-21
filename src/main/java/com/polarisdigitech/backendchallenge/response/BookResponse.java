package com.polarisdigitech.backendchallenge.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {

    @Builder.Default
    private int status = HttpStatus.CREATED.value();
    @Builder.Default
    private String message = "success";

}


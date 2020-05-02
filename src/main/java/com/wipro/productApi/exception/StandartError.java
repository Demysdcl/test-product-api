package com.wipro.productApi.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StandartError {
    private Long timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
}

package com.window_system_ai.demo.dtos;

import com.window_system_ai.demo.constants.Constants;
import com.window_system_ai.demo.constants.Program;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionDTO {
    private String code;
    private String message;

    public ExceptionDTO buildGenericError() {
        return new ExceptionDTO(Constants.GENERIC_ERROR_CODE, Constants.STATUS_DESC_INTERNAL_SERVER_ERROR);
    }

}

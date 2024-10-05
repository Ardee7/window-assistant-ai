package com.window_system_ai.demo.dtos;

import com.window_system_ai.demo.constants.Program;
import com.window_system_ai.demo.utilities.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class GenericResponseDTO<T> {

    private final String programName;
    private final String dateTime;
    private final T body;

    public static <T> GenericResponseDTO<T> build(T body) {
        return GenericResponseDTO.<T>builder()
                .programName(Program.PROGRAM_NAME)
                .dateTime(DateUtils.getCurrentDateInResponseFormat())
                .body(body)
                .build();
    }


}

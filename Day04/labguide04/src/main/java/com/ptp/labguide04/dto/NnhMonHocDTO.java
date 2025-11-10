package com.ptp.labguide04.dto;

import jakarta.validation.constraints.*;

public record NnhMonHocDTO(
        @NotBlank(message = "Mã môn không được để trống")
        @Size(min = 2, max = 10, message = "Mã môn từ 2-10 ký tự")
        String nnhmamh,

        @NotBlank(message = "Tên môn không được để trống")
        String nnhtenmh,

        @Min(value = 45, message = "Số tiết tối thiểu 45")
        @Max(value = 75, message = "Số tiết tối đa 75")
        int nnhsotiet
) {}
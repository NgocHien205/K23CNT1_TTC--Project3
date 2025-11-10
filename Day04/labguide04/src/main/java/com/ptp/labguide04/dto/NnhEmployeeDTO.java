package com.ptp.labguide04.dto;

import jakarta.validation.constraints.*;

public record NnhEmployeeDTO(
        Long nnhid,

        @NotBlank(message = "Họ tên không được để trống")
        @Size(min = 3, max = 25, message = "Họ tên từ 3-25 ký tự")
        String nnhfullName,

        @NotBlank(message = "Giới tính không được để trống")
        String nnhgender,

        @Min(value = 18, message = "Tuổi tối thiểu 18")
        @Max(value = 60, message = "Tuổi tối đa 60")
        int nnhage,

        @Positive(message = "Lương phải lớn hơn 0")
        double nnhsalary
) {}
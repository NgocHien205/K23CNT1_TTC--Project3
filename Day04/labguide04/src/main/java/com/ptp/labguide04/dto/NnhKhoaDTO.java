package com.ptp.labguide04.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record NnhKhoaDTO(
        @NotBlank(message = "Mã khoa không được để trống")
        @Size(min = 2, max = 5, message = "Mã khoa từ 2-5 ký tự")
        String nnhmakh,

        @NotBlank(message = "Tên khoa không được để trống")
        String nnhtenkh
) {}
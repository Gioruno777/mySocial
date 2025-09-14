package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateUserNameRequestDTO {

    @NotBlank(message = "名稱不能為空")
    private String userName;
}

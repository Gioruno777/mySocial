package com.example.demo.dto;

import lombok.Data;

@Data
public class PaginationDTO{
    private Integer current_page;
    private Integer page_size;
    private Integer total_pages;
}

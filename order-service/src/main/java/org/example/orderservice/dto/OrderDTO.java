package org.example.orderservice.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderDTO implements Serializable {
    private Integer id;
    private String description;
}

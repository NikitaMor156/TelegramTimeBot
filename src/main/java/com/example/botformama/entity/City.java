package com.example.botformama.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class City implements Serializable {
    private String name;
    private int timezone;
}

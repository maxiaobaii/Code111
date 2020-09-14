package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassNmae: City
 * @Author: yddm
 * @DateTime: 2020/9/3 21:28
 * @Description: TODO
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class City implements Serializable {
    private String name;
    private String value;
}
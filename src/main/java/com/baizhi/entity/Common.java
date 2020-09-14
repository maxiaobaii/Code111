package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassNmae: Common
 * @Author: yddm
 * @DateTime: 2020/9/3 21:37
 * @Description: TODO
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Common {
    private String sex;
    private List<City> citys;
}

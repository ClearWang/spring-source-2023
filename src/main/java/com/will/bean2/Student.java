package com.will.bean2;

import com.will.annotation.MyAnnotationType;
import lombok.Data;

/**
 * test
 *
 * @author Will
 * @create 2023:04:04 19:51
 **/
@MyAnnotationType
@Data
public class Student {
    private String name = "mimi";
}

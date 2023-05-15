package com.jawad.Instagram.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostOutPut {

    private String postData;
    private Timestamp createdDate;
    private Timestamp updatedDate;
}

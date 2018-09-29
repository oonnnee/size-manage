package com.alitbit.sizeManage.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageChunk<T> {

    private List<T> content = new ArrayList<>();
    private long totalElements;
    private int totalPages;
    private int number;
    private int size;

}

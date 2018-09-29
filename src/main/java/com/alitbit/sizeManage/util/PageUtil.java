package com.alitbit.sizeManage.util;

import com.alitbit.sizeManage.dto.PageChunk;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class PageUtil {

    public static Pageable getPageable(Integer page, Integer size){
        page = page<0 ? 0:page;
        size = size<=0 ? 20:size;

        return PageRequest.of(page, size);
    }

    public static PageChunk getPageChunk(Page page){
        PageChunk pageChunk = new PageChunk();
        pageChunk.setContent(page.getContent());
        pageChunk.setTotalElements(page.getTotalElements());
        pageChunk.setNumber(page.getNumber());
        pageChunk.setSize(page.getSize());
        pageChunk.setTotalPages(page.getTotalPages());
        return pageChunk;
    }
}

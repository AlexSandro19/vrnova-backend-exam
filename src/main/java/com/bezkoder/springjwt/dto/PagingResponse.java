package com.bezkoder.springjwt.dto;


import com.bezkoder.springjwt.models.Course;

import java.util.List;

/**
 * DTO using for pagination
 */
public class PagingResponse {

    /**
     * entity count
     */
    private Long count;
    /**
     * page number, 0 indicate the first page.
     */
    private Long pageNumber;
    /**
     * size of page, 0 indicate infinite-sized.
     */
    private Long pageSize;
    /**
     * Offset from the of pagination.
     */
    private Long pageOffset;
    /**
     * the number total of pages.
     */
    private Long pageTotal;
    /**
     * elements of page.
     */
    private List<Course> elements;

    public PagingResponse(Long count, Long pageNumber, Long pageSize, Long pageOffset, Long pageTotal, List<Course> elements) {
        this.count = count;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.pageOffset = pageOffset;
        this.pageTotal = pageTotal;
        this.elements = elements;
    }

    public List<Course> getElements() {
        return elements;
    }

    public Long getCount() {
        return count;
    }

    public Long getPageNumber() {
        return pageNumber;
    }

    public Long getPageSize() {
        return pageSize;
    }

    public Long getPageOffset() {
        return pageOffset;
    }

    public Long getPageTotal() {
        return pageTotal;
    }
}


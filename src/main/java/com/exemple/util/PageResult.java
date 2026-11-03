package com.exemple.util;

import java.util.List;

public class PageResult<T> {
    private final List<T> contenu;
    private final int page;
    private final int size;
    private final long totalElements;
    private final int totalPages;

    public PageResult(List<T> contenu, int page, int size, long totalElements) {
        this.contenu = contenu;
        this.page = page;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = (int) Math.ceil((double) totalElements / size);
    }

    public boolean isFirst()       { return page <= 1; }
    public boolean isLast()        { return page >= totalPages; }
    public boolean hasNext()       { return page < totalPages; }
    public boolean hasPrevious()   { return page > 1; }

    public List<T> getContenu() {
        return contenu;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }
}

package io.codelex.flightplanner.dto;

import java.util.List;
import java.util.Objects;

public class PageResult<T> {
    private final int page;
    private final int totalItems;
    private final List<T> items;

    public PageResult(int page, int totalItems, List<T> items) {
        this.page = page;
        this.totalItems = totalItems;
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PageResult<?> that)) return false;
        return page == that.page && totalItems == that.totalItems && items.equals(that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(page, totalItems, items);
    }
}

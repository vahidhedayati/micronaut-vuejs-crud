package gateway.adaptors.models.implementation;

import javax.annotation.Nullable;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

public class SortingAndOrderArguments {
    @Nullable
    private String name;

    @Nullable
    @PositiveOrZero
    private Integer offset;

    @Nullable
    @Positive
    private Integer max;

    ////@Pattern(regexp = "id|name")
    //generic class used by Users which has own regex and backend has own regex
    @Nullable
    private String sort;

    @Pattern(regexp = "asc|ASC|desc|DESC")
    @Nullable
    private String order;

    public SortingAndOrderArguments() {

    }


    public Integer getOffset() {
        if(offset == null) {
            return 0;
        }
        return offset;
    }
    public void setOffset(@Nullable Integer offset) {
        this.offset = offset;
    }

    public Integer getMax() {
        if(max == null) {
            return 10;
        }
        return max;
    }
    public void setMax(@Nullable Integer max) {
        this.max = max;
    }


    public String getSort() {
        if(sort == null) {
            return "id";
        }
        return sort;
    }

    public void setSort(@Nullable String sort) {
        if (sort==null) {
            sort="asc";
        }

        this.sort = sort;
    }


    public String getName() {
        return name;
    }

    public void setName(@Nullable String name) {
        this.name = name;
    }

    public String getOrder() {
        if(order == null) {
            return "asc";
        }
        return order;
    }

    public void setOrder(@Nullable String order) {
        if (order==null) {
            order="id";
        }
        this.order = order;
    }

}
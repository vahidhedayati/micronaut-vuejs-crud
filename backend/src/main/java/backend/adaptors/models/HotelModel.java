package backend.adaptors.models;

import backend.domain.Hotel;
import com.sun.istack.Nullable;

import java.util.List;
import java.util.Optional;

public class HotelModel {

   @Nullable
    private Optional<List<Hotel>> instanceList;

    @Nullable
    private Long instanceTotal;

    @Nullable
    private Long numberOfPages;



    public Long getInstanceTotal() {
        return instanceTotal;
    }

    public void setInstanceTotal(Long instanceTotal) {
        this.instanceTotal = instanceTotal;
    }

    public Long getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Long numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public Optional<List<Hotel>> getInstanceList() {
        return instanceList;
    }

    public void setInstanceList(Optional<List<Hotel>> instanceList) {
        this.instanceList = instanceList;
    }



}

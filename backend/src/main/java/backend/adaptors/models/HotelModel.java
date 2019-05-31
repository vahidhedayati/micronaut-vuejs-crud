package backend.adaptors.models;

import backend.domain.Hotel;
import com.sun.istack.Nullable;

import java.util.List;

public class HotelModel {



   @Nullable
    private List<Hotel> instanceList;
    @Nullable
    private Long instanceTotal;

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

    @Nullable
    private Long numberOfPages;



    public List<Hotel> getInstanceList() {
        return instanceList;
    }

    public void setInstanceList(List<Hotel> instanceList) {
        this.instanceList = instanceList;
    }




}

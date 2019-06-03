package gateway.adaptors.models;

import com.sun.istack.Nullable;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public class HotelModel {

   @Nullable
    private Optional<List<Hotel>> instanceList;

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



    public Optional<List<Hotel>> getInstanceList() {
        return instanceList;
    }

    public void setInstanceList(Optional<List<Hotel>> instanceList) {
        this.instanceList = instanceList;
    }

}

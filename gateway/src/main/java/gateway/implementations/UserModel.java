package gateway.implementations;

import com.sun.istack.Nullable;
import gateway.domain.User;

import java.util.List;

public class UserModel {

    @Nullable
    private List<User> instanceList;

    @Nullable
    private Long instanceTotal;

    @Nullable
    private Long numberOfPages;

    public List<User> getInstanceList() {
        return instanceList;
    }

    public void setInstanceList(List<User> instanceList) {
        this.instanceList = instanceList;
    }

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


}

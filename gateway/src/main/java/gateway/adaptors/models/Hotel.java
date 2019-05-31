package gateway.adaptors.models;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@Entity
//@Table(name = "hotel")
//@NoArgsConstructor
//@Getter
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "lastUpdated")
    private Date lastUpdated;
    //@ElementCollection
    //@CollectionTable(name = "hotel_rooms", joinColumns = @JoinColumn(name = "hotel_id"))
    //private List<HotelRooms> rooms;

    @Column(name = "hotelRooms")
    //@OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<HotelRooms> hotelRooms;

    public Hotel() {

    }

    public Hotel(String code, String name) {
        this.code = code;
        this.name=name;
        this.hotelRooms = new ArrayList<>();
    }

    public Date getLastUpdated() {
        return this.lastUpdated;
    }


    public String getCode() {
    	return this.code;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public List<HotelRooms> getHotelRooms() {
        return hotelRooms;
    }


    public HotelRoomList rooms() {
        return new HotelRoomList(hotelRooms);
    }
    @Override
    public String toString() {
        return "hotel{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", rooms='" + getHotelRooms() + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
    
}

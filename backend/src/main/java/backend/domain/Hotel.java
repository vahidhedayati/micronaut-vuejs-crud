package backend.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "hotel")
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

    
    //@ElementCollection
    //@CollectionTable(name = "hotel_rooms", joinColumns = @JoinColumn(name = "hotel_id"))
    //private List<HotelRooms> rooms;

    @Column(name = "hotelRooms")
    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<HotelRooms> hotelRooms;

    @Column(name = "lastUpdated")
    private Date lastUpdated;



    public Hotel() {

    }

    public Hotel(String code,String name) {
        this.code = code;
        this.name=name;
        this.hotelRooms = new ArrayList<>();
        this.lastUpdated=new Date();
    }

    public Hotel(String code,String name, Date date) {
        this.code = code;
        this.name=name;
        this.hotelRooms = new ArrayList<>();
        this.lastUpdated=date;
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

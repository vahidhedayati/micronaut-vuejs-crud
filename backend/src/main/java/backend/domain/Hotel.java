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

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;


    @Column(name = "update_user_id")
    private Long updateUserId;

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
        this.updateUserId = 1L;
        this.lastUpdated=new Date();
    }

    public Hotel(String code,String name, Date date) {
        this.code = code;
        this.name=name;
        this.hotelRooms = new ArrayList<>();
        this.updateUserId = 1L;
        this.lastUpdated=date;
    }

    public Hotel(String code, String name, String phone, String email) {
        this.code = code;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.hotelRooms = new ArrayList<>();
        this.updateUserId = 1L;
        this.lastUpdated=new Date();
    }

    public Hotel(String code, String name, String phone, String email, Date lastUpdated) {
        this.code = code;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.hotelRooms = new ArrayList<>();
        this.updateUserId = 1L;
        this.lastUpdated = lastUpdated;
    }

    public Hotel(String code, String name, String phone, String email, Long updateUserId) {
        this.code = code;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.updateUserId = updateUserId;
        this.hotelRooms = new ArrayList<>();
        this.lastUpdated = new Date();
    }

    public Hotel(String code, String name, String phone, String email, Long updateUserId, Date lastUpdated) {
        this.code = code;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.hotelRooms = new ArrayList<>();
        this.updateUserId = updateUserId;
        this.lastUpdated = lastUpdated;
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


    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    @Override
    public String toString() {
        return "hotel{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", update_user_id='" + updateUserId + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", rooms='" + getHotelRooms() + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
    
}

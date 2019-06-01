package gateway.adaptors.models;


import io.micronaut.validation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Hotel {

    private Long id;


    private String code;


    private String name;


    private Date lastUpdated;

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

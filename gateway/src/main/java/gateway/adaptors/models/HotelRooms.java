package gateway.adaptors.models;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

/*
@Embeddable
@NoArgsConstructor
@Getter
 */
//@Entity
//@DiscriminatorColumn(name = "type")
//@Table(name = "hotel_rooms")
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@NoArgsConstructor
//@Getter
public class HotelRooms {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    protected Hotel hotel;

    
    @Column(name = "room_type")
    private String roomType;


    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "stock_total")
    private Long stockTotal;
    public HotelRooms() {
    	
    }

    public void setRoomType(String roomType) {
        this.roomType=roomType;
    }

    public void setPrice(String price) {
        this.price=new BigDecimal(price);
    }
    public void setStockTotal(String stockTotal) {
        this.stockTotal=Long.valueOf(stockTotal);
    }

    public String getRoomType() {
        return roomType;
    }

    public BigDecimal getPrice() {
        return price;
    }
    public Long getStockTotal() {
        return stockTotal;
    }


    public HotelRooms(Hotel hotel, String roomType,BigDecimal  price,Long stockTotal ) {
    	this.hotel = hotel;
    	this.roomType = roomType;
    	this.price=price;
    	this.stockTotal=stockTotal;
    }
}

package backend.domain;

import java.math.BigDecimal;
import java.util.List;

public class HotelRoomList {
	
	  private List<HotelRooms> hotelRooms;

	  HotelRoomList(List<HotelRooms> hotelRooms) {
	        this.hotelRooms = hotelRooms;
	  }
	  
	  public void addBaseRoomTypes(Hotel hotel, String roomType, BigDecimal price, Long total) {
		  HotelRooms room =  new HotelRooms(hotel,roomType, price, total);
	      hotelRooms.add(room);
	  }

}

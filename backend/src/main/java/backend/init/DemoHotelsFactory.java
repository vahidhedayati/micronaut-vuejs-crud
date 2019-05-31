package backend.init;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import backend.domain.Hotel;

public class DemoHotelsFactory {

	
	static List<Hotel> defaultHotels() {
		List<Hotel> hotels = new ArrayList<Hotel>();
		hotels.add(addHotel("HILL","Hilton - London Bridge"));
		hotels.add(addHotel("MARL","Mariott International - London Bridge"));
		hotels.add(addHotel("STWL","Starwood Hotels - London Bridge"));
		hotels.add(addHotel("ACGL","Accor Group - London Bridge"));
		hotels.add(addHotel("CHIL","Choice Hotels - London Bridge"));
		hotels.add(addHotel("BEWL","Best Western - London Bridge"));
		hotels.add(addHotel("CARL","Carlson - London Bridge"));
		
		
		hotels.add(addHotel("HILI","Hilton - Islington"));
		hotels.add(addHotel("MARI","Mariott International - Islington"));
		hotels.add(addHotel("STWI","Starwood Hotels - Islington"));
		hotels.add(addHotel("ACGI","Accor Group - Islington"));
		hotels.add(addHotel("CHII","Choice Hotels - Islington"));
		hotels.add(addHotel("BEWI","Best Western - Islington"));
		hotels.add(addHotel("CARI","Carlson - Islington"));
		
		hotels.add(addHotel("BEWK","Best Western - Kensington"));
		hotels.add(addHotel("STWK","Starwood Hotels - Kensington"));
		hotels.add(addHotel("ACGK","Accor Group - Kensington"));
		hotels.add(addHotel("CHIK","Choice Hotels - Kensington"));
		hotels.add(addHotel("BEWK","Best Western - Kensington"));
		hotels.add(addHotel("CARK","Carlson - Kensington"));
		
		
		hotels.add(addHotel("BEWW","Best Western - Waterloo"));
		hotels.add(addHotel("STWW","Starwood Hotels - Waterloo"));
		hotels.add(addHotel("ACGW","Accor Group - Waterloo"));
		hotels.add(addHotel("CHIW","Choice Hotels - Waterloo"));
		hotels.add(addHotel("BEWW","Best Western - Waterloo"));
		hotels.add(addHotel("CARW","Carlson - Waterloo"));
		
		
		return hotels;
    }
	
	
	static Hotel addHotel(String code,String name) {
		Hotel hotel = addHotel(new Hotel(code,name ,
				Date.from(LocalDate.parse( "2019-01-10" ).plusDays( 10 ).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant())));
		return hotel;
	}

	static Hotel addHotel(Hotel hotel) {
		//Some generic values of room types for each hotel added
		hotel.rooms().addBaseRoomTypes(hotel,"SING",new BigDecimal("45.00"), 200L);
		hotel.rooms().addBaseRoomTypes(hotel,"DOUB",new BigDecimal("65.00"), 200L);
		hotel.rooms().addBaseRoomTypes(hotel,"TWIN",new BigDecimal("65.00"), 200L);
		hotel.rooms().addBaseRoomTypes(hotel,"TRIP",new BigDecimal("85.00"), 200L);
		hotel.rooms().addBaseRoomTypes( hotel,"FAM",new BigDecimal("95.00"), 200L);
		return hotel;
	}

	
}

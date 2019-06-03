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
		hotels.add(addHotel("HILL","Hilton - London Bridge","aa@aa.com","+44-111111111111"));
		hotels.add(addHotel("MARL","Mariott International - London Bridge","bb@aa.com","+44-22222222222222"));
		hotels.add(addHotel("STWL","Starwood Hotels - London Bridge","cc@aa.com","+44-3333333333333"));
		hotels.add(addHotel("ACGL","Accor Group - London Bridge","dd@aa.com","+44-44444444444444444"));
		hotels.add(addHotel("CHIL","Choice Hotels - London Bridge","ee@aa.com","+44-5555555555555"));
		hotels.add(addHotel("BEWL","Best Western - London Bridge","ff@aa.com","+44-6666666666"));
		hotels.add(addHotel("CARL","Carlson - London Bridge","hh@aa.com","+44-777777777777777"));
		
		
		hotels.add(addHotel("HILI","Hilton - Islington","ii@aa.com","+44-88888888888888888"));
		hotels.add(addHotel("MARI","Mariott International - Islington","jj@aa.com","+44-99999999999999"));
		hotels.add(addHotel("STWI","Starwood Hotels - Islington","kk@aa.com","+45-1111111111"));
		hotels.add(addHotel("ACGI","Accor Group - Islington","ll@aa.com","+45-22222222"));
		hotels.add(addHotel("CHII","Choice Hotels - Islington","mm@aa.com","+45-33333333333333"));
		hotels.add(addHotel("BEWI","Best Western - Islington","nn@aa.com","+45-44444444444444444"));
		hotels.add(addHotel("CARI","Carlson - Islington","oo@aa.com","+45-5555555555555555"));
		
		hotels.add(addHotel("BEWK","Best Western - Kensington","pp@aa.com","+45-666666666666"));
		hotels.add(addHotel("STWK","Starwood Hotels - Kensington","qq@aa.com","+45-77777777777777777"));
		hotels.add(addHotel("ACGK","Accor Group - Kensington","rr@aa.com","+45-88888888888888888888"));
		hotels.add(addHotel("CHIK","Choice Hotels - Kensington","ss@aa.com","+45-99999999999"));
		hotels.add(addHotel("BEWK","Best Western - Kensington","tt@aa.com","+46-11111111111111"));
		hotels.add(addHotel("CARK","Carlson - Kensington","uu@aa.com","+46-2222222222222222"));
		
		
		hotels.add(addHotel("BEWW","Best Western - Waterloo","vv@aa.com","+46-333333333"));
		hotels.add(addHotel("STWW","Starwood Hotels - Waterloo","ww@aa.com","+46-4444444444444"));
		hotels.add(addHotel("ACGW","Accor Group - Waterloo","xx@aa.com","+46-55555555555555555"));
		hotels.add(addHotel("CHIW","Choice Hotels - Waterloo","yy@aa.com","+46-66666666666666666"));
		hotels.add(addHotel("BEWW","Best Western - Waterloo","zz@aa.com","+46-7777777777777777777"));
		hotels.add(addHotel("CARW","Carlson - Waterloo","ab@aa.com","+46-999999999999999999"));
		
		
		return hotels;
    }
	
	
	static Hotel addHotel(String code,String name, String email, String phone) {

		Hotel hotel = addHotel(new Hotel(code,name ,phone,email,
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

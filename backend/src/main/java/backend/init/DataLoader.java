package backend.init;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import backend.adaptors.db.HotelDb;
import backend.domain.Hotel;
import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.runtime.server.event.ServerStartupEvent;
import io.micronaut.spring.tx.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Singleton
@RequiredArgsConstructor
public class DataLoader  implements ApplicationEventListener<ServerStartupEvent> {

	@Inject
	private HotelDb hotelDb;
	 
	@Transactional
	@Override
	public void onApplicationEvent(ServerStartupEvent event) {
		if (!hotelDb.findByCode("HILL").isPresent()) {
		List<Hotel> hotels = DemoHotelsFactory.defaultHotels();
		hotelDb.add(hotels);
		//for ( final Hotel hotel : hotels ) {
			//System.out.println(" working on "+hotel.getCode());
			//hotelDb.add(hotel);

            //Hotel hotel1 =  DemoHotelsFactory.addHotel(hotel);
           // hotelDb.add(hotel1);

		//}
			//hotelDb.add(hotels);
        }

	}
}

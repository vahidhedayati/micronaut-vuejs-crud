package backend.domain.interfaces;

import backend.adaptors.models.HotelModel;
import backend.domain.Hotel;
import backend.implementation.SortingAndOrderArguments;
import io.reactivex.Maybe;
import io.reactivex.Single;
import org.hibernate.mapping.Map;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface Hotels {

	Optional<Hotel> findById(@NotNull Long id);
	void deleteById(@NotNull Long id);
	int update(@NotNull Long id, @NotBlank String name, String code);

	Optional<HotelModel> findAll(@NotNull SortingAndOrderArguments args);


	Optional<Hotel> findByCode(String code);

	//Single<List<Hotel>> listAll(Map input);

	//Maybe<Hotel> reolveCode(String code);

	Hotel getByCode(String code);
	
	void add(Hotel hotel);

	Hotel save(@NotBlank String code, @NotBlank String name);
	
	void add(List<Hotel> hotel);
}

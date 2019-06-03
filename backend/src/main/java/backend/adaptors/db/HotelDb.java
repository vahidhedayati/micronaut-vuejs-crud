package backend.adaptors.db;

import backend.adaptors.models.HotelModel;
import backend.domain.Hotel;
import backend.domain.interfaces.Hotels;
import backend.implementation.ApplicationConfiguration;
import backend.implementation.SortingAndOrderArguments;
import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;
import io.reactivex.Single;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Singleton
public class HotelDb implements Hotels {



    @PersistenceContext
    private EntityManager entityManager;
    private final ApplicationConfiguration applicationConfiguration;



    public HotelDb(@CurrentSession EntityManager entityManager,
                     ApplicationConfiguration applicationConfiguration) {
        this.entityManager = entityManager;
        this.applicationConfiguration = applicationConfiguration;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Hotel> findById(@NotNull Long id) {
        return Optional.ofNullable(entityManager.find(Hotel.class, id));
    }

    @Override
    @Transactional
    public Hotel save(@NotBlank String code, @NotBlank String name) {
        Hotel hotel = new Hotel(code,name);
        entityManager.persist(hotel);
        return hotel;
    }


    @Override
    @Transactional
    public void deleteById(@NotNull Long id) {
        findById(id).ifPresent(hotel -> entityManager.remove(hotel));
    }

    private final static List<String> VALID_PROPERTY_NAMES = Arrays.asList("id", "name", "code", "lastUpdated", "phone","email");

    @Transactional(readOnly = true)
    public Optional<HotelModel> findAll(@NotNull SortingAndOrderArguments args) {
        //SELECT new map (h.code as code, h.name as name, h.id as id, h.hotelRooms as hotelRooms)

        String countQueryString= "select count(h) FROM Hotel as  h ";
        String qlString = "FROM Hotel as  h ";
        if (args.getName().isPresent()) {
            qlString += " where lower(h.name) like (:name) ";
            countQueryString += " where lower(h.name) like (:name) ";
        }
        if (args.getOrder().isPresent() && args.getSort().isPresent() && VALID_PROPERTY_NAMES.contains(args.getSort().get())) {
            qlString += " ORDER BY h." + args.getSort().get() + " " + args.getOrder().get().toLowerCase();
        }
        //System.out.println("Query "+qlString);
        TypedQuery<Hotel> query;
        TypedQuery<Long> countQuery;
        //Long countQuery=0L;
        if (args.getName().isPresent()) {
            query=entityManager.createQuery(qlString, Hotel.class).setParameter("name",'%'+args.getName().get().toLowerCase()+'%');
            countQuery=entityManager.createQuery(countQueryString, Long.class).setParameter("name",'%'+args.getName().get().toLowerCase()+'%');
        } else {
            query=entityManager.createQuery(qlString, Hotel.class);
            countQuery=entityManager.createQuery(countQueryString, Long.class);
        }

        query.setMaxResults(args.getMax().orElseGet(applicationConfiguration::getMax));
        args.getOffset().ifPresent(query::setFirstResult);
        //countQuery.setMaxResults(1);
        HotelModel model = new HotelModel();
        //System.out.println(" "+(countQuery.getFirstResult())+"1 sss ssss");
        model.setInstanceList(Optional.of(query.getResultList()));//.flatMap(hotel -> {}));
        //System.out.println(" "+(countQuery.getSingleResult())+"0 sss ssss");
        model.setInstanceTotal(countQuery.getSingleResult());

        model.setNumberOfPages(model.getInstanceTotal()/args.getMax().get());
        //System.out.println(" "+model.getInstanceTotal()+" "+model.getNumberOfPages()+" "+model.getInstanceList());
        return Optional.of(model); //Single.just(model);
    }

    @Override
    @Transactional
    public int update(@NotNull Long id, @NotBlank String name, @NotBlank String code,@NotBlank  String phone,@NotBlank String email) {
        return entityManager.createQuery("UPDATE Hotel h  SET name = :name, code = :code, email = :email, phone = :phone  where id = :id")
                .setParameter("name", name)
                .setParameter("id", id)
                .setParameter("code", code)
                .setParameter("phone", phone)
                .setParameter("email", email)
                .executeUpdate();
    }



    // public Single<List<Hotel>> listAll(Map input) {

    // }


    @Transactional
    @Override
    public Optional<Hotel> findByCode(String code) {
        return entityManager
                .createQuery("from Hotel h where h.code = :code", Hotel.class)
                .setParameter("code", code)
                .getResultStream()
                .findFirst();
    }

    @Override
    public Hotel getByCode(String code) {
        return findByCode(code).orElseThrow(() -> new RuntimeException("Hotel not found"));
    }


    @Transactional
    @Override
    public void add(Hotel hotel) {
        entityManager.persist(hotel);
    }

    @Transactional
    @Override
    public void add(List<Hotel> hotels) {
        for ( final Hotel hotel : hotels ) {
            entityManager.persist(hotel);
        }
    }
}

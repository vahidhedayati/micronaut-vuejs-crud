package userbase.implementations;

import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.spring.tx.annotation.Transactional;
import userbase.adaptors.models.SortingAndOrderArguments;
import userbase.adaptors.models.UserModel;
import userbase.domain.User;
import userbase.interfaces.Users;

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
public class UsersImpl implements Users {

    @PersistenceContext
    private EntityManager entityManager;
    private final MyApplicationConfiguration myApplicationConfiguration;


    public UsersImpl(@CurrentSession EntityManager entityManager,
                     MyApplicationConfiguration myApplicationConfiguration) {
        this.entityManager = entityManager;
        this.myApplicationConfiguration = myApplicationConfiguration;
    }

    private final static List<String> VALID_PROPERTY_NAMES = Arrays.asList("id", "username", "firstname", "surname");


    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(@NotNull Long id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    @Override
    @Transactional
    public void deleteById(@NotNull Long id) {
        findById(id).ifPresent(user -> entityManager.remove(user));
    }

    @Transactional
    @Override
    public int update(@NotNull Long id, @NotBlank String username, @NotBlank String password, @NotBlank String firstname, @NotBlank String surname) {
        return entityManager.createQuery("UPDATE User h  SET username = :username, password = :password, firstname=:firstname, surname=:surname where id = :id")
                .setParameter("username", username)
                .setParameter("id", id)
                .setParameter("password", password)
                .setParameter("firstname", firstname)
                .setParameter("surname", surname)
                .executeUpdate();
    }

    @Transactional(readOnly = true)
    public Optional<UserModel> findAll(@NotNull SortingAndOrderArguments args) {

        String countQueryString= "select count(h) FROM User as  h ";
        String qlString = "FROM User as  h ";
        if (args.getName().isPresent()) {
            qlString += " where  ( lower(h.username) like (:name)  or  lower(h.firstname) like (:name)   lower(h.surname) like (:name) )  ";
            countQueryString += " where ( lower(h.username) like (:name)  or  lower(h.firstname) like (:name)   lower(h.surname) like (:name) ) ";
        }
        if (args.getOrder().isPresent() && args.getSort().isPresent() && VALID_PROPERTY_NAMES.contains(args.getSort().get())) {
            qlString += " ORDER BY h." + args.getSort().get() + " " + args.getOrder().get().toLowerCase();
        }
        //System.out.println("Query "+qlString);
        TypedQuery<User> query;
        TypedQuery<Long> countQuery;
        //Long countQuery=0L;
        if (args.getName().isPresent()) {
            query=entityManager.createQuery(qlString, User.class).setParameter("name",'%'+args.getName().get().toLowerCase()+'%');
            countQuery=entityManager.createQuery(countQueryString, Long.class).setParameter("name",'%'+args.getName().get().toLowerCase()+'%');
        } else {
            query=entityManager.createQuery(qlString, User.class);
            countQuery=entityManager.createQuery(countQueryString, Long.class);
        }

        query.setMaxResults(args.getMax().orElseGet(myApplicationConfiguration::getMax));
        args.getOffset().ifPresent(query::setFirstResult);
        UserModel model = new UserModel();
        model.setInstanceList(query.getResultList());
        model.setInstanceTotal(countQuery.getSingleResult());

        model.setNumberOfPages(model.getInstanceTotal()/args.getMax().get());
        //System.out.println(" "+model.getInstanceTotal()+" "+model.getNumberOfPages()+" "+model.getInstanceList());
        return Optional.of(model); //Single.just(model);
    }

    @Transactional
    @Override
    public Optional<User> findByUsername(String username) {
        System.out.print("looking for "+username);
        return entityManager
                .createQuery("from User h where h.username = :username", User.class)
                .setParameter("username", username)
                .getResultStream()
                .findFirst();
    }

    @Override
    public User getByUsername(String username) {
        return findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Transactional
    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Transactional
    @Override
    public User save(@NotBlank String username, @NotBlank String password, @NotBlank String firstname, @NotBlank String surname) {
        User user = new User(username,password,firstname,surname);
        entityManager.persist(user);
        return user;
    }

    @Transactional
    @Override
    public void add(List<User> users) {

        for ( final User user : users ) {
            System.out.println(" Adding user: "+user.getFirstname());
            entityManager.persist(user);
        }
    }
}

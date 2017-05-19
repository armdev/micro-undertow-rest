package com.backend.web.facades;

import com.backend.entities.Contact;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class ContactRepository {

    private final EntityManagerFactory emf;


    public ContactRepository() {
        emf = Persistence.createEntityManagerFactory("primary");
    }
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Contact findById(Long id) {
        EntityManager em = getEntityManager();
        System.out.println("Id is " + id);
        // System.out.println("EM &&&&&&&& " + em.toString());

        return em.find(Contact.class, id);
    }

    public List<Contact> findAll() {
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Contact> criteria = cb.createQuery(Contact.class);
        Root<Contact> contact = criteria.from(Contact.class);

        criteria.select(contact).orderBy(cb.desc(contact.get("id")));
        return em.createQuery(criteria).getResultList();
    }

    public Contact findByEmail(String email) {
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Contact> criteria = cb.createQuery(Contact.class);
        Root<Contact> contact = criteria.from(Contact.class);
        // Swap criteria statements if you would like to try out type-safe criteria queries, a new
        // feature in JPA 2.0
        // criteria.select(member).where(cb.equal(member.get(Member_.email), email));
        criteria.select(contact).where(cb.equal(contact.get("email"), email));
        return em.createQuery(criteria).getSingleResult();
    }
//
//    @Produces
//    @PersistenceContext
//    public EntityManager entityManager() {
//        return em;
//    }
}

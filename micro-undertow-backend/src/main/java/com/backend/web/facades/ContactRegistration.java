package com.backend.web.facades;

import com.backend.entities.Contact;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


@Stateless
public class ContactRegistration {

    private final EntityManagerFactory emf;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public ContactRegistration() {
        emf = Persistence.createEntityManagerFactory("primary");
    }

    @Inject
    private Event<Contact> memberEventSrc;

    public void register(Contact contact) throws Exception {
        EntityManager em = getEntityManager();
        System.out.println("Registering " + contact.getFirstName());
        em.persist(contact);
        memberEventSrc.fire(contact);
    }

}

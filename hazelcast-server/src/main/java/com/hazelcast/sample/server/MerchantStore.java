package com.hazelcast.sample.server;

import com.hazelcast.core.MapStore;
import com.hazelcast.logging.ILogger;
import com.hazelcast.logging.Logger;
import com.hazelcast.sample.client.Merchant;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MerchantStore implements MapStore<Integer, Merchant> {

    private static ILogger log = Logger.getLogger(MerchantStore.class);

    private EntityManagerFactory emf;

    public MerchantStore(){
        log.info("NEW MAP STORE");
        emf = Persistence.createEntityManagerFactory( "com.hazelcast.sample" );
    }

    @Override
    public void store(Integer integer, Merchant merchant) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(merchant);
            em.getTransaction().commit();
        } catch(Exception x){
            em.getTransaction().rollback();
        }
    }

    @Override
    public void storeAll(Map<Integer, Merchant> map) {
        log.info("STORE ALL");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            for(Merchant m: map.values()){
                em.persist(m);
            }
            em.getTransaction().commit();
        } catch(Exception x){
            em.getTransaction().rollback();
        }
    }

    @Override
    public void delete(Integer id) {
        log.info("DELETE");
        EntityManager em = emf.createEntityManager();
        Merchant m = em.find(Merchant.class,id );
        if (m != null){
            em.getTransaction().begin();
            try {
                em.remove(m);
            } catch(Exception x){
                em.getTransaction().rollback();
            }
        }
    }

    @Override
    public void deleteAll(Collection<Integer> ids) {
        log.info("DELETE ALL");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            for(Integer id: ids){
                Merchant m = em.find(Merchant.class,id);
                if (m != null)  em.remove(m);
            }
            em.getTransaction().commit();
        } catch(Exception x){
            em.getTransaction().rollback();
        }
    }

    @Override
    public Merchant load(Integer id) {
        log.info("LOAD");
        EntityManager em = emf.createEntityManager();
        Merchant m = em.find(Merchant.class,id );
        return m;
    }

    @Override
    public Map<Integer, Merchant> loadAll(Collection<Integer> ids) {
        log.info("LOAD ALL");
        HashMap<Integer,Merchant> result = new HashMap<>(ids.size());
        EntityManager em = emf.createEntityManager();
        for(Integer id: ids){
            Merchant m = em.find(Merchant.class,id);
            if (m != null) result.put(m.getMerchantId(), m);
        }

        return result;
    }

    @Override
    public Iterable<Integer> loadAllKeys() {
        return null;
    }
}

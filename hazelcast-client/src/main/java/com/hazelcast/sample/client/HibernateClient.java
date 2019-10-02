package com.hazelcast.sample.client;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateClient {
    public static void main(String []args){
        EntityManagerFactory emf  = Persistence.createEntityManagerFactory( "com.hazelcast.sample" );


    }
}

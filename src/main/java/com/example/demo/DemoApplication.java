package com.example.demo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Member;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try{
			// 영속
			Member findMember1=em.find(Member.class,101L);
			Member findMember2=em.find(Member.class,101L);
			tx.commit();
		} catch (Exception e){
			tx.rollback();
		} finally {
			em.close();

		}
		emf.close();


		Member member = new Member() {
			@Override
			public Class<?> getDeclaringClass() {
				return null;
			}

			@Override
			public String getName() {
				return "";
			}

			@Override
			public int getModifiers() {
				return 0;
			}

			@Override
			public boolean isSynthetic() {
				return false;
			}
		};
	}

}

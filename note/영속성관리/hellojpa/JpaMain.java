package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        // EntityManager 는 애플리케이션 로딩 시점에 딱 한번 만 생성해야한다.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // 트랜잭션 단위, 일괄적인 단위 작업을 할 때 마다 EntityManagerFactory 에서 생성하고 소명한다.
        EntityManager em = emf.createEntityManager();

        // 트랜잭션 불러오기 및 시작
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {

            Member member = em.find(Member.class, 200L);
            member.setName("ZZZZ");

            em.detach(member);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
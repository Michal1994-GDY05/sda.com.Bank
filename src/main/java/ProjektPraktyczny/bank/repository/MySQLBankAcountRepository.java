package ProjektPraktyczny.bank.repository;

import ProjektPraktyczny.bank.model.BankAccount;
import ProjektPraktyczny.bank.request.CreateBankAccountRequest;
import lombok.NonNull;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

public class MySQLBankAcountRepository implements BankAccountRepository {
    private List<BankAccount> bankAccounts;

    @Override
    public boolean existsByPesel(@NonNull String pesel) {
        SessionFactory sessionFactory = SesionFactoryBuild.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            BankAccount bankAccountQuery = session.createQuery("FROM BankAccount AS h WHERE h.pesel = :pesel", BankAccount.class)
                    .setParameter("pesel", pesel)
                    .getSingleResult();
            System.out.println(bankAccountQuery);
        } catch (NoResultException e) {
            System.out.println("Nie ma takiego klienta.");
        }

        transaction.commit();
        session.close();

        return false;
    }

    @Override
    public BankAccount create(CreateBankAccountRequest request) {
        SessionFactory sessionFactory = SesionFactoryBuild.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Random random = new Random();
        Long accountNumber = random.nextLong();
        BankAccount bankAccount = new BankAccount(request.getPesel(), request.getInitValue(), accountNumber.toString());
        session.persist(bankAccount);


        transaction.commit();
        session.close();
        return bankAccount;
    }

    @Override
    public List<BankAccount> findAll() {
        SessionFactory sessionFactory = SesionFactoryBuild.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query<BankAccount> queryThree = session.createQuery("FROM BankAccount", BankAccount.class);
        List<BankAccount> resultList = queryThree.getResultList();
        resultList.forEach(System.out::println);


        transaction.commit();
        session.close();

        return resultList;
    }

    @Override
    public BankAccount delete(@NonNull String pesel) {

        SessionFactory sessionFactory = SesionFactoryBuild.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();


        session.createQuery("DELETE FROM BankAccount AS e WHERE e.pesel = :pesel")
                .setParameter("pesel", pesel)
                .executeUpdate();

        transaction.commit();
        session.close();

        return null;
    }

    @Override
    public BankAccount getMoney(String pesel, BigDecimal money) {

        SessionFactory sessionFactory = SesionFactoryBuild.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        BankAccount bankAccount = session.createQuery("FROM BankAccount AS h WHERE h.pesel = :pesel", BankAccount.class)
                .setParameter("pesel", pesel)
                .getSingleResult();

        bankAccount.getValue().add(money);


        System.out.println("Podaj ile chcesz wypłacić. Twój stan konta: " + bankAccount.getValue());

        bankAccount.setValue(bankAccount.getValue().add(money));
        transaction.commit();
        session.close();

        return null;
    }

    @Override
    public BankAccount withdrawMoney(String pesel, BigDecimal howMuchwithdrawMoney) {

        SessionFactory sessionFactory = SesionFactoryBuild.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        BankAccount bankAccount = session.createQuery("FROM BankAccount AS h WHERE h.pesel = :pesel", BankAccount.class)
                .setParameter("pesel", pesel)
                .getSingleResult();


        int concurrentValueBnak = bankAccount.getValue().compareTo(howMuchwithdrawMoney);

        if (concurrentValueBnak < 0){
            System.out.println("Masz za mało na koncie");
        }
        else if (concurrentValueBnak >= 0){
            bankAccount.setValue(bankAccount.getValue().subtract(howMuchwithdrawMoney));
        }

        transaction.commit();
        session.close();
        return null;
    }
}

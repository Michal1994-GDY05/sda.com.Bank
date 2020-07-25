package ProjektPraktyczny.bank.repository;

import ProjektPraktyczny.bank.model.BankAccount;
import ProjektPraktyczny.bank.request.CreateBankAccountRequest;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

//@NoArgsConstructor(access = AccessLevel.PRIVATE) -> tworzy prywatny konstruktor
public class InMemoryBankAccountRepository implements BankAccountRepository {

    private static InMemoryBankAccountRepository repository = null;


    public static InMemoryBankAccountRepository getInstance() {
        if (repository == null)
            repository = new InMemoryBankAccountRepository();

        return repository;
    }

    private List<BankAccount> bankAccounts;

    private InMemoryBankAccountRepository() {
        bankAccounts = new ArrayList<>();
    }

    @Override
    public boolean existsByPesel(@NonNull String pesel) {
        return bankAccounts
                .stream()
                .anyMatch(b -> b.getPesel().equalsIgnoreCase(pesel));
    }

    @Override
    public BankAccount create(CreateBankAccountRequest request) {
        Random random = new Random();
        Long accountNumber = random.nextLong();

        BankAccount bankAccount = new BankAccount(request.getPesel(), request.getInitValue(), accountNumber.toString());
        bankAccounts.add(bankAccount);
        return bankAccount;
    }

    @Override
    public List<BankAccount> findAll() {
        return Collections.unmodifiableList(bankAccounts);
    }

    @Override
    public BankAccount delete(@NonNull String pesel) {
        return null;
    }

    @Override
    public BankAccount getMoney(String pesel, BigDecimal howMuchMoney) {
        return null;
    }

    @Override
    public BankAccount withdrawMoney(String pesel, BigDecimal howMuchMoney) {
        return null;
    }


}

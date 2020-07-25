package ProjektPraktyczny.bank.repository;

import ProjektPraktyczny.bank.model.BankAccount;
import ProjektPraktyczny.bank.request.CreateBankAccountRequest;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.List;

public interface BankAccountRepository {

    boolean existsByPesel(@NonNull String pesel);

    BankAccount create(CreateBankAccountRequest request);

    List<BankAccount> findAll();

    BankAccount delete(@NonNull String pesel);

    BankAccount getMoney(String pesel, BigDecimal howMuchMoney);
    BankAccount withdrawMoney(String pesel, BigDecimal howMuchMoney);




}

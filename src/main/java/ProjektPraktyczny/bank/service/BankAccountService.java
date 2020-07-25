package ProjektPraktyczny.bank.service;

import ProjektPraktyczny.bank.model.BankAccount;
import ProjektPraktyczny.bank.repository.BankAccountRepository;
import ProjektPraktyczny.bank.request.CreateBankAccountRequest;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class BankAccountService {

    private BankAccountRepository repository;


    public Optional<BankAccount> createBankAccount(CreateBankAccountRequest request) {

        boolean exists = repository.existsByPesel(request.getPesel());
        if (exists) {
            return Optional.empty();
        }

        return Optional.of(repository.create(request));
    }

    public List<BankAccount> findAll() {

        return findAll();
    }

}

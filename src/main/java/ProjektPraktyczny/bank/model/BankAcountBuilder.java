package Projekt_praktyczny.bank.model;

import java.math.BigDecimal;

public interface BankAcountBuilder {

    void setPesel(String pesel);

    void setValue(BigDecimal value);

    void setAccountNumber(String accountNumber);
}

package ProjektPraktyczny.bank.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
// Klasa raz stworzona, nie może zostać zmieniona - > @Value

public class BankAccount {

    @Id
    private String pesel;
    @Column()
    private BigDecimal value;
    @Column(nullable = false)
    private String accountNumber;

    private BankAccount(BankAccountBuilder bankAccountBuilder) {
        this.pesel = bankAccountBuilder.pesel;
        this.value = bankAccountBuilder.value != null ? bankAccountBuilder.value : BigDecimal.ZERO;
        this.accountNumber = bankAccountBuilder.accountNumber;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "pesel='" + pesel + '\'' +
                ", value=" + value +
                ", accountNumber='" + accountNumber + '\'' +
                '}';
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }


    public static class BankAccountBuilder {
        private String pesel;
        private BigDecimal value;
        private String accountNumber;


        public BankAccountBuilder buildPesel(String pesel) {
            this.pesel = pesel;
            return this;
        }

        public BankAccountBuilder buildValue(BigDecimal value) {
            this.value = value;
            return this;
        }

        public BankAccountBuilder buildNumberAccount(String accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }
        public BankAccount build() {
            return new BankAccount(this);
        }
    }
}

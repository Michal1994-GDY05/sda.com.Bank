package ProjektPraktyczny.bank.request;

import lombok.NonNull;
import lombok.Value;

import java.math.BigDecimal;

@Value
// klasa nie mutowalna, wszystkie pola są dzięki temu finalne. Oraz tworzy getter i construktor
public class CreateBankAccountRequest {

    @NonNull
    private String pesel;

    @NonNull
    private BigDecimal initValue;
}

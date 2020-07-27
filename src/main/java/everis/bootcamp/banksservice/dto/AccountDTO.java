package everis.bootcamp.banksservice.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private String idBankAccount;
    private Double availableBalance;
    private String accountTypeName;
    private Double minAmount;
    private Double minBalance;
    private List<String> customers;
    private int numberTransactionsRemainder;
    private double commission;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
    private Date createdAt;
}
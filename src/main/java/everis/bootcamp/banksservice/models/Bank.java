package everis.bootcamp.banksservice.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "bank")
public class Bank {
    @Id
    private String idBank;
    private String name;
    private double depositFromOtherBankCommission;
    private double withdrawFromOtherBankCommission;
}
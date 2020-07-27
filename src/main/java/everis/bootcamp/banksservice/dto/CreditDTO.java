package everis.bootcamp.banksservice.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditDTO {
    private String creditId;
    private String customerId;
    private String currencyName;
    private Double availableAmount;
    private Double comsumedAmount;
    private Double limit;
    private String creditTypeName;
    private String expiredDebt;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
    private Date createdAt;
}
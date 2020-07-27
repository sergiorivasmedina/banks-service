package everis.bootcamp.banksservice.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductsReport {
    private List<AccountDTO> accounts;
    private List<CreditDTO> credits;
}
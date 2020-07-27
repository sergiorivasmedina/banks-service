package everis.bootcamp.banksservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import everis.bootcamp.banksservice.dto.AccountDTO;
import everis.bootcamp.banksservice.dto.CreditDTO;
import everis.bootcamp.banksservice.dto.InitialEndDates;
import everis.bootcamp.banksservice.dto.ProductsReport;
import everis.bootcamp.banksservice.models.Bank;
import everis.bootcamp.banksservice.repositories.BankRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BankService {

    private static String accountsUri = "localhost:8082";
    private static String creditsUri = "localhost:8083";
    
    @Autowired
    private BankRepository bankRepository;

    public Flux<Bank> getAllBanks() {
        return bankRepository.findAll();
    }

    public Mono<Bank> findById(String bankId) {
        return bankRepository.findById(bankId);
    }

    public Mono<Bank> save(Bank bank) {
        return bankRepository.save(bank);
    }

    public Mono<Void> delete(Bank bank) {
        return bankRepository.delete(bank);
    }

    public Mono<ProductsReport> getProductsReportByBankId(InitialEndDates dates, String bankId) {
        
        Mono<List<AccountDTO>> accounts = WebClient.create()
            .post()
            .uri(accountsUri+"/account/search/betweenDates/" + bankId)
            .accept(MediaType.APPLICATION_JSON)
            .body(Mono.just(dates), InitialEndDates.class)
            .retrieve()
            .bodyToFlux(AccountDTO.class)
            .collectList();

        Mono<List<CreditDTO>> credits = WebClient.create()
            .post()
            .uri(creditsUri+"/credit/search/betweenDates/" + bankId)
            .accept(MediaType.APPLICATION_JSON)
            .body(Mono.just(dates), InitialEndDates.class)
            .retrieve()
            .bodyToFlux(CreditDTO.class)
            .collectList();

        return Mono.zip(accounts, credits).map(tuple -> {
            return new ProductsReport(tuple.getT1(), tuple.getT2());
        });
    }
}
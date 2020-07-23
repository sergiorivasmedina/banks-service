package everis.bootcamp.banksservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import everis.bootcamp.banksservice.models.Bank;
import everis.bootcamp.banksservice.repositories.BankRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BankService {
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
}
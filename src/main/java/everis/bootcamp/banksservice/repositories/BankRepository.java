package everis.bootcamp.banksservice.repositories;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import everis.bootcamp.banksservice.models.Bank;

@Repository
public interface BankRepository extends ReactiveMongoRepository<Bank, String> {
    
}
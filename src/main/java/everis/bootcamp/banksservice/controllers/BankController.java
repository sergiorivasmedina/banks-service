package everis.bootcamp.banksservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import everis.bootcamp.banksservice.models.Bank;
import everis.bootcamp.banksservice.services.BankService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class BankController {
    @Autowired
    private BankService bankService;

    @GetMapping(value = "/banks")
    public Flux<Bank> getAllBanks() {
        return bankService.getAllBanks();
    }

    @GetMapping(value = "/bank/{bankId}")
    public Mono<Bank> getAllBanks(@PathVariable(name = "bankId") String bankId) {
        return bankService.findById(bankId);
    }

    @PostMapping(value = "/bank/new")
    public Mono<Bank> newBank(@RequestBody Bank bank) {
        return bankService.save(bank);
    }

    @PutMapping(value = "/bank/{bankId}")
    public Mono<ResponseEntity<Bank>> updateBank(@PathVariable(name = "bankId") String bankId, @RequestBody Bank bank) {
        return bankService.findById(bankId)
            .flatMap(existingBank -> {
                return bankService.save(bank);
            })
            .map(updateBank -> new ResponseEntity<>(updateBank, HttpStatus.ACCEPTED))
            .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(value = "/bank/{bankId}")
    public Mono<ResponseEntity<Void>> deleteBank(@PathVariable(name = "bankId") String bankId) {
        return bankService.findById(bankId)
            .flatMap(existingBank ->
                bankService.delete(existingBank)
                    .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))) 
            )
            .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
package ir.ariana.home_service_mvc.service;

import ir.ariana.home_service_mvc.exception.InvalidEntityException;
import ir.ariana.home_service_mvc.exception.NotFoundException;
import ir.ariana.home_service_mvc.model.BankAccount;
import ir.ariana.home_service_mvc.repository.BankAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class BankAccountService {
    @Autowired
    private final BankAccountRepository bankAccountRepository;

    public BankAccount findByCardNumber(String cardNumber) {
        return bankAccountRepository.findByCardNumber(cardNumber).orElseThrow(()
                -> new NotFoundException(String.format("the entity with %s not found", cardNumber)));
    }

    public BankAccount findById(Long id) {
        return bankAccountRepository.findById(id).orElseThrow(()
                -> new NotFoundException(String.format("the entity with %s not found", id)));
    }

    public BankAccount bankAccountCheck(BankAccount bankAccount) {
        BankAccount byCardNumber = findByCardNumber(bankAccount.getCardNumber());
        throw new InvalidEntityException("the parameters of entity not valid ");
    }

    public void updateCredit(Long bankAccountId, Long amount) {
        BankAccount byId = findById(bankAccountId);
        byId.setAmount(amount);
        bankAccountRepository.save(byId);
    }

    public BankAccount save(BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }

}

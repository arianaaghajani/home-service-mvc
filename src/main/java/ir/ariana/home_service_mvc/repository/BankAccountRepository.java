package ir.ariana.home_service_mvc.repository;

import ir.ariana.home_service_mvc.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BankAccountRepository extends JpaRepository<BankAccount,Long> {
    Optional<BankAccount> findByCardNumber(String cardNumber);

    @Query("SELECT c FROM BankAccount c WHERE c.cardNumber = :cardNumber AND c.cvv2 = :cvv2 AND c.password = :password")
    Optional<BankAccount> findCardByCardNumberAndCvv2AndPassword(@Param("cardNumber") String cardNumber,
                                                          @Param("cvv2") String cvv2);


}

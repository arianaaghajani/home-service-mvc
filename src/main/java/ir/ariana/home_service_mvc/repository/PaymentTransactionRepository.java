package ir.ariana.home_service_mvc.repository;

import ir.ariana.home_service_mvc.model.PaymentTransaction;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface PaymentTransactionRepository extends JpaRepository<PaymentTransaction, Long> {
    @Modifying
    @Query("UPDATE PaymentTransaction p SET p.captchaAnswer=:newAnswer where p.id=:id")
    void updateCaptchaAnswer(@Param("id") Long id, @Param("newAnswer") String newAnswer);
}

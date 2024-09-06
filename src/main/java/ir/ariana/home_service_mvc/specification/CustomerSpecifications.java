package ir.ariana.home_service_mvc.specification;

import ir.ariana.home_service_mvc.dto.customer.CustomerCriteriaDto;
import ir.ariana.home_service_mvc.model.Customer;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class CustomerSpecifications {
    public static Specification<Customer> getCustomerSpecification(CustomerCriteriaDto customerCriteriaDto) {
        return (root, query, criteriaBuilder) ->{ List<Predicate> predicates =new ArrayList<>();

            if (customerCriteriaDto.firstName!=null&& !customerCriteriaDto.firstName.isEmpty())
                predicates.add( criteriaBuilder.like(root.get("firstName"),customerCriteriaDto.firstName));
            if (customerCriteriaDto.lastName!=null&& !customerCriteriaDto.lastName.isEmpty())
                predicates.add( criteriaBuilder.like(root.get("lastName"),customerCriteriaDto.lastName));
            if (customerCriteriaDto.email!=null&& !customerCriteriaDto.email.isEmpty())
                predicates.add( criteriaBuilder.like(root.get("email"),customerCriteriaDto.email));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }


}

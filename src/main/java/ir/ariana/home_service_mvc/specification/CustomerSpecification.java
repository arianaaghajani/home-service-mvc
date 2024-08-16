package ir.ariana.home_service_mvc.specification;

import ir.ariana.home_service_mvc.criteria.SearchCriteria;
import ir.ariana.home_service_mvc.model.Customer;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.data.jpa.domain.Specification;

@AllArgsConstructor
public class CustomerSpecification implements Specification<Customer> {

    private SearchCriteria criteria;

    @Override
    public Predicate toPredicate(@NonNull
                                 Root<Customer> root, @NonNull CriteriaQuery<?> query, @NonNull CriteriaBuilder builder) {

        return switch (criteria.getOperation()) {
            case EQUALITY -> builder.equal(root.get(criteria.getKey()), criteria.getValue());
            case NEGATION -> builder.notEqual(root.get(criteria.getKey()), criteria.getValue());
            case GREATER_THAN -> builder.greaterThan(root.get(
                    criteria.getKey()), criteria.getValue().toString());
            case LESS_THAN -> builder.lessThan(root.get(
                    criteria.getKey()), criteria.getValue().toString());
            case LIKE -> builder.like(root.get(
                    criteria.getKey()), criteria.getValue().toString());
            case STARTS_WITH -> builder.like(root.get(criteria.getKey()), criteria.getValue() + "%");
            case ENDS_WITH -> builder.like(root.get(criteria.getKey()), "%" + criteria.getValue());
            case CONTAINS -> builder.like(root.get(
                    criteria.getKey()), "%" + criteria.getValue() + "%");
            case JOIN -> builder.equal(root.get("subService").get("subServiceName"), criteria.getValue().toString());
            case JOIIN -> builder.equal(root.get("customer").get("firstName"), criteria.getValue().toString());
        };
    }
}

package ir.ariana.home_service_mvc.specification;

import ir.ariana.home_service_mvc.criteria.SearchCriteria;
import ir.ariana.home_service_mvc.criteria.SearchOperation;
import ir.ariana.home_service_mvc.model.Customer;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class CustomerSpecificationsBuilder {

    private final List<SearchCriteria> params = new ArrayList<>();

    public CustomerSpecificationsBuilder with(
            String key, String operation, Object value, String prefix, String suffix) {

        SearchOperation op = SearchOperation.getSimpleOperation(operation.charAt(0));
        if (op != null) {
            if (op == SearchOperation.EQUALITY) {
                boolean startWithAsterisk = prefix.contains("*");
                boolean endWithAsterisk = suffix.contains("*");

                if (startWithAsterisk && endWithAsterisk) {
                    op = SearchOperation.CONTAINS;
                } else if (startWithAsterisk) {
                    op = SearchOperation.ENDS_WITH;
                } else if (endWithAsterisk) {
                    op = SearchOperation.STARTS_WITH;
                }
            }
            params.add(new SearchCriteria(key, op, value));
        }
        return this;
    }

    public Specification<Customer> build() {

        if (params.isEmpty()) {
            return null;
        }

        Specification<Customer> result = new CustomerSpecification(params.get(0));

        for (int i = 1; i < params.size(); i++) {
            result = params.get(i).isOrPredicate()
                    ? Specification.where(result).or(new CustomerSpecification(params.get(i)))
                    : Specification.where(result).and(new CustomerSpecification(params.get(i)));
        }
        return result;
    }
}

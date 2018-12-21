package com.vict.elastics.registry;

import com.vict.elastics.domain.Employee;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EmployeeReposirtory extends ElasticsearchRepository<Employee,String> {
    List<Employee> queryEmployeeByFirstName(String firsetName);
}

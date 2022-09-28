package com.example.ReportSolution.Repository;

import com.example.ReportSolution.model.Items;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemsRepository extends CrudRepository<Items, Long> {

}

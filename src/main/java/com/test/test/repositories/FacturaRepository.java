package com.test.test.repositories;

import com.test.test.model.Facturas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacturaRepository extends JpaRepository<Facturas, Long> {

    List<Facturas> findAllByNit(String nit);

    Facturas findById(Integer id);

    void deleteAllByNit(String nit);

}

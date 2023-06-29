package com.test.test.repositories;

import com.test.test.model.DetalleFactura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleRepository extends JpaRepository<DetalleFactura, Long> {
    DetalleFactura findById(Integer idFactura);

    List<DetalleFactura> findAllByFacturaId(Integer idFactura);
}

package com.gestor.tienda.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestor.tienda.Entity.DetalleOrden;

public interface DetalleOrdenRepository extends JpaRepository<DetalleOrden, Integer> {
}

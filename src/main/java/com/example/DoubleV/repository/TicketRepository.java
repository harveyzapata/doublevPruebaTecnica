package com.example.DoubleV.repository;

import com.example.DoubleV.model.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, Long>, JpaSpecificationExecutor<TicketEntity> {
    /* - Esta interfaz extiende JpaRepository, lo que proporciona métodos predefinidos para realizar operaciones CRUD en la entidad TicketEntity.
     - Los métodos heredados incluyen guardar, buscar, eliminar y contar registros en la base de datos
     - También extiende JpaSpecificationExecutor, lo que habilita la capacidad de realizar consultas más complejas y dinámicas utilizando especificaciones.
     - Aquí no es necesario definir ningún método adicional, ya que JpaRepository y JpaSpecificationExecutor proporcionan todo lo necesario para interactuar con la base de datos
       y realizar operaciones básicas y consultas personalizadas en la entidad TicketEntity. */
}

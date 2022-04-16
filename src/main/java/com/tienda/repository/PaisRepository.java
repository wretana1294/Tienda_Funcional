/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tienda.repository;

import com.tienda.entity.Pais;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Carlitos
 */
@Repository //EL REPOSITORIO VA A LA BD Y CONSULTA LA INFO REQUERIDA
public interface PaisRepository extends CrudRepository<Pais,Long> {
    
}

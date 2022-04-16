/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tienda.service;
import com.tienda.entity.Persona;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Carlitos
 */
@Repository
public interface iPersonaService {
    public List<Persona> getAllPerson();//devuelve lista de personas
    public void savePerson(Persona persona); //permite guardar personas dentro de la BD
    public Persona getPersonById(Long id); //devuelve objeto Persona mediante el id pasado
    public void deleteById(Long id);//elimina un registro usando el id
    public Persona findByNombre(String Nombre);
}

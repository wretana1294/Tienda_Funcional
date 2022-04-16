/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.service;

import com.tienda.entity.Persona;
import com.tienda.repository.PersonaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Carlitos
 */
@Service //Indica al controlador que esta clase se usa como servicio
public class PersonaService implements iPersonaService{
    //Inyección de dependencias
    @Autowired
    private PersonaRepository personaRepository;
    
    @Override
    @Transactional(readOnly=true)//Se le indica a la BD que la consulta solamente será de lectura
    public List<Persona> getAllPerson(){
        return (List<Persona>)personaRepository.findAll();//Ejecuta el metodo ubicado en PersonaRepository para listar todas las personas de la BD
    }

    @Override
    @Transactional
    public void savePerson(Persona persona) {
        personaRepository.save(persona);
    }

    @Override
    @Transactional(readOnly=true)
    public Persona getPersonById(Long id) {
        return personaRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        personaRepository.deleteById(id);
    }

    @Override
    public Persona findByNombre(String Nombre) {
        return personaRepository.findByNombre(Nombre);
    }


}

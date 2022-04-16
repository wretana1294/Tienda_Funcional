/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.controller;

import com.tienda.entity.Persona;
import com.tienda.service.iPersonaService;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Carlitos
 */
@Controller
public class PersonasController {

    @Autowired
    private iPersonaService personaService;

    @GetMapping("/personas")
    public String index(Model model) {
        List<Persona> listaPersona = personaService.getAllPerson();
        model.addAttribute("titulo", "Personas");
        model.addAttribute("personas", listaPersona);
        return "personas";
    }

    //Metodo que se ejecutará la presionar el botón de nueva persona
    @GetMapping("/nuevaPersona")
    public String nuevaPersona(Persona persona, Model model) {
        model.addAttribute("persona", new Persona());
        return "modificarPersona"; //se usa la misma pagina tanto para modificar persona como para ingresar una nueva
    }

    @PostMapping("/guardarPersona")
    public String guardarPersona(@ModelAttribute Persona persona) {
        personaService.savePerson(persona);
        return "redirect:/personas"; //redirige a la pagina principal
    }

    @GetMapping("/modificarPersona/{getId}")
    public String modificarPersona(@PathVariable("id") Long idPersona, Model model) {
        Persona persona = personaService.getPersonById(idPersona);
        model.addAttribute("persona", persona);
        return "modificarPersona";
    }

    @RequestMapping("/pdf")

    public void getReportsinPDF(HttpServletResponse response) throws JRException, IOException {

        //Compiled report
        InputStream jasperStream = (InputStream) this.getClass().getResourceAsStream("/reports/Reporte_Empleados.jasper");

        //Adding attribute names
        Map params = new HashMap<>();
        params.put("id", "id");
        params.put("nombre", "nombre");
        params.put("apellido1", "apellido1");
        params.put("apellido2", "apellido2");
        params.put("roles", "roles");
        

        // Fetching the student from the data database.
        final JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(personaService.getAllPerson());

        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, source);

        response.setContentType("application/x-pdf");
        response.setHeader("Content-disposition", "inline; filename=ListaPersonas.pdf");

        final ServletOutputStream outStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
    }
}


package com.tienda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Carlitos
 * ESTA CLASE LO QUE HACE ES QUE CAPTURA EL URL O REQUEST MEDIANTE EL 
 * GETMAPPING, CUANDO DETECTA LOS VALORES QUE SE LE PASARON COMO PARAMETROS
 * EJECUTA EL METODO DEBAJO DE ÉL. EN ESTE CASO LO QUE HACE ES RETORNAR "LOGIN" QUE 
 * ES EL NOMBRE DE LA PAGINA QUE QUEREMOS QUE SE MUESTRE. ESTA PAGINA DEBE APARECER
 * EN LA CARPETA TEMPLATES
 */
@Controller
public class LoginController {
    
    @GetMapping({"/"})
    public String index(){
        return "login";
    }
}

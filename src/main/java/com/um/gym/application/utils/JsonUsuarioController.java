package com.um.gym.application.utils;

import com.um.gym.application.models.Persona;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class JsonUsuarioController {
    private static final Logger logger = LoggerFactory.getLogger(JsonUsuarioController.class);

    public String getList(List<Persona> lista){
        JSONArray jsonArray = new JSONArray();
        for (Persona persona : lista) {
                JSONObject u = new JSONObject();
                u.put("id", persona.getId());
                u.put("nombre", persona.getNombre());
                u.put("apellido", persona.getApellido());
                u.put("dni", persona.getDni());
                u.put("email", persona.getEmail());
                u.put("horas", persona.getHoras());
                u.put("minutos", persona.getMinutos());
                jsonArray.put(u);
            }
        JSONObject mainObj = new JSONObject();
        mainObj.put("usuarios", jsonArray);
        logger.info(mainObj.toString());
        return mainObj.toString();
    }

    public String getObj(Persona persona) {
        JSONArray jsonArray = new JSONArray();
        JSONObject u = new JSONObject();
        JSONObject mainObj = new JSONObject();
        u.put("id", persona.getId());
        u.put("nombre", persona.getNombre());
        u.put("apellido", persona.getApellido());
        u.put("dni", persona.getDni());
        u.put("email", persona.getEmail());
        u.put("horas", persona.getHoras());
        u.put("minutos", persona.getMinutos());
        jsonArray.put(u);
        mainObj.put("persona", jsonArray);
        logger.info(mainObj.toString());
        return mainObj.toString();
    }

}

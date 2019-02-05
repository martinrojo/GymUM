package com.um.gym.application.utils;

import com.um.gym.application.controllers.UserController;
import com.um.gym.application.models.Usuario;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class JsonUsuarioController {
    private static final Logger logger = LoggerFactory.getLogger(JsonUsuarioController.class);

    public String getList(List<Usuario> lista){
        JSONArray jsonArray = new JSONArray();
        for (Usuario usuario : lista) {
                JSONObject u = new JSONObject();
                u.put("nombre", usuario.getNombre());
                u.put("apellido", usuario.getApellido());
                u.put("dni", usuario.getDni());
                u.put("email", usuario.getEmail());
                jsonArray.put(u);
            }
        JSONObject mainObj = new JSONObject();
        mainObj.put("usuarios", jsonArray);
        logger.info(mainObj.toString());
        return mainObj.toString();
    }

    public String getObj(Usuario usuario) {
        JSONArray jsonArray = new JSONArray();
        JSONObject u = new JSONObject();
        JSONObject mainObj = new JSONObject();
        u.put("nombre", usuario.getNombre());
        u.put("apellido", usuario.getApellido());
        u.put("dni", usuario.getDni());
        u.put("email", usuario.getEmail());
        jsonArray.put(u);
        mainObj.put("usuario", jsonArray);
        logger.info(mainObj.toString());
        return mainObj.toString();
    }

}

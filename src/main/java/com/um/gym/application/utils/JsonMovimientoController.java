package com.um.gym.application.utils;

import com.um.gym.application.models.Movimiento;
import com.um.gym.application.models.Usuario;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JsonMovimientoController {
    private static final Logger logger = LoggerFactory.getLogger(JsonMovimientoController.class);

    public String getList(List<Movimiento> lista){
        JSONArray jsonArray = new JSONArray();
        for (Movimiento movimiento : lista) {
            JSONObject m = new JSONObject();
            m.put("usuario", movimiento.getUsuario());
            m.put("fecha_entrada", movimiento.getFechaEntrada());
            m.put("fecha_salida", movimiento.getFechaSalida());
            jsonArray.put(m);
        }
        JSONObject mainObj = new JSONObject();
        mainObj.put("movimientos", jsonArray);
        logger.info(mainObj.toString());
        return mainObj.toString();
    }

    public String getObj(Movimiento movimiento) {
        JSONArray jsonArray = new JSONArray();
        JSONObject m = new JSONObject();
        JSONObject mainObj = new JSONObject();
        m.put("usuario", movimiento.getUsuario());
        m.put("fecha_entrada", movimiento.getFechaEntrada());
        m.put("fecha_salida", movimiento.getFechaSalida());
        jsonArray.put(m);
        mainObj.put("movimiento", jsonArray);
        logger.info(mainObj.toString());
        return mainObj.toString();
    }
}

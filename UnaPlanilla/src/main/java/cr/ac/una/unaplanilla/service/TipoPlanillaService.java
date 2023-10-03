/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.unaplanilla.service;

import java.util.logging.Level;
import java.util.logging.Logger;
import cr.ac.una.unaplanilla.model.TipoPlanillaDto;
import cr.ac.una.unaplanilla.util.Request;
import cr.ac.una.unaplanilla.util.Respuesta;
import jakarta.ws.rs.core.GenericType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Carlos
 */
public class TipoPlanillaService {

    public Respuesta getTipoPlanilla(Long id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("PlanillasController/tipoplanilla", "/{id}", parametros);
            request.get();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            TipoPlanillaDto tipoPlanilla = (TipoPlanillaDto) request.readEntity(TipoPlanillaDto.class);
            return new Respuesta(true, "", "", "TipoPlanilla", tipoPlanilla);
        } catch (Exception ex) {
            Logger.getLogger(TipoPlanillaService.class.getName()).log(Level.SEVERE, "Ocurrio un error al consultar el tipo de planilla.", ex);
            return new Respuesta(false, "Ocurrio un error al consultar el tipo de planilla.", "getTipoPlanilla " + ex.getMessage());
        }
    }

    public Respuesta guardarTipoPlanilla(TipoPlanillaDto tipoPlanilla) {
        try {
            Request request = new Request("PlanillasController/tipoplanilla");
            request.post(tipoPlanilla);
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            TipoPlanillaDto tipoPlanillaDto = (TipoPlanillaDto) request.readEntity(TipoPlanillaDto.class);
            return new Respuesta(true, "", "", "TipoPlanilla", tipoPlanillaDto);
        } catch (Exception ex) {
            Logger.getLogger(TipoPlanillaService.class.getName()).log(Level.SEVERE, "Ocurrio un error al guardar el tipo de planilla.", ex);
            return new Respuesta(false, "Ocurrio un error al guardar el tipo de planilla.", "guardarTipoPlanilla " + ex.getMessage());
        }
    }

    public Respuesta eliminarTipoPlanilla(Long id) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            Request request = new Request("PlanillasController/eliminartipoplanilla", "/{id}", parametros);
            request.delete();
            if (request.isError()) {
                return new Respuesta(false, request.getError(), "");
            }
            return new Respuesta(true, "", "");
        } catch (Exception ex) {
            Logger.getLogger(TipoPlanillaService.class.getName()).log(Level.SEVERE, "Ocurrio un error al eliminar el tipo de planilla.", ex);
            return new Respuesta(false, "Ocurrio un error al eliminar el tipo de planilla.", "eliminarTipoPlanilla " + ex.getMessage());
        }
    }
    
    public Respuesta getTipoPlanillas(String codigo,String descripcion,String planillasPorMes) {
        try {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("codigo", codigo);
            parametros.put("descripcion", descripcion);
            parametros.put("planillasPorMes",planillasPorMes);
            Request request = new Request("PlanillasController/tipoplanillas","/{codigo}/{descripcion}/{planillasPorMes}", parametros);
            request.get();
            if(request.isError())
            {
                return new Respuesta(false, request.getError(), "");
            }
            List<TipoPlanillaDto> tipoPlanilla = (List<TipoPlanillaDto>) request.readEntity(new GenericType<List<TipoPlanillaDto>>(){});
            return new Respuesta(true, "", "", "Planillas", tipoPlanilla);

        } catch (Exception ex) {
            Logger.getLogger(TipoPlanillaService.class.getName()).log(Level.SEVERE, "Ocurrio un error al consultar el tipo de planilla.", ex);
            return new Respuesta(false, "Ocurrio un error al consultar el tipo de planilla.", "getTipoPlanilla " + ex.getMessage());
        }
    }
}

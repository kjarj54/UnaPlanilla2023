/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.unaplanillaws.controller;

import cr.ac.una.unaplanillaws.model.TipoPlanillaDto;
import cr.ac.una.unaplanillaws.service.TipoPlanillaService;
import cr.ac.una.unaplanillaws.util.CodigoRespuesta;
import cr.ac.una.unaplanillaws.util.Respuesta;
import jakarta.ejb.EJB;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.GenericEntity;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kevin
 */
@Path("/PlanillasController")
public class PlanillasController {
    @EJB
    TipoPlanillaService tipoPlanillaService;

    @GET
    @Path("/ping")
    public Response ping() {
        return Response
                .ok("ping")
                .build();
    }

    @GET
    @Path("/tipoplanilla/{id}")
    public Response getPlanilla(@PathParam("id") Long id) {
        try {
            Respuesta res = tipoPlanillaService.getTipoPlanilla(id);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok((TipoPlanillaDto) res.getResultado("TipoPlanilla")).build();
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo Tipo planilla").build();
        }
    }

    @POST
    @Path("/tipoplanilla")
    public Response guardarTipoPlanilla(TipoPlanillaDto planilla) {
        try {
            Respuesta respuesta = tipoPlanillaService.guardarTipoPlanilla(planilla);
            if (!respuesta.getEstado()) {
                return Response.status(respuesta.getCodigoRespuesta().getValue()).entity(respuesta.getMensaje()).build();
            }
            return Response.ok((TipoPlanillaDto) respuesta.getResultado("TipoPlanilla")).build();
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error guardando TipoPlanilla").build();
        }
    }

    @DELETE
    @Path("/eliminartipoplanilla/{id}")
    public Response eliminarPlanilla(@PathParam("id") Long id) {
        try {
            Respuesta respuesta = tipoPlanillaService.getTipoPlanilla(id);
            if (!respuesta.getEstado()) {
                return Response.status(respuesta.getCodigoRespuesta().getValue()).entity(respuesta.getMensaje()).build();
            }
            return Response.ok().build();
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error eliminando Tipo planilla").build();
        }
    }

    @GET
    @Path("/tipoplanillas/{codigo}/{descripcion}/{planillasPorMes}")
    public Response getPlanillas(@PathParam("codigo") String codigo, @PathParam("descripcion") String descripcion, @PathParam("planillasPorMes") String planillasPorMes) {
        try {
            Respuesta res = tipoPlanillaService.getPlanillas(codigo, descripcion, planillasPorMes);
            if (!res.getEstado()) {
                return Response.status(res.getCodigoRespuesta().getValue()).entity(res.getMensaje()).build();
            }
            return Response.ok(new GenericEntity<List<TipoPlanillaDto>>((List<TipoPlanillaDto>) res.getResultado("Planillas")) {
            }).build();
        } catch (Exception ex) {
            Logger.getLogger(EmpleadoController.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(CodigoRespuesta.ERROR_INTERNO.getValue()).entity("Error obteniendo Tipo planilla").build();
        }
    }
}

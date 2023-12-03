package edu.pucmm.eict.pwa.springbootjasperreport.controllers;

import edu.pucmm.eict.pwa.springbootjasperreport.encapsulaciones.ReporteDTO;
import edu.pucmm.eict.pwa.springbootjasperreport.servicios.ReporteServices;
import edu.pucmm.eict.pwa.springbootjasperreport.utils.Utilidades;
import net.sf.jasperreports.engine.JRException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Genera los reportes y lo retorna en un format JSON con el archivo en base64.
 *
 */
@RestController
@RequestMapping(path = "/api")
public class ApiController {

    private ReporteServices reporteServices;
    private Logger logger = LoggerFactory.getLogger(ApiController.class);

    public ApiController(ReporteServices reporteServices) {
        this.reporteServices = reporteServices;
    }

    @GetMapping(path = "/simple")
    public ReporteDTO reporteSimple() throws JRException, IOException {
        logger.info("Reporte de simple vía API");
        File file = reporteServices.reporteSimplePDF();
        return new ReporteDTO("Reporte Simple", "application/pdf", Utilidades.convertirFileABase64(file));
    }

    @GetMapping(path = "/conexion")
    public ReporteDTO reporteConexion() throws JRException, IOException, SQLException {
        logger.info("Reporte de conexión vía API");
        File file = reporteServices.reporteConexionPDF();
        return new ReporteDTO("Reporte Conexion", "application/pdf", Utilidades.convertirFileABase64(file));
    }

    @GetMapping(path = "/parametros/{id}" )
    public ReporteDTO reporteParametros(@PathVariable Long id) throws JRException, IOException, SQLException {
        logger.info("Reporte de parametro vía API");
        File file = reporteServices.reporteConexionParametrosPDF(id);
        return new ReporteDTO("Reporte Parametro", "application/pdf", Utilidades.convertirFileABase64(file));
    }

    @GetMapping(path = "/jrdatasource")
    public ReporteDTO reporteJRDataSource() throws JRException, IOException, SQLException {
        logger.info("Reporte de JRDataSource vía API");
        File file = reporteServices.reporteJRDataSourcePDF();
        return new ReporteDTO("Reporte JR Data Source", "application/pdf", Utilidades.convertirFileABase64(file));
    }



}

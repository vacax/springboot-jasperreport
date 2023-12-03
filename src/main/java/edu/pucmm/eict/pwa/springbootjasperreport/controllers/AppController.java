package edu.pucmm.eict.pwa.springbootjasperreport.controllers;

import edu.pucmm.eict.pwa.springbootjasperreport.servicios.ReporteServices;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.*;
import java.sql.SQLException;

@Controller
@RequestMapping(path = "/mvc")
public class AppController {

    private ReporteServices reporteServices;
    private Logger logger = LoggerFactory.getLogger(AppController.class);

    public AppController(ReporteServices reporteServices) {
        this.reporteServices = reporteServices;
    }

    @RequestMapping(path = "/")
    public String index(Model model){
        model.addAttribute("mensaje", "Utilizando Plantillas");
        return "index.html";
    }

    /**
     * Reporte simple generando el hola mundo del reporte.
     * @param request
     * @param response
     * @throws JRException
     * @throws IOException
     */
    @RequestMapping(path = "/simple")
    public void generacionReporteSimple(HttpServletRequest request, HttpServletResponse response) throws JRException, IOException {
        logger.info("Generando reporte simple");
        File file = reporteServices.reporteSimplePDF();
        downloadArchivoPDF(request, response, "Reporte Simple", file);
    }

    /**
     * Reporte para la conexión de la base de datos.
     * @param request
     * @param response
     * @throws JRException
     * @throws IOException
     * @throws SQLException
     */
    @RequestMapping(path = "/conexion")
    public void generacionReporteConexion(HttpServletRequest request, HttpServletResponse response) throws JRException, IOException, SQLException {\
        logger.info("Generando reporte conexión");
        File file = reporteServices.reporteConexionPDF();
        downloadArchivoPDF(request, response, "Reporte Conexion", file);
    }

    /**
     * Generacion del reporte enviando el parametro. Notar que la generación del ID es random buscar en la base de datos
     * o buscar el reporte de conexión para ver los datos.
     * @param request
     * @param response
     * @param id
     * @throws JRException
     * @throws IOException
     * @throws SQLException
     */
    @RequestMapping(path = "/parametros/{id}")
    public void generacionReporteConexionParametros(HttpServletRequest request,
                                                    HttpServletResponse response,
                                                    @PathVariable Long id)
            throws JRException, IOException, SQLException {
        logger.info("Generando reporte parametros");
        File file = reporteServices.reporteConexionParametrosPDF(id);
        downloadArchivoPDF(request, response, "Reporte Conexion Parametros", file);
    }

    @RequestMapping(path = "/jrdatasource")
    public void generacionReporteJRDataSource(HttpServletRequest request, HttpServletResponse response) throws JRException, IOException, SQLException {
        logger.info("Generando reporte JR DataSource");
        File file = reporteServices.reporteJRDataSourcePDF();
        downloadArchivoPDF(request, response, "Reporte Data Source", file);
    }

    /**
     * Metodo para centralizar la descarga de un archivo al cliente.
     * @param request
     * @param response
     * @param nombreArchivo
     * @param archivo
     * @throws IOException
     */
    private void downloadArchivoPDF(HttpServletRequest request, HttpServletResponse response,
                                    String nombreArchivo, File archivo) throws IOException {
        //dependiendo el archivo indicamos el mimetype, si queremos la descarga podemos utilizar:
        // application/octet-stream
        String mimeType = "application/pdf";

        //indicando al salida a la respuesta.
        response.setContentType(mimeType);
        //header para especificar el nombre del archivo.
        response.setHeader("Content-Disposition", String.format("inline; filename=\"" + nombreArchivo + "\""));
        response.setContentLength((int) archivo.length());

        //
        InputStream inputStream = new BufferedInputStream(new FileInputStream(archivo));
        FileCopyUtils.copy(inputStream, response.getOutputStream());


    }
}

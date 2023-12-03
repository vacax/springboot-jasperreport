package edu.pucmm.eict.pwa.springbootjasperreport.servicios;

import edu.pucmm.eict.pwa.springbootjasperreport.encapsulaciones.EstudianteJRDataSource;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRSaver;
import net.sf.jasperreports.export.SimpleExporterInput;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;

/**
 *
 */
@Service
public class ReporteServices {

    // Representa la conexión activa que tenemos en el proyecto.
    private DataSource dataSource;

    public ReporteServices(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Mostrando un reporte simple sin conexión y sin parametros.
     * El reporte está configurada para presentar todas las secciones cuando no existe datos.
     * @return
     */
    public JasperPrint getReporteSimple() throws JRException, IOException {

        //Cargando el reporte desde el proyecto, compilando desde jrxml y obtiendo el valor en jasper.
        InputStream simpleReportStream = getClass().getResourceAsStream("/reportes/PruebaReporte.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(simpleReportStream);

        //Para no tener que hacer ese proceso siempre almaceno la compiliación.
        JRSaver.saveObject(jasperReport, File.createTempFile("jasper_", ".jasper").getName());

        //Mandando a ejecutar el proyecto.
        JasperPrint print = JasperFillManager.fillReport(jasperReport, null);

        return print;
    }


    public JasperPrint getReporteConexion() throws JRException, IOException, SQLException {

        //Cargando el reporte desde el proyecto, compilando desde jrxml y obtiendo el valor en jasper.
        InputStream estudianteReportStream = getClass().getResourceAsStream("/reportes/PruebaEstudiante.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(estudianteReportStream);

        //Para no tener que hacer ese proceso siempre almaceno la compiliación.
        JRSaver.saveObject(jasperReport, File.createTempFile("jasper_", ".jasper").getName());

        //Mandando a ejecutar el proyecto.
        JasperPrint print = JasperFillManager.fillReport(jasperReport, null, dataSource.getConnection());

        return print;
    }

    /**
     *
     * @param id
     * @return
     * @throws JRException
     * @throws IOException
     * @throws SQLException
     */
    public JasperPrint getReporteParametro(Long id) throws JRException, IOException, SQLException {

        //Cargando el reporte desde el proyecto, compilando desde jrxml y obtiendo el valor en jasper.
        InputStream estudianteParametroReportStream = getClass().getResourceAsStream("/reportes/PruebaEstudianteParametro.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(estudianteParametroReportStream);

        //Para no tener que hacer ese proceso siempre almaceno la compiliación.
        JRSaver.saveObject(jasperReport, File.createTempFile("jasper_", ".jasper").getAbsolutePath());

        //
        HashMap<String, Object> parametros=new HashMap<>();
        parametros.put("id", id); //Nombre del parametro del reporte para el key y el value el tipo que corresponde.

        //Mandando a ejecutar el proyecto.
        JasperPrint print = JasperFillManager.fillReport(jasperReport, parametros, dataSource.getConnection());

        return print;
    }

    public JasperPrint getReporteJRDataSource() throws JRException, IOException {

        //Cargando el reporte desde el proyecto, compilando desde jrxml y obtiendo el valor en jasper.
        InputStream jrDataSourceReportStream = getClass().getResourceAsStream("/reportes/PruebaEstudiante.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jrDataSourceReportStream);

        //Para no tener que hacer ese proceso siempre almaceno la compiliación.
        JRSaver.saveObject(jasperReport, File.createTempFile("jasper_", ".jasper").getName());

        //Obteniendo le objeto que implementa el JRDataSource.
        EstudianteJRDataSource datos = new EstudianteJRDataSource();

        //Mandando a ejecutar el proyecto.
        JasperPrint print = JasperFillManager.fillReport(jasperReport, null, datos);

        return print;
    }

    public File reporteSimplePDF() throws JRException, IOException {
        return exportarPDF(getReporteSimple());
    }

    public File reporteConexionPDF() throws JRException, IOException, SQLException {
        return exportarPDF(getReporteConexion());
    }

    public File reporteConexionParametrosPDF(long id) throws JRException, IOException, SQLException {
        return exportarPDF(getReporteParametro(id));
    }

    public File reporteJRDataSourcePDF() throws JRException, IOException, SQLException {
        return exportarPDF(getReporteJRDataSource());
    }

    /**
     *
     * @param jasperPrint
     * @throws JRException
     */
    public File exportarPDF(JasperPrint jasperPrint) throws JRException, IOException {
        File archivoPdf =  null;
        FileOutputStream fos = null;

        byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);

        archivoPdf = File.createTempFile("simple_", ".pdf");
        fos = new FileOutputStream(archivoPdf);
        fos.write(bytes); // Escribe el arreglo de bytes en el archivo
        fos.close();

        return archivoPdf;
    }

}

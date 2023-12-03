package edu.pucmm.eict.pwa.springbootjasperreport.encapsulaciones;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import java.util.ArrayList;
import java.util.List;

/**
 * Objeto que nos permite sustituir una conexion JDBC o a una fuente de dato a una estructura que permite
 * la construcción de los datos de diferentes fuentes, necesario cuando requerimos integrar varias informaciones
 * en una única estructura.
 */
public class EstudianteJRDataSource implements JRDataSource {

    private List<EstudianteDTO> listado;
    private int indice=-1;

    public EstudianteJRDataSource() {
        listado=new ArrayList<>();
        /*
        Puede ser cargado de la base de datos o de servicios externos
         */
        for (int i = 0; i < 100; i++) {
            listado.add(new EstudianteDTO(new Long(i), "Nombre "+i, "Apellido "+i));
        }

    }

    /**
     * Indica que si existe un próximo elemento a consultar.
     * @return
     * @throws JRException
     */
    @Override
    public boolean next() throws JRException {
        indice++;
        if(indice<listado.size()){
            return true;
        }
        return false;
    }

    /**
     * Valida la fila dentro del reporte para buscar dentro de la colección y visualiar en el reporte.
     * @param fila
     * @return
     * @throws JRException
     */
    @Override
    public Object getFieldValue(JRField fila) throws JRException {
        Object tmp = null;
        if(fila.getName().equalsIgnoreCase("id")){
            tmp = listado.get(indice).id();
        }else if(fila.getName().equalsIgnoreCase("nombre")){
            tmp = listado.get(indice).nombre();
        }else if(fila.getName().equalsIgnoreCase("apellido")){
            tmp = listado.get(indice).apellido();
        }

        return tmp;
    }
}

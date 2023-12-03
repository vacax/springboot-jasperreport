package edu.pucmm.eict.pwa.springbootjasperreport.entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Estudiante implements Serializable {

    @Id
    private Long id;
    private String nombre;
    private String apellido;
}

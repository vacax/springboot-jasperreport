package edu.pucmm.eict.pwa.springbootjasperreport.encapsulaciones;

/**
 * Record que representanta un DTO para la carga de información que pueden venir de otras fuentes.
 * @param id
 * @param nombre
 * @param apellido
 */
public record EstudianteDTO(Long id, String nombre, String apellido) {
}

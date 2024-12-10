package InterfazDAO;

import Modelos.Programa;
import java.util.List;

public interface ProgramaDAO {

    default void guardarPrograma(Programa programa) {
    }

    default List<Programa> obtenerProgramas() {
        return List.of();
    }

   default boolean borrarProgramaPorNombre(String nombre) {
       return false;
   }

    default boolean actualizarProgramaPorId(int id, Programa programaActualizado) {
        return false;
    }

    default Programa buscarProgramaPornombre(String nombre) {

        return null;
    }

    default Programa buscarProgramaPorId(int id) {
        return null;
    }

    default List<Programa> listarProgramaPorCategoria(String categoria) {
        return List.of();
    }

    default Programa obtenerProgramaConMayorAudiencia(String fecha) {
        return null;
    }

    default int calcularAudienciaMedia(int id, String fechaInicio, String fechaFin) {
        return 0;
    }
}

package estudioDesarrollo;

import com.dogma.busClass.ApiaAbstractClass;
import com.dogma.busClass.BusClassException;

// TODO: Auto-generated Javadoc
/**
 * Asigna el rol Jefe de Proyecto al usuario correspondiente.
 * Pueden ser José, Jorge o Federico.
 */
public class AsignarRolJefeDeProyecto extends ApiaAbstractClass{

	/**
	 * Ejecuta la clase
	 */
	@Override
	protected void executeClass() throws BusClassException {
		
		//Obtiene el proceso y el jefe seleccionado
		com.dogma.busClass.object.Process proc = this.getCurrentProcess();
		String jefeSeleccionado = this.getCurrentEntity().getAttribute("CAP_JEFEPROYECTO_STR").getValueAsString();
		
		//Setea el rol al jefe seleccionado
		
	
		if(jefeSeleccionado.compareTo("José") == 0){
			proc.setRol("CAP_JEFE_PROYECTO", "USUARIO4");
		}
		else if(jefeSeleccionado.compareTo("Jorge") == 0){
			proc.setRol("CAP_JEFE_PROYECTO", "USUARIO5");
		}
		else if(jefeSeleccionado.compareTo("Federico") == 0){
			proc.setRol("CAP_JEFE_PROYECTO", "USUARIO6");
		}
	}

}

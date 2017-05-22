package estudio;

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
		String jefeSeleccionado = this.getCurrentEntity().getAttribute("SE_JEFEPROYECTO").getValueAsString();
		
		//Setea el rol al jefe seleccionado
		
	
		if(jefeSeleccionado.compareTo("José") == 0){
			proc.setRol("JEFE_PROYECTO", "JOSE");
		}
		else if(jefeSeleccionado.compareTo("Jorge") == 0){
			proc.setRol("JEFE_PROYECTO", "JORGE");
		}
		else if(jefeSeleccionado.compareTo("Federico") == 0){
			proc.setRol("JEFE_PROYECTO", "FEDERICO");
		}
	}

}

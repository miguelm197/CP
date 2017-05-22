package estudioTesting;

import com.dogma.busClass.ApiaAbstractClass;
import com.dogma.busClass.BusClassException;

public class AsignarRolJefeDeProyecto extends ApiaAbstractClass{

	@Override
	protected void executeClass() throws BusClassException {
		// TODO Auto-generated method stub
		com.dogma.busClass.object.Process proc = this.getCurrentProcess();
		String jefeSeleccionado = this.getCurrentEntity().getAttribute("SE_JEFEPROYECTO").getValueAsString();
		
		if(jefeSeleccionado.compareTo("José") == 0){
			proc.setRol("JEFE_PROYECTO_TESTING", "JOSE");
		}
		else if(jefeSeleccionado.compareTo("Jorge") == 0){
			proc.setRol("JEFE_PROYECTO_TESTING", "JORGE");
		}
		else if(jefeSeleccionado.compareTo("Federico") == 0){
			proc.setRol("JEFE_PROYECTO_TESTING", "FEDERICO");
		}
	}

}

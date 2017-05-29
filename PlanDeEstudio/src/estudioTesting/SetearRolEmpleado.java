package estudioTesting;

import java.util.Collection;

import com.dogma.busClass.ApiaAbstractClass;
import com.dogma.busClass.BusClassException;
import com.dogma.busClass.object.Attribute;
import com.dogma.busClass.object.Entity;
import com.dogma.busClass.object.PossibleValue;
import com.dogma.busClass.object.Process;
import com.dogma.busClass.object.Task;
import com.dogma.busClass.object.User;


public class SetearRolEmpleado extends ApiaAbstractClass {
	@Override
	protected void executeClass() throws BusClassException {
		
		Entity currEnt = this.getCurrentEntity();
		User usuarioCreador = this.getCurrentProcess().getCreator();
		String usuario = usuarioCreador.getLogin().toString();
		this.getCurrentProcess().setRol("INICIADOR", usuario);
		
		// Notifico al usuario creador que inició el proceso de solicitud
		String mail = usuarioCreador.getEmail();
		String nombreCreador = usuarioCreador.getName();
		String tema = currEnt.getAttribute("SE_TEMAINTERES").getValueAsString();
		String titulo = currEnt.getAttribute("TITULO_SOL_ESTUDIO").getValueAsString();
		String jefe = currEnt.getAttribute("SE_JEFEPROYECTO").getValueAsString();
		String fechaInicio = currEnt.getAttribute("SE_FECHAINICIO").getValueAsString().substring(0, 10);
		String fechaFin = currEnt.getAttribute("SE_FECHAFIN").getValueAsString().substring(0, 10);
		String comentarios = currEnt.getAttribute("SE_COMENTARIOS").getValueAsString();

		Helpers.notificarInicioProcesoEst(this, nombreCreador, mail, titulo, tema, jefe, fechaInicio, fechaFin, comentarios);

	}
}

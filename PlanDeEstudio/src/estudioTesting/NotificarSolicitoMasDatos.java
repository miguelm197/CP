package estudioTesting;

import com.dogma.busClass.ApiaAbstractClass;
import com.dogma.busClass.BusClassException;
import com.dogma.busClass.object.Attribute;
import com.dogma.busClass.object.Entity;
import com.dogma.busClass.object.User;

public class NotificarSolicitoMasDatos extends ApiaAbstractClass{

	@Override
	protected void executeClass() throws BusClassException {
		// TODO Auto-generated method stub
		Entity currEnt = this.getCurrentEntity();
		Attribute combo = currEnt.getAttribute("P2_CR_COMPLETARORECHAZAR");
		String comentarios = currEnt.getAttribute("P2_CR_COMENTARIOS2").getValueAsString();

		String titulo = currEnt.getAttribute("TITULO_SOL_ESTUDIO").getValueAsString();

		User creador = currEnt.getCreator();
		String nombreCreador = creador.getName();
		String mail = this.getUser("ppi").getEmail();
		
		// Obtengo jefe de proyecto
		User jefeProyecto = null;
		// this.addMessage(this.getCurrentEntity().getAttribute("SE_JEFEPROYECTO").getValueAsString());
		String valuejefeProy = this.getCurrentEntity().getAttribute("SE_JEFEPROYECTO").getValueAsString();
		if (valuejefeProy.compareTo("José") == 0)
			jefeProyecto = this.getUser("jrussomano");
		else if (valuejefeProy.compareTo("Jorge") == 0)
			jefeProyecto = this.getUser("jartave");
		else if (valuejefeProy.compareTo("Federico") == 0)
			jefeProyecto = this.getUser("froda");

		String mailJefeProy = this.getUser("ppi").getEmail();
		
		if (combo.getValueAsString().compareTo("3") == 0) {
			Helpers.notificarSolicitoMasDatos(this, nombreCreador, mail, mailJefeProy, titulo, comentarios);
		}
	}

}

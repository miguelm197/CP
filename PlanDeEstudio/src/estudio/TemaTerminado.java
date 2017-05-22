package estudio;

import java.util.Collection;

import com.dogma.busClass.ApiaAbstractClass;
import com.dogma.busClass.BusClassException;
import com.dogma.busClass.object.Attribute;
import com.dogma.busClass.object.Entity;
import com.dogma.busClass.object.Field;


// TODO: Auto-generated Javadoc
/**
 * Escribe el nombre del tema terminado automaticamente en la tarea Registrar avance de estudio.
 */
public class TemaTerminado extends ApiaAbstractClass {

	/* (non-Javadoc)
	 * @see com.dogma.busClass.ApiaAbstractClass#executeClass()
	 */
	@Override
	protected void executeClass() throws BusClassException {
		
		int index = 0;
		Field gridColumn = null;
		//make sure the event source is a field
		if(this.getEvtSource() instanceof Field)
			gridColumn = (Field)this.getEvtSource();
		//get index that triggered the event
		index = gridColumn.getFireIndex();
		

		Entity currEnt = this.getCurrentEntity();
		Attribute attAvance = currEnt.getAttribute("RA_AVANCE");

		Object[] temas = currEnt.getAttribute("P2_SE_TEMA_STR").getValues().toArray();
		attAvance.setValue(temas[index].toString());

	}

}

package estudioTesting;

import java.util.Collection;

import com.dogma.busClass.ApiaAbstractClass;
import com.dogma.busClass.BusClassException;
import com.dogma.busClass.object.Attribute;
import com.dogma.busClass.object.Entity;
import com.dogma.busClass.object.Field;

public class TemaTerminado extends ApiaAbstractClass {

	@Override
	protected void executeClass() throws BusClassException {
		
		int index = 0;
		Field gridColumn = null;
		//make sure the event source is a field
		if(this.getEvtSource() instanceof Field)
			gridColumn = (Field)this.getEvtSource();
		//get index that triggered the event
		index = gridColumn.getFireIndex();
		
		//this.addMessage("Ind "+index);

		Entity currEnt = this.getCurrentEntity();
		Attribute attAvance = currEnt.getAttribute("RA_AVANCE");

		Object[] temas = currEnt.getAttribute("P2_SE_TEMA_STR").getValues().toArray();
		attAvance.setValue(temas[index].toString());

	}

}

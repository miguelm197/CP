package estudioTesting;

import com.dogma.busClass.ApiaAbstractClass;
import com.dogma.busClass.BusClassException;
import com.dogma.busClass.object.Attribute;
import com.dogma.busClass.object.Entity;
import com.dogma.busClass.object.Field;
import com.dogma.busClass.object.Form;
import com.dogma.busClass.object.PossibleValue;
import com.dogma.vo.IProperty;

public class CargarCompletarORechazar extends ApiaAbstractClass {

	@Override
	protected void executeClass() throws BusClassException {
		Entity currEnt = this.getCurrentEntity();
		Attribute attCompletarORechazar = currEnt.getAttribute("P2_CR_COMPLETARORECHAZAR");

		PossibleValue pos1 = new PossibleValue("1", "Perfecto. Se completa la solicitud de capacitación.");
		PossibleValue pos2 = new PossibleValue("2", "Se rechaza la solicitud de capacitación.");
		//PossibleValue pos3 = new PossibleValue("3", "Solicitar más datos al usuario.");

		attCompletarORechazar.addPossibleValues(pos1);
		attCompletarORechazar.addPossibleValues(pos2);
		//attCompletarORechazar.addPossibleValues(pos3);
		
	}

}

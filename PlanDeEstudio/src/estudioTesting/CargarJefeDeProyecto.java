package estudioTesting;

import com.dogma.busClass.ApiaAbstractClass;
import com.dogma.busClass.BusClassException;
import com.dogma.busClass.object.Attribute;
import com.dogma.busClass.object.PossibleValue;

public class CargarJefeDeProyecto extends ApiaAbstractClass {

	@Override
	protected void executeClass() throws BusClassException {
		// TODO Auto-generated method stub
		Attribute attJefes = this.getCurrentEntity().getAttribute("SE_JEFEPROYECTO");
		
		PossibleValue fede = new PossibleValue("Federico", "Federico");
		PossibleValue jorge = new PossibleValue("Jorge", "Jorge");
		PossibleValue jose = new PossibleValue("José", "José");
		PossibleValue miguel = new PossibleValue("Miguel", "Miguel");
		
		attJefes.addPossibleValues(fede);
		attJefes.addPossibleValues(jorge);
		attJefes.addPossibleValues(jose);
		attJefes.addPossibleValues(miguel);
		
	}

}

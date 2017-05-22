package estudioTesting;

import com.dogma.busClass.ApiaAbstractClass;
import com.dogma.busClass.BusClassException;
import com.dogma.busClass.object.Attribute;
import com.dogma.busClass.object.PossibleValue;

public class CargarAprobacion extends ApiaAbstractClass {
	@Override
	protected void executeClass() throws BusClassException {
		// TODO Auto-generated method stub
		Attribute attAprobacion = this.getCurrentEntity().getAttribute("EAS_APROBACION");

		PossibleValue pos1 = new PossibleValue("1", "Aprobado");
		PossibleValue pos2 = new PossibleValue("2", "NO Aprobado");

		attAprobacion.addPossibleValues(pos1);
		attAprobacion.addPossibleValues(pos2);
	}
}

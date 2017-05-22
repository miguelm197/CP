package estudioTesting;

import com.dogma.busClass.ApiaAbstractClass;
import com.dogma.busClass.BusClassException;
import com.dogma.busClass.object.Attribute;
import com.dogma.busClass.object.Entity;

public class UltimaAct extends ApiaAbstractClass{

	@Override
	protected void executeClass() throws BusClassException {
		
		Entity currEnt = this.getCurrentEntity();
		Attribute attFecha = currEnt.getAttribute("ESTUDIO_FECHA_ULT_ACT_STR");
		
		Helpers.setFecha(this, attFecha);
		
	}
}

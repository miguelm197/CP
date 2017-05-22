package estudioTesting;

import java.util.Date;

import com.dogma.busClass.ApiaAbstractClass;
import com.dogma.busClass.BusClassException;
import com.dogma.busClass.object.Attribute;

public class SetTiempoDisponible extends ApiaAbstractClass {

	@Override
	protected void executeClass() throws BusClassException {

		Date fechaInicio = (Date) getCurrentEntity().getAttribute("ECS_FECHAINICIO").getValue();
		Date fechaFin = (Date) getCurrentEntity().getAttribute("ECS_FECHAFIN").getValue();

		if (fechaInicio != null && fechaFin != null) {
			Date fechaActual = new Date();
			Attribute attTiempoDisp = getCurrentEntity().getAttribute("RA_TIEMPODISPONIBLE");
			attTiempoDisp.setValue(Helpers.diferenciaEnHoras(fechaFin, fechaActual));
		}

	}

}

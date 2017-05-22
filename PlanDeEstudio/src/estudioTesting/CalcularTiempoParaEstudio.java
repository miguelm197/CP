package estudioTesting;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.dogma.busClass.ApiaAbstractClass;
import com.dogma.busClass.BusClassException;
import com.dogma.busClass.object.Attribute;

public class CalcularTiempoParaEstudio extends ApiaAbstractClass {

	@Override
	protected void executeClass() throws BusClassException {

		Attribute attFechaInicio = this.getCurrentEntity().getAttribute("ECS_FECHAINICIO");
		Attribute attFechaFin = this.getCurrentEntity().getAttribute("ECS_FECHAFIN");

		Date fechaInicio = (Date) attFechaInicio.getValue();
		Date fechaFin = (Date) attFechaFin.getValue();

		Attribute tiempoParaCompletar = this.getCurrentEntity().getAttribute("ECS_TIEMPOESTUDIO");
		Double diasDisp = Helpers.diferenciaEnDias(fechaFin, fechaInicio);
		tiempoParaCompletar.setValue(diasDisp);

	}

}

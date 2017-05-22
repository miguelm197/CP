package estudioDesarrollo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.dogma.busClass.ApiaAbstractClass;
import com.dogma.busClass.BusClassException;
import com.dogma.busClass.object.Attribute;

public class VerificarFechas extends ApiaAbstractClass {

	@Override
	protected void executeClass() throws BusClassException {

		// TODO Auto-generated method stub
		Attribute attFechaInicio = this.getCurrentEntity().getAttribute("CAP_FECHAINICIOESPERADA_FEC");
		Attribute attFechaFin = this.getCurrentEntity().getAttribute("CAP_FECHAFINESPERADA_FEC");

		Date fechaInicio = (Date) attFechaInicio.getValue();
		Date fechaFin = (Date) attFechaFin.getValue();

		Date fechaActual = new Date();
		fechaActual.setHours(0);
		fechaActual.setMinutes(0);
		fechaActual.setSeconds(0);

		// this.addMessage(fechaActual.toString());
		// this.addMessage(fechaInicio.toString());
		// this.addMessage(fechaFin.toString());
		if (fechaInicio.toString().isEmpty()) {
			throw new BusClassException("Fecha de Inicio vacía");
		} else {
			if (fechaFin.toString().isEmpty()) {
				throw new BusClassException("Fecha de Finalización vacía");
			} else {
				if ((fechaInicio.after(fechaActual) || (fechaInicio.getDay() == fechaActual.getDay()
						&& fechaInicio.getMonth() == fechaActual.getMonth()
						&& fechaInicio.getYear() == fechaActual.getYear()))
						&& (fechaFin.after(fechaActual) || (fechaFin.getDay() == fechaActual.getDay()
						&& fechaFin.getMonth() == fechaActual.getMonth()
						&& fechaFin.getYear() == fechaActual.getYear()))
						&& (fechaInicio.before(fechaFin) || (fechaInicio.getDay() == fechaFin.getDay()
						&& fechaInicio.getMonth() == fechaFin.getMonth()
						&& fechaInicio.getYear() == fechaFin.getYear()))) {
					System.out.println("Fechas correctas");
				} else {
					throw new BusClassException("Verificar fechas.");
				}
			}
		}
		
				
	}
}

package estudio;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.dogma.busClass.ApiaAbstractClass;
import com.dogma.busClass.BusClassException;
import com.dogma.busClass.object.Attribute;

// TODO: Auto-generated Javadoc
/**
 * Calcula el tiempo según la fecha de inicio y de fin de la capacitación,
 * y setea el atributo tiempoParaCompletar con la cantidad de días
 */
public class CalcularTiempoParaEstudio extends ApiaAbstractClass {

	/**
	 * Ejecuta la clase
	 */
	@Override
	protected void executeClass() throws BusClassException {

		//Obtiene los atributos Fehca de inicio y Fecha de Fin
		Attribute attFechaInicio = this.getCurrentEntity().getAttribute("ECS_FECHAINICIO");
		Attribute attFechaFin = this.getCurrentEntity().getAttribute("ECS_FECHAFIN");

		//Obtiene las fechas de esos atributos
		Date fechaInicio = (Date) attFechaInicio.getValue();
		Date fechaFin = (Date) attFechaFin.getValue();

		//Obtiene el atributo TiempoEstudio
		Attribute tiempoParaCompletar = this.getCurrentEntity().getAttribute("ECS_TIEMPOESTUDIO");
		
		//Calcula la diferencia en días
		Double diasDisp = Helpers.diferenciaEnDias(fechaFin, fechaInicio);
		
		//Setea la diferencia en días en el atributo TiempoEstudio
		tiempoParaCompletar.setValue(diasDisp);

	}

}

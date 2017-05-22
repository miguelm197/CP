package estudio;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.dogma.busClass.ApiaAbstractClass;
import com.dogma.busClass.BusClassException;
import com.dogma.busClass.object.Attribute;
import com.dogma.busClass.object.Entity;


// TODO: Auto-generated Javadoc
/**
 * Setea los atributos correspondientes cuando se aprueba la capacitación.
 * 
 */
public class EstudioAprobado extends ApiaAbstractClass {

	/**
	 * Ejecuta la clase
	 */
	@Override
	protected void executeClass() throws BusClassException {

		Entity ent = this.getCurrentEntity();
		if (ent.getAttribute("EAS_APROBACION").getValueAsString().compareTo("1") == 0) {
			Date fechaActual = new Date();
			Attribute attFechaFin = ent.getAttribute("ECS_FECHAFIN");
			Date fechaFin = (Date) attFechaFin.getValue();

			ent.getAttribute("P2_RA_HORAS_TOTALES_EMPLEADAS").setValue(0.0);
			ent.getAttribute("RA_TIEMPODISPONIBLE").setValue(Helpers.diferenciaEnHoras(fechaFin, fechaActual));
			ent.getAttribute("RA_FINALIZARESTUDIO").setValue("false");
			ent.getAttribute("CONTADOR_AUX").setValue(0.0);
			ent.getAttribute("CONTADOR_AUX2").setValue(0.0);
		}
	}

}

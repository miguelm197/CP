package estudio;

import com.dogma.busClass.ApiaAbstractClass;
import com.dogma.busClass.BusClassException;
import com.dogma.busClass.object.Attribute;
import com.dogma.busClass.object.Entity;


// TODO: Auto-generated Javadoc
/**
 * Setea la fecha actual al atributo de Apia que indica la última actualización.
 */
public class UltimaAct extends ApiaAbstractClass{

	/* (non-Javadoc)
	 * @see com.dogma.busClass.ApiaAbstractClass#executeClass()
	 */
	@Override
	protected void executeClass() throws BusClassException {
		
		Entity currEnt = this.getCurrentEntity();
		Attribute attFecha = currEnt.getAttribute("ESTUDIO_FECHA_ULT_ACT_STR");
		
		Helpers.setFecha(this, attFecha);
		
	}
}

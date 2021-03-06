package estudio;

import com.dogma.busClass.ApiaAbstractClass;
import com.dogma.busClass.BusClassException;
import com.dogma.busClass.object.Attribute;
import com.dogma.busClass.object.Entity;
import com.dogma.busClass.object.Field;
import com.dogma.busClass.object.Form;
import com.dogma.vo.IProperty;

/**
 * Muestro y/o dejo requerido el campo comentarios del formulario actual seg�n
 * la elecci�n en la tarea Completar o Rechazar solicitud. 
 */
public class ChangeComboCR extends ApiaAbstractClass {

	/**
	 * Ejecuta la clase
	 */
	@Override
	protected void executeClass() throws BusClassException {

		// Obtengo entidad y formulario actual
		Entity currEnt = this.getCurrentEntity();
		Form form = this.getCurrentForm();

		// Obtengo atributo
		Field comentarios = form.getFieldByAttributeName("P2_CR_COMENTARIOS");
		String comboValue = currEnt.getAttribute("P2_CR_COMPLETARORECHAZAR").getValueAsString();

		int propIdVis = IProperty.PROPERTY_VISIBILITY_HIDDEN;
		int propIdReq = IProperty.PROPERTY_REQUIRED;

		if (comboValue.compareTo("1") == 0) {
			comentarios.setProperty(propIdVis, true);
			comentarios.setProperty(propIdReq, false);
		} else {
			comentarios.setProperty(propIdVis, false);
			comentarios.setProperty(propIdReq, true);
		}
	}

}

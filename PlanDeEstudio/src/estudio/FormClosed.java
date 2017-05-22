package estudio;

import com.dogma.busClass.ApiaAbstractClass;
import com.dogma.busClass.BusClassException;
import com.dogma.busClass.object.Form;
import com.dogma.vo.IProperty;


// TODO: Auto-generated Javadoc
/**
 * Deja abiertos los formularios en la tarea de Cancelaci�n.
 */
public class FormClosed extends ApiaAbstractClass {

	/**
	 * Ejecuta la clase
	 */
	@Override
	protected void executeClass() throws BusClassException {

		try {
			String tituloTarea = this.getCurrentTask().getTaskTitle();
			Form form = this.getCurrentForm();
			String tituloForm = form.getTitle();

			int propClosed = IProperty.PROPERTY_FORM_CLOSED;

			if (tituloTarea.compareTo(tituloForm) == 0 || tituloTarea.compareTo("Cancelar solicitud de licencia") == 0
					|| tituloTarea.compareTo("Cancelar capacitaci�n") == 0) {
				form.setFormProperty(propClosed, false);
			} else {
				form.setFormProperty(propClosed, true);
			}
		} catch (Exception e) {
		}

	}

}

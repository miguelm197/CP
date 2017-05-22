package estudioTesting;

import com.dogma.busClass.ApiaAbstractClass;
import com.dogma.busClass.BusClassException;
import com.dogma.busClass.object.Attribute;
import com.dogma.busClass.object.Entity;
import com.dogma.busClass.object.Field;
import com.dogma.busClass.object.Form;
import com.dogma.vo.IProperty;

public class DeshabilitarAvance extends ApiaAbstractClass {

	@Override
	protected void executeClass() throws BusClassException {

		Double td = (Double) this.getCurrentEntity().getAttribute("RA_TIEMPODISPONIBLE").getValue();
		if (td != null) {
			Entity currEnt = this.getCurrentEntity();
			Form form = this.getCurrentForm();

			Field field = form.getFieldByAttributeName("RA_AVANCE");
			Field field2 = form.getFieldByAttributeName("RA_FINALIZARESTUDIO");
			Field field3 = form.getFieldByAttributeName("RA_PASARAJEFE");
			Field field4 = form.getFieldByAttributeName("P2_RA_HORAS_EMPLEADAS_AVANCE");
			Field field5 = form.getFieldByAttributeName("P2_RA_HORAS_TOTALES_EMPLEADAS");
			Field field6 = form.getFieldByAttributeName("RA_COMENTARIOS");
			
			Attribute a1 = currEnt.getAttribute("RA_AVANCE");
			Attribute a2 = currEnt.getAttribute("P2_RA_HORAS_EMPLEADAS_AVANCE");
			Attribute a3 = currEnt.getAttribute("RA_COMENTARIOS");

			// get property id
			int propVer = IProperty.PROPERTY_VISIBILITY_HIDDEN;
			int propIdRO = IProperty.PROPERTY_READONLY;
			int propIdReq = IProperty.PROPERTY_REQUIRED;

			if (td.intValue() == 0) {
				// set property value
				a1.clear();
				a2.clear();
				a3.clear();
				
				field.setProperty(propIdRO, true);
				field.setProperty(propIdReq, false);
				field2.setProperty(propIdRO, true);
				field3.setProperty(propVer, false);
				field4.setProperty(propIdRO, true);
				field4.setProperty(propIdReq, false);
				field6.setProperty(propIdRO, true);
			}
		}
	}

}

package estudioDesarrollo;

import java.util.ArrayList;

import java.util.Collections;
import java.util.Comparator;

import com.dogma.busClass.ApiaAbstractClass;
import com.dogma.busClass.BusClassException;
import com.dogma.busClass.object.Attribute;
import com.dogma.busClass.object.PossibleValue;


// TODO: Auto-generated Javadoc
/**
 * Carga los temas de Capacitación al combo correspondiente.
 */
public class CargarTemas extends ApiaAbstractClass {

	/**
	 * 
	 */
	@Override
	protected void executeClass() throws BusClassException {
		
		//Obtiene el atributo TemaInteres
		Attribute attTema = this.getCurrentEntity().getAttribute("CAP_TEMAINTERES_STR");
		
		//Temas de interés definidos en JIRA
		String[] temasOrig = {"Appium","SqlServer 2014", "IIS", "MySql","MVC 5 + Razor","Actualización Framework 4.6",
				"Bootstrap","Material Design Lite","WCF","WFF","Node.js","HTML5 + CSS3 + JS","ASP.Net Web API",
				"Azure","Android SDK","IOS SDK (Swift)","Xamarin.Android","Xamarin.iOS","Xamarin.Forms",
				"Selenium","TFS (Gestión de proyectos e Integración Contínua)","Apia","Scrum"};
		
		//Los agrego a un ArrayList y ordeno alfabéticamente
		ArrayList<String> temasOrigList = new ArrayList<>();
		for(int i = 0; i<temasOrig.length; i++){
			temasOrigList.add(temasOrig[i]);
		}
		Collections.sort(temasOrigList);
		
		//Creo el ArrayList de posibles valores
		ArrayList<PossibleValue> temas = new ArrayList<>();
		
		for(int i = 0; i<temasOrigList.size(); i++){
			String tema = temasOrigList.get(i);
			temas.add(new PossibleValue(tema, (i+1)+" - "+tema));
		}
		
		//Agrego los posibles valores al att
		for(PossibleValue pos : temas){
			attTema.addPossibleValues(pos);
		}
	}

}

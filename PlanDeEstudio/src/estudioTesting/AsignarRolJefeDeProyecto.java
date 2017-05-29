package estudioTesting;

import java.util.Collection;

import com.dogma.busClass.ApiaAbstractClass;
import com.dogma.busClass.BusClassException;
import com.dogma.busClass.object.Entity;
import com.dogma.busClass.object.Process;
import com.dogma.busClass.object.User;

public class AsignarRolJefeDeProyecto extends ApiaAbstractClass{

	@Override
	protected void executeClass() throws BusClassException {

		Entity currEnt = this.getCurrentEntity();
		Process currProc = this.getCurrentProcess();
		String usuario = currProc.getCreator().getLogin().toString();
		User ucreador = currProc.getCreator();

		String[][] matriz = new String[7][6];

		matriz[0][0] = "ADMINISTRACION_TESTING";matriz[0][1] = "MIGUEL";	matriz[0][2] = "MIGUEL";		matriz[0][3] = "ppi";			matriz[0][4] = "MIGUEL";		matriz[0][5] = "MIGUEL";
	//---------------------------------------------------------------------------------------------------------------------------------------------------------
		matriz[1][0] = "mjacques";				matriz[1][1] = "jtolve";	matriz[1][2] = "mcamacho";		matriz[1][3] = "mmerelli";		matriz[1][4] = "kfabregat";		matriz[1][5] = "jrussomano";
		matriz[2][0] = "mtilve";				matriz[2][1] = "eluna";		matriz[2][2] = "froda";			matriz[2][3] = "ppi";			matriz[2][4] = "sorrego";		matriz[2][5] = "mezquerra";
		matriz[3][0] = "jmignaco";				matriz[3][1] = "jbraganca";	matriz[3][2] = "vbonini";		matriz[3][3] = "fmartinez";		matriz[3][4] = "fnoboa";		matriz[3][5] = "-";
		matriz[4][0] = "admin";					matriz[4][1] = "-";			matriz[4][2] = "-";				matriz[4][3] = "agomez";		matriz[4][4] = "jartave";		matriz[4][5] = "-";
		matriz[5][0] = "-";						matriz[5][1] = "-";			matriz[5][2] = "-";				matriz[5][3] = "glopez";		matriz[5][4] = "cfoucault";		matriz[5][5] = "-";
		matriz[6][0] = "-";						matriz[6][1] = "-";			matriz[6][2] = "-";				matriz[6][3] = "-";				matriz[6][4] = "fcairelo";		matriz[6][5] = "-";

		
		boolean notifica = false;
		
		
		String buscado=usuario;
		String buscando = "";
		String encargado = "";
		boolean bandera = false;
		
		for (int j = 0; j < matriz[0].length; j++) {
			for (int i = 0; i < matriz.length; i++) {
				
				buscando = matriz[i][j];
				if (buscando.equals(buscado)){	
					encargado = matriz[0][j];
					bandera = true;
					break;
				}
			}
		}

		if (notifica) {
			if (bandera) {
				currProc.setRol("JEFE_PROYECTO", encargado);
				currEnt.getAttribute("SE_JEFEPROYECTO").setValue(encargado);
				Collection<User> us = this.getGroup(encargado).getUsers();

				String nombreCreador = ucreador.getName();
				String tema = currEnt.getAttribute("SE_TEMAINTERES").getValueAsString();
				String titulo = currEnt.getAttribute("TITULO_SOL_ESTUDIO").getValueAsString();
				String jefe = encargado;
				String fechaInicio = currEnt.getAttribute("SE_FECHAINICIO").getValueAsString().substring(0, 10);
				String fechaFin = currEnt.getAttribute("SE_FECHAFIN").getValueAsString().substring(0, 10);
				String comentarios = currEnt.getAttribute("SE_COMENTARIOS").getValueAsString();

				for (User u : us) {
					String mail = u.getEmail();

					Helpers.notificarInicioProcesoEst(this, nombreCreador, mail, titulo, tema, jefe, fechaInicio,
							fechaFin, comentarios);
				}

			} else {
				String entidad = this.getCurrentEntity().getIdentifier();
				throw new BusClassException(
						" No se encontró su encargado directo, porfavor comuniquese con Miguel (miguel@fx2.com.uy).        Cod: "
								+ entidad);
			}
		}
		
		// Notifico al jefe de proyecto que inició el proceso de solicitud
	
		
 
		
		 
		

	

	

		
		
	}

}

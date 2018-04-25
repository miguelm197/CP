package estudio;

import java.util.Collection;

import com.dogma.busClass.ApiaAbstractClass;
import com.dogma.busClass.BusClassException;
import com.dogma.busClass.object.Entity;
import com.dogma.busClass.object.Process;
import com.dogma.busClass.object.User;

import estudioTesting.Helpers;

// TODO: Auto-generated Javadoc
/**
 * Asigna el rol Jefe de Proyecto al usuario correspondiente.
 * Pueden ser José, Jorge o Federico.
 */
public class AsignarRolJefeDeProyecto extends ApiaAbstractClass{

	/**
	 * Ejecuta la clase
	 */
	@Override
	protected void executeClass() throws BusClassException {
		
		Entity currEnt = this.getCurrentEntity();
		Process currProc = this.getCurrentProcess();
		String usuario = currProc.getCreator().getLogin().toString();
		User ucreador = currProc.getCreator();

		String[][] matriz = new String[15][7];
		
  		matriz[0][0] = "-froda";		matriz[0][1] = "-jartave";	matriz[0][2] = "-cfoucault";	matriz[0][3] = "-jrussomano";	matriz[0][4] = "-mezquerra";		matriz[0][5] = "-mmerelli";		matriz[0][6] = "-fnoboa";
  	//  	--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
  		matriz[1][0] = "-";				matriz[1][1] = "-";			matriz[1][2] = "brodriguez";	matriz[1][3] = "ppi";			matriz[1][4] = "mnunez";			matriz[1][5] = "admin";		 	matriz[1][6] = "fcairello";
  		matriz[2][0] = "-";				matriz[2][1] = "-";			matriz[2][2] = "jmignaco";		matriz[2][3] = "-";				matriz[2][4] = "sorrego";			matriz[2][5] = "-";				matriz[2][6] = "-";
  		matriz[3][0] = "-";				matriz[3][1] = "-";			matriz[3][2] = "vbonini";		matriz[3][3] = "agomez";		matriz[3][4] = "fnoboa";			matriz[3][5] = "-";				matriz[3][6] = "-";
  		matriz[4][0] = "-";				matriz[4][1] = "-";			matriz[4][2] = "lbarzilai";		matriz[4][3] = "glopez";		matriz[4][4] = "-";					matriz[4][5] = "-";				matriz[4][6] = "-";
  		matriz[5][0] = "-";				matriz[5][1] = "-";			matriz[5][2] = "asaif";			matriz[5][3] = "jrussomano";	matriz[5][4] = "jbraganca";			matriz[5][5] = "-";				matriz[5][6] = "-";
  		matriz[6][0] = "-";				matriz[6][1] = "-";			matriz[6][2] = "skucharski";	matriz[6][3] = "cfoucault";		matriz[6][4] = "mtilve";			matriz[6][5] = "-";				matriz[6][6] = "-";
  		matriz[7][0] = "-";				matriz[7][1] = "-";			matriz[7][2] = "yclames";		matriz[7][3] = "eluna";			matriz[7][4] = "mezquerra";			matriz[7][5] = "-";				matriz[7][6] = "-";
  		matriz[8][0] = "-";				matriz[8][1] = "-";			matriz[8][2] = "-";				matriz[8][3] = "froda";			matriz[8][4] = "mjacques";			matriz[8][5] = "-";				matriz[8][6] = "-";
  		matriz[9][0] = "-";				matriz[9][1] = "-";			matriz[9][2] = "-";				matriz[9][3] = "jtolve";		matriz[9][4] = "mmerelli";			matriz[9][5] = "-";				matriz[9][6] = "-";
  		matriz[10][0] = "-";			matriz[10][1] = "-";		matriz[10][2] = "-";			matriz[10][3] = "-";			matriz[10][4] = "-";				matriz[10][5] = "-";			matriz[10][6] = "-";
  		matriz[11][0] = "-";			matriz[11][1] = "-";		matriz[11][2] = "-";			matriz[11][3] = "-";			matriz[11][4] = "-";				matriz[11][5] = "-";			matriz[11][6] = "-";
  		matriz[12][0] = "-";			matriz[12][1] = "-";		matriz[12][2] = "-";			matriz[12][3] = "-";			matriz[12][4] = "-";				matriz[12][5] = "-";			matriz[12][6] = "-";
  		matriz[13][0] = "-";			matriz[13][1] = "-";		matriz[13][2] = "-";			matriz[13][3] = "-";			matriz[13][4] = "-";				matriz[13][5] = "-";			matriz[13][6] = "-";
  		matriz[14][0] = "-";			matriz[14][1] = "-";		matriz[14][2] = "-";			matriz[14][3] = "-";			matriz[14][4] = "-";				matriz[14][5] = "-";			matriz[14][6] = "-";
    
		   
		String buscado=usuario;
		String buscando = "";
		String encargado = "";
		boolean bandera = false;

		
		boolean notifica = true;
		
		for (int j = 0; j < matriz[0].length; j++) {
			for (int i = 0; i < matriz.length; i++) {
				
				buscando = matriz[i][j];
				
				if (buscando.equals(buscado)){	
					encargado = matriz[0][j].substring(1,matriz[0][j].length());
					
					
					bandera = true;
					break;
				}
			}
		}

		if (bandera) {
			currProc.setRol("JEFE_PROYECTO", encargado);
			currEnt.getAttribute("SE_JEFEPROYECTO").setValue(encargado);

			if (notifica) {
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

			}
		} else {
			String entidad = this.getCurrentEntity().getIdentifier();
			throw new BusClassException(
					" No se encontró su encargado directo, porfavor comuniquese con Miguel (miguel@fx2.com.uy).        Cod: "
							+ entidad);
		}

		
	}

}

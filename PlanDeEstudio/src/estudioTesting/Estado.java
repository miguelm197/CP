package estudioTesting;

import java.util.ArrayList;
import java.util.Collection;

import com.dogma.busClass.ApiaAbstractClass;
import com.dogma.busClass.BusClassException;
import com.dogma.busClass.object.Entity;
import com.dogma.busClass.object.User;

import estudio.Helpers;

public class Estado extends ApiaAbstractClass {

	@Override
	protected void executeClass() throws BusClassException {
		// TODO Auto-generated method stub
		
		Entity currEnt = this.getCurrentEntity(); //Obtengo entidad actual
		String tareaActual = this.getCurrentTask().getTaskTitle();
		
		//Defino el estado de la tarea (por donde anda el proceso)
		currEnt.getAttribute("ESTADO_ACTUAL_ESTUDIO").setValue(tareaActual);
		
		User creador = currEnt.getCreator();
		String nombreCreador = creador.getName();
			
		//Notifico al usuario que tiene una tarea para completar
				Collection<User> us = this.getCurrentTask().getGroup().getUsers();
				String titulo = currEnt.getAttribute("TITULO_SOL_ESTUDIO").getValueAsString();
				
				for (User u : us) {
						String mail = u.getEmail();
						Helpers.notificarTareaPendiente(this, nombreCreador, mail, titulo);
				}
	}

}

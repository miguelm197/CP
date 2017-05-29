package estudioTesting;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Timer;

import com.dogma.busClass.ApiaAbstractClass;
import com.dogma.busClass.BusClassException;
import com.dogma.busClass.object.Attribute;
import com.dogma.busClass.object.Entity;
import com.dogma.busClass.object.EntityFilter;
import com.dogma.busClass.object.Identifier;
import com.dogma.busClass.object.Task;
import com.dogma.busClass.object.User;
import com.dogma.busClass.object.Process;

public class EstadoInicial extends ApiaAbstractClass {

	@Override
	protected void executeClass() throws BusClassException {

		// Obtengo entidad actual y tarea actual
		Entity currEnt = this.getCurrentEntity();

		// Filtro para que no tome las entidades finalizadas
		EntityFilter ef1 = new EntityFilter("Finalizado", 2, EntityFilter.COLUMN_FILTER_NOT_LIKE);
		// Filtro para que tome sólo las entidades del usuario actual
		EntityFilter ef2 = new EntityFilter(this.getCurrentUser().getName(), 3, EntityFilter.COLUMN_FILTER_EQUAL);
		EntityFilter ef3 = new EntityFilter("Cancelado", 2, EntityFilter.COLUMN_FILTER_NOT_LIKE);
		EntityFilter ef4 = new EntityFilter("Rechazado", 2, EntityFilter.COLUMN_FILTER_NOT_LIKE);

		Collection<EntityFilter> colFilters1 = new ArrayList<EntityFilter>();
		colFilters1.add(ef1);
		colFilters1.add(ef2);
		colFilters1.add(ef3);
		colFilters1.add(ef4);

		// Busca entidades que creó el usuario
		Collection<Identifier> entQueCreoUsuario = this.findEntities("PLAN_ESTUDIO", colFilters1);
		int nroEntQueCreoUsuario = entQueCreoUsuario.size();

		if (nroEntQueCreoUsuario < 3) {
			
			// Seteo el usuario creador
			currEnt.getAttribute("P2_EST_USUARIOCREADOR_STR").setValue(currEnt.getCreator().getName());
			currEnt.getAttribute("P2_EST_SOLICITADOPOR").setValue(currEnt.getCreator().getName());

			// Seteo la fecha de creacion
			Attribute fechaCreacion = currEnt.getAttribute("P2_EST_FECHACREACION_STR");
			Helpers.setFecha(this, fechaCreacion);

			// Seteo el Título del proceso con los correspondientes datos
			String tema = currEnt.getAttribute("SE_TEMAINTERES").getValueAsString();
			User creador = currEnt.getCreator();
			String nombreCreador = creador.getName();
			String fechaSolicitud = currEnt.getCreateDate().toString();
			String diaSolicitud = fechaSolicitud.substring(8, 10);
			String mesSolicitud = fechaSolicitud.substring(5, 7);

			currEnt.getAttribute("TITULO_SOL_ESTUDIO").setValue("Capacitación " + tema + " - " + nombreCreador + " - " + diaSolicitud + "/" + mesSolicitud);
			
			//Pongo que el proceso no está finalizado
			currEnt.getAttribute("PROCESO_FINALIZADO_EST_STR").setValue("NO");

		
			 
			 
		} else {
			throw new BusClassException("Actualmente están en proceso tres solicitudes suyas. Debe aguardar"
					+ " que finalice al menos una para poder iniciar una nueva.");
		}
	}

}

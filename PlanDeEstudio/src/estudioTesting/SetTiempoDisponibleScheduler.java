package estudioTesting;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import com.dogma.busClass.ApiaAbstractClass;
import com.dogma.busClass.BusClassException;
import com.dogma.busClass.object.Identifier;
import com.dogma.busClass.object.User;
import com.dogma.busClass.object.Attribute;
import com.dogma.busClass.object.Entity;
import com.dogma.busClass.object.EntityFilter;

public class SetTiempoDisponibleScheduler extends ApiaAbstractClass {

	@Override
	protected void executeClass() throws BusClassException {

		EntityFilter ef = new EntityFilter("Finalizado", 2, EntityFilter.COLUMN_FILTER_NOT_LIKE);
		EntityFilter ef2 = new EntityFilter("Rechazado", 2, EntityFilter.COLUMN_FILTER_NOT_LIKE);
		EntityFilter ef3 = new EntityFilter("Cancelado", 2, EntityFilter.COLUMN_FILTER_NOT_LIKE);
		ArrayList<EntityFilter> colEf = new ArrayList<>();
		colEf.add(ef);
		colEf.add(ef2);
		colEf.add(ef3);

		Collection<Identifier> entidades = this.findEntities("PLAN_ESTUDIO", colEf);
		int nEnt = entidades.size();
		// this.addMessage("Nro. de entidades encontradas: " + nEnt);

		if (nEnt > 0) {
			Date fechaActual = new Date();

			for (Identifier id : entidades) {
				Entity ent = this.getEntity("PLAN_ESTUDIO", id.getPrefix(), id.getNumber(), id.getPostfix());
				Attribute attFechaInicio = ent.getAttribute("ECS_FECHAINICIO");
				Attribute attFechaFin = ent.getAttribute("ECS_FECHAFIN");

				if (attFechaFin.getValueAsString() != null && attFechaFin.getValueAsString() != "") {
					Date fechaInicio = (Date) attFechaInicio.getValue();
					Date fechaFin = (Date) attFechaFin.getValue();

					// this.addMessage("TD antes
					// "+ent.getAttribute("RA_TIEMPODISPONIBLE").getValueAsString());
					ent.getAttribute("RA_TIEMPODISPONIBLE").setValue(Helpers.diferenciaEnHoras(fechaFin, fechaActual));
					// this.addMessage("TD despu�s
					// "+ent.getAttribute("RA_TIEMPODISPONIBLE").getValueAsString());

					// Obtengo usuario creador
					User usuarioCreador = ent.getCreator();
					// Nombre, email de creador
					String nombreCreador = usuarioCreador.getName();
					String mailCreador = this.getUser("ppi").getEmail();
					String[] mailUsuarioCreador = { this.getUser("ppi").getEmail() };

					// Obtengo jefe de proyecto
					User jefeProyecto = null;
					String valuejefeProy = ent.getAttribute("SE_JEFEPROYECTO").getValueAsString();
					if (valuejefeProy.compareTo("Jos�") == 0)
						jefeProyecto = this.getUser("jrussomano");
					else if (valuejefeProy.compareTo("Jorge") == 0)
						jefeProyecto = this.getUser("jartave");
					else if (valuejefeProy.compareTo("Federico") == 0)
						jefeProyecto = this.getUser("froda");

					String nombreJefeProy = jefeProyecto.getName();
					String mailJefeP = this.getUser("ppi").getEmail();
					String[] mailJefeProy = { this.getUser("ppi").getEmail() };

					String titulo = ent.getAttribute("TITULO_SOL_ESTUDIO").getValueAsString();
					Double td = (Double) ent.getAttribute("RA_TIEMPODISPONIBLE").getValue();

					Double mitadInicioFin = (Double) Helpers.diferenciaEnHoras(fechaFin, fechaInicio)/2;
					Date fechaMitad = Helpers.sumarRestarHorasFecha(fechaInicio, mitadInicioFin.intValue());
					
					if (this.getCurrentEnvironment().compareTo("DEFAULT") == 0 && nombreCreador.compareTo("System Administrator") != 0) {
						if (td == 0) {
							this.sendMail(mailJefeProy, "Tiempo terminado para realizar capacitaci�n",
									"Hola " + nombreJefeProy + ",<br><br>Le informamos que ha terminado el tiempo de "
											+ nombreCreador
											+ " para realizar su capacitaci�n, correspondiente al proceso: " + titulo
											+ ".<br><br>Saludos,<br>Apia.");
							this.sendMail(mailUsuarioCreador, "Tiempo terminado para realizar capacitaci�n",
									"Le informamos "
											+ "que ha terminado el tiempo para realizar su capacitaci�n, correspondiente al proceso "
											+ titulo + ".<br><br>Saludos,<br>Apia.");
						}
						
						else if(fechaActual.compareTo(fechaMitad) >= 0){
							Helpers.notificarMitadCalendario(this, nombreCreador, mailCreador, mailJefeP, titulo);
						}

						else if (td % 240 == 0 || td == 120) {
							if (fechaActual.compareTo(fechaInicio) >= 0) {
								this.sendMail(mailUsuarioCreador, "Debe completar el avance en Apia",
										"Le informamos "
												+ "que debe ingresar a Apia y completar el avance de su estudio, correspondiente al proceso "
												+ titulo + ".<br><br>Muchas gracias.<br>Saludos,<br>Apia.");
							}
						}
					}
				}
			}

		}
	}

}

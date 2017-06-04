package estudio;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import com.dogma.busClass.ApiaAbstractClass;
import com.dogma.busClass.BusClassException;
import com.dogma.busClass.object.Identifier;
import com.dogma.busClass.object.User;

import estudio.Helpers;

import com.dogma.busClass.object.Attribute;
import com.dogma.busClass.object.Entity;
import com.dogma.busClass.object.EntityFilter;

// TODO: Auto-generated Javadoc
/**
 * Esta clase se ejecuta con el Scheduler.
 * Verifica los procesos vigentes. Si terminó el tiempo notifica al creador y jefe de proyecto
 * que terminó el tiempo.
 * Si llegó a mitad calendario (según Fecha de inicio y fin) notifica al creador y jefe de proyecto
 * que se llegó a mitad calendario.
 * Desde que corre el tiempo para realizar la capacitación, cada 10 días y 5 días antes de terminar notifica
 * al creador que debe registrar el avance.
 */
public class SetTiempoDisponibleScheduler extends ApiaAbstractClass {

	/* (non-Javadoc)
	 * @see com.dogma.busClass.ApiaAbstractClass#executeClass()
	 */
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

		if (nEnt > 0) {
			Date fechaActual = new Date();

			for (Identifier id : entidades) {
				Entity ent = this.getEntity("PLAN_ESTUDIO", id.getPrefix(), id.getNumber(), id.getPostfix());
				Attribute attFechaInicio = ent.getAttribute("ECS_FECHAINICIO");
				Attribute attFechaFin = ent.getAttribute("ECS_FECHAFIN");

				if (attFechaFin.getValueAsString() != null && attFechaFin.getValueAsString() != "") {
					Date fechaInicio = (Date) attFechaInicio.getValue();
					Date fechaFin = (Date) attFechaFin.getValue();

					ent.getAttribute("RA_TIEMPODISPONIBLE").setValue(Helpers.diferenciaEnHoras(fechaFin, fechaActual));

					// Obtengo usuario creador
					User usuarioCreador = ent.getCreator();
					String nombreCreador = usuarioCreador.getName();

					// Obtengo jefe de proyecto
					String encargado = ent.getAttribute("SE_JEFEPROYECTO").getValueAsString();
					Collection<User> usEncargado = this.getGroup(encargado).getUsers();
					String mailUsuarioCreador = usuarioCreador.getEmail();
					String[] EmailCreador = { mailUsuarioCreador };

					String titulo = ent.getAttribute("TITULO_SOL_ESTUDIO").getValueAsString();
					Double td = (Double) ent.getAttribute("RA_TIEMPODISPONIBLE").getValue();

					Double mitadInicioFin = (Double) Helpers.diferenciaEnHoras(fechaFin, fechaInicio) / 2;
					Date fechaMitad = Helpers.sumarRestarHorasFecha(fechaInicio, mitadInicioFin.intValue());

					
					
					//-------------------------------------------------------------------------------------------------------
					//-------------------------------------------------------------------------------------------------------
					
					boolean notificarA = true; // NOTIFICAR AL JEFE DE PROYECTO QUE SE ACABÓ EL TIEMPO
					boolean notificarB = true; // NOTIFICAR AL USUARIO CREADOR  QUE SE ACABÓ EL TIEMPO
					boolean notificarC = true; // NOTIFICAR AL USUARIO CREADOR  QUE SE FINALIZÓ LA MITAD DE LA CAPACITACIÓN
					boolean notificarD = true; // NOTIFICAR AL JEFE DE PROYECTO QUE SE FINALIZÓ LA MITAD DE LA CAPACITACIÓN
					boolean notificarE = true; // NOTIFICAR AL USUARIO CREADOR  QUE DEBE COMPLETAR EL AVANCE EN APIA
					
					//-------------------------------------------------------------------------------------------------------
					//-------------------------------------------------------------------------------------------------------
					
					
					if (td == 0) {

						if (notificarA) {
							for (User u : usEncargado) {
								String mail = u.getEmail();
								String nombreJefeProy = u.getName();
								String[] mailEnviar = { mail };
								this.sendMail(mailEnviar, "Tiempo terminado para realizar capacitación", "Hola "
										+ nombreJefeProy + ",<br><br>Le informamos que ha terminado el tiempo de "
										+ nombreCreador + " para realizar su capacitación, correspondiente al proceso: "
										+ titulo + ".<br><br>Saludos,<br>Apia.");
							}
						}

						if (notificarB) {
							this.sendMail(EmailCreador, "Tiempo terminado para realizar capacitación",
									"Le informamos "
											+ "que ha terminado el tiempo para realizar su capacitación, correspondiente al proceso "
											+ titulo + ".<br><br>Saludos,<br>Apia.");
						}

					}

					else if (fechaActual.compareTo(fechaMitad) >= 0) {

						if (notificarC) {
							this.sendMail(EmailCreador, "Mitad de la capacitación finalizada",
									"Según los días que se han usado en la capacitación, aproximadamente la mitad del proceso "
											+ titulo + " ha finalizado correctamente."
											+ "<br><br>Gracias por usar Apia.<br>Saludos.");
						}

						if (notificarD) {
							for (User u : usEncargado) {
								String mail = u.getEmail();
								String[] mailEnviar = { mail };
								this.sendMail(mailEnviar, "Mitad de la capacitación finalizada",
										"Según los días que se han usado en la capacitación, aproximadamente la mitad del proceso "
												+ titulo + " ha finalizado correctamente."
												+ "<br><br>Gracias por usar Apia.<br>Saludos.");

							}
						}

					}

					else if (td % 240 == 0 || td == 120) {

						if (notificarE) {
							this.sendMail(EmailCreador, "Debe completar el avance en Apia",
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

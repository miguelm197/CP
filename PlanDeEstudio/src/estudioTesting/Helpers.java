package estudioTesting;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.dogma.busClass.ApiaAbstractClass;
import com.dogma.busClass.BusClassException;
import com.dogma.busClass.object.Attribute;

public class Helpers {
	
	static boolean notificarInicioProceso = true;
	
	
	//INICIO DE PROCESO
	public static void notificarInicioProcesoEst(ApiaAbstractClass apia, String nombreCreador, String mail, String titulo, String tema, String jefe, String fechaInicio, String fechaFin, String comentarios)
			throws BusClassException {

		if (notificarInicioProceso) {

			String[] mailEnviar = { mail };
			apia.sendMail(mailEnviar, "TESTING - Solicitud de capacitación para " + nombreCreador + " iniciada ",
					"Se ha creado correctamente la solicitud de capacitación: " + titulo + ".<br>" + "Tema de interés: "
							+ tema + ".<br>" + "Jefe de proyecto: " + jefe + ".<br>"
							+ "Fecha de inicio de estudio tentativa: " + fechaInicio + ".<br>"
							+ "Fecha de fin de estudio tentativa: " + fechaFin + ".<br>" + "Comentarios: " + comentarios
							+ "<br><br>Saludos,<br>Apia.");
		}
	}

	
	
	
	
	
	public static void notificarTareaPendiente(ApiaAbstractClass apia, String nombreCreador, String mail, String titulo)
			throws BusClassException {
		
		String link = "http://proyectos.fx2.com.uy:8080/Apia";
		if (apia.getCurrentEnvironment().compareTo("DEFAULT") != 0) {
			String[] mailEnviar = { mail };
			apia.sendMail(mailEnviar, "Tarea en Apia",
					"Te han asignado una tarea en Apia correspondiente al proceso: " + titulo + "<br><br>"
							+ "Por favor ingresa y completa la tarea.<br>" + "Puedes verla aquí: " + link
							+ "<br><br>Muchas gracias.<br>Saludos,<br>Apia.");
		}
	}

	
	
	
	
	
	
	
	public static void notificarFinalizacionProc(ApiaAbstractClass apia, String nombreCreador, String mailCreador,	String mailJefeP, String titulo) throws BusClassException {

		//Se deshabilitò el envìo de notificaciones por acà, se cambiò a la misma clase Finalizacion
		
	}

	public static void notificarFinalizacionCR(ApiaAbstractClass apia, String nombreCreador, String mailCreador,String mailJefeP, String titulo, String comentarios) throws BusClassException {
		
		//Se deshabilitò el envìo de notificaciones por acà, se cambiò a la misma clase FinalizacionCR
		
	}
	
	
	
	
	
	
	
	
	public static void notificarSolicitoMasDatos(ApiaAbstractClass apia, String nombreCreador, String mailCreador,
			String mailJefeP, String titulo, String comentarios) throws BusClassException {
		if (apia.getCurrentEnvironment().compareTo("DEFAULT") != 0) {

			String[] mailEnviar = { mailCreador };
			String[] mailEnviarJefeP = { mailJefeP };

			apia.sendMail(mailEnviar, "Se solicitaron más datos", "Se ha solicitado que ingreses/modifiques datos de la solicitud de capacitación que has realizado, correspondiente al proceso " + titulo
					+ ".<br>Comentarios del jefe de proyecto: " + comentarios + "<br>Tiene pendiente la tarea en Apia.<br><br>Muchas gracias.<br>Saludos,<br>Apia.");
			
			apia.sendMail(mailEnviarJefeP, "Solicitaste más datos", "Has solicitado que se ingresen/modifiquen los datos de la solicitud de capacitación correspondiente al proceso: "
					+ titulo + "." + "<br><br>Gracias por usar Apia.<br>Saludos.");
		}
	}

	public static void notificarCancelacionProc(ApiaAbstractClass apia, String nombreCreador, String mailCreador,
			String mailJefeP, String titulo) throws BusClassException {
		if (apia.getCurrentEnvironment().compareTo("DEFAULT") != 0) {

			String[] mailEnviar = { mailCreador };
			String[] mailEnviarJefeP = { mailJefeP };

			apia.sendMail(mailEnviar, "Capacitación cancelada", "Se ha cancelado el proceso " + titulo
					+ " correctamente. " + "<br><br>Gracias por usar Apia.<br>Saludos.");
			apia.sendMail(mailEnviarJefeP, "Capacitación cancelada", "Se ha cancelado correctamente el proceso: "
					+ titulo + "." + "<br><br>Gracias por usar Apia.<br>Saludos.");
		}
	}

	public static void notificarMitadHoras(ApiaAbstractClass apia, String nombreCreador, String mailCreador,
			String mailJefeP, String titulo) throws BusClassException {
		if (apia.getCurrentEnvironment().compareTo("DEFAULT") != 0) {

			String[] mailEnviar = { mailCreador };
			String[] mailEnviarJefeP = { mailJefeP };

			apia.sendMail(mailEnviar, "Mitad de la capacitación finalizada", "Según las horas que se han usado en la capacitación, aproximadamente la mitad del proceso "
					+ titulo + " ha finalizado correctamente." + "<br><br>Gracias por usar Apia.<br>Saludos.");
			apia.sendMail(mailEnviarJefeP, "Mitad de la capacitación finalizada",
					"Según las horas que se han usado en la capacitación, aproximadamente la mitad del proceso " + titulo + " ha finalizado correctamente."
							+ "<br><br>Gracias por usar Apia.<br>Saludos.");
		}
	}
	
	public static void notificarMitadCalendario(ApiaAbstractClass apia, String nombreCreador, String mailCreador,
			String mailJefeP, String titulo) throws BusClassException {
		if (apia.getCurrentEnvironment().compareTo("DEFAULT") != 0) {

			String[] mailEnviar = { mailCreador };
			String[] mailEnviarJefeP = { mailJefeP };

			apia.sendMail(mailEnviar, "Mitad de la capacitación finalizada", "Según los días que se han usado en la capacitación, aproximadamente la mitad del proceso "
					+ titulo + " ha finalizado correctamente." + "<br><br>Gracias por usar Apia.<br>Saludos.");
			apia.sendMail(mailEnviarJefeP, "Mitad de la capacitación finalizada",
					"Según los días que se han usado en la capacitación, aproximadamente la mitad del proceso " + titulo + " ha finalizado correctamente."
							+ "<br><br>Gracias por usar Apia.<br>Saludos.");
		}
	}

	public static void notificarSuperoHoras(ApiaAbstractClass apia, String nombreCreador, String mailCreador,
			String mailJefeP, String titulo) throws BusClassException {
		if (apia.getCurrentEnvironment().compareTo("DEFAULT") != 0) {

			String[] mailEnviar = { mailCreador };
			String[] mailEnviarJefeP = { mailJefeP };

			apia.sendMail(mailEnviar, "Se superó la cantidad de horas asignadas",
					"Se superó la cantidad de horas asignadas al proceso " + titulo + "."
							+ "<br><br>Saludos,<br>Apia.");
			apia.sendMail(mailEnviarJefeP, "Se superó la cantidad de horas asignadas",
					"Se superó la cantidad de horas asignadas al proceso " + titulo + "."
							+ "<br><br>Saludos,<br>Apia.");
		}
	}

	public static void setFecha(ApiaAbstractClass apia, Attribute attFecha) throws BusClassException {
		String f = Helpers.getFechaActual();
		// apia.addMessage(f);
		attFecha.setValue(f);
	}

	// Metodo usado para obtener la fecha actual
	// @return Retorna un <b>STRING</b> con la fecha actual formato "dd/MM/yyyy"
	public static String getFechaActual() {
		Date ahora = new Date();
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		return formateador.format(ahora);
	}

	public static Double diferenciaEnHoras(Date fechaMayor, Date fechaMenor) {
		long diferenciaEn_ms = fechaMayor.getTime() - fechaMenor.getTime();
		long horas = diferenciaEn_ms / (1000 * 60 * 60);
		if (horas < 0)
			return 0.0;
		else
			return (double) horas;
	}

	public static Double diferenciaEnDias(Date fechaMayor, Date fechaMenor) {
		long diferenciaEn_ms = fechaMayor.getTime() - fechaMenor.getTime();
		long dias = diferenciaEn_ms / (1000 * 60 * 60 * 24);
		return (double) dias;
	}

	public static Date sumarRestarHorasFecha(Date fecha, int horas) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha); // Configuramos la fecha que se recibe

		calendar.add(Calendar.HOUR, horas); // numero de horas a añadir, o
											// restar en caso de horas<0

		return calendar.getTime(); // Devuelve el objeto Date con las nuevas
									// horas añadidas

	}
	
	

}

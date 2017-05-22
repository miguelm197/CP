package estudio;

import java.util.ArrayList;
import java.util.Collection;

import com.dogma.busClass.ApiaAbstractClass;
import com.dogma.busClass.BusClassException;
import com.dogma.busClass.object.Entity;
import com.dogma.busClass.object.EntityFilter;
import com.dogma.busClass.object.Identifier;

/**
 * Setea el atributo SolicitadoPor con el creador del proceso.
 */
public class SetearSolicitadoPor extends ApiaAbstractClass {

	@Override
	protected void executeClass() throws BusClassException {
		// TODO Auto-generated method stub
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

			for (Identifier id : entidades) {
				Entity ent = this.getEntity("PLAN_ESTUDIO", id.getPrefix(), id.getNumber(), id.getPostfix());
				ent.getAttribute("P2_EST_SOLICITADOPOR").setValue(ent.getCreator().getName());
			}
		}
	}

}

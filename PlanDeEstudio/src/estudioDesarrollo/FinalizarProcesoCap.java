package estudioDesarrollo;

import com.dogma.busClass.ApiaAbstractClass;
import com.dogma.busClass.BusClassException;

public class FinalizarProcesoCap extends ApiaAbstractClass{
	@Override
	protected void executeClass() throws BusClassException {
		
		this.getCurrentProcess().finalizeProcess();
	}

}

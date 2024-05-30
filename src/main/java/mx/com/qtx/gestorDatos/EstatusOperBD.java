package mx.com.qtx.gestorDatos;


import org.hibernate.exception.LockAcquisitionException;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.RollbackException;
import jakarta.persistence.TransactionRequiredException;

public enum EstatusOperBD {
	OK,
	OPER_JPA_ESTADO_ILEGAL,
	LLAVE_DUPLICADA,
	REGISTRO_INEXISTENTE,
	CONEXION_NO_DISPONIBLE,
	FALLA_COMMIT,
	LOCK_BD_NO_DISPONIBLE,
	PROBLEMA_AUTENTICACION, 
	FALLA_JPA, 
	TRANSACCION_REQUERIDA, 
	FALLA_JSE, 
	NO_EXISTE;
	
	public String getMensaje() {
		switch (this) {
		case LLAVE_DUPLICADA: return "Ya hay un registro con la misma llave";
		case OK: return "Operaci�n exitosa";
		default: return this.toString();
		}
	}
	public static EstatusOperBD getStatus(Exception ex) {
		if(ex instanceof EntityExistsException)
			return EstatusOperBD.LLAVE_DUPLICADA;
		if(ex instanceof RollbackException) {
			 Throwable causaEx = ex.getCause();
			 if(causaEx == null)
				 return EstatusOperBD.FALLA_COMMIT;
			 while(causaEx != null)
				 causaEx = causaEx.getCause();
			 if(causaEx instanceof LockAcquisitionException)
				 return EstatusOperBD.LOCK_BD_NO_DISPONIBLE;
			 return EstatusOperBD.FALLA_COMMIT;
		}
		if(ex instanceof TransactionRequiredException)
			return EstatusOperBD.TRANSACCION_REQUERIDA;
		if(ex instanceof PersistenceException)
			return EstatusOperBD.FALLA_JPA;
		return EstatusOperBD.FALLA_JSE;
		
	}

}

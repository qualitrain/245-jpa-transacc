package mx.com.qtx.gestorDatos;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import jakarta.persistence.*;
import mx.com.qtx.jpa.dominio.*;

public class ServicioDatos {
	
	private static final String UNIDAD_PERSISTENCIA = "bdEjmTransacciones";
	private EntityManagerFactory fabrica;
	
	public ServicioDatos() {
		this.fabrica = Persistence.createEntityManagerFactory(UNIDAD_PERSISTENCIA);
	}
	
	public Cliente getClienteXID(long id) {
		Cliente unCliente = null;
		try (EntityManager em = fabrica.createEntityManager() ){
		  unCliente = em.find(Cliente.class, id);
		}
	    return unCliente;		
	}
	
	public void cerrar() {
		this.fabrica.close();
	}
	
	public Vendedor getVendedorXID(long id) {
		Vendedor unVendedor = null;
		try (EntityManager em = fabrica.createEntityManager() ){
		     unVendedor = em.find(Vendedor.class, id);
		}
	    return unVendedor;		
	}
	
	public Articulo getArticuloXID(String id) {
		Articulo unArticulo = null;
		try (EntityManager em = fabrica.createEntityManager() ){
		    unArticulo = em.find(Articulo.class, id);
		}
	    return unArticulo;		
	}
	
	public boolean yaExisteCliente(Cliente p) {
		try (EntityManager em = fabrica.createEntityManager() ){
			Cliente clienteBD = em.find(Cliente.class, p.getNumCte());
			if(clienteBD != null) // Ya existe en BD!
				return true;
			else
				return false;
		}
	}
	
	public boolean yaExisteVendedor(Vendedor p) {
		try (EntityManager em = fabrica.createEntityManager() ){
			Vendedor vendedorBD = em.find(Vendedor.class, p.getNumVendedor());
			if(vendedorBD != null) // Ya existe en BD!
				return true;
			else
				return false;
		}
	}
	
	public EstatusOperBD insertarVendedor(Vendedor nvoVendedor) {
		if(this.yaExisteVendedor(nvoVendedor))
			return EstatusOperBD.LLAVE_DUPLICADA;		
		try (EntityManager em = fabrica.createEntityManager() ){
			EntityTransaction transaccion = em.getTransaction();
			transaccion.begin();
			em.persist(nvoVendedor);
			try {
				transaccion.commit();
			}
			catch(Exception ex) {
				return EstatusOperBD.getStatus(ex);
			}
		}
		return EstatusOperBD.OK;
	}

	public EstatusOperBD actualizarCliente(Cliente p) { //Premisa: p YA EXISTE en la BD
		try (EntityManager em = this.fabrica.createEntityManager() ){
			EntityTransaction transaccion = em.getTransaction();
			transaccion.begin();
			Cliente pMerged = em.merge(p);
			em.persist(pMerged);
			try {
				transaccion.commit();
			}
			catch(Exception ex) {
				return EstatusOperBD.getStatus(ex);
			}
		}
		return EstatusOperBD.OK;
	}

	public EstatusOperBD borrarCliente(Cliente p) {
		if (this.yaExisteCliente(p) == false)
			return EstatusOperBD.REGISTRO_INEXISTENTE;
		
		try ( EntityManager em = this.fabrica.createEntityManager() ){
			EntityTransaction transaccion = em.getTransaction();
			transaccion.begin();
			Cliente pMerged = em.merge(p);
			try {
				em.remove(pMerged);
				transaccion.commit();
			}
			catch(Exception ex) {
				return EstatusOperBD.getStatus(ex);
			}
		}
		return EstatusOperBD.OK;
	}
	
	public EstatusOperBD registrarVenta(Venta nuevaVta) {
		try ( EntityManager em = this.fabrica.createEntityManager() ){
			EntityTransaction transaccion = em.getTransaction();
			transaccion.begin();
			
			Cliente clienteMerge = em.merge(nuevaVta.getCliente());
			double nuevoSaldo = clienteMerge.getSaldo() + nuevaVta.getTotal();
			clienteMerge.setSaldo(nuevoSaldo);
			em.persist(clienteMerge);
			
			Vendedor vendedorMerge = em.merge(nuevaVta.getVendedor());
			long nVtas = vendedorMerge.getnVtas() + 1;
			vendedorMerge.setnVtas(nVtas);
			em.persist(vendedorMerge);
			
			em.persist(nuevaVta);
			
			for(DetalleVenta detI : nuevaVta.getDetalleVentas()) {
				Articulo artMergeI = em.merge(detI.getArticulo());
				int  existencia = artMergeI.getExistencia() - detI.getCantidad();
				artMergeI.setExistencia(existencia);
				em.persist(artMergeI); 
			}
			try {
				transaccion.commit();
			}
			catch(Exception ex) {
				System.out.println("**************" + " ERROR!! en ServicioDatos.RegistrarVenta" + "******************");
				System.out.println("**************" + ex.getClass().getName() + "******************");
				return EstatusOperBD.getStatus(ex);
			}
		}
		return EstatusOperBD.OK;
	}
	
	public List<Cliente> getAllClientes(){
		try ( EntityManager em = this.fabrica.createEntityManager() ){
			@SuppressWarnings("unchecked")
			List<Cliente> clientes = (List<Cliente>) em.createNamedQuery("Cliente.findAll")
															.getResultList();
		    return clientes;		
		}
	}
	
	public List<Vendedor> getAllVendedores(){
		try ( EntityManager em = this.fabrica.createEntityManager() ){
			@SuppressWarnings("unchecked")
			List<Vendedor> vendedores = (List<Vendedor>) em.createNamedQuery("Vendedor.findAll")
															.getResultList();
		    return vendedores;
		}
	}
	
	public List<Articulo> getAllArticulos(){
		try ( EntityManager em = this.fabrica.createEntityManager() ){
			@SuppressWarnings("unchecked")
			List<Articulo> articulos = (List<Articulo>) em.createNamedQuery("Articulo.findAll")
															.getResultList();
		    return articulos;
		}
	}
	
	public Map<String,String> getAllDescArticulos(){
		List<Articulo> articulos = this.getAllArticulos();
		Map<String,String> descripciones = new TreeMap<>();
		for(Articulo artI : articulos) {
			descripciones.put(artI.getDescripcionExtendida(),artI.getCveArticulo());
		}
		return descripciones;
	}

}

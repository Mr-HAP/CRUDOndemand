package testOD;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import cl.com.ondemand.Folder;
import cl.com.ondemand.IndBusqueda;
import cl.com.ondemand.metodos.Folders;

public class testOD {

	public static void main(String[] args) {
        try {
        	
        	Folder f = new Folder();
        	Folders fo = new Folders();
        	f.setFolder("CUENTAS_AR_NEW");

        	//Lista Resultados de una Busqueda +++++++++++++++++++++++++++++++++++++++++++++
        	String busqueda = "3";
        	List<IndBusqueda> indicesBusqueda = new ArrayList<IndBusqueda>();
        	indicesBusqueda=fo.listaIndicesBusqueda(f.getFolder());
        	
        	List<IndBusqueda> paramBusqueda = new ArrayList<IndBusqueda>();
        	
        	ListIterator<IndBusqueda> iterador = indicesBusqueda.listIterator(); 
        	
        	while(iterador.hasNext() ) {
        		IndBusqueda b = (IndBusqueda) iterador.next();
        		
        		if(b.getCampo().equals("num_cartola")){
        			b.setParametro(busqueda);
        			System.out.println(b.getCampo()+" - "+b.getParametro());
        			paramBusqueda.add(b);
        		}
        	}
        	List<String> resultados = new ArrayList<String>();
        	resultados=fo.buscaArchivo(f.getFolder(),paramBusqueda);
        	ListIterator<String> itera= resultados.listIterator(); 
        	while(itera.hasNext() ) {
        		String bs = (String) itera.next();
        		System.out.println("Resultado: "+bs);
        	}
        	
        	//Lista Folders++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//        	List<Folder> listaFolders = new ArrayList<Folder>();
//        	listaFolders=fo.listaFolders();
//
//        	ListIterator<Folder> iterador = listaFolders.listIterator(); 
//        	while(iterador.hasNext() ) {
//        		Folder b = (Folder) iterador.next();
//        		System.out.println(b.getFolder()+" - "+b.getDescripcionFolder());
//        	}
        
        	//Lista Indices de Busqueda de un folder +++++++++++++++++++++++++++++++++++++++++++++
//        	List<String> listaIndBusqueda = new ArrayList<String>();
//        	listaIndBusqueda=fo.listaIndicesBusqueda(f.getFolder());
//
//        	ListIterator<String> iterador = listaIndBusqueda.listIterator(); 
//        	while(iterador.hasNext() ) {
//        		String b = iterador.next();
//        		System.out.println(b);
//        	}

        	
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	

}

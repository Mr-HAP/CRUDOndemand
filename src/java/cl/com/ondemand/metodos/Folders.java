package cl.com.ondemand.metodos;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import cl.com.ondemand.Folder;
import cl.com.ondemand.IndBusqueda;

import com.ibm.edms.od.ODCriteria;
import com.ibm.edms.od.ODException;
import com.ibm.edms.od.ODFolder;
import com.ibm.edms.od.ODHit;
import com.ibm.edms.od.ODServer;

public class Folders {
	Conexion od = new Conexion();
	ODServer odc= od.conecta();
	
	/*	
	*	Metodo entrega un listado de los campos de busqueda de un Folder en especifico.
	*/	
	public List<IndBusqueda> listaIndicesBusqueda(String folder){
		
		List<IndBusqueda> indicesBusqueda = new ArrayList<IndBusqueda>();
		
		try{
			
	        ODFolder odFolder = odc.openFolder(folder);
	        IndBusqueda ind = null;
	        for (Enumeration criteria_enum = odFolder.getCriteria(); criteria_enum.hasMoreElements();)
	        {
	        	ind = new IndBusqueda();
	        	ODCriteria criteria = (ODCriteria) criteria_enum.nextElement();
	        	if (criteria.isQueryable())
	        		ind.setCampo(criteria.getName());
	        		indicesBusqueda.add(ind);
	        	}
		}
		catch (ODException od){
       	 System.out.println(od.getErrorId());
       	 System.out.println(od.getErrorMsg());
            od.printStackTrace();
       }
       catch (Exception e) {
       
           System.out.println(e.getMessage());
           e.printStackTrace();
       }
		return indicesBusqueda;
	}

	/*
	*	Metodo entrega un listado de los Folders existentes
	*/	
	public List<Folder> listaFolders(){
		
		List<Folder> listaFolders = new ArrayList<Folder>();
		
		try{
			Folder fo = null;
			for (Enumeration folder_enum = odc.getFolders();folder_enum.hasMoreElements();)
			{
				fo = new Folder();
				ODFolder folder = (ODFolder) folder_enum.nextElement();
				fo.setFolder(folder.getName());
				fo.setDescripcionFolder(folder.getDescription());
				listaFolders.add(fo);
			}
			
		}catch (ODException od){
       	 System.out.println(od.getErrorId());
       	 System.out.println(od.getErrorMsg());
         od.printStackTrace();
         return null;
       }catch (Exception e) {
       
           System.out.println(e.getMessage());
           e.printStackTrace();
       }return listaFolders;

	}

	/*
	 * Metodo realiza una busqueda en un Folder Especifico
	 */
	public List<String> buscaArchivo(String folder, List<IndBusqueda> indices){
		
		List<String> resultado = new ArrayList<String>();
		
		try{
	        ODFolder odFolder = odc.openFolder(folder);
            //Consulta un/unos Documento
            String whereClause = "WHERE num_cuenta = 'E-0000817-22' AND num_cartola='1'";
            Vector searchResults = odFolder.search(whereClause);
            Iterator resultados = searchResults.iterator();
            	
            while(resultados.hasNext()){ //Se listan los Aplication del APG
            	ODHit ap = (ODHit)resultados.next();
            	//System.out.println("App: "+ap.getDocId() + "Cuenta : "+ap.getDocumentName());
            	String  xx = "App: "+ap.getDocId() + "Cuenta : "+ap.getDocumentName();
            	resultado.add(xx);
            	
//            	byte [] docBytes = ap.getDocument();
//            	Date date = new Date();
//            	String OUTPUT_FILE_NAME = "C:/doc/"+ap.getDocumentName()+"a.txt";
//            	 FileOutputStream fos = new FileOutputStream(OUTPUT_FILE_NAME);
//            	    fos.write(docBytes);
//            	    fos.close();
			}
            
		}
		catch (ODException od){
       	 System.out.println(od.getErrorId());
       	 System.out.println(od.getErrorMsg());
            od.printStackTrace();
       }
       catch (Exception e) {
       
           System.out.println(e.getMessage());
           e.printStackTrace();
       }
		return resultado;

	}
}

package testOD;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Enumeration;


import java.util.Iterator;

import com.ibm.edms.od.ODApplicationGroup;
import com.ibm.edms.od.ODConfig;
import com.ibm.edms.od.ODException;
import com.ibm.edms.od.ODServer;
import com.ibm.edms.od.ODFolder;

import java.util.Vector;

import com.ibm.edms.od.*;

public class ConnectOD {
//    public static void main(String[] args) {
//        try {
//            connect();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
 
    public static void connect() {
        ODConfig odConfig = new ODConfig();
        ODServer odServer;
 
        if(odConfig == null)
            return;
        else
        {
            try {
                odServer = new ODServer( odConfig );
                odServer.initialize( "ConnectOD" ); // Necesario un nombre para inicializar la conexion
                System.out.println( "Logeando ..." );
                odServer.setPort(1445);                                 // Puerto del servidor
                odServer.logon( "190.243.31.84", "admin", "P4ssw0rd.1" ); // Nombre del servidor y credenciales
 
                // Se obtiene Un aplication Group especifico
                ODApplicationGroup enu = odServer.getApplicationGroup("CUENTAS_AR_NEW");
                System.out.println("AppGrp"+enu.getDescription());
                String[] app = enu.getApplicationNames();
                int x= 0;
                while(x<app.length ){ //Se listan los Aplication del APG
                	String ap = app[x];
                	System.out.println("App: "+ap);
                	
                	x++;
                }

                // Se obtiene Un Folder especifico
                ODFolder odFolder = odServer.openFolder("CUENTAS_AR_NEW");
                // get list of odcriteria and iterate
                for (Enumeration criteria_enum = odFolder.getCriteria(); criteria_enum.hasMoreElements();)
                {
                	//ODCriteriaBean odCriteriaBean = new ODCriteriaBean();
                	ODCriteria criteria = (ODCriteria) criteria_enum.nextElement();
                	// make sure this is elgible for query display
                	if (criteria.isQueryable())
                	{
                		System.out.println(criteria.getName());
                	}
                }
                                
                //Consulta un/unos Documento
                String whereClause = "WHERE num_cuenta = 'E-8895104-89' AND num_cartola='2'";
                Vector searchResults = odFolder.search(whereClause);
                Iterator resultados = searchResults.iterator();
                while(resultados.hasNext()){ //Se listan los Aplication del APG
                	ODHit ap = (ODHit)resultados.next();
                	System.out.println("App: "+ap.getDocId() + "Cuenta : "+ap.getDocumentName());
                	byte [] docBytes = ap.getDocument();
                	Date date = new Date();

                	String OUTPUT_FILE_NAME = "C:/doc/"+ap.getDocumentName()+"a.txt";
                	 FileOutputStream fos = new FileOutputStream(OUTPUT_FILE_NAME);
                	    fos.write(docBytes);
                	    fos.close();
                }
                
                
                // Desconexion
                //------------------------------------------------------------------------------
                System.out.println( "Logging off..." );
                odServer.logoff( );
                odServer.terminate( );
                System.out.println( "" );
                System.out.println( "---------------------------------------------------" );
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
        }
    }
}

package cl.com.ondemand.metodos;

import com.ibm.edms.od.ODConfig;
import com.ibm.edms.od.ODException;
import com.ibm.edms.od.ODServer;

public class Conexion {

	private	String usuario= "admin";
    private	String clave= "P4ssw0rd.1";
    private	String servidor= "190.243.31.84";
    private	ODServer odServer;    	
    private ODConfig odConfig;
    /**
     * Metodo que abre la coneccion con OnDemand
     */
    public ODServer conecta() {
        odServer=null;    	
        odConfig = new ODConfig();
        if(odConfig == null)
            return odServer;
        else
            System.out.println( "---------------------------- \n Conectando \n ----------------------------" );
        {
            try {
                odServer = new ODServer(odConfig);
                odServer.initialize("ConnectOD"); 		  // Necesario un nombre para inicializar la conexion
                odServer.setPort(1445);                   // Puerto del servidor
                odServer.logon(servidor, usuario, clave); // Nombre del servidor y credenciales
                System.out.println ("OnDemand server " + odServer.getServerName () + " esta logeado");
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
		return odServer;
    }
    
    public void desconectar() {
    	
        System.out.println( "----------------------------" );
        System.out.println( "Desconectando" );
        System.out.println( "----------------------------" );
        		try {
            odServer.logoff();
            odServer.terminate();
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
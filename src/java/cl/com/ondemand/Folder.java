package cl.com.ondemand;

import java.util.ArrayList;
import java.util.List;

public class Folder {
	
	private String folder;
	private String descripcionFolder;
	private List<String> indicesBusqueda = new ArrayList<String>();


	public String getDescripcionFolder() {
		return descripcionFolder;
	}
	public void setDescripcionFolder(String descripcionFolder) {
		this.descripcionFolder = descripcionFolder;
	}

	public List<String> getIndicesBusqueda() {
		return indicesBusqueda;
	}
	public void setIndicesBusqueda(List<String> indicesBusqueda) {
		this.indicesBusqueda = indicesBusqueda;
	}

	public String getFolder() {
		return folder;
	}
	
	public void setFolder(String folder) {
		this.folder = folder;
	}

	
}

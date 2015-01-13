package snapshot;

import drawing.DrawHostRange;

/**
 * object which stores info about analysis's date
 * 
 * Copyright (c) 2014 ICT Consulting
 */
public class Data {

	private String anno;
	private String mese;
	private String giorno;
	private String ora;
	private String minuto;
	private DrawHostRange posizioneHostRange = null;
	
	
	public Data(String a, String m, String g, String o, String mm){
		this.anno=a;
		this.mese=m;
		this.giorno=g;
		this.ora=o;
		this.minuto=mm;
	}
	
	
	
	public String getAnno() {
		return anno;
	}


	public void setAnno(String anno) {
		this.anno = anno;
	}


	public String getMese() {
		return mese;
	}


	public void setMese(String mese) {
		this.mese = mese;
	}


	public String getGiorno() {
		return giorno;
	}


	public void setGiorno(String giorno) {
		this.giorno = giorno;
	}


	public String getOra() {
		return ora;
	}


	public void setOra(String ora) {
		this.ora = ora;
	}


	public String getMinuto() {
		return minuto;
	}


	public void setMinuto(String minuto) {
		this.minuto = minuto;
	}



	public DrawHostRange getPosizioneHostRange() {
		return posizioneHostRange;
	}



	public void setPosizioneHostRange(DrawHostRange posizioneHostRange) {
		this.posizioneHostRange = posizioneHostRange;
	}
	
}

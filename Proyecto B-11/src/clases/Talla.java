package clases;

public enum Talla {
	
	S("S",48,2),M("M",50,4),L("L",52,8),XL("XL",54,12),XXL("XXL",56,16);
	
	 private final String letra;
	    private final int numeroEU;
	    private final int numeroUSA;

	    Talla(String letra, int numeroEU, int numeroUSA) {
	        this.letra = letra;
	        this.numeroEU =numeroEU ;
	        this.numeroUSA=numeroUSA;
	    }

	    public String getLetra() {
	        return letra;
	    }

	    public int getNumeroEU() {
	        return numeroEU;
	    }
	    
	    public int getNumeroUSA() {
	        return numeroUSA;
	    }
	}



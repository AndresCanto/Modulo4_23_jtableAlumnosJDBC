package sesion3_jtable;

public class Alumno 
{
	final int matricula;
	String nombre;
	int diagnostico;
	int modulo;
	
	public Alumno(int matricula, String nombre, int diagnostico, int modulo) 
	{
		this.matricula = matricula;
		this.nombre = nombre;
		this.diagnostico = diagnostico;
		this.modulo = modulo;
	}
	
	public int getMatricula() {
		return matricula;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getDiagnostico() {
		return diagnostico;
	}
	public void setDiagnostico(int diagnostico) {
		this.diagnostico = diagnostico;
	}
	public int getModulo() {
		return modulo;
	}
	public void setModulo(int modulo) {
		this.modulo = modulo;
	}
}

package sesion3_jtable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Controlador implements ActionListener
{
	Vista v;
	Modelo m;
	public Controlador(Vista v, Modelo m) 
	{
		this.v = v;
		this.m = m;
		
		agregarlisteners();
	}
	private void agregarlisteners() 
	{
		v.lanzarGUI();
		while(!v.vistaLanzada)
		{
			System.out.println("cargando...");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		v.btnMostrarAlumnos.addActionListener(this);
//		iniTable();
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == v.btnMostrarAlumnos)
		{
			iniTable();
		}
	}
	private void iniTable() 
	{
		DefaultTableModel modelo = (DefaultTableModel) v.tabla.getModel();
		DefaultTableModel modeloAlumnos = m.mostrarVideojuegos(modelo);
		v.tabla.setModel(modeloAlumnos);
		JOptionPane.showMessageDialog(null, "Alumnos listados correctamente!");
	}
	
}

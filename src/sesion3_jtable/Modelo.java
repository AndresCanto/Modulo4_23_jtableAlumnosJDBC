package sesion3_jtable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

public class Modelo
{
	Connection con;
	private void conectar() 
	{
		String server = "jdbc:mysql://localhost/alumnos";
		String user = "andres"; 
		String password = "qwerty123456"; 
		
		try {
			con = DriverManager.getConnection(server, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(con!=null)
		{
			System.out.println("Conexion exitosa!!");
		}
	}
	
	public DefaultTableModel mostrarVideojuegos(DefaultTableModel modelo) 
	{
		conectar();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String query = "SELECT * FROM javase18;";
		
		try
		{
//			el arraylist se tendria que hacer desde controlador y pasarlo como parametro
//			¿Pero como se haria si el arraylist se llena con la consulta, y eso se hizo aqui en el modelo?
			ArrayList<Alumno> lista = new ArrayList<>();
			pstm= con.prepareStatement(query);
			rs=pstm.executeQuery();
			
//			System.out.println("Matricula\tNombre\t\tDiagnostico\tModulo");
			while(rs.next())
			{
				lista.add(new Alumno(rs.getInt("matricula"),rs.getString("nombre"),
						rs.getInt("diagnostico"),rs.getInt("modulo")));
//				System.out.print(rs.getInt("matricula")+"\t\t");
//				System.out.print(rs.getString("nombre")+"\t\t");
//				System.out.print(rs.getInt("diagnostico")+"\t\t");
//				System.out.println(rs.getInt("modulo")+"\t\t");
				Object[][] datos = new Object[lista.size()][4];
				for(int i=0;i<lista.size();i++)
				{
					datos[i][0] = lista.get(i).getMatricula();
					datos[i][1] = lista.get(i).getNombre();
					datos[i][2] = lista.get(i).getDiagnostico();
					datos[i][3] = lista.get(i).getModulo();
				}
				String[] Encabezados = {"Matricula","Nombre","Diagnostico","Modulo"};
				modelo = new DefaultTableModel(datos, Encabezados);
			}
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rs != null) rs.close();
				if(pstm != null) pstm.close();
			} 
			catch (Exception e2) 
			{
				// TODO: handle exception
			}
		}
		cerrarConexion();
		return modelo;
	}
	
	private void cerrarConexion() 
	{
		try 
		{
			if(con != null) con.close();
		} 
		catch (Exception e2) 
		{
			// TODO: handle exception
		}
	}
}

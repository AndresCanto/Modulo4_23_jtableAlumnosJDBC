package sesion2_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class JDBC_CRUD
{
	Connection con;
	public static void main(String[] args) 
	{
		JDBC_CRUD crud = new JDBC_CRUD();
		crud.conectar();
//		crud.insertarAlumno2();
		
//		crud.delete();
//		crud.update();
		crud.leerAlumnos();
		
	}
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
			System.out.println("Conneciotn a BD establecida!!");
		}
	}
	private void leerAlumnos()
	{
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String query = "SELECT * FROM javase18;";
		
		try 
		{
			pstm= con.prepareStatement(query);
			rs=pstm.executeQuery();
			
			System.out.println("Matricula\tNombre\t\tDiagnostico\tModulo");
			while(rs.next())
			{
				System.out.print(rs.getInt("matricula")+"\t\t");
				System.out.print(rs.getString("nombre")+"\t\t");
				System.out.print(rs.getInt("diagnostico")+"\t\t");
				System.out.println(rs.getInt("modulo")+"\t\t");
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
				if(con != null) con.close();
			} 
			catch (Exception e2) 
			{
				// TODO: handle exception
			}
		}
	}
	private void insertarAlumno() 
	{
		int cve = Integer.parseInt(JOptionPane.showInputDialog("Matricula: "));
		String nom = JOptionPane.showInputDialog("Nombre: ");
		int diag = Integer.parseInt(JOptionPane.showInputDialog("Diagnostico: "));
		int mod = Integer.parseInt(JOptionPane.showInputDialog("Modulo: "));
		
		Statement st = null;
		try 
		{
			st = con.createStatement();
			st.executeUpdate("INSERT INTO javase18(matricula,nombre,diagnostico,modulo) VALUES ("+cve+",'"+nom+"',"+diag+","+mod+")");
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
				if(st != null) st.close();
			} 
			catch (Exception e2) 
			{
				// TODO: handle exception
			}
		}
	}
	private void insertarAlumno2() 
	{
		int cve = Integer.parseInt(JOptionPane.showInputDialog("Matricula: "));
		String nom = JOptionPane.showInputDialog("Nombre: ");
		int diag = Integer.parseInt(JOptionPane.showInputDialog("Diagnostico: "));
		int mod = Integer.parseInt(JOptionPane.showInputDialog("Modulo: "));
		
		String query = "INSERT INTO javase18(matricula,nombre,diagnostico,modulo)"
				+ "VALUES (?,?,?,?)";
		PreparedStatement pstm = null;
		try 
		{
			pstm = con.prepareStatement(query);
			pstm.setInt(1, cve);
			pstm.setString(2, nom);
			pstm.setInt(3, diag);
			pstm.setInt(4, mod);
			
			pstm.execute();
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
				if(pstm != null) pstm.close();
			} 
			catch (Exception e2) 
			{
				// TODO: handle exception
			}
		}
	}
	private void delete()
	{
		int mat = Integer.parseInt(JOptionPane.showInputDialog("Matricula: "));
		String query = "DELETE FROM `javase18` WHERE matricula = ?";
		PreparedStatement pstm = null;
		
		try 
		{
			pstm = con.prepareStatement(query);
			pstm.setInt(1, mat);
			pstm.execute();
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
				if(pstm != null) pstm.close();
			} 
			catch (Exception e2) 
			{
				// TODO: handle exception
			}
		}
	}
	private void update()
	{
		int mat = Integer.parseInt(JOptionPane.showInputDialog("Matricula: "));
		PreparedStatement pstm = null;
		ResultSet rs;
		try {
			pstm = con.prepareStatement("SELECT * FROM `javase18` WHERE matricula = ?");
			pstm.setInt(1, mat);
			rs = pstm.executeQuery();
			
			int cve = 0,mod = 0,diag = 0;
			String nom = null;
			while (rs.next()) 
			{
				cve = rs.getInt("matricula");
				nom = rs.getString("nombre");
				diag = rs.getInt("diagnostico");
				mod = rs.getInt("modulo");
			}
			
			int mat1 = Integer.parseInt(JOptionPane.showInputDialog("Matricula: ",cve));
			String nom1 = JOptionPane.showInputDialog("Nombre: ",nom);
			int diag1 = Integer.parseInt(JOptionPane.showInputDialog("Diagnostico: ",diag));
			int mod1 = Integer.parseInt(JOptionPane.showInputDialog("Modulo: ",mod));
			
			pstm = con.prepareStatement("UPDATE `javase18` SET `matricula`=?,`nombre`=?,`diagnostico`=?,`modulo`=? WHERE matricula = ?");
			pstm.setInt(1, mat1);
			pstm.setString(2, nom1);
			pstm.setInt(3, diag1);
			pstm.setInt(4, mod1);
			pstm.setInt(5, mat);
			pstm.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(pstm != null) pstm.close();
			} 
			catch (Exception e2) 
			{
				// TODO: handle exception
			}
		}
	}
}

package sql;
/**
 * @author lifen
 * ���ݿ����Ӽ����ݲ�ѯ
 */
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;


public class sqlConnect {
	private Connection connection;
	private Statement statement;
	String classForName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String connectRule="jdbc:sqlserver://localhost:1433;DatabaseName=BMS;user=sa;password=123;";
	
	/**
	 * �������ݿ�����
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public sqlConnect() throws ClassNotFoundException, SQLException{
			Class.forName(classForName);
			connection=DriverManager.getConnection(connectRule);
			statement=connection.createStatement();
	}
	
	/**
	 * �ر�����
	 */
	public void close(){
		try {
			this.connection.close();
			this.statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ִ��sql���
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public int executeSql(String sql) throws Exception {
		//��¼��Ӱ��ļ�¼��
		int recoders = 0;
		//ִ�����ݿ����
		recoders = statement.executeUpdate(sql);		
		return recoders;
	}
	
	/**
	 * ����sql�����в�ѯ�����ر��ģ��
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public DefaultTableModel getTableModel(String sql) throws Exception{
		//ִ�����ݿ��ѯ����ý����
		ResultSet rset = statement.executeQuery(sql);
		
		ResultSetMetaData rsmd=rset.getMetaData();   //����Ԫ���ݶ���
        
		//���������ע�����ֵ����ദ�õ�
		int columns = rsmd.getColumnCount();   
		
        String columntitle[] = new String[columns];        //������������
        for (int j=1; j<=columns; j++)
            columntitle[j-1] = rsmd.getColumnLabel(j);     //�������������������
        
        /**
         * ʹ���˱��ģ�������������������ĺô��ǿ��԰������ֱ���ӱ������
         * 
         * ����������û�õı�������columntitle ����һ�����ģ�壬Ϊ��ָ�����У��ñ���ж�Ӧ����
         */
        DefaultTableModel tm = new DefaultTableModel(columntitle, 0); 

        //���ݽ�����������������飬�������ݽ�����е�һ����¼
        String results[]= new String[columns]; 
        
        //���������������ÿ�ν�һ�м�¼��ӵ� table ��
        //��������ʹ����ģ�壬ֻ������һ�α���
        while (rset.next()) {                               
            for(int i=0; i<columns; i++){
            	//����ǰ�е�ֵ�浽���飬���ﶼ�����ַ�����ʽ��ע�� rset �ļ����Ǵ� 1 ��ʼ
            	//��������ֱ�������ֶε�˳������ֵ��û�����ֶ����ķ�ʽ���������Ժ;����ṹ�޹�
            	results[i] = rset.getString(i+1);	
            }
            
            //��һ�м�¼��ӵ���ģ���У�ע���Ǽӵ���ģ��������Ǳ��
            tm.addRow(results);
        }
        
        rset.close();
		return tm;
	}
	
	/**
	 * ��ȡ�����
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public ResultSet getResultSet(String sql) throws Exception{
		ResultSet rset = statement.executeQuery(sql);
		return rset;
	}
	
	/**
	 * ����Ա���Ĵ洢���̵�ִ��
	 * @param idBegin
	 * @param name
	 * @param sex
	 * @param contact
	 * @param password
	 * @return
	 */
	public String createStaff(String idBegin,String name,String sex,String contact,String password){
		try {
			CallableStatement c=this.connection.prepareCall("{call create_staff(?,?,?,?,?,?)}");
			c.setString(1, idBegin);
			c.setString(2, name);
			c.setString(3, sex);
			c.setString(4, contact);
			c.setString(5, password);
			c.registerOutParameter(6, java.sql.Types.NCHAR);
			c.execute();
			return c.getString(6);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * �½�����,���۵�,�����ƻ�,��Ʒ������,���������,������ⵥ�Ĵ洢���̵�ִ��
	 * @param typeNum
	 * @param count
	 * @param staff
	 * @param chooseSet
	 * @return
	 * @throws SQLException
	 */
	public String inputOrders(String typeNum,String count,String staff,int chooseSet,String Onum) throws SQLException{
		String[] sqls=new String[]{"order_insert","sell_insert","process_insert","bike_produce_insert","material_produce_insert","material_withdraw_insert"};
		String s=null;
		if(chooseSet==1)
			s="(?,?,?,?,?)";	
		else 
			s="(?,?,?,?)";
		CallableStatement c=this.connection.prepareCall("{call "+sqls[chooseSet]+""+s+"}");
			c.setString(1, typeNum);
			c.setString(2, count);
			c.setString(3, staff);
			c.registerOutParameter(4, java.sql.Types.INTEGER);
			if(chooseSet==1){
				c.setString(5,Onum);
			}
			c.execute();
			String result=c.getString(4);
			return result;
	}
	
	
	/**
	 * ������洢���̵�ִ��
	 * @param mnum
	 * @param amount
	 * @param destroy
	 * @param staff
	 * @param source
	 * @return result
	 */
	public String materialInsert(String mnum,String amount,String destroy,String staff,String source){
		try {
			CallableStatement c=this.connection.prepareCall("{call material_store_insert(?,?,?,?,?,?)}");
			c.setString(1, mnum);
			c.setString(2, amount);
			c.setString(3, destroy);
			c.setString(4, staff);
			c.setString(5, source);
			c.registerOutParameter(6, java.sql.Types.INTEGER);
			c.execute();
			String result=c.getString(6);
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	/**
	 * ������ٴ洢����ִ��
	 * @param mnum
	 * @param amount
	 * @param staff
	 * @return dnum
	 */
	public String materialDestroy(String mnum,String amount,String staff){
		try {
			CallableStatement c=this.connection.prepareCall("{call material_destroy_insert(?,?,?,?)}");
			c.setString(1, mnum);
			c.setString(2, amount);
			c.setString(3, staff);
			c.registerOutParameter(4, java.sql.Types.INTEGER);
			String dnum;
			c.execute();
			dnum=c.getString(4);
			return dnum;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
}

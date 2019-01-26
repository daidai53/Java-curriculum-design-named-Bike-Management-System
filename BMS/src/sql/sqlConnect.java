package sql;
/**
 * @author lifen
 * 数据库连接及数据查询
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
	 * 构造数据库连接
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public sqlConnect() throws ClassNotFoundException, SQLException{
			Class.forName(classForName);
			connection=DriverManager.getConnection(connectRule);
			statement=connection.createStatement();
	}
	
	/**
	 * 关闭连接
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
	 * 执行sql语句
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public int executeSql(String sql) throws Exception {
		//记录受影响的记录数
		int recoders = 0;
		//执行数据库操作
		recoders = statement.executeUpdate(sql);		
		return recoders;
	}
	
	/**
	 * 根据sql语句进行查询并返回表格模型
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public DefaultTableModel getTableModel(String sql) throws Exception{
		//执行数据库查询，获得结果集
		ResultSet rset = statement.executeQuery(sql);
		
		ResultSetMetaData rsmd=rset.getMetaData();   //返回元数据对象
        
		//获得列数，注意这个值后面多处用到
		int columns = rsmd.getColumnCount();   
		
        String columntitle[] = new String[columns];        //创建列名数组
        for (int j=1; j<=columns; j++)
            columntitle[j-1] = rsmd.getColumnLabel(j);     //获得列名填充表格标题数组
        
        /**
         * 使用了表格模板对象来创建表格，这样的好处是可以按行来分别添加表格数据
         * 
         * 下面语句先用获得的标题数组columntitle 定义一个表的模板，为其指定了列，该表具有对应的列
         */
        DefaultTableModel tm = new DefaultTableModel(columntitle, 0); 

        //根据结果集的列数创建数组，保存数据结果集中的一条记录
        String results[]= new String[columns]; 
        
        //迭代遍历结果集，每次将一行记录添加到 table 中
        //这里由于使用了模板，只进行了一次遍历
        while (rset.next()) {                               
            for(int i=0; i<columns; i++){
            	//将当前行的值存到数组，这里都用了字符串格式，注意 rset 的计数是从 1 开始
            	//另外这里直接用了字段的顺序来赋值。没有用字段名的方式，这样可以和具体表结构无关
            	results[i] = rset.getString(i+1);	
            }
            
            //将一行记录添加到表模板中，注意是加到了模板里，而不是表格
            tm.addRow(results);
        }
        
        rset.close();
		return tm;
	}
	
	/**
	 * 获取结果集
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public ResultSet getResultSet(String sql) throws Exception{
		ResultSet rset = statement.executeQuery(sql);
		return rset;
	}
	
	/**
	 * 创建员工的存储过程的执行
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
	 * 新建订单,销售单,生产计划,成品生产单,配件生产单,配件出库单的存储过程的执行
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
	 * 配件入库存储过程的执行
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
	 * 配件销毁存储过程执行
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

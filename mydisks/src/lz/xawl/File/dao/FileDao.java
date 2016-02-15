package lz.xawl.File.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import lz.xawl.File.domain.File;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import cn.itcast.jdbc.TxQueryRunner;

public class FileDao {

	private QueryRunner qr = new TxQueryRunner();
	
	/*
	 * 给一个cf，就可以帮你把所有的文件打包起来
	 */
	public List<File> findByCf(String cf) throws SQLException {
		String sql = "select * from catalog_file where cf=?";
		List<Map<String, Object>> result = qr.query(sql, new MapListHandler(),
				cf);
		List<File> myfile = new ArrayList<File>();
		for (Map<String, Object> map : result) {
			String sql2 = "select * from file where fId=?";
			File f = qr.query(sql2, new BeanHandler<File>(File.class),
					(String) map.get("fId").toString());

			myfile.add(f);
		}
		return myfile;
	}

	/*
	 * 给一个fid 帮你把文件打包出来 暂时没有封装 user
	 */
	public File findByFid(String fid) throws SQLException {
		String sql = "select * from file where fId=?";
		return qr.query(sql, new BeanHandler<File>(File.class), fid);
	}

	// 上传完后保存文件信息
	public void upLoadFile(File file) throws SQLException {
		String sql = "insert into file (fId,fPath,fSize,fType,fName,fHash,fDowncount,fDesc,fUploadtime,isShare,cid,fDiskName) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] param = { file.getfId(), file.getfPath(), file.getfSize(),
				file.getfType(), file.getfName(), file.getfHash(),
				file.getfDowncount(), file.getfDesc(), file.getfUploadtime(),
				file.getIsShare(), file.getCatalog().getcId(),
				file.getfDiskName() };
		qr.update(sql, param);
	}

	// cf写入
	public void writecf(String fid, String cf) throws SQLException {
		String sql = "insert into catalog_file values(?,?)";
		qr.update(sql, cf, fid);
	}

	// 删除文件 ajax做吧
	public boolean deleteByFid(String fid) throws SQLException {

		String sql = "delete from file where fId=?";
		Boolean b = qr.update(sql, fid) == 0 ? false : true;
		sql = "delete from catalog_file where fid=?";
		Boolean a = qr.update(sql, fid) == 0 ? false : true;
		return a && b;
	}

	// 修改文件信息
	public void editFile(String count, String fid) throws SQLException {
		String sql = "update file set fDowncount=? where fId=?";
		qr.update(sql, count, fid);
	}

	/*
	 * 批量删除纯文件
	 */
	public void removeAll(List<File> myfile, ServletContext context)
			throws SQLException {
		String sql = "delete from file where fId=?";
		for (File f : myfile) {

			qr.update(sql, f.getfId());
			java.io.File abf = new java.io.File(context.getRealPath(f
					.getfPath().substring(8)));
			abf.delete();
		}
	}
	//查找fpath 
	public File findByFpath(String path) throws SQLException{
		String sql="select * from file where fPath=?";
		return qr.query(sql, new BeanHandler<File>(File.class),path);
	}
	
		//查找hash
	public File findByHash(String hash) throws SQLException{
		String sql="select * from file where fHash=?";
		return qr.query(sql, new BeanHandler<File>(File.class),hash);
	}
	public void test() throws Exception {
		File f2 = new File();
		File f1 = new File();
		File f3 = new File();
		f1.setfId("1");
		f2.setfId("2");
		f3.setfId("3");
		List<File> mylist = new ArrayList<File>();
		mylist.add(f1);
		mylist.add(f2);
		mylist.add(f3);
	}
}

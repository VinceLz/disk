package lz.xawl.Catalog.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import lz.xawl.Catalog.domain.Catalog;
import lz.xawl.File.dao.FileDao;
import lz.xawl.File.domain.File;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;

public class CatalogDao {
	private QueryRunner qr = new TxQueryRunner();
	private FileDao fileDao = new FileDao();

	// 给我一个cid，就可以查找下面一级的目录和文件
	public Catalog findByCidToCatalog(String cid) throws SQLException {
		String sql = "select * from catalog where cId=?";
		Map<String, Object> beanMap = qr.query(sql, new MapHandler(), cid);
		Catalog catalog = CommonUtils.toBean(beanMap, Catalog.class);
		// 还有pid cF 这个属性就行映射
		if (beanMap.get("pId") != null) {
			// 这不是最顶层目录 需要封装他的父级目录 只有pid
			Catalog c = new Catalog();
			c.setcId((String) beanMap.get("pId"));
			catalog.setParent(c);
		}
		sql = "select * from catalog where pId=?";
		List<Map<String, Object>> beanMapList = qr.query(sql,
				new MapListHandler(), catalog.getcId());

		List<Catalog> cataList = this.listToBean(beanMapList);

		catalog.setChildren(cataList);
		// 开始封装catalog 的子文件

		String cf = (String) beanMap.get("cF");
		List<File> myfile = fileDao.findByCf(cf);
		catalog.setMyFile(myfile);
		return catalog;
	}

	// 给我一个list map 集合 我帮你转换成 list bean map-bean
	public List<Catalog> listToBean(List<Map<String, Object>> map) {
		List<Catalog> toList = new ArrayList<Catalog>();
		for (Map<String, Object> mymap : map) {
			Catalog c = CommonUtils.toBean(mymap, Catalog.class);
			if (mymap.get("pId") != null) {
				Catalog c1 = new Catalog();
				c1.setcId((String) mymap.get("pId"));
				c.setParent(c1);
			}
			toList.add(c);
		}
		return toList;
	}

	// 通过cid 你bean 一个对象

	public Catalog findByCid(String cid) throws SQLException {
		String sql = "select * from catalog where cId=?";
		return qr.query(sql, new BeanHandler<Catalog>(Catalog.class), cid);
	}

	public void createCatalog(Catalog c) throws SQLException {
		String sql = "insert into catalog (cId,pId,cName,cDate,isShare) values(?,?,?,?,?)";
		Object[] para = { c.getcId(), c.getParent().getcId(), c.getcName(),
				c.getcDate(), c.getIsShare() };
		qr.update(sql, para);

	}

	// 通过cid 找到cf值
	public String cidTocf(String cid) throws SQLException {
		String sql = "select cf from catalog where cId=?";
		return (String) qr.query(sql, new ScalarHandler(), cid);
	}

	// 写入cf值

	public void intoCf(String cid, String cf) throws SQLException {
		String sql = "update catalog set cF=? where cId=?";
		qr.update(sql, cf, cid);
	}

	/*
	 * 给cid 删除他后面所有的文件和文件夹 需要遍历数 递归
	 */
	public void deleteByCatalog(String cid, ServletContext context)
			throws SQLException {

		Catalog cata = this.findByCidToCatalog(cid);

		// 先删除该目录下的文件
		if (cata.getMyFile() != null) {

			System.out.println("开始删除纯文件");
			fileDao.removeAll(cata.getMyFile(), context);
			// 还要删除本地文件
		}

		String sql = "select cF from catalog where cId=?";
		String cf = (String) qr.query(sql, new ScalarHandler(), cata.getcId());
		sql = "delete from catalog_file where cf=?";
		qr.update(sql, cf);

		sql = "delete from catalog where cId=?";
		qr.update(sql, cata.getcId());
		List<Catalog> chileList = cata.getChildren();

		for (Catalog c : chileList) {
			deleteByCatalog(c.getcId(), context);
		}

		return;
	}

	public void testDelete() throws Exception {

	}
}

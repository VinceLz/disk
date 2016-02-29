package lz.xawl.Catalog.service;

import java.sql.SQLException;

import javax.servlet.ServletContext;

import lz.xawl.Catalog.dao.CatalogDao;
import lz.xawl.Catalog.domain.Catalog;

public class CatalogService {
	private CatalogDao cDao = new CatalogDao();

	// 拿cid去查找他下面的一级文件夹已经文件
	public Catalog findByCidToCatalog(String cid) {

		try {
			return cDao.findByCidToCatalog(cid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	// 通过cid 封装catalog
	public Catalog findByCid(String cid) {
		try {
			return cDao.findByCid(cid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void createCatalog(Catalog c) {
		try {
			cDao.createCatalog(c);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public String cidTocf(String cid) {
		try {
			return cDao.cidTocf(cid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void intoCf(String cid, String cf) {
		try {
			cDao.intoCf(cid, cf);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void deleteByCatalog(String cid, ServletContext context) {
		try {
			cDao.deleteByCatalog(cid, context);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}

package com.ylkj.fbs.service.bean;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmqZigbeeClassToDao {

	static Logger log = LoggerFactory.getLogger(EmqZigbeeClassToDao.class);

	/**
	 * 新协议添组操作数据转换
	 * 
	 * @param dao
	 * @return
	 * @throws Exception
	 */
	public static EmqZigbeeDao getClientDaoByte(ProReceivingEmqEntity dao)
			throws Exception {
		EmqZigbeeDao reqDao = new EmqZigbeeDao();
		reqDao.setDataLeng(hexString2Bytes("0" + Integer.toHexString(15))[0]);
		reqDao.setIdentType(hexString2Bytes(dao.getIdentType())[0]);
		reqDao.setNwkCount(hexString2Bytes("0" + 1)[0]);
		reqDao.setNekId(hexString2Bytes(dao.getNekId()));
		reqDao.setPackLeng(hexString2Bytes("0" + 5)[0]);
		reqDao.setMainMl(hexString2Bytes("0" + 1)[0]);
		reqDao.setzMl(hexString2Bytes("0" + 4)[0]);
		reqDao.setData(hexString2Bytes("a00001"));
		return reqDao;
	}

	public static EmqZigbeeDao delTerminal(ProReceivingEmqEntity dao) {
		EmqZigbeeDao reqDao = new EmqZigbeeDao();
		List<byte[]> nwkList = new ArrayList<byte[]>();
		if (dao.getDataLeng().length() > 1) {
			reqDao.setDataLeng(hexString2Bytes(dao.getDataLeng() + "")[0]);
		} else {
			reqDao.setDataLeng(hexString2Bytes("0" + dao.getDataLeng())[0]);
		}
		if (dao.getIdentType().length() > 1) {
			reqDao.setIdentType(hexString2Bytes(dao.getIdentType() + "")[0]);
		} else {
			reqDao.setIdentType(hexString2Bytes("0" + dao.getIdentType())[0]);
		}

		if (dao.getNwkCount() > 9) {
			String nwkCount = Integer.toHexString(dao.getNwkCount());
			if (nwkCount.length() > 1) {
				reqDao.setNwkCount(hexString2Bytes(nwkCount + "")[0]);
			} else {
				reqDao.setNwkCount(hexString2Bytes("0" + nwkCount)[0]);
			}

		} else {
			reqDao.setNwkCount(hexString2Bytes("0"
					+ Integer.toHexString(dao.getNwkCount()))[0]);
		}
		// reqDao.setNekId(hexString2Bytes(dao.getNekId()));
		for (int i = 0; i < dao.getNwkList().size(); i++) {
			byte[] nwkId = hexString2Bytes(dao.getNwkList().get(i));
			nwkList.add(nwkId);
		}
		reqDao.setNwkList(nwkList);
		if (dao.getMainMl().length() > 1) {
			reqDao.setMainMl(hexString2Bytes(dao.getMainMl() + "")[0]);
		} else {
			reqDao.setMainMl(hexString2Bytes("0" + dao.getMainMl())[0]);
		}
		if (dao.getZMl().length() > 1) {
			reqDao.setzMl(hexString2Bytes(dao.getZMl() + "")[0]);
		} else {
			reqDao.setzMl(hexString2Bytes("0" + dao.getZMl())[0]);
		}
		if (dao.getData() != null) {
			if (dao.getData().length() > 1) {
				reqDao.setData(hexString2Bytes(dao.getData()));
			} else {
				reqDao.setData(hexString2Bytes("0" + dao.getData()));
			}
		}
		return reqDao;

	}

	public static EmqZigbeeDao specialTerminal(ProReceivingEmqEntity dao) {
		EmqZigbeeDao reqDao = new EmqZigbeeDao();
		if (dao.getDataLeng().length() > 1) {
			reqDao.setDataLeng(hexString2Bytes(dao.getDataLeng() + "")[0]);
		} else {
			reqDao.setDataLeng(hexString2Bytes("0" + dao.getDataLeng())[0]);
		}
		if (dao.getIdentType().length() > 1) {
			reqDao.setIdentType(hexString2Bytes(dao.getIdentType() + "")[0]);
		} else {
			reqDao.setIdentType(hexString2Bytes("0" + dao.getIdentType())[0]);
		}
		if (dao.getNwkCount() > 9) {
			String nwkCount = Integer.toHexString(dao.getNwkCount());
			if (nwkCount.length() > 1) {
				reqDao.setNwkCount(hexString2Bytes(nwkCount + "")[0]);
			} else {
				reqDao.setNwkCount(hexString2Bytes("0" + nwkCount)[0]);
			}

		} else {
			reqDao.setNwkCount(hexString2Bytes("0"
					+ Integer.toHexString(dao.getNwkCount()))[0]);
		}
		reqDao.setNekId(hexString2Bytes(dao.getNekId()));
		reqDao.setDkMac(hexString2Bytes(dao.getDkMac()));
		if (dao.getMainMl().length() > 1) {
			reqDao.setMainMl(hexString2Bytes(dao.getMainMl() + "")[0]);
		} else {
			reqDao.setMainMl(hexString2Bytes("0" + dao.getMainMl())[0]);
		}
		if (dao.getZMl().length() > 1) {
			reqDao.setzMl(hexString2Bytes(dao.getZMl() + "")[0]);
		} else {
			reqDao.setzMl(hexString2Bytes("0" + dao.getZMl())[0]);
		}
		return reqDao;
	}

	/*
	 * 字符转换为字节
	 */
	private static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}

	/*
	 * 16进制字符串转字节数组
	 */
	public static byte[] hexString2Bytes(String hex) {

		if ((hex == null) || (hex.equals(""))) {
			return null;
		} else if (hex.length() % 2 != 0) {
			return null;
		} else {
			hex = hex.toUpperCase();
			int len = hex.length() / 2;
			byte[] b = new byte[len];
			char[] hc = hex.toCharArray();
			for (int i = 0; i < len; i++) {
				int p = 2 * i;
				b[i] = (byte) (charToByte(hc[p]) << 4 | charToByte(hc[p + 1]));
			}
			return b;
		}

	}
}

package com.greatmap.tregulation.usermanger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Date;

import com.greatmap.tregulation.information.websocket.GPSUploadInformationReq;
import com.greatmap.tregulation.information.websocket.SearchGPSInformationReq;



public class GpsUploadUtil {
	public static Socket sockets = null;
	
	
	public static byte[] searchGpsMessage(SearchGPSInformationReq gpsmap) throws Exception {
		byte[] result = null;
		System.out.println(sockets==null);
		if(sockets==null) {
			sockets=open();
		}
		System.out.println(sockets==null);
		if (sockets.isConnected()) {
			result = searchgps(sockets, gpsmap);

		}
		return result;
	}
	
	public static byte[] sendGpsMessage(GPSUploadInformationReq gpsmap) throws Exception {
		byte[] result = null;
		System.out.println(sockets==null);
		if(sockets==null) {
			sockets=open();
		}
		System.out.println(sockets==null);

		
		try {
			if (sockets.isConnected()) {
				result = operate(sockets, gpsmap);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return result;
		}
		

		System.out.println("resultresultresultresult" + result);

		return result;
	}

	public static Socket open() throws Exception, IOException {

		String ip = "192.168.9.150";
		int port = 6888;
		Socket socket=null;
		try {
			socket = new Socket(ip, port); // socket连接

			
		} catch (Exception e) {
			
			e.printStackTrace();
			return socket;
			// TODO: handle exception
		}
		socket.setSoTimeout(3000);

		sockets = socket;
		return socket;

	}

	public static byte[] operate(Socket socket, GPSUploadInformationReq gpsmap) throws Exception {
		OutputStream outputStream = socket.getOutputStream(); // 获取发送数据流对象
		InputStream inputStream = socket.getInputStream();

		Date startDate = new Date();

		// 发送数据、查询数据
		for (int i = 0;; i++) {

			byte[] sendBytes = getMobileSendBytes(gpsmap.getAccount(), gpsmap.getLongitude(), gpsmap.getLatitude(),
					gpsmap.getAltitude(), gpsmap.getBearing(), gpsmap.getSpeed(), new Date(gpsmap.getLocalTime()));

			// 发送数据
			outputStream.write(sendBytes);
			outputStream.flush();
			Thread.sleep(300); // 休眠一下避免多次发送数据,数据直接产生粘合。(建议1~3秒一次)
			//System.out.println("sendBytessendBytessendBytessendBytessendBytes" + sendBytes);

			return sendBytes;

		}

	}

	public static byte[] searchgps(Socket socket, SearchGPSInformationReq gpsmap) throws Exception {
		OutputStream outputStream = socket.getOutputStream(); // 获取发送数据流对象
		InputStream inputStream = socket.getInputStream();

		Date startDate = new Date();

		// 查询数据
		for (int i = 0;; i++) {
			Thread.sleep(300); // 休眠一下避免多次发送数据,数据直接产生粘合。(建议1~3秒一次)
			// 达到条件执行查询操作
			if (true) {
				System.out.println("进入查询结果");

				byte[] queryBytes = getMobileQueryBytes(gpsmap.getAccount(), new Date(gpsmap.getStartTime()),
						new Date(gpsmap.getEndTime()));
				outputStream.write(queryBytes);
				outputStream.flush();

				// 接收查询结果 协议头
				byte[] recvbuf = new byte[9];

				int count = inputStream.read(recvbuf);

				ByteBuffer byteBuffer = ByteBuffer.wrap(recvbuf);
				byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
				int len = byteBuffer.getShort(5);

				if (len > 0) {
					// 读取数据内容
					recvbuf = new byte[len];
					count = inputStream.read(recvbuf);
					System.out.println("查询条数: " + len / 55);

				} else {
					System.out.println("only recv header ");
				}
				return recvbuf;

			}
		}

	}

	/**
	 * 生成定位网络字节
	 * 
	 * @param gid
	 * @param longitude
	 * @param latitude
	 * @param date
	 * @return
	 */
	public static byte[] getMobileSendBytes(String gid, double longitude, double latitude, double Precision,
			double Elevation, double speed, Date date) {
		ByteBuffer byteBuffer = ByteBuffer.allocate(105);
		byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
		// 头
		byteBuffer.putShort((short) 0xAAAB);
		byteBuffer.putShort((short) 0xE001);
		byteBuffer.put((byte) 0x01);
		byteBuffer.putInt(95);
		// 包体 STIME=2017-08-24 06:15:03&ETIME=2017-08-24 18:15:03
		// gid 长度为40, 长度不满后面补\0
		for (int i = gid.getBytes().length; i < 40; i++) {
			gid += "\0";
		}
		byteBuffer.put(gid.getBytes());
		/*
		 * double Longitude;//经度 double Latitude;//纬度 double Speed;//速度 double
		 * Direction;//方向 double Elevation;//高程 double Precision;//精度
		 */
		byteBuffer.putDouble(longitude);
		byteBuffer.putDouble(latitude);
		byteBuffer.putDouble(Precision);
		byteBuffer.putDouble(Elevation);
		byteBuffer.putDouble(speed);
		byteBuffer.putDouble(0);
		byteBuffer.putShort((short) (date.getYear() + 1900));
		byteBuffer.put((byte) (date.getMonth() + 1));
		byteBuffer.put((byte) date.getDate());
		byteBuffer.put((byte) date.getHours());
		byteBuffer.put((byte) date.getMinutes());
		byteBuffer.put((byte) date.getSeconds());

		return byteBuffer.array();
	}

	/**
	 * 生成查询网络字节
	 * 
	 * @param gid
	 * @param start
	 * @param end
	 * @return
	 */
	public static byte[] getMobileQueryBytes(String gid, Date start, Date end) {
		ByteBuffer byteBuffer = ByteBuffer.allocate(63);
		byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
		// 头
		byteBuffer.putShort((short) 0xAAAB);
		byteBuffer.putShort((short) 0xE002);
		byteBuffer.put((byte) 0x01);
		byteBuffer.putInt(54);
		// 包体 STIME=2017-08-24 06:15:03&ETIME=2017-08-24 18:15:03
		for (int i = gid.length(); i < 40; i++) {
			gid += "\0";
		}
		byteBuffer.put(gid.getBytes());
		byteBuffer.putShort((short) (start.getYear()));
		byteBuffer.put((byte) (start.getMonth()));
		byteBuffer.put((byte) start.getDate());
		byteBuffer.put((byte) start.getHours());
		byteBuffer.put((byte) start.getMinutes());
		byteBuffer.put((byte) start.getSeconds());

		byteBuffer.putShort((short) (end.getYear()));
		byteBuffer.put((byte) (end.getMonth()));
		byteBuffer.put((byte) end.getDate());
		byteBuffer.put((byte) end.getHours());
		byteBuffer.put((byte) end.getMinutes());
		byteBuffer.put((byte) end.getSeconds());

		return byteBuffer.array();
	}

	public static String getString(ByteBuffer buffer) {
		Charset charset = null;
		CharsetDecoder decoder = null;
		CharBuffer charBuffer = null;
		try {
			charset = Charset.forName("UTF-8");
			decoder = charset.newDecoder();
			// 用这个的话，只能输出来一次结果，第二次显示为空
			// charBuffer = decoder.decode(buffer);
			charBuffer = decoder.decode(buffer.asReadOnlyBuffer());
			return charBuffer.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
			return "error";
		}
	}
}

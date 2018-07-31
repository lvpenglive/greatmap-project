package com.greatmap.common.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class CreateFileUtil {

	/**
	 * 生成.json格式文件
	 */
	public static boolean createJsonFile(String jsonString, String filePath, String fileName) {
		// 标记文件生成是否成功
		boolean flag = true;
		System.out.println("22222222222222222222222222222222222222222222222222");

		// 拼接文件完整路径
		String fullPath = filePath + File.separator + fileName + ".json";

		// 生成json格式文件
		try {
			// 保证创建一个新文件
			File file = new File(fullPath);
			if (!file.getParentFile().exists()) { // 如果父目录不存在，创建父目录
				file.getParentFile().mkdirs();
			}
			if (file.exists()) { // 如果已存在,删除旧文件

				file.delete();

			}
			file.createNewFile();

			// 将格式化后的字符串写入文件
			OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
			write.write(jsonString);
			write.flush();
			write.close();
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}

		// 返回是否成功的标记
		return flag;
	}

	/** */
	/**
	 * 文件重命名
	 * 
	 * @param path
	 *            文件目录
	 * @param oldname
	 *            原来的文件名
	 * @param newname
	 *            新文件名
	 */
	public static void renameFile(String path, String oldname, String newname) {
		if (!oldname.equals(newname)) {// 新的文件名和以前文件名不同时,才有必要进行重命名
			File oldfile = new File(path + File.separator + oldname);
			File newfile = new File(path + File.separator + newname);
			
			
			
			
			
			if (!oldfile.exists()) {
				return;// 重命名文件不存在
			}
			if (newfile.exists())// 若在该目录下已经有一个文件和新文件名相同，则不允许重命名
				System.out.println(newname + "已经存在！");
			else {
				oldfile.renameTo(newfile);
			}
		} else {
			System.out.println("新文件名和旧文件名相同...");
		}
	}
}
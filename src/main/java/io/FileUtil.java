package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class FileUtil {
	
	/**
	 * 输出对象至文件
	 * @param obj
	 * @param savePath
	 */
	public static void OutputObject(Object obj,String savePath){
		File file =new File(savePath);
        FileOutputStream out;
        try {
            out = new FileOutputStream(file);
            ObjectOutputStream objOut=new ObjectOutputStream(out);
            objOut.writeObject(obj);
            objOut.flush();
            objOut.close();
            System.out.println("write object success!");
        } catch (IOException e) {
            System.out.println("write object failed");
            e.printStackTrace();
        }
	}
	
	/**
	 * 从文件读入对象
	 * @param savePath
	 * @return
	 */
	public static Object InputObject(String savePath){
		Object temp=null;
        File file =new File(savePath);
        FileInputStream in;
        try {
            in = new FileInputStream(file);
            ObjectInputStream objIn=new ObjectInputStream(in);
            temp=objIn.readObject();
            objIn.close();
            System.out.println("read object success!");
        } catch (IOException e) {
            System.out.println("read object failed");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return temp;
	}
	
	public static void OutputString(String it, String savePath) {
		File f = new File(savePath);
		try (FileWriter fw = new FileWriter(f, true); PrintWriter pw = new PrintWriter(fw);) {
			pw.println(it);
			pw.println();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void OutputIterator(Iterator<String> it, String savePath, boolean append) {
		File f = new File(savePath);
		try (FileWriter fw = new FileWriter(f, append); PrintWriter pw = new PrintWriter(fw);) {
			while (it.hasNext()) {
				pw.println(it.next());
				pw.println();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void OutputIteratorList(Iterator<List<String>> it, String savePath, boolean append) {
		File f = new File(savePath);
		try (FileWriter fw = new FileWriter(f, append); PrintWriter pw = new PrintWriter(fw);) {
			while (it.hasNext()) {
				List<String> list = it.next();
				if (list!=null && list.size()>0){
					for (String sr : list){
						pw.println(sr);
					}
					pw.println();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void OutputJSONObject(JSONObject params, String savePath, boolean append) {
		File f = new File(savePath);
		try (FileWriter fw = new FileWriter(f, append); PrintWriter pw = new PrintWriter(fw);) {
			pw.println(JSON.toJSONString(params));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static JSONObject InputJSONObject(String savePath) {
		try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(savePath)));) {
			String line = br.readLine();
			if (StringUtils.isNotBlank(line)){
				return JSONObject.parseObject(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String,Set<String>> Json2Map(JSONObject json){
		Map<String, Set<String>> map = new HashMap<String, Set<String>>();
		Map<String, Object> temp = JSON.parseObject(JSON.toJSONString(json), Map.class);
		Set<String> set;
		for (Entry<String, Object> entry : temp.entrySet()) {
			String key = entry.getKey();
			if (map.containsKey(key)) {
				set = map.get(key);
			} else {
				set = new HashSet<String>();
				map.put(key, set);
			}
			List<String> listString = JSON.parseArray(entry.getValue().toString(), String.class);
			for(String s : listString){
				if (StringUtils.isNotEmpty(s) && !s.equals("null")){
					set.add(s);
				}
			}
		}
		return map;
	}
	

	/**
	 * 读入数据：一行为一条数据
	 * @param sourceFile
	 * @return
	 */
	public static List<String> InputData(String sourceFile)  {
		List<String> data = new ArrayList<String>();
		String line;
		try(BufferedReader br = new BufferedReader( new InputStreamReader(new FileInputStream(sourceFile)));){
			while ((line=br.readLine())!=null){
				if (StringUtils.isNotBlank(line)) {
					data.add(line);
				}
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	
	/**
	 * 读入数据：一行为一条数据，多行为一组，各组以空行为分界
	 * @param sourceFile
	 * @return
	 * @throws IOException
	 */
	public static List<List<String>> InputGroupData(String sourceFile){
		List<List<String>> data = new ArrayList<List<String>>();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(sourceFile)));) {
			String line;
			List<String> dups = new ArrayList<String>();
			while ((line = br.readLine()) != null) {
				if (StringUtils.isBlank(line)) {
					if (dups.size() > 0) {
						data.add(new ArrayList<>(dups));
						dups.clear();
					}
				} else {
					dups.add(line);
				}
			}
			if (dups.size() > 0) {
				data.add(new ArrayList<>(dups));
				dups.clear();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}


	
}

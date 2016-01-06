/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toolapp;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chan
 */
public class ToolApp {
    
    private List<String> list=new ArrayList<String>();
    private Map<String,String> map=new HashMap<String, String>();
    private static final String ROOT_STRING="D:\\apache-tomcat-7.0.59\\webapps\\DrcomManager";

    public void outputfile(File file){
        File[] files=file.listFiles();
        for(File f:files){
            if(f.isDirectory()){
                outputfile(f);
            }else{
                System.out.println(f.getName());
                list.add(f.getName());
            }
        }
        fileautogetpath(list);
    }
    
    private void fileautogetpath(List<String> list){
        if(list.size()>0){
            Iterator<String> it=list.iterator();
            while(it.hasNext()){
                String str=it.next();
                if(str.endsWith("Action.class")){
                    map.put(str, ROOT_STRING+"\\WEB-INF\\classes\\com\\operator\\struts\\action\\");
                    it.remove();
                }
                if(str.endsWith("Form.class")){
                    map.put(str, ROOT_STRING+"\\WEB-INF\\classes\\com\\operator\\struts\\form\\");
                    it.remove();
                }
                if(str.endsWith("ServiceSupport.class")){
                    map.put(str, ROOT_STRING+"\\WEB-INF\\classes\\com\\operator\\service\\");
                    it.remove();
                }
                if(str.endsWith("propertie")||str.endsWith("Context.xml")){
                    map.put(str, ROOT_STRING+"\\WEB-INF\\classes\\");
                    it.remove();
                }
            }
        }
        manulgetpath();
    }
    
    private void manulgetpath(){
        System.out.println("list size:"+list.size());
        if(list.size()>0){
            for (int i = 0; i < list.size(); i++) {
                String get = list.get(i);
                System.out.println("get:"+get);
                BufferedReader strin=new BufferedReader(new InputStreamReader(System.in));  
                System.out.println("请输入"+get+"的路径11");
                try {
                    String str = strin.readLine();
                    map.put(get, str);
                } catch (IOException ex) {
                    Logger.getLogger(ToolApp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        display();
    }
    
    private void display(){
        if(map.size()>0){
            System.out.println("文件对应路径如下：");
            for (Map.Entry<String, String> entrySet : map.entrySet()) {
                String key = entrySet.getKey();
                String value = entrySet.getValue();
                value=value.replace(ROOT_STRING, "ROOT");
                if(!value.endsWith("\\")){
                    value=value+"\\";
                }
                System.out.println(key+"="+value);
            }
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String path="E:\\项目定制\\TEMP";
        ToolApp ta=new ToolApp();
        File f=new File(path);
        ta.outputfile(f);
    }
    
}

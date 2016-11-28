package com.acms.byoc.test.defitest;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.acms.byoc.global.Processor;
import com.acms.framework.acmstools.FileTools;
import com.acms.framework.taglib.util.html.json.JSONArray;

@RunWith(Parameterized.class)
public class CompareContentTest
{

  private static final  String    DATA_SOURCE     = "datas/";
  private static final  String    OUTPUT_EXPECTED = "output_expected/";
  private static final  String    OUTPUT_RESULT   = "output_result/";
  private               String    pathDirectory;
  private static final  UnZipUtil unzip           = new UnZipUtil();
  private               JSONArray actions;
  private               Processor processor       = new Processor();
  
  public CompareContentTest (String pathDirectory)
  {
    this.pathDirectory = DATA_SOURCE+pathDirectory;
    String actionsChaine = FileTools.lireFichier(this.pathDirectory+"/action.json");
    actions = new JSONArray(actionsChaine);
  }
  
  @Parameterized.Parameters
  public static List<Object[]> dataFiles() throws Exception
  {
    List<Object[]> list = new ArrayList<Object[]>();
    File directory = new File(DATA_SOURCE);
    ArrayList<File> files = new ArrayList<File>(Arrays.asList(directory.listFiles()));
    for(File f:files)
    {
      Object[] o = {f.getName()};
      list.add(o);
    }
    return list;
  }

  @Test
  public void test()
  {
    compareContentXml();
  } 
  
  
  public void unzipInit()
  {
    
  }
  
  public void unzipFinal()
  {
    
  }
  
  public boolean compareContentXml()
  {
    //extract the content of init
    System.out.println("extract initial odt");
    unzip.unZipIt(pathDirectory+"/initial.odt",OUTPUT_RESULT);
    String content = FileTools.lireFichier(OUTPUT_RESULT+"content.xml");
    processor.process(content, actions);
    //extract the result expected
    System.out.println("extract finale odt");
    unzip.unZipIt(pathDirectory+"/final.odt",OUTPUT_EXPECTED);
    //get file content
    System.out.println("compare files");
    String expected = FileTools.lireFichier(OUTPUT_EXPECTED+"content.xml");
    String created = FileTools.lireFichier(OUTPUT_RESULT+"content.xml");
    return expected.equals(created);
  }
  

}

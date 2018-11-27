package korpusi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Korpusi 
{
	
    ArrayList<String> isiFile;
    public void Tokenisasi(String dokumen)
    {
	isiFile = new ArrayList<String>();
	
	try
        {
            BufferedReader br = new BufferedReader(new FileReader(dokumen));
            int j=0;
            Scanner baca = new Scanner(br);
            while(baca.hasNextLine())
            {
            	isiFile.add(baca.nextLine().toLowerCase().replaceAll("[^A-Za-z\']", " ").trim())  ;
            	System.out.println(isiFile.get(j)+"//");
                j++;  
            }
            baca.close();      
	} 
        catch (Exception e)
        {
            System.out.println("File tidak ada");
	}
    }
	
	File file[];
	
	public void bacaMultiFile(File berkas) throws IOException 
        {
		file = berkas.listFiles();
		isiFile = new ArrayList<String>();
		
		for(int i=0; i<file.length; i++) 
                {
                    BufferedReader br = new BufferedReader(new FileReader(file[i]));
                 //   System.out.println(file[i].getAbsolutePath());            
                    Scanner baca = new Scanner(br);
                    String isi="";
                    while(baca.hasNextLine())
                    {
                        isi = isi + baca.nextLine().toLowerCase().replaceAll("[^A-Za-z\']", " ").trim(); 
                    }
                        isiFile.add(isi)  ;
                   //     System.out.println(isiFile.get(i)+"//");
                        baca.close();
                }
	}
	
	ArrayList<String> stoplist;
	
	public boolean stopWordRemoval(String kt) 
        {
            stoplist = new ArrayList<String>();
            try
            {
                BufferedReader br = new BufferedReader(new FileReader("stopwordlist.txt"));
                Scanner baca = new Scanner(br);
                String kata = "";

                while (baca.hasNext())
                {
                    kata = baca.next().toLowerCase();
                    stoplist.add(kata);
                }
                baca.close();
            }
            catch (Exception e) 
            {
                System.out.println("File tidak ada");
            }
		
		return (stoplist.contains(kt));
	}
	
	HashMap<String, postingList> daftarTerm = new HashMap<String, postingList>();
	postingList postList;
	
	
	public void invertedIndex(String term, String namaDok, int posisi) 
        {
            if(daftarTerm.containsKey(term)) 
            {
		postList = daftarTerm.get(term);
		if(postList.namaDok.indexOf(namaDok)<0) 
                {
                    postList.namaDok.add(namaDok);
                    postList.frekTermDiDok.add(new ArrayList<Integer>());	
		}
                    postList.frekTermDiDok.get(postList.namaDok.indexOf(namaDok)).add(posisi);
                    daftarTerm.put(term, postList);
            } 
            else 
            {
                    daftarTerm.put(term, new postingList(namaDok, posisi));
            }
	}
	
	
	public void dictionaryConstruct() 
        {
            String isiDok, token;
            StringTokenizer st;
            for(int i=0; i<isiFile.size(); i++) 
            {
		isiDok = isiFile.get(i);
		st = new StringTokenizer(isiDok);
		int posisi = 1;
			//tokenisasis
		while(st.hasMoreTokens()) 
                {
                    token = st.nextToken();
				
				if(!stopWordRemoval(token)) {
                    invertedIndex(token, "Dok-"+(i+1), posisi);
                    posisi++;
				}
		}
            }	
	}
	
	public void cobaCetak() 
        {
            for(Map.Entry<String, postingList> entry : daftarTerm.entrySet()) 
            {
		System.out.print(entry.getKey()+" : ");
		for(int i =0; i< entry.getValue().namaDok.size(); i++) 
                {
                    System.out.print(entry.getValue().namaDok.get(i)+"");
                    System.out.print("(tf="+entry.getValue().frekTermDiDok.get(i).size()+" : "+entry.getValue().frekTermDiDok.get(i)+")");
                    System.out.print(",");
		}
		System.out.println();
		}
	}
}

package korpusi;

import java.io.File;
import java.io.IOException;

public class Main 
{
     public static void main(String[] args) throws IOException
    {
//        Korpusi b = new Korpusi();
//        String kata, namafile;
//        int jumlahdokumen=20;
//        for (int i = 1; i<=jumlahdokumen; i++) 
//        {
//            System.out.println(" ---------------- ");
//            namafile = "Korpusi"+i+".txt";
//            kata = "Dokumen "+i + " : "+namafile;
//            System.out.println(kata);
//            System.out.println(" ---------------- ");
//            b.Tokenisasi (namafile);
//            
//        
//        }
        
        Korpusi baca = new Korpusi();
		
	//	baca.("ContohKorpus.txt");
		baca.bacaMultiFile(new File("C:\\Users\\Lenovo\\Documents\\NetBeansProjects\\Korpusi\\BeritaKorpusi"));
		baca.dictionaryConstruct();
		baca.cobaCetak();
        
    }   

}

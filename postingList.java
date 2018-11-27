package korpusi;
import java.util.ArrayList;
class postingList 
{
    ArrayList<String> namaDok;
    ArrayList<ArrayList<Integer>> frekTermDiDok;

    public postingList(String nameDoc, int posisiTerm)
    {
        namaDok = new ArrayList<>();
        ArrayList<Integer>posisi = new ArrayList<>();
        frekTermDiDok = new ArrayList<>();
    
        namaDok.add(nameDoc);
        posisi.add(posisiTerm);
        frekTermDiDok.add(posisi);
    }
}

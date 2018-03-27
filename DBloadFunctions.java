import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class DBloadFunctions{
    private static File inFile = null;
    private static int pageSize = 32768;
    private static int numRecord = 1;
    private static int numPage = 1;
    private static Page heap = null;
    private static ExecutionTimer timer = new ExecutionTimer();

    private void setPerm(String f, int p){
        inFile = new File(f);
        pageSize = p;
    }


    private void loadArray(){
        BufferedReader reader = new BufferedReader(new FileReader(inFile));
        heap = new Page(pageSize);
        Record r = null;
        int _numRecord = 0;
        String line = "";
        try{
            while((line = reader.readLine())!=null){
                if(_numRecord == heap.records.length){
                    Page npage = new Page(pageSize);
                    npage.pp = heap;
                    heap.np = npage;
                    heap = npage;
                }
                
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    private void dumpHeap(){

    }

    private void stdout(){

    }

    public boolean run(String args1, String args2, String args3){
        if(args1.equalsIgnoreCase("-p")){
            setPerm(args3, Integer.parseInt(args2));
        }
        else{
            if(new File(args1)!=null){
                inFile = new File(args1);
            }
        }
        timer.start();
        loadArray();
        dumpHeap();
        timer.end();
        stdout();
        return true;
    }
}
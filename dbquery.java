import java.io.File;
import java.io.FileInputStream;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class dbquery{
    private static File inFile = null;
    private static int pagesize = 32768;
    private static String find = null;
    private static ArrayList<Page> heap = new ArrayList<Page>();
    private static ExecutionTimer timer = new ExecutionTimer();
    private static int pagesSearched = 0;

    private static int convert(String s) throws NumberFormatException{
        return Integer.parseInt(s);
    }

    private static void readArray(){
        try{
            Page page = null;
            FileInputStream fos = new FileInputStream(inFile);
            ObjectInputStream stream = new ObjectInputStream(fos);
            while(true){
                try{
                    page = (Page)stream.readObject();
                }catch(EOFException e){
                    break;
                }
                heap.add(page);
            }
            // System.out.println(heap.size());
        }catch(FileNotFoundException fnf){
            System.out.println(fnf.getMessage());
        }catch(IOException ioe){
            System.out.println(ioe.getMessage());
        }catch(ClassNotFoundException cnf){
            System.out.println(cnf.getMessage());
        }
    }

    private static void search(){
        for(Page p:heap){
            for(Record r:p.records){
                System.out.println(r);
                if(r.getBN_NAME().toLowerCase().contains(find.toLowerCase())){
                    System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s\n",r.getBN_NAME(),r.getBN_STATUS(),r.getBN_REG_DT(),r.getBN_CANCEL_DT(),r.getBN_RENEW_DT(),r.getBN_STATE_NUM(),r.getBN_STATE_OF_REG(),r.getBN_ABN());
                    pagesSearched++;
                }
            }
        }
    }

    private static void searchResult(){
        System.out.printf("## stdout ##\n");
        System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s\n","BN_NAME","BN_STATUS","BN_REG_DT","BN_CANCEL_DT","BN_RENEW_DT","BN_STATE_NUM","BN_STATE_OF_REG","BN_ABN");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
        timer.start();
        search();
        timer.end();
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("Time taken: %d milliseconds\n",timer.duration());
        System.out.printf("Pages Searched: %d\n",pagesSearched);
    }

    //MAIN ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public static void main(String[] args) {
        if(args.length > 1){
            try{
                pagesize = convert(args[1]);
                inFile = new File("heap."+pagesize);
                find = args[0];
            }catch(java.lang.NumberFormatException pe){
                System.out.println(pe.getMessage());
            }
        }
        readArray();
        searchResult();
    }
}
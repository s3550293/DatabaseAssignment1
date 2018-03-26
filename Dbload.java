import java.io.File;

public class Dbload{
    private static File inFile = null;
    private static int pageSize = 32768;
    private static int numRecord = 1;
    private static int numPage = 1;
    private static ExecutionTimer timer = new ExecutionTimer();

    private static void setPerm(String f, int p){
        inFile = new File(f);
        pageSize = p;
    }


    private static void loadArray(){

    }

    private static void dumpHeap(){

    }

    private static void stdout(){

    }

    public static void main(String[] args) {
        if(args[0].equalsIgnoreCase("-p")){
            setPerm(args[2], Integer.parseInt(args[1]));
        }
        else{
            if(new File(args[0])!=null){
                inFile = new File(args[0]);
            }
        }
        timer.start();
        loadArray();
        dumpHeap();
        timer.end();
        stdout();
    }
}
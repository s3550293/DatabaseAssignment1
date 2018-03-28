import java.io.*;
import java.util.ArrayList;

public class dbload implements Serializable {
    private static File inFile = null;
    private static int pageSize = 32768;
    private static int num = 0;
    private static int numPage = 0;
    private static ArrayList<Page> heap = new ArrayList<Page>();
    private static ExecutionTimer timer = new ExecutionTimer();


    private static void setPeram(String f, int p){
        inFile = new File(f);
        pageSize = p;
    }

    private static void loadArray(){
        try{
            String line;
            String[] data;
            Page page = new Page(pageSize);
            Record record = null;
            boolean reg = true;
            BufferedReader reader = new BufferedReader(new FileReader(inFile));
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                data = line.split(",");
                if(data[2].equals("Deregistered")){
                    reg = false;
                }else{
                    reg = true;
                }
                record = new Record(data[1], data[3], reg);
                if(data[4]!=null){record.setCancelDate(data[4]);}
                if(data[5]!=null){record.setRenewDate(data[5]);}
                if(!(data.length < 7)){
                    if(data[6]!=null){record.setSateNumber(data[6]);}
                    if(!(data.length < 8 )){
                        if(data[7]!=null){record.setSateOfReg(data[7]);}
                        if(!(data.length < 9 )){
                            if(data[8]!=null){record.setABN(data[8]);}
                        }
                    }
                }
                
                if(page.getSize()+record.alloctionSize() > pageSize){
                    heap.add(page);
                    page = new Page(pageSize);
                }
                page.addRecord(record);
                num++;
            }
            heap.add(page);
            reader.close();
        }catch(IOException e){
            System.out.println(e.getLocalizedMessage());
            System.out.println("Creating Heap");
        }
    }


    private static void dumpHeap(){
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream("heap."+pageSize);
            out = new ObjectOutputStream(fos);
            for(Page p:heap){
                if(p!=null){
                    numPage++;
                    out.writeObject(p);
                }
                if((numPage % 1000)==0){
                    System.out.println(numPage);
                }
            }
            fos.close();
            out.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
          } 
        }

    private static void stdout(){
        System.out.printf("## stdout ##\n");
        System.out.printf("%-20s %-20s %-10s\n","Number of Records","Number of Pages","Time Milliseconds");
        System.out.println("------------------------------------------------------------");
        System.out.printf("%-20d %-20d %-15s\n",num,numPage,timer.duration());
    }

    //Main Funciton ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public static void main(String[] args) {
        if(args[0].equalsIgnoreCase("-p")){
            setPeram(args[2], Integer.parseInt(args[1]));
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
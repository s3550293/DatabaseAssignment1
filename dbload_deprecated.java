import java.io.File;
import java.io.*;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.FileReader;
import java.lang.instrument.Instrumentation;
import java.util.ArrayList;

public class dbload implements Serializable {
    private static File inFile = null;
    private static int pageSize = 32768;
    private static int num = 1;
    private static int numPage = 1;
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
                
                if(page.getSize()+record.alloctionSize() < pageSize){
                    page.addRecord(record);
                    System.out.println(record);
                }else{
                    numPage++;
                    heap.add(page);
                    page = new Page(pageSize);
                    page.addRecord(record);
                }
                num++;
            }
            reader.close();
        }catch(IOException e){
            System.out.println(e.getLocalizedMessage());
            // System.out.println(e.getMessage());
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
                    out.writeObject(p);
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




class Record implements Serializable{
    String BN_NAME, BN_REG_DT, BN_CANCEL_DT, BN_RENEW_DT, BN_STATE_NUM, BN_STATE_OF_REG, BN_ABN;
    boolean BN_STATUS;

    Record(String name, String regDate, boolean status){
        BN_NAME = name;
        BN_REG_DT = regDate;
        BN_STATUS = status;
    }

    public String getBN_NAME(){return BN_NAME;}
    public String getBN_REG_DT(){return BN_REG_DT;}
    public String getBN_CANCEL_DT(){return BN_CANCEL_DT;}
    public String getBN_RENEW_DT(){return BN_RENEW_DT;}
    public String getBN_STATE_NUM(){return BN_STATE_NUM;}
    public String getBN_STATE_OF_REG(){return BN_STATE_OF_REG;}
    public String getBN_ABN(){return BN_ABN;}
    public boolean getBN_STATUS(){return BN_STATUS;}

    public void setCancelDate(String input){
        BN_CANCEL_DT = input;
    }
    public void setRenewDate(String input){
        BN_RENEW_DT = input;
    }
    public void setSateNumber(String input){
        BN_STATE_NUM = input;
    }
    public void setSateOfReg(String input){
        BN_STATE_OF_REG = input;
    }
    public void setABN(String input){
        BN_ABN = input;
    }

    public long alloctionSize(){
        int value = 0;
        value += PrimitiveSizeFetcher.memSize(BN_NAME);
        value += PrimitiveSizeFetcher.memSize(BN_REG_DT);
        value += PrimitiveSizeFetcher.memSize(BN_STATUS);
        if(BN_CANCEL_DT!=null){
            value += PrimitiveSizeFetcher.memSize(BN_CANCEL_DT);
        }
        if(BN_RENEW_DT!=null){
            value += PrimitiveSizeFetcher.memSize(BN_RENEW_DT);
        }
        if(BN_STATE_NUM!=null){
            value += PrimitiveSizeFetcher.memSize(BN_STATE_NUM);
        }
        if(BN_STATE_OF_REG!=null){
            value += PrimitiveSizeFetcher.memSize(BN_STATE_OF_REG);
        }
        if(BN_ABN!=null){
            value += PrimitiveSizeFetcher.memSize(BN_ABN);
        }
        return value;
    }

    public String toString(){
        return getBN_NAME();
    }

}

class Page implements Serializable{
    ArrayList<Record> records = null;
    int pageSize = 0;
    static int pageNum = 0;
    Page(int size){
        records = new ArrayList<>();
        pageSize = size;
        pageNum++;
    }

    public void addRecord(Record readIn){
        records.add(readIn);
    }

    public ArrayList<Record> getArray(){return records;}

    public int getNum(){return pageNum;}

    public long getSize(){
        int value = 0;
        for(Record record : records){
            value += record.alloctionSize();
        }
        return value;
    }
}


class PrimitiveSizeFetcher{
    static int memSize(byte x) {
        return (Byte.SIZE/8);
    }
    static int memSize(int x) {
        return (Integer.SIZE/8);
    }
    static int memSize(String x) {
        return (8 * (int) ((((x.length()) * 2) + 45) / 8));
    }
    static int memSize(float x) {
        return (Float.SIZE/8);
    }
    static int memSize(double x) {
        return (Double.SIZE/8);
    }
    static int memSize(char x) {
        return (Character.SIZE/8);
    }
    static int memSize(boolean x) {
        return 16;
    }
}


class ExecutionTimer {
    private static long start;
    private static long end;
  
    public ExecutionTimer() {
        start = 0;
        end = 0;
    }

    public static void start(){
        start = System.currentTimeMillis();
    }
  
    public static void end() {
      end = System.currentTimeMillis();
    }
  
    public static long duration(){
      return (end-start);
    }
}
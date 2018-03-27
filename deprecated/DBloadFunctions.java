import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class DBloadFunctions{
    private File inFile = null;
    private int pageSize = 32768;
    private int numRecord = 1;
    private int numPage = 0;
    private Page heap = null;
    private ExecutionTimer timer = new ExecutionTimer();

    private void setPerm(String f, int p){
        inFile = new File(f);
        pageSize = p;
    }


    private void loadArray(){
        heap = new Page(pageSize);
        Record r = null;
        int _numRecord = 0;
        String line = "";
        String[] data;
        try{
            BufferedReader reader = new BufferedReader(new FileReader(inFile));
            reader.readLine();
            while((line = reader.readLine())!=null){
                data = line.split("\t");
                r.bn_Name = recordAdd(data[1], r.bn_Name);
                if(data[2].equals("Deregistered")){
                    r.bn_status = false;
                }else{
                    r.bn_status = true;
                }
                r.bn_date_of_reg = recordAdd(data[3], r.bn_date_of_reg);
                if(data[4]!=null){r.bn_date_of_cancel = recordAdd(data[4], r.bn_date_of_cancel);}
                if(data[5]!=null){r.bn_date_of_renew = recordAdd(data[5], r.bn_date_of_renew);}
                if(!(data.length < 7)){
                    if(data[6]!=null){r.bn_former_state_num = recordAdd(data[6], r.bn_former_state_num);}
                    if(!(data.length < 8 )){
                        if(data[7]!=null){r.bn_former_stat = recordAdd(data[7], r.bn_former_stat);}
                        if(!(data.length < 9 )){
                            if(data[8]!=null){r.bn_abn = recordAdd(data[8], r.bn_abn);}
                        }
                    }
                }
                if(_numRecord == heap.records.length){
                    Page npage = new Page(pageSize);
                    npage.pp = heap;
                    heap.np = npage;
                    heap = npage;
                }
                heap.record = r;
                r = new Record();

            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    private char[] recordAdd(String s, char[] array){
        for(int i=0;i<s.length();i++){
            if(i<array.length){
                break;
            }
            array[i] = s.charAt(i);
        }
        return array;
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
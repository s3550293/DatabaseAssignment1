import java.util.ArrayList;
import java.io.Serializable;

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
public class Record{
    public char[] bn_Name = new char[200];
    public char[] bn_status = new char[12];
    public char[] bn_date_of_reg = new char[10];
    public char[] bn_date_of_cancel = new char[10];
    public char[] bn_date_of_renew = new char[10];
    public char[] bn_former_state_num = new char[10];
    public char[] bn_former_stat = new char[3];
    public char[] bn_abn = new char[20];

    public Record(){
        for(int i=0;i<bn_Name.length;i++){
            bn_Name[i] = '\0';
        }
    }
}
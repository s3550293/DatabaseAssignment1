public class Record{
    public Record pr = null;
    public Record nr = null;
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
            if(i<bn_status.length){bn_status[i] = '\0';}
            if(i<bn_date_of_reg.length){bn_date_of_reg[i] = '\0';}
            if(i<bn_date_of_cancel.length){bn_date_of_cancel[i] = '\0';}
            if(i<bn_date_of_renew.length){bn_date_of_renew[i] = '\0';}
            if(i<bn_former_state_num.length){bn_former_state_num[i] = '\0';}
            if(i<bn_former_stat.length){bn_former_stat[i] = '\0';}
            if(i<bn_abn.length){bn_abn[i] = '\0';}
        }
    }

    public String bn_NameToString(){
        String returnValue = "";
        for(int i=0;i<bn_Name.length;i++){
            if(bn_Name[i]!='\0'){
                returnValue += bn_Name[i];
            }
        }
        return returnValue;
    }

    public String bn_statusToString(){
        String returnValue = "";
        for(int i=0;i<bn_status.length;i++){
            if(bn_status[i]!='\0'){
                returnValue += bn_status[i];
            }
        }
        return returnValue;
    }

    public String bn_date_of_regToString(){
        String returnValue = "";
        for(int i=0;i<bn_date_of_reg.length;i++){
            if(bn_date_of_reg[i]!='\0'){
                returnValue += bn_date_of_reg[i];
            }
        }
        return returnValue;
    }

    public String bn_date_of_cancelToString(){
        String returnValue = "";
        for(int i=0;i<bn_date_of_cancel.length;i++){
            if(bn_date_of_cancel[i]!='\0'){
                returnValue += bn_date_of_cancel[i];
            }
        }
        return returnValue;
    }

    public String bn_date_of_renewToString(){
        String returnValue = "";
        for(int i=0;i<bn_date_of_renew.length;i++){
            if(bn_date_of_renew[i]!='\0'){
                returnValue += bn_date_of_renew[i];
            }
        }
        return returnValue;
    }

    public String bn_former_statToString(){
        String returnValue = "";
        for(int i=0;i<bn_former_stat.length;i++){
            if(bn_former_stat[i]!='\0'){
                returnValue += bn_former_stat[i];
            }
        }
        return returnValue;
    }

    public String bn_abnToString(){
        String returnValue = "";
        for(int i=0;i<bn_abn.length;i++){
            if(bn_abn[i]!='\0'){
                returnValue += bn_abn[i];
            }
        }
        return returnValue;
    }

}
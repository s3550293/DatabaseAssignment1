import java.io.Serializable;

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
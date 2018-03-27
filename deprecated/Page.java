public class Page{
    public Page pp = null;
    public Page np = null;
    public Record[] records = null;
    public int pageSize = 0;

    public Page(int _pageSize){
        pageSize = _pageSize;
        records = new Record[pageSize/530];
    }
    
}
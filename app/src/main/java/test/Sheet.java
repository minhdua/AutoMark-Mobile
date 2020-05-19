package test;

public class Sheet {
    protected int page = 0;

    public static class SheetSample extends Sheet {
       public int getPage(){
            return this.page;
        }
        public void setPage(int page){
            this.page = page;
        }
    }

    public static class SheetInstance extends Sheet {
        public int getPage(){
            return this.page;
        }
        public void setPage(int page){
            this.page = page;
        }
    }

    public static void main(String args[]){
        Sheet.SheetSample sheetA1 = new Sheet.SheetSample();
        Sheet.SheetInstance sheetB1 = new Sheet.SheetInstance();
        Sheet.SheetSample sheetA2 = new Sheet.SheetSample();
        Sheet.SheetInstance sheetB2 = new Sheet.SheetInstance();
        sheetA1.setPage(10);
        sheetA2.setPage(5);
        sheetB1.setPage(3);
        sheetB2.setPage(8);
        System.out.println("sheet A1, "+sheetA1.page+"; sheet A2, "+sheetA2.page+";sheet B1, "+sheetB1.page+";sheet B2,"+sheetB2.page);
    }
}

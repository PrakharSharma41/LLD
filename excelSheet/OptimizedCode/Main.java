package OptimizedCode;

public class Main {
    public static void main(String[] args) {
        ExcelSheet excelSheet=new ExcelSheet();
        excelSheet.addCell(0, 0, CellType.NUMBER, null);
        excelSheet.addCell(0, 1, CellType.NUMBER, null);
        excelSheet.setCellValue(0, 0, 1);
        excelSheet.setCellValue(0, 1, 10);
        excelSheet.addCell(0, 2, CellType.NUMBER, CellFunction.SUM);
        excelSheet.setCellObserver(0, 0, 0, 2);
        // excelSheet.setCellObserver(0, 1, 0, 2);
        // excelSheet.setCellValue(0, 0, 10);
        // System.out.println(excelSheet.getCell(0, 0));
        System.out.println(excelSheet.getCell(0, 2));

        excelSheet.setCellObserver(0, 1, 0, 2);
        System.out.println(excelSheet.getCell(0, 2));
        excelSheet.setCellValue(0, 0, 100);
        System.out.println(excelSheet.getCell(0, 2));


    }
}
// 0,0 1 
// 0,1 10
// 0,2 sum 
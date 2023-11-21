import java.util.concurrent.ThreadLocalRandom;

class Board{
    Cells[][] cells;
    public Board(int size,int numberOfSnakes, int numberOfLadders){
        createBoard(size);
        addSnakeAndLadder(numberOfSnakes,numberOfLadders);
    }
    private void addSnakeAndLadder(int numberOfSnakes, int numberOfLadders) {
        while(numberOfSnakes>0){
            int snakeStart=(int)(Math.random()*(cells.length*cells.length));
            int snakeEnd=(int)(Math.random()*(cells.length*cells.length));            
            // int snakeStart=ThreadLocalRandom.current().nextInt(1,cells.length*cells.length-1);
            if(snakeEnd>snakeStart)continue;
            Jump snakeHead=new Jump(snakeStart,snakeEnd);
            Cells cells=getCell(snakeStart);
            cells.jump=snakeHead;
            numberOfSnakes--;
        }
        while(numberOfLadders>0){
            int ladderStart=(int)(Math.random()*(cells.length*cells.length));
            int ladderEnd=(int)(Math.random()*(cells.length*cells.length));            
            // int snakeStart=ThreadLocalRandom.current().nextInt(1,cells.length*cells.length-1);
            if(ladderStart>ladderEnd)continue;
            Jump snakeHead=new Jump(ladderStart,ladderEnd);
            Cells cells=getCell(ladderStart);
            cells.jump=snakeHead;
            numberOfLadders--;
        }
    }
    private void createBoard(int size){
        cells=new Cells[size][size];
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                cells[i][j]=new Cells();
            }
        }        
    }
    public Cells getCell(int position){
        int row=position/cells.length;
        int column=position%cells.length;
        return cells[row][column];
    }
}


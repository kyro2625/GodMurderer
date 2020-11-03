//Class for a sparse matrix
package sparse_matrix;

public class SparseMatrix<T> {
    int maxRow = 0, maxCol = 0; //maximum number of rows, columns
    SparseMatrixNode<T>[] rowRefs = null; //references to rows
    SparseMatrixNode<T>[] colRefs = null; //references to columns

    //Initialize a sparse matrix
    public SparseMatrix(int maxR, int maxC) {
        maxRow = maxR;
        maxCol = maxC;
        rowRefs = new SparseMatrixNode[maxR];
        colRefs = new SparseMatrixNode[maxC];
        int i = 0;
        for (i = 0; i < maxR; i++) rowRefs[i] = null;
        for (i = 0; i < maxC; i++) colRefs[i] = null;
    }

    public int getMaxRow() {
        return maxRow;
    }

    public void setMaxRow(int maxRow) {
        this.maxRow = maxRow;
    }

    public int getMaxCol() {
        return maxCol;
    }

    public void setMaxCol(int maxCol) {
        this.maxCol = maxCol;
    }

    public SparseMatrixNode<T>[] getRowRefs() {
        return rowRefs;
    }

    public void setRowRefs(SparseMatrixNode<T>[] rowRefs) {
        this.rowRefs = rowRefs;
    }

    public SparseMatrixNode<T>[] getColRefs() {
        return colRefs;
    }

    public void setColRefs(SparseMatrixNode<T>[] colRefs) {
        this.colRefs = colRefs;
    }

    //Add an element to the position [r,c]
    public boolean add(int r, int c, T info) {
        boolean result = true;
        //Find the insertion position in the row r
        SparseMatrixNode beforeInRow = null, afterInRow = rowRefs[r];
        while (afterInRow != null && afterInRow.col < c) {
            beforeInRow = afterInRow;
            afterInRow = afterInRow.nextInRow;
        }
        //Find the insertion position in the column c
        SparseMatrixNode beforeInCol = null, afterInCol = colRefs[c];
        while (afterInCol != null && afterInCol.row < r) {
            beforeInCol = afterInCol;
            afterInCol = afterInCol.nextInCol;
        }

        //Case of existing an element at the position [r,c]
        if (afterInRow != null && afterInRow == afterInCol) result = false;
            //add a new element
        else {
            SparseMatrixNode node = new SparseMatrixNode(r, c, info);
            //PROCESS THE ROW r
            //It is a new element of the row r
            if (rowRefs[r] == null) rowRefs[r] = node;
                //It is the beginning of the row r
            else if (afterInRow == rowRefs[r]) {
                node.nextInRow = rowRefs[r];
                rowRefs[r] = node;
            } else {
                //It is put between beforeInRow and afterInRow
                node.nextInRow = afterInRow;
                beforeInRow.nextInRow = node;
            }
            //PROCESS THE COLUMN c
            //It is a new element of the column c
            if (afterInCol == null) colRefs[c] = node;
                //It is the beginning of the column c
            else if (afterInCol == colRefs[c]) {
                node.nextInCol = colRefs[c];
                colRefs[c] = node;
            } else {
                //It is put between beforeInCol and afterInCol
                node.nextInCol = afterInCol;
                beforeInCol.nextInCol = node;
            }
        }
        return result;
    }

    //Get the  element at the position [r,c]
    public T get(int r, int c) {
        if (rowRefs[r] != null && colRefs[c] != null) {
            //Traverse the row r
            SparseMatrixNode<T> node = rowRefs[r];
            while (node != null && node.col < c) node = node.nextInRow;
            if (node == null) return null;
            return (node.col == c) ? node.info : null;
        }
        return null;
    }

    //Get the element at the position [r,c]
    public SparseMatrixNode<T> getNode(int r, int c) {
        //traverse the row r
        SparseMatrixNode<T> node = colRefs[r];
        while (node != null && node.col < r) node = node.nextInRow;
        return (node == null || node.col > c) ? null : node;
    }
    public boolean update(int r, int c, T newInfo) {
        SparseMatrixNode<T> node = getNode(r,c);
        if(node== null) return false;
        node.setInfo(newInfo);
        return true;
    }
}

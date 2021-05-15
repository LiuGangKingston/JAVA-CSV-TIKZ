//   This is the supporting source file for
//       https://github.com/LiuGangKingston/JAVA-CSV-TIKZ.git
//            Version 1.0
//   free for non-commercial use.
//   Please send us emails for any problems/suggestions/comments.
//   Please be advised that none of us accept any responsibility
//   for any consequences arising out of the usage of this
//   software, especially for damage.
//   For usage, please refer to the README file.
//   This code was written by
//        Gang Liu (gl.cell@outlook)
//                 (http://orcid.org/0000-0003-1575-9290)
//          and
//        Shiwei Huang (huang937@gmail.com)
//   Copyright (c) 2021
//
//
//
import java.util.Arrays;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;


public class JavaCSVTikZFileGroup{
    final public static String JavaCSVTikZFileExtension = new String(".csv");
    private ArrayList<String> FileNamePrefixes = new ArrayList<String>();
    private int TotalFileGroups = 0;
    private FileWriter[] TheFilesOpened;

    private int TotalRowsInEachFile;
    private int StartingRowNumber ;
    private int EndingRowNumber ;
    private int AbsoluteRowRange ;
    private int RowNumberDirection ;
    private int TotalFiles ;
    private int i, j, k, l;


    public JavaCSVTikZFileGroup(String FileNamePrefix, int StartingRow, int EndingRow, int RowsInEachFile){
        l = FileNamePrefix.length();
        for (char ch: FileNamePrefix.toCharArray()) {
             if (ch == ' ')  l--;
             if (ch == '\t') l--;
        }
        if (l < 1) {
            System.out.println("Sorry for empty FileNamePrefix: "+FileNamePrefix+" \n");
            System.out.println("stopped, when creating a JavaCSVTikZFileGroup object.\n");
            System.exit(-9);
        }

        if (RowsInEachFile < 0) {
            System.out.println("Since RowsInEachFile is "+RowsInEachFile+" negative, \n");
            System.out.println("stopped, when creating a JavaCSVTikZFileGroup object.\n");
            System.exit(-9);
        }

        TotalRowsInEachFile = RowsInEachFile;
        StartingRowNumber = StartingRow;
        EndingRowNumber = EndingRow;
        AbsoluteRowRange = Math.abs(EndingRow - StartingRow);
        RowNumberDirection = 1;
        if (StartingRow > EndingRowNumber) {
            RowNumberDirection = -1; }
        TotalFiles = Math.abs(StartingRow - EndingRow) / RowsInEachFile + 1;

        TheFilesOpened = new FileWriter[TotalFiles];
        for(i=0;i<TotalFiles;i++){
            String istr = Integer.toString(i+1);
            try {
                 TheFilesOpened[i] = new FileWriter(FileNamePrefix+istr+JavaCSVTikZFileExtension);
                 System.out.println("The file: "+FileNamePrefix+istr+JavaCSVTikZFileExtension+
                                    " is opened successfully, as number "+(i+1)+" of total "+TotalFiles+"\n");
            } catch (IOException e) {
                 System.out.println("The file: "+FileNamePrefix+istr+JavaCSVTikZFileExtension+" open is failed\n");
                 System.out.println("then stopped, when creating a JavaCSVTikZFileGroup object.\n");
                 e.printStackTrace();
                 System.exit(-9);
            }
        }
        FileNamePrefixes.add(FileNamePrefix);
    }


    public void FileGroupClose() {
        for(i=0;i<TotalFiles;i++){
            try {
                 (TheFilesOpened[i]).close();
                 //System.out.println("Successfully closed the file.");
            } catch (IOException e) {
                 System.out.println("An error occurred during file closing in class JavaCSVTikZFileGroup.");
                 e.printStackTrace();}
            }
    }


    public void FirstLineToFiles(String FirstLine) {
        for(i=0;i<TotalFiles;i++){
            try {
                 (TheFilesOpened[i]).write(FirstLine);
            } catch (IOException e) {
                 System.out.println("An error occurred during writing file  in class JavaCSVTikZFileGroup.");
                 e.printStackTrace();
            }
        }
    }


    public FileWriter GetFileForRow(int RowNumber){
        k = RowNumber-StartingRowNumber;
        //System.out.println("The from  "+StartingRowNumber+" to "+EndingRowNumber+" \n");
        //System.out.println("RowNumber "+RowNumber+" "+k+" "+RowNumberDirection+" "+AbsoluteRowRange+" \n");
        if ((k*RowNumberDirection) < 0) {
            System.out.println("The RowNumber should be from "+StartingRowNumber+" to "+EndingRowNumber+" \n");
            System.out.println("Stopped for bad RowNumber "+RowNumber+" in GetFileForRow() of class JavaCSVTikZFileGroup.\n");
            System.exit(-9);
        }
        if (Math.abs(k) > AbsoluteRowRange){
            System.out.println("The RowNumber should be from "+StartingRowNumber+" to "+EndingRowNumber+" \n");
            System.out.println("Stopped for bad RowNumber "+RowNumber+" in GetFileForRow() of class JavaCSVTikZFileGroup.\n");
            System.exit(-9);
        }
        j = Math.abs(StartingRowNumber - RowNumber) / TotalRowsInEachFile;
        return (TheFilesOpened[j]);
    }

}


// The end of class JavaCSVTikZ and file.
// The end of class JavaCSVTikZ and file.
// The end of class JavaCSVTikZ and file.



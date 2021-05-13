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

public class JavaCSVTikZ{

    final public static double  PI             = 3.14159265358979323846e0;
    final public static double  Rad2Deg        = 57.29577951308232e0     ;  // 180/pi;
    final public static double  Deg2Rad        = 0.017453292519943296e0  ;  // pi/180;
    final public static double  NapierConstant = 2.71828182845904523536e0;
    final public static double  EulerNumber    = 2.71828182845904523536e0;

    final public static double  AccelerationDueToEarthGravity  = 9.80e0               ;// "m/s$^2$"
    final public static double  AtomicMassConstant             = 1.66053906660e-27    ;// kg
    final public static double  AvogadroNumber                 = 6.02214076e23        ;// "mol$^{-1}$"
    final public static double  BohrMagneton                   = 9.2740100783e-24     ;// "J/T"
    final public static double  BohrRadius                     = 5.29177210903e-11    ;// m
    final public static double  BoltzmannConstant              = 1.380649e-23         ;// "J/K"
    final public static double  ClassicalElectronRadius        = 2.8179403262e-15     ;// m
    final public static double  CoulombConstant                = 8.9875517923e9       ;// "N$\cdot $m$^2$/C$^2$"
    final public static double  ElementaryCharge               = 1.602176634e-19      ;// "C"
    final public static double  FaradayConstant                = 9.648533212e4        ;// C/mol
    final public static double  FineStructureConstant          = 7.2973525693e-3      ;//
    final public static double  FirstRadiationConstant         = 3.741771852e-16      ;// W$\dot m^2$
    final public static double  MassOfElectron                 = 9.1093837015e-31     ;// "kg"
    final public static double  MassOfNeutron                  = 1.67492749804e-27    ;// "kg"
    final public static double  MassOfProton                   = 1.67262192369e-27    ;// "kg"
    final public static double  NuclearMagneton                =  5.0507837461e-27    ;// "J/T"
    final public static double  PlanckConstant                 = 6.62607015e-34       ;// "J$\cdot $s"
    final public static double  RydbergConstant                = 1.0973731568160e7    ;// 1/m
    final public static double  SecondRadiationConstant        = 1.438776877e-2       ;// m$\dot K$
    final public static double  SpeedoFlightInVacuum           = 2.99792458e+8        ;// "m/s"
    final public static double  ThomsonCrossSection            = 6.6524587321e-29     ;//  $m^2$
    final public static double  UniversalGasConstant           = 8.314462618e0        ;// "J/(mol$\cdot $K)"
    final public static double  UniversalGravitationalConstant = 6.67430e-11          ;// "N$\cdot $m$^2$/kg$^2$"
    final public static double  VacuumElectricPermittivity     = 8.8541878128e-12     ;// "F/m"
    final public static double  VacuumMagneticPermeability     = 1.25663706212e-6     ;// "N/$A^2$"

    final public static String[] TypicalColors = new String[]
                        {"red","orange","yellow","green","blue"};

    final public static String[] TikZColors = new String[]
                        {"red","purple","magenta","pink",
                         "violet","white","orange","yellow",
                         "green","lime","brown","olive",
                         "blue","cyan","teal","lightgray",
                         "gray","darkgray","black"           };

    final public static String[] Colors = new String[]
                        {"Apricot","Aquamarine","Bittersweet","Black",
                         "Blue","BlueGreen","BlueViolet","BrickRed",
                         "Brown","BurntOrange","CadetBlue","CarnationPink",
                         "Cerulean","CornflowerBlue","Cyan","Dandelion",
                         "DarkOrchid","Emerald","ForestGreen","Fuchsia",
                         "Goldenrod","Gray","Green","GreenYellow",
                         "JungleGreen","Lavender","LimeGreen","Magenta",
                         "Mahogany","Maroon","Melon","MidnightBlue",
                         "Mulberry","NavyBlue","OliveGreen","Orange",
                         "OrangeRed","Orchid","Peach","Periwinkle",
                         "PineGreen","Plum","ProcessBlue","Purple",
                         "RawSienna","Red","RedOrange","RedViolet",
                         "Rhodamine","RoyalBlue","RoyalPurple","RubineRed",
                         "Salmon","SeaGreen","Sepia","SkyBlue",
                         "SpringGreen","Tan","TealBlue","Thistle",
                         "Turquoise","Violet","VioletRed","White",
                         "WildStrawberry","Yellow","YellowGreen","YellowOrange"};

    public static String PickTypicalColor(int i) {
           return TypicalColors[Math.abs(i)%(TypicalColors.length)];
    }

    public static String PickTikZColor(int i) {
           return TikZColors[Math.abs(i)%(TikZColors.length)];
    }

    public static String PickColor(int i) {
           return Colors[Math.abs(i)%(Colors.length)];
    }

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


    public JavaCSVTikZ(String FileNamePrefix, int StartingRow, int EndingRow, int RowsInEachFile){
        l = FileNamePrefix.length();
        for (char ch: FileNamePrefix.toCharArray()) {
             if (ch == ' ')  l--;
             if (ch == '\t') l--;
        }
        if (l < 1) {
            System.out.println("Sorry for empty FileNamePrefix: "+FileNamePrefix+" \n");
            System.out.println("stopped, when creating a JavaCSVTikZ object.\n");
            System.exit(-9);
        }

        if (RowsInEachFile < 0) {
            System.out.println("Since RowsInEachFile is "+RowsInEachFile+" negative, \n");
            System.out.println("stopped, when creating a JavaCSVTikZ object.\n");
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
                 System.out.println("then stopped, when creating a JavaCSVTikZ object.\n");
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
            System.out.println("Stopped for bad RowNumber "+RowNumber+" in GetFileForRow() of class JavaCSVTikZ.\n");
            System.exit(-9);
        }
        if (Math.abs(k) > AbsoluteRowRange){
            System.out.println("The RowNumber should be from "+StartingRowNumber+" to "+EndingRowNumber+" \n");
            System.out.println("Stopped for bad RowNumber "+RowNumber+" in GetFileForRow() of class JavaCSVTikZ.\n");
            System.exit(-9);
        }
        j = Math.abs(StartingRowNumber - RowNumber) / TotalRowsInEachFile;
        return (TheFilesOpened[j]);
    }

}


// The end of class JavaCSVTikZ and file.
// The end of class JavaCSVTikZ and file.
// The end of class JavaCSVTikZ and file.



/*!   This is example02.ellipsoidal source file for
!       https://github.com/LiuGangKingston/JAVA-CSV-TIKZ.git
!            Version 1.0
!   free for non-commercial use.
!   Please send us emails for any problems/suggestions/comments.
!   Please be advised that none of us accept any responsibility
!   for any consequences arising out of the usage of this
!   software, especially for damage.
!   For usage, please refer to the README file.
!   This code was written by
!        Gang Liu (gl.cell@outlook)
!                 (http://orcid.org/0000-0003-1575-9290)
!          and
!        Shiwei Huang (huang937@gmail.com)
!   Copyright (c) 2021
!
!   This file is formed by adding new lines at the
!   "Specific calculation to generate CSV files" area at
!   the end of the template file.
! */

import java.io.FileWriter;
import java.io.IOException;


class Main {


  public static void main(String[] args) {

// Specific calculation to generate CSV files
// Specific calculation to generate CSV files

    int i, j, totallines=10, datalinesineachfile=50, startingline=1;
    double  a, b, startingangleofsoidal, endinggangleofsoidal, startinxofsoidal, startinyofsoidal, c, d, dk;
    double  startingangle, bigf, t, x, y, z, yprime, tangentangle, normalangle, incidentangle, reflectangle;

    ConstantsForJavaCSVTikZ csts = new ConstantsForJavaCSVTikZ();

    a=7.0e0;
    b=3.0e0;
    startingangleofsoidal = 35.0e0;
    endinggangleofsoidal = 325.0e0;
    startinxofsoidal = a * Math.cos(startingangleofsoidal*csts.Deg2Rad);
    startinyofsoidal = b * Math.sin(startingangleofsoidal*csts.Deg2Rad);
    c=-Math.sqrt(a*a-b*b);

    try {
        FileWriter afile = new FileWriter("setup.scalars.csv");
        afile.write("a,b,c,startingangleofsoidal,endinggangleofsoidal,startinxofsoidal,startinyofsoidal\n");
        afile.write(a + "," + b + "," + c + "," + startingangleofsoidal + "," + endinggangleofsoidal
                      + "," + startinxofsoidal + "," + startinyofsoidal +"\n");
        afile.close();
    } catch (IOException e) {
         System.out.println("An error occurred when closing the file setup.scalars.csv, then stopped.");
         e.printStackTrace();
         System.exit(-9);
    }

    JavaCSVTikZFileGroup bigfiles = new JavaCSVTikZFileGroup("iterated.alldata.",startingline,totallines,datalinesineachfile);
    bigfiles.FirstLineToFiles("c,d,startingangle,dk,bigf,t,x,y,yprime," +
                          "tangentangle,normalangle,incidentangle,reflectangle,mycolor"+"\n");

    d=Math.abs(c);
    for(j=1; j > -2; j=j-2) {
    for(i=startingline; i<=totallines; i++) {
       startingangle= j*(5.0e0 + 5.0e0*i*i);
       dk=d*Math.cos(startingangle*csts.Deg2Rad);
       bigf=a*a - dk*dk;
       t=b*b*(dk+Math.sqrt(bigf+dk*dk))/bigf;
       x=t*Math.cos(startingangle*csts.Deg2Rad)-d;
       y=t*Math.sin(startingangle*csts.Deg2Rad);
       yprime=-b*b*x/(a*a*y);
       z = Math.sqrt((b*b*x)*(b*b*x) + (a*a*y)*(a*a*y));
       if(x < 0.0e0) {
          if(y < 0.0e0) {
             tangentangle = Math.asin(b*b*x /z) * csts.Rad2Deg; }
          else {
             tangentangle = 180.0e0 + Math.acos(a*a*y /z) * csts.Rad2Deg; }
       }
       else {
          if(y  < 0.0e0) {
             tangentangle = Math.acos(-a*a*y /z) * csts.Rad2Deg;}
          else {
             tangentangle = Math.acos(-a*a*y /z) * csts.Rad2Deg;}
       }
       normalangle = tangentangle - 90.0e0;
       incidentangle = normalangle - startingangle;
       reflectangle = tangentangle + 90.0e0 + incidentangle;

       try {
            (bigfiles.GetFileForRow(i)).write(c + "," + d + "," + startingangle + "," + dk + "," + bigf + ","
                                             + t + "," + x + "," + y + "," + yprime + ","
                                             + tangentangle + "," + normalangle + "," + incidentangle + ","
                                             + reflectangle + "," + csts.PickTypicalColor(i) + "\n");
       } catch (IOException e) {
         System.out.println("An error occurred when closing the file setup.scalars.csv, then stopped.");
         e.printStackTrace();
         System.exit(-9);
       }
    }
    }
    bigfiles.FileGroupClose();

  }

}



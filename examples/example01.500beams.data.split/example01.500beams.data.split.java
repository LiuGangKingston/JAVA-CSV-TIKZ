/*!   This is example01.500beams.data.split source file for
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
!   "Specific calculation to generate CSV files" area in the
!   functin "void MyComputing() {...}" at the end of the
!   template file.
! */

import java.io.FileWriter;
import java.io.IOException;


class Main {


  public static void main(String[] args) {

// Specific calculation to generate CSV files
// Specific calculation to generate CSV files

    int i, j, k, totallines=500, datalinesineachfile=50, startingline=1;
    double  refractiveindex, bigradius , a ,b , z, anglez, c, anglea, incidentangle;
    double  refractiveangle, anglede, dx, ee, et, ex, ey, anglece, angleced, outangle;

    ConstantsForJavaCSVTikZ csts = new ConstantsForJavaCSVTikZ();

    refractiveindex=1.5e0;
    bigradius=8.0e0;
    a=3.0e0;
    b=3.0e0;
    z=Math.sqrt(bigradius * bigradius- b * b);
    anglez=Math.asin(b/bigradius)*csts.Rad2Deg;
    c=-Math.sqrt(bigradius * bigradius - (a+b) * (a+b));
    anglea=Math.acos(c/bigradius)*csts.Rad2Deg;

    try {
        FileWriter afile = new FileWriter("setup.scalars.csv");
        afile.write("totallines,refractiveindex,bigradius,a,b,z,anglez,c,anglea\n");
        afile.write(totallines + "," + refractiveindex + "," + bigradius + ","
          + a + "," + b + "," + z + "," + anglez + "," + c + "," + anglea +"\n");
        afile.close();
    } catch (IOException e) {
         System.out.println("An error occurred when closing the file setup.scalars.csv, then stopped.");
         e.printStackTrace();
         System.exit(-9);
    }

    JavaCSVTikZFileGroup bigfile = new JavaCSVTikZFileGroup("iterated.alldata.",startingline,totallines,datalinesineachfile);
    bigfile.FirstLineToFiles("totallines,i,refractiveindex,bigradius,a,b,z,anglez,c,"+
                          "anglea,incidentangle,refractiveangle,anglede,dx,ee,et,ex,ey,"+
                          "anglece,angleced,outangle,mycolor"+"\n");
    for(i=startingline; i<=totallines; i++) {
       incidentangle=3.0e0 + 0.1e0*i;
       refractiveangle=Math.asin(Math.sin(incidentangle*csts.Deg2Rad)/refractiveindex)*csts.Rad2Deg;
       anglede=180-refractiveangle-anglea;
       // x component of D position
       dx=a/Math.tan(anglede*csts.Deg2Rad) + c;

       //! To find E position by solving equations, with t and et as DE length:
       //!  (t sin(anglede) + \b)^2 + (t cos(anglede) + \dx )^2 = 64
       //!  t^2 + 2 (\b sin + \dx cos ) + (\b sin + \dx cos )^2
       //!  = 64 - \b^2 - \dx^2 + (\b sin +  \dx cos  )^2
       //!  t = sqrt ((\b sin + \dx cos )^2 +  64 - \b^2 - \dx^2 )  - (\b sin + \dx cos )

       ee=b*Math.sin(anglede*csts.Deg2Rad) + dx*Math.cos(anglede*csts.Deg2Rad);
       et=Math.sqrt(ee * ee + bigradius * bigradius -b * b - dx * dx) - ee;

       //! x and y components of E position
       ex=et*Math.cos(anglede*csts.Deg2Rad) + dx;
       ey=et*Math.sin(anglede*csts.Deg2Rad) + b;
       anglece=Math.acos(ex/Math.sqrt(ex * ex+ey * ey))*csts.Rad2Deg;
       angleced=anglece-anglede;
       outangle=Math.asin(Math.sin(angleced*csts.Deg2Rad) * refractiveindex)*csts.Rad2Deg;

       try {
            (bigfile.GetFileForRow(i)).write(totallines+ "," + i+ "," + refractiveindex+ ","
                  + bigradius+ ","+ a+ "," + b + "," + z+ "," + anglez+ "," + c + "," + anglea
                  + "," + incidentangle + "," + refractiveangle + "," + anglede + "," + dx + ","
                  + ee+ "," + et + "," + ex + "," + ey + "," + anglece + "," + angleced + ","
                  + outangle + "," + csts.PickTikZColor(i) + "\n");
       } catch (IOException e) {
         System.out.println("An error occurred when writing the files iterated.alldata.*.csv, then stopped.");
         e.printStackTrace();
         System.exit(-9);
       }
    }
    bigfile.FileGroupClose();

  }

}



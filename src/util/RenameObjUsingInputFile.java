package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class RenameObjUsingInputFile {

   private static final String FILEPATH = "C:\\Users\\an645435\\Desktop\\Angular2Fundamentals\\";

   public static void main(String[] args) {
      try (BufferedReader br = new BufferedReader(new FileReader(FILEPATH + "course.json"))) {

         String sCurrentLine;
         String[] detailArr;

         while ((sCurrentLine = br.readLine()) != null) {
            if (!sCurrentLine.equals("[") && !sCurrentLine.equals("]")) {
               sCurrentLine = sCurrentLine.replace("    \"", "");
               sCurrentLine = sCurrentLine.replace("\",", "");
               sCurrentLine = sCurrentLine.replace("\"", "");
               detailArr = sCurrentLine.split("##");
               renameFile(detailArr[1], detailArr[0]);
               // System.out.println(detailArr[0] + " -- " + detailArr[1]);
            }
         }

      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   public static void renameFile(String oldNm, String newNm) {
      boolean fail = true;
      newNm = newNm.replace("#", "-");
      File oldfile = new File(FILEPATH + oldNm + ".webm");
      File newfile = new File(FILEPATH + newNm + ".mp4");

      System.out.println(oldNm + " -- " + newNm);
      if (oldfile.renameTo(newfile)) {
         System.out.println("Rename succesful");
      } else {
         for (int i = 1; i < 101; i++) {
            oldfile = new File(FILEPATH + oldNm + " (" + i + ").mp4");
            if (oldfile.exists()) {
               oldfile.renameTo(newfile);
               fail = false;
               System.out.println("Rename succesful");
               break;
            }
         }
         if (fail)
            System.out.println("Rename failed");
      }
   }

}

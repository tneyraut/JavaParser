import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class FileList
{
    public static void main(String[] args)
    {
        if (args.length == 0)
        {
            System.out.println("Erreur : Veuillez fournir en paramètre le pathname d'un dossier...");
            return;
        }
        String pathNameFile = args[0];
        
        File file = new File(pathNameFile);
        
        BufferedWriter writer = null;
        try {
            File fileList = new File("filelist.txt");
            writer = new BufferedWriter(new FileWriter(fileList));
            if (file.isDirectory())
            {
                addFolder(file, writer);
            }
            else if (file.getName().contains(".java"))
            {
                writer.write(file.getAbsolutePath() + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void addFolder(File folder, BufferedWriter writer)
    {
        for (int i=0;i<folder.listFiles().length;i++)
        {
            File child = folder.listFiles()[i];
            if (child.isDirectory())
            {
                addFolder(child, writer);
            }
            else
            {
                try {
                    if (child.getName().contains(".java"))
                    {
                        writer.write(child.getAbsolutePath() + "\n");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
}
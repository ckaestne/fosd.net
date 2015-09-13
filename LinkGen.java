import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class LinkGen {
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(new File(
				"links")));

		File targetDir = new File("pages/fosd.net/");

		FileWriter linkListWriter = new FileWriter(new File(targetDir,"_includes/linklist.html"));

		String line;
		while ((line = reader.readLine()) != null) {
			StringTokenizer tokenizer = new StringTokenizer(line, ";");
			try {
				String linkTitle; 
				// If line starts with ";" the StringTokenizer would skip the empty string before ";".
				if (line.startsWith(";"))
					linkTitle="";
				else
					linkTitle = tokenizer.nextToken();
				String linkUrl = tokenizer.nextToken();
				ArrayList linkNames = new ArrayList();
				while (tokenizer.hasMoreElements())
					linkNames.add(tokenizer.nextToken());

				String content = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">"
						+ "<html><head><title>"
						+ (linkTitle.isEmpty() ? linkUrl : linkTitle) // if the title is empty, use url instead
						+ "</title><meta http-equiv=\"REFRESH\" content=\"0;url="
						+ linkUrl
						+ "\"></head><body>"
						+ "Loading <a href=\""
						+ linkUrl + "\">" + (linkTitle.isEmpty() ? linkUrl : linkTitle) + "</a>." + "</body></html>";

				for (Iterator iterator = linkNames.iterator(); iterator
						.hasNext();) {
					String linkName = (String) iterator.next();
					File dir = new File(targetDir,linkName);
					if (!dir.exists())
						dir.mkdir();
					File file = new File(dir, "index.html");
					if (file.exists())
						file.delete();

					FileWriter writer = new FileWriter(file);

					writer.write(content);
					writer.close();
				}

				if (linkNames.size()>0 && ! linkTitle.isEmpty())
					linkListWriter.write("<li><a target=\"_blank\" href=\""+linkUrl+"\">"+linkTitle+"</a> (/"+linkNames.get(0)+")</li>");
				
			} catch (NoSuchElementException e) {
				System.out.println("Failed to interprete line " + line);
				e.printStackTrace();
			}
		}
		linkListWriter.close();
	}
}


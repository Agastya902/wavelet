import java.io.IOException;
import java.net.URI;

class StringHandler implements URLHandler 
{

    int num = 0;
    String newString = "";

    public String handleRequest(URI url) 
    {
        if (url.getPath().equals("/")) 
        {
            return "";
        }
        else 
        {
            System.out.println("Path: " + url.getPath());
            if (url.getPath().contains("/add-message")) 
            {
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) 
                {
                    if(num == 0)
                    {
                        newString += parameters[1];
                        num++;
                    }
                    else
                    {
                        newString += "/n" + parameters[1];                            
                    } 
                }
                return newString;
            }
            return "404 Not Found!";
        }
    }
}

class StringServer 
{
    public static void main(String[] args) throws IOException 
    {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new StringHandler());
    }
}

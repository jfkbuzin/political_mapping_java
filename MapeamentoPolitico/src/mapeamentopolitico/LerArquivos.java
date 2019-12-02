package mapeamentopolitico;

import java.awt.Dimension;
import java.io.File;
import java.util.*;
import java.io.*;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import org.jsoup.Jsoup;

public class LerArquivos {

    private Estrutura b;
    
    public void LeituraHTM(File arq){
        
        try{
                FileReader fileReader = new FileReader(arq);
                BufferedReader br = new BufferedReader(fileReader);
                
                String html = "";
                PrintWriter out = new PrintWriter("timeline.txt");
                while ((html = br.readLine()) != null){
                    
                    String NEW_LINE_MARK = "NEWLINESTART1234567890NEWLINEEND";
                    for (String tag: new String[]{"</p>","<br/>","</h1>","</h2>","</h3>","</h4>","</h5>","</h6>","</li>"}) {
                        html = html.replace(tag, NEW_LINE_MARK+tag);
                        }

                    String text = Jsoup.parse(html).text();

                    text = text.replace(NEW_LINE_MARK + " ", " \n\n ");
                    text = text.replace(NEW_LINE_MARK, " \n\n ");
                
                    out.println(text);
                    
                }
                out.close();
                br.close();
            } catch(Exception e){
                e.printStackTrace();
            }
    }
    
    public ArrayList<Estrutura> LeituraTXT() throws IOException{
       
        ArrayList<Estrutura> a = new ArrayList<>();
        BufferedReader br = null;
        BufferedReader db = null;
        
        try {

            String sCurrentLine;
            String sCurrentLine2;
            String comunid;
            //String dat;
            String post;
            String posicao;
            //String p2;
            //String[] parts2;
            String[] parts;
            

            int fr;
            int flag = 0;
            br = new BufferedReader(new FileReader("timeline.txt"));

            sCurrentLine = br.readLine();//.split(regex);
            parts = sCurrentLine.split("-");
            String nome = parts[0]; //nome do usuário
            //System.out.println(nome);
            
            while (!br.readLine().contains(nome)) {}
            
            
            while ((sCurrentLine = br.readLine()) != null) {
                
                if (sCurrentLine.contains("curtiu")) {
                        
                        posicao = "Neutro";
                        parts = sCurrentLine.replaceAll("\\.(.*)","").split("curtiu");
                        comunid = parts[1];
                        //System.out.println(comunid);
                        //parts2 = sCurrentLine.replaceAll("\\.(.*)","").split("UTC-0");
                        //dat = parts2[0];
                        //post = parts2[1].replaceAll("2","").replaceAll("3","");
                        post = sCurrentLine.replaceAll("UTC-02","UTC-02 ").replaceAll("UTC-03","UTC-03 ");
                        
                        db = new BufferedReader(new FileReader("esquerda.txt"));
                        
                        while ((sCurrentLine2 = db.readLine()) != null){

                            if (sCurrentLine2.equals(comunid)){
                                posicao = "Esquerda";
                                break;
                            }  
                        }
                        db.close();
                        
                        db = new BufferedReader(new FileReader("direita.txt"));
                        
                        while ((sCurrentLine2 = db.readLine()) != null){
                            
                            if (sCurrentLine2.equals(comunid)){
                                posicao = "Direita";
                                break;
                            }  
                        }
                        db.close();
                        
                        if(!"Neutro".equals(posicao)){
                            
                        
                            if(!a.isEmpty()){
                              for (Estrutura item : a) {  
                                  if(comunid.equals(item.getComunidade())){
                                    fr = item.getFreq();
                                    fr++;
                                    item.setFreq(fr);
                                    item.setPost(item.getPost().concat("\r\n").concat(post));
                                    flag = 0;
                                    break;
                                 }
                                 else{
                                    flag = 1;
                                 }
                               }
                            }
                            else{
                                Estrutura b = new Estrutura(nome,comunid,post,1,posicao);  
                                a.add(b);
                            }
                        
                        
                            if(flag == 1){
                                Estrutura b = new Estrutura(nome,comunid,post,1,posicao);
                                a.add(b);
                            }
                            flag = 0;
                        }
                    }
                
                if (sCurrentLine.contains("compartilhou a foto de")) {
                        
                        posicao = "Neutro";
                        parts = sCurrentLine.replaceAll("\\.(.*)","").split("compartilhou a foto de");
                        comunid = parts[1];
                        //parts2 = sCurrentLine.replaceAll("\\.(.*)","").split("UTC-0");
                        //dat = parts2[0];
                        //post = parts2[1].replaceAll("2","").replaceAll("3","");
                        post = sCurrentLine.replaceAll("UTC-02","UTC-02 ").replaceAll("UTC-03","UTC-03 ");
                        
                        db = new BufferedReader(new FileReader("esquerda.txt"));
                        
                        while ((sCurrentLine2 = db.readLine()) != null){

                            if (sCurrentLine2.equals(comunid)){
                                posicao = "Esquerda";
                                break;
                            }  
                        }
                        db.close();
                        
                        db = new BufferedReader(new FileReader("direita.txt"));
                        
                        while ((sCurrentLine2 = db.readLine()) != null){
                            
                            if (sCurrentLine2.equals(comunid)){
                                posicao = "Direita";
                                break;
                            }  
                        }
                        db.close();
                        
                        if(!"Neutro".equals(posicao)){
                            
                        
                            if(!a.isEmpty()){
                              for (Estrutura item : a) {  
                                  if(comunid.equals(item.getComunidade())){
                                    fr = item.getFreq();
                                    fr++;
                                    item.setFreq(fr);
                                    item.setPost(item.getPost().concat("\r\n").concat(post));
                                    flag = 0;
                                    break;
                                 }
                                 else{
                                    flag = 1;
                                 }
                               }
                            }
                            else{
                                Estrutura b = new Estrutura(nome,comunid,post,1,posicao);  
                                a.add(b);
                            }
                        
                        
                            if(flag == 1){
                                Estrutura b = new Estrutura(nome,comunid,post,1,posicao);
                                a.add(b);

                            }
                            flag = 0;

                        }                         
                        
                }
                
                if (sCurrentLine.contains("compartilhou o vídeo de")) {
                        
                        posicao = "Neutro";
                        parts = sCurrentLine.replaceAll("\\.(.*)","").split("compartilhou o vídeo de");
                        comunid = parts[1];
                        //parts2 = sCurrentLine.replaceAll("\\.(.*)","").split("UTC-0");
                        //dat = parts2[0];
                        //post = parts2[1].replaceAll("2","").replaceAll("3","");
                        post = sCurrentLine.replaceAll("UTC-02","UTC-02 ").replaceAll("UTC-03","UTC-03 ");
                        
                        db = new BufferedReader(new FileReader("esquerda.txt"));
                        
                        while ((sCurrentLine2 = db.readLine()) != null){

                            if (sCurrentLine2.equals(comunid)){
                                posicao = "Esquerda";
                                break;
                            }  
                        }
                        db.close();
                        
                        db = new BufferedReader(new FileReader("direita.txt"));
                        
                        while ((sCurrentLine2 = db.readLine()) != null){
                            
                            if (sCurrentLine2.equals(comunid)){
                                posicao = "Direita";
                                break;
                            }  
                        }
                        db.close();
                        
                        if(!"Neutro".equals(posicao)){
                            
                        
                            if(!a.isEmpty()){
                              for (Estrutura item : a) {  
                                  if(comunid.equals(item.getComunidade())){
                                    fr = item.getFreq();
                                    fr++;
                                    item.setFreq(fr);
                                    item.setPost(item.getPost().concat("\r\n").concat(post));
                                    flag = 0;
                                    break;
                                 }
                                 else{
                                    flag = 1;
                                 }
                               }
                            }
                            else{
                                Estrutura b = new Estrutura(nome,comunid,post,1,posicao);  
                                a.add(b);
                            }
                        
                            if(flag == 1){
                                Estrutura b = new Estrutura(nome,comunid,post,1,posicao);
                                a.add(b);
                            }
                            flag = 0;

                        }                         
                        
                }                
                
                
                if (sCurrentLine.contains("compartilhou a publicação de")) {
                        
                        posicao = "Neutro";
                        parts = sCurrentLine.replaceAll("\\.(.*)","").split("compartilhou a publicação de");
                        comunid = parts[1];
                        //parts2 = sCurrentLine.replaceAll("\\.(.*)","").split("UTC-0");
                        //dat = parts2[0];
                        //post = parts2[1].replaceAll("2","").replaceAll("3","");
                        post = sCurrentLine.replaceAll("UTC-02","UTC-02 ").replaceAll("UTC-03","UTC-03 ");

                        db = new BufferedReader(new FileReader("esquerda.txt"));
                        
                        while ((sCurrentLine2 = db.readLine()) != null){

                            if (sCurrentLine2.equals(comunid)){
                                posicao = "Esquerda";
                                break;
                            }  
                        }
                        db.close();
                        
                        db = new BufferedReader(new FileReader("direita.txt"));
                        
                        while ((sCurrentLine2 = db.readLine()) != null){
                            
                            if (sCurrentLine2.equals(comunid)){
                                posicao = "Direita";
                                break;
                            }  
                        }
                        db.close();
                        
                        if(!"Neutro".equals(posicao)){
                            
                        
                            if(!a.isEmpty()){
                              for (Estrutura item : a) {  
                                  if(comunid.equals(item.getComunidade())){
                                    fr = item.getFreq();
                                    fr++;
                                    item.setFreq(fr);
                                    item.setPost(item.getPost().concat("\r\n").concat(post));
                                    flag = 0;
                                    break;
                                 }
                                 else{
                                    flag = 1;
                                 }
                               }
                            }
                            else{
                                Estrutura b = new Estrutura(nome,comunid,post,1,posicao);  
                                a.add(b);
                            }
                        
                            if(flag == 1){
                                Estrutura b = new Estrutura(nome,comunid,post,1,posicao);
                                a.add(b);
                            }
                            flag = 0;

                        }                

                } 
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return a;
    }
    public void listaComTXT(){
        
        try {
        StringBuilder sb=new StringBuilder();
        BufferedReader db = null;
        String sCurrentLine2;
        
        Object[] possibleValues2 = { "Direita", "Esquerda"};
        Object selectedValue2 = JOptionPane.showInputDialog(null,"Escolha um", "Input"
                    ,JOptionPane.INFORMATION_MESSAGE, null,possibleValues2, possibleValues2[0]); 
                                    
        if(selectedValue2 == "Esquerda"){
            JOptionPane.showMessageDialog(null, "Listando paginas de Esquerda que são usadas na classificação da posição politica");
            
            db = new BufferedReader(new FileReader("esquerda.txt"));
                        
            while ((sCurrentLine2 = db.readLine()) != null){

                sb.append(sCurrentLine2);
                sb.append("\n");
            }  
   
            db.close();    
        
        }    
        else{
            JOptionPane.showMessageDialog(null, "Listando paginas de Direita que são usadas na classificação da posição politica");
            db = new BufferedReader(new FileReader("direita.txt"));
                        
            while ((sCurrentLine2 = db.readLine()) != null){

                sb.append(sCurrentLine2);
                sb.append("\n");
            }  
   
            db.close(); 
        }
           
        
        JTextArea textArea = new JTextArea(sb.toString());
        JScrollPane scrollPane = new JScrollPane(textArea);  
        textArea.setLineWrap(true);  
        textArea.setWrapStyleWord(true); 
        scrollPane.setPreferredSize( new Dimension( 500, 500 ) );
        JOptionPane.showMessageDialog(null, scrollPane, "Lista de Paginas",  
                                       JOptionPane.YES_NO_OPTION);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void addComTXT(){
        
        try {
        
        Object[] possibleValues2 = { "Direita", "Esquerda"};
        Object selectedValue2 = JOptionPane.showInputDialog(null,"Escolha um", "Input"
                    ,JOptionPane.INFORMATION_MESSAGE, null,possibleValues2, possibleValues2[0]); 
                                    
        if(selectedValue2 == "Esquerda"){
            String na = (" " + JOptionPane.showInputDialog("Pagina a adicionar às paginas de Esquerda que são usadas na classificação da posição politica: "));
            
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("esquerda.txt", true)));
            
            //out.println("\n");
            out.println("");
            out.print(na);
            out.close();
        }    
        else{
            String na = (" " + JOptionPane.showInputDialog("Pagina a adicionar às paginas de Direita que são usadas na classificação da posição politica: "));
            
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("direita.txt", true)));
            
            //out.println("\n");
            out.println("");
            out.print(na);
            out.close();
        }
           
       JOptionPane.showMessageDialog(null, "Nova pagina salva no arquivo");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

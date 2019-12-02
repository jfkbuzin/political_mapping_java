package mapeamentopolitico;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class SalvaD {
    
    public void ArqB(ArrayList<Estrutura> com){
        
        String na = null;
        
        for(Estrutura a : com){
            na = a.getNome().replaceAll(" ",".");
            break;
        }
        //criação dos arquivos binários
        String dados = na + "dat";
        String indicen = na + "indice.dat";
        
        
        StringBuilder sb=new StringBuilder();
        sb.append("Arquivo gravado com o nome: ").append(dados);
        sb.append("\n");
        sb.append("Índice gravado com o nome: ").append(indicen);
        
        

        JOptionPane.showMessageDialog(null,sb.toString());
        
        try {
            ObjectOutputStream out;
            ObjectOutputStream indicesOut;
            out = new ObjectOutputStream(new FileOutputStream(dados));
            indicesOut = new ObjectOutputStream(new FileOutputStream(indicen));
            
            for (Estrutura item : com) {  //aqui são preenchidos os arquivos com os dados, sequencialmente
            
                indicesOut.writeObject(item.getComunidade()); //salva só o nome da comunidade para o arquivo do indice
                out.writeObject(item); //salva todos os dados da respectiva comunidade para o arquivo binario
            }
            out.close();
            indicesOut.close();
	
        } catch (Exception e) {
		e.printStackTrace();
	}
        

    }
    
    public void LerTudo(){
        
        String na = (JOptionPane.showInputDialog("Digite o nome da pessoa conforme o perfil no Facebook:")).replaceAll(" ",".");
        
        String dados = na + ".dat";
        String indicen = na + ".indice.dat";
        
        HashMap<String, Estrutura> paginas;
        
        //salva o arquivo de indices para uma lista de strings
	ArrayList<String> indices = new ArrayList<>();
	ObjectInputStream indicesIn;
	try {
		indicesIn = new ObjectInputStream(new FileInputStream(indicen));
		String indice;
                do {
			indice = (String) indicesIn.readObject();
			if (indice != null) {
				indices.add(indice);
			}
                    }	while (indice != null); 
		indicesIn.close();
            } catch (EOFException eof) {

            } catch (IOException | ClassNotFoundException e) {
		JOptionPane.showMessageDialog(null, "Erro, o índice não foi criado");
            }

            int n = 0;
        
            if (!indices.isEmpty()) { 
                    do{

                        Object[] possibleValues = { "Posição Política", "Todas as Paginas", 
                            "Posts da Pagina mais curtida(se foi escolhida Ordenação Decrescente)", 
                            "Pesquisa Posts relativos a uma Pagina", "Imprimir todo o arquivo", 
                            "Imprimir Posts por Dia da Semana", 
                            "Imprimir Posts por qualquer outro critério", "Voltar" };
                        Object selectedValue = JOptionPane.showInputDialog(null,"Escolha um", 
                                "Input",JOptionPane.INFORMATION_MESSAGE, null,possibleValues, 
                                possibleValues[0]);  
                  
                        if(selectedValue == "Posição Política")
                            n = 1;
                        else if(selectedValue == "Todas as Paginas")
                            n = 2;
                        else if(selectedValue == "Posts da Pagina mais curtida(se foi escolhida Ordenação Decrescente)")
                            n = 3;
                        else if(selectedValue == "Pesquisa Posts relativos a uma Pagina")
                            n = 4;
                        else if(selectedValue == "Imprimir todo o arquivo")
                            n = 5;
                        else if(selectedValue == "Imprimir Posts por Dia da Semana")
                            n = 6;
                        else if(selectedValue == "Imprimir Posts por qualquer outro critério")
                            n = 7;
                        else
                             n=0;

                      switch (n){

                          case 1: //todo arq na memoria
                                  paginas = SalvaTudoM(dados);
                                  PosPol(paginas,indices);
                                  paginas.clear();
                                  break;
                         case 2: //só precisa do indice
                                  ImprimeComunidades(indices);
                                  break;
                          case 3: //só uma parte do arquivo na memoria
                                  ImprimePostsMF(dados,indices);
                                  break;
                          case 4://só uma parte do arquivo na memoria
                                  ImprimePostsC(dados,indices);
                                  break;
                          case 5://todo arq na memoria
                                  paginas = SalvaTudoM(dados);
                                  ImprimeTudo(paginas,indices);
                                  paginas.clear();
                                  break;  
                          case 6://todo arq na memoria?
                                  paginas = SalvaTudoM(dados);
                                  ImprimeDiaS(paginas,indices);
                                  paginas.clear();
                                  break;    
                          case 7://todo arq na memoria?
                                  paginas = SalvaTudoM(dados);
                                  ImprimeQC(paginas,indices);
                                  paginas.clear();
                                  break;   
                          default:      
                                  JOptionPane.showMessageDialog(null, "Voltando ao menu anterior");
                      }
              }while (n != 0);
        }
        else
           JOptionPane.showMessageDialog(null, "Usuario não existe ou não possui dados suficientes "
                   + "para o cálculo da posição política, voltando ao menu anterior");   
        
        //paginas.clear();
        indices.clear();        

    }
    
    public HashMap<String, Estrutura> SalvaTudoM(String dados){
        HashMap<String, Estrutura> paginas = new HashMap<>();
        ObjectInputStream in;
        try {
                in = new ObjectInputStream(new FileInputStream(dados));
			Estrutura estrutura;
			do {
				estrutura = (Estrutura) in.readObject();
				if (estrutura != null) {
					paginas.put(estrutura.getComunidade(), estrutura);
				}
			}	while (estrutura != null); 
			in.close();
            } catch (EOFException eof) {

            } catch (IOException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Arquivo nao existe, o prefil não está na base de dados ou "
                                + "ocorreu erro na digitação");
            }
        return paginas;
    }
    
    public void ImprimeTudo(HashMap<String, Estrutura> paginas, ArrayList<String> indices){
        
        StringBuilder sb=new StringBuilder();
        JOptionPane.showMessageDialog(null, "Imprimindo tudo que está no arquivo binário");
        
        
		
        for (String indice: indices) {
                        
            sb.append("Nome: ").append(paginas.get(indice).getNome());
            sb.append("\n");
            sb.append("Comunidade: ").append(paginas.get(indice).getComunidade());
            sb.append("\n");
            sb.append("Posts: ");
            sb.append("\n");
            sb.append(paginas.get(indice).getPost());
            sb.append("\n");
            sb.append("Frequencia: ").append(paginas.get(indice).getFreq());
            sb.append("\n");
            sb.append("Posição Politica: ").append(paginas.get(indice).getPosicao());
            sb.append("\n");
            sb.append("\n");
	}
       
        JTextArea textArea = new JTextArea(sb.toString());
        JScrollPane scrollPane = new JScrollPane(textArea);  
        textArea.setLineWrap(true);  
        textArea.setWrapStyleWord(true); 
        scrollPane.setPreferredSize( new Dimension( 500, 500 ) );
        JOptionPane.showMessageDialog(null, scrollPane, "Todo o arquivo",  
                                       JOptionPane.YES_NO_OPTION);
        
        
    }
    
    public void ImprimeComunidades(ArrayList<String> indices){
        
        StringBuilder sb=new StringBuilder();
        JOptionPane.showMessageDialog(null, "Imprimindo todas as comunidades");
        
        for (String indice: indices) {
            sb.append(indice);
            sb.append("\n");
	}
        sb.append("\n");
        
        JTextArea textArea = new JTextArea(sb.toString());
        JScrollPane scrollPane = new JScrollPane(textArea);  
        textArea.setLineWrap(true);  
        textArea.setWrapStyleWord(true); 
        scrollPane.setPreferredSize( new Dimension( 500, 500 ) );
        JOptionPane.showMessageDialog(null, scrollPane, "Lista de comunidades",  
                                       JOptionPane.YES_NO_OPTION);
    }

    public void ImprimePostsC(String dados, ArrayList<String> indices){
        
        StringBuilder sb=new StringBuilder();
        
        String na = (" " + JOptionPane.showInputDialog("Digite o nome da comunidade que quer ver a lista de posts:"));
        
        JOptionPane.showMessageDialog(null, "Imprimindo todos os posts da comunidade " + na);
        
        //HashMap<String, Estrutura> paginas = new HashMap<>();
        ObjectInputStream in;
        try {
                in = new ObjectInputStream(new FileInputStream(dados));
			Estrutura estrutura;
			do {
				estrutura = (Estrutura) in.readObject();
                                
				if (estrutura != null) {
                                    if(na.equals(estrutura.getComunidade())){
                                        sb.append("Posts: ");
                                        sb.append("\n");
                                        sb.append(estrutura.getPost());
                                        sb.append("\n");
                                        sb.append("\n");
                                        //paginas.put(estrutura.getComunidade(), estrutura);
                                    }
				}
			}	while (estrutura != null); 
			in.close();
            } catch (EOFException eof) {

            } catch (IOException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Arquivo nao existe, o prefil não está na base de dados ou "
                                + "ocorreu erro na digitação");
            }
        
        JTextArea textArea = new JTextArea(sb.toString());
        JScrollPane scrollPane = new JScrollPane(textArea);  
        textArea.setLineWrap(true);  
        textArea.setWrapStyleWord(true); 
        scrollPane.setPreferredSize( new Dimension( 500, 500 ) );
        JOptionPane.showMessageDialog(null, scrollPane, "Posts da Comunidade",  
                                       JOptionPane.YES_NO_OPTION);
        
        /*for (String indice: indices) {
            if(paginas.get(indice).getComunidade() != null){
                System.out.println("Posts: ");
                System.out.println(paginas.get(indice).getPost());
                System.out.println("");
                break;
            }            
	}*/
        //paginas.clear();
    }  
    
    public void ImprimePostsMF(String dados, ArrayList<String> indices){
        StringBuilder sb=new StringBuilder();
        
        JOptionPane.showMessageDialog(null, "Imprimindo todos os posts da comunidade que mais influenciou no resultado");
	
        ObjectInputStream in;
        try {
                in = new ObjectInputStream(new FileInputStream(dados));
		Estrutura estrutura;
			
		estrutura = (Estrutura) in.readObject();
                                
		if (estrutura != null) {
                    sb.append("Posts: ");
                    sb.append("\n");
                    sb.append(estrutura.getPost());
                    sb.append("\n");
                    sb.append("\n");
                }
				
		
                in.close();
            } catch (EOFException eof) {

            } catch (IOException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Arquivo nao existe, o prefil não está na base de dados ou "
                                + "ocorreu erro na digitação");
            }
        
        
        JTextArea textArea = new JTextArea(sb.toString());
        JScrollPane scrollPane = new JScrollPane(textArea);  
        textArea.setLineWrap(true);  
        textArea.setWrapStyleWord(true); 
        scrollPane.setPreferredSize( new Dimension( 500, 500 ) );
        JOptionPane.showMessageDialog(null, scrollPane, "Posts da Comunidade",  
                                       JOptionPane.YES_NO_OPTION);
        /*
        for (String indice: indices) {
            System.out.println("Posts: ");
            System.out.println(paginas.get(indice).getPost());
            System.out.println("");
            break;
            }    */        
    }
    
    public void PosPol(HashMap<String, Estrutura> paginas, ArrayList<String> indices){
        StringBuilder sb=new StringBuilder();
        int pontuacao = 0;
        String nome = null;
        
        JOptionPane.showMessageDialog(null, "Mostrando a posição Politica");
	
        for (String indice: indices) {
            nome=paginas.get(indice).getNome();
        }
        
        for (String indice: indices) {
            if("Direita".equals(paginas.get(indice).getPosicao()))
                    pontuacao = pontuacao - paginas.get(indice).getFreq();
                    
            if("Esquerda".equals(paginas.get(indice).getPosicao()))
                    pontuacao = pontuacao + paginas.get(indice).getFreq();
        }    
        
        
        sb.append(nome);
        sb.append("\n");
        sb.append("Pontuação = ").append(pontuacao);
        sb.append("\n");
        if(pontuacao > 0){
            sb.append("Você é de Esquerda");
            sb.append("\n");
        }
        else if(pontuacao < 0){
            sb.append("Você é de Direita");
            sb.append("\n");
        }
        else{
            sb.append("Você é de Centro");
            sb.append("\n");
        }
        
        sb.append("\n");
        
        JOptionPane.showMessageDialog(null,sb.toString());
    }
    
    public void ImprimeDiaS(HashMap<String, Estrutura> paginas, ArrayList<String> indices){
        StringBuilder sb=new StringBuilder();
        
        Object[] possibleValues = { "Domingo", "Segunda", "Terça", 
                    "Quarta", "Quinta", "Sexta", "Sábado"};
        Object selectedValue = JOptionPane.showInputDialog(null,"Escolha o dia da semana", 
              "Input",JOptionPane.INFORMATION_MESSAGE, null,possibleValues, possibleValues[0]);  
        
        String teste = (String) selectedValue;
        
        JOptionPane.showMessageDialog(null, "Imprimindo toda a atividade da timeline feita no dia da semana: " + teste);
	
        String sCurrentLine;
        BufferedReader br = null;
        try{
        for (String indice: indices) {
            
            br = new BufferedReader(new StringReader(paginas.get(indice).getPost()));
            
            while ((sCurrentLine = br.readLine()) != null){
                
                if (sCurrentLine.contains(teste)){
                    sb.append(sCurrentLine);
                    sb.append("\n");
                    sb.append("\n");
                }

               }          
            }
        } catch(IOException e){
          e.printStackTrace();
        }
        sb.append("\n");
        
        JTextArea textArea = new JTextArea(sb.toString());
        JScrollPane scrollPane = new JScrollPane(textArea);  
        textArea.setLineWrap(true);  
        textArea.setWrapStyleWord(true); 
        scrollPane.setPreferredSize( new Dimension( 500, 500 ) );
        JOptionPane.showMessageDialog(null, scrollPane, "Posts no dia da semana",  
                                       JOptionPane.YES_NO_OPTION);
    }
    
    public void ImprimeQC(HashMap<String, Estrutura> paginas, ArrayList<String> indices){
        StringBuilder sb=new StringBuilder();
        
        String teste = (JOptionPane.showInputDialog("Digite o critério que quer ver a lista de posts:"));
        
        JOptionPane.showMessageDialog(null, "Imprimindo toda a atividade da timeline em que aparece " + teste + ":");
	
        String sCurrentLine;
        BufferedReader br = null;
        try{
        for (String indice: indices) {
            
            br = new BufferedReader(new StringReader(paginas.get(indice).getPost()));
            
            while ((sCurrentLine = br.readLine()) != null){
                
                if (sCurrentLine.contains(teste)){
                    sb.append(sCurrentLine);
                    sb.append("\n");
                    sb.append("\n");                    
                }
               }          
            }
        } catch(IOException e){
          e.printStackTrace();
        }
        sb.append("\n");
        
        JTextArea textArea = new JTextArea(sb.toString());
        JScrollPane scrollPane = new JScrollPane(textArea);  
        textArea.setLineWrap(true);  
        textArea.setWrapStyleWord(true); 
        scrollPane.setPreferredSize( new Dimension( 500, 500 ) );
        JOptionPane.showMessageDialog(null, scrollPane, "Posts seguindo o critério escolhido",  
                                       JOptionPane.YES_NO_OPTION);
    }
    
    public void listaNomes(File curDir){
        StringBuilder sb=new StringBuilder();
        File[] filesList = curDir.listFiles();
        String fname = null;
        for(File f : filesList){
            if(f.isFile()){
                fname = f.getName();
                if (fname.contains(".indice.dat")){
                    fname = fname.replaceAll(".indice.dat","");
                    fname = fname.replace("."," ");
                    sb.append(fname);
                    sb.append("\n");
                    //System.out.println(fname.replaceAll("."," "));
                }
            }
        }
        
        JTextArea textArea = new JTextArea(sb.toString());
        JScrollPane scrollPane = new JScrollPane(textArea);  
        textArea.setLineWrap(true);  
        textArea.setWrapStyleWord(true); 
        scrollPane.setPreferredSize( new Dimension( 500, 500 ) );
        JOptionPane.showMessageDialog(null, scrollPane, "Usuarios cadastrados",  
                                       JOptionPane.YES_NO_OPTION);
    }     

}
